// Linked List Cycle.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "LinkedList.h"
#include <iostream>

using namespace std;



int main()
{

	LinkedList list = LinkedList();

	string input;

	while (true) {

		cout
			<<"Instructions: " << endl
			<< "pop" << endl
			<< "push" << endl
			<< "delete" << endl
			<< "insert" << endl
			<< "generate" << endl
			<< "sort" << endl
			<< "print" << endl
			<< "erase" << endl
			<< "quit" << endl
			<< ": ";
		cin >> input;
		cout << endl;

		if (input == "pop") {
			list.pop();
		}
		else if (input == "push") {
			cout << "Input: ";
			int target;
			cin >> target;
			list.push(target);
		}
		else if (input == "delete") {
			int target;
			cout << "Enter element: ";
			cin >> target;
			list.deleteElement(target);
		}
		else if (input == "quit") {
			return 0;
		}
		else if (input == "print") {
			list.printData();

		}
		else if (input == "erase") {
			list.deleteAllData();
			input = "";
		}
		else if (input == "generate") {
			int numItems;
			cout << "Enter number of items: ";
			cin >> numItems;
			list.generateList(numItems);
		}
	}
}

