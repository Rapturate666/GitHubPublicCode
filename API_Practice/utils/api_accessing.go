package utils

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
)

// Building out the struct for storing the data from https://jsonplaceholder.typicode.com/users
type Users []struct {
	ID       int    `json:"id"`
	Name     string `json:"name"`
	Username string `json:"username"`
	Email    string `json:"email"`
	Address  struct {
		Street  string `json:"street"`
		Suite   string `json:"suite"`
		City    string `json:"city"`
		Zipcode string `json:"zipcode"`
		Geo     struct {
			Lat string `json:"lat"`
			Lng string `json:"lng"`
		} `json:"geo"`
	} `json:"address"`
	Phone   string `json:"phone"`
	Website string `json:"website"`
	Company struct {
		Name        string `json:"name"`
		CatchPhrase string `json:"catchPhrase"`
		BS          string `json:"bs"`
	} `json:"company"`
}

func PrintUsers(users Users) string {
	var output string
	for _, user := range users {
		output += fmt.Sprintf("ID: %d\nName: %s\nUsername: %s\nEmail: %s\n", user.ID, user.Name, user.Username, user.Email)
		output += fmt.Sprintf("\tAddress: \n\t%s, %s\n\t%s, %s\n", user.Address.Street, user.Address.Suite, user.Address.City, user.Address.Zipcode)
		output += fmt.Sprintf("Geo Cooridnates:\n\tLatitude: %s\n\tLongitude: %s\n", user.Address.Geo.Lat, user.Address.Geo.Lng)
		output += fmt.Sprintf("Phone: %s\nWebsite: %s\n", user.Phone, user.Website)
		output += fmt.Sprintf("Company:\n\tCompany Name: %s\n\tCatchPhrase: %s\n\tBS: %s\n\n", user.Company.Name, user.Company.CatchPhrase, user.Company.BS)
	}
	return output
}

func GetBasicUserInfo(users Users) string {
	var output string
	for _, user := range users {
		output += fmt.Sprintf("User ID: %d\nName: %s\nUsername: %s\nEmail: %s\n\n", user.ID, user.Name, user.Username, user.Email)
	}
	return output
}

func PullData() Users {
	rawData, err := http.Get("https://jsonplaceholder.typicode.com/users")
	if err != nil {
		log.Fatal(err)
	}
	defer rawData.Body.Close()

	var users Users

	err = json.NewDecoder(rawData.Body).Decode(&users)
	if err != nil {
		log.Fatal(err)
	}

	return users
}
