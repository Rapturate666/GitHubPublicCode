/*
Author: Lew Price

Note: THIS WILL NOT WORK WITHOUT CREATING A BOT ON DISCORD AND SUPPLYING THIS CODE WITH THE BOT TOKEN!
*/

package main

import (
	"fmt"
	"log"
	"os"
	"os/signal"
	"strings"

	"github.com/bwmarrin/discordgo"
	"github.com/gocolly/colly"
)

type Key struct {
	hash   string
	reward string
	date   string
}

func getTable() []Key {

	var keyList []Key

	c := colly.NewCollector()

	c.OnRequest(func(r *colly.Request) {
		fmt.Println("Visiting: ", r.URL)
	})

	c.OnError(func(_ *colly.Response, err error) {
		fmt.Println("Something went wrong: ", err)
	})

	c.OnResponse(func(r *colly.Response) {
		fmt.Println("Page visited: ", r.Request.URL)
	})

	c.OnScraped(func(r *colly.Response) {

	})

	c.OnHTML("table", func(e *colly.HTMLElement) {
		fmt.Print("Processing the table...\n\n")
		var key Key
		count := 0
		e.ForEach("span", func(i int, h *colly.HTMLElement) {
			if i >= 3 {
				if count == 0 {
					key.hash = strings.TrimSpace(h.Text)
				} else if count == 1 {
					key.reward = strings.TrimSpace(h.Text)
				} else if count == 2 {
					key.date = strings.TrimSpace(h.Text)
					keyList = append(keyList, key)
					count = -1
				}
				count++
			}
		})
	})

	c.Visit("https://mobalytics.gg/borderlands-4/shift-codes-borderlands-4")

	return keyList
}

func run() {
	botToken := /* INSERT YOUR DISCORD BOT TOKEN TO SEE THIS RUN */
	bot, err := discordgo.New("Bot " + botToken)

	if err != nil {
		log.Fatal("bad connection to discord.")
		return
	}

	bot.AddHandler(userMessage)

	bot.Open()
	defer bot.Close()

	c := make(chan os.Signal, 1)
	signal.Notify(c, os.Interrupt)
	<-c
}

func userMessage(bot *discordgo.Session, message *discordgo.MessageCreate) {
	//fmt.Println(message.ChannelID)
	//fmt.Println(message.Content)
	if message.Author.ID == bot.State.User.ID {
		return
	}
	prefix := "!"
	if message.Content == prefix+"codes" {
		keyList := getTable()

		for _, key := range keyList {
			tableLine := fmt.Sprintf("Reward: %s\nCode: %s\nExpires: %s\n\n", key.reward, key.hash, key.date)

			_, _ = bot.ChannelMessageSend(message.ChannelID, tableLine)
		}
		fmt.Println("Done.")
	}
}

func main() {
	fmt.Println("Press ctrl + c to kill the server.")
	run()
}
