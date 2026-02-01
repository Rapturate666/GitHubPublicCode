package main

import (
	"fmt"
	"goPractice/utils"
)

func main() {
	//Practicing Pulling and Printing data from an API
	data := utils.PullData()
	fmt.Print(utils.PrintUsers(data))
	fmt.Print(utils.GetBasicUserInfo(data))
}
