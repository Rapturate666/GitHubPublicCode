#pragma once
#include <iostream>
#include <random>

using namespace std;

class LinkedList {

	struct Node {
	public:
		int data;
		Node* next;
		Node* prev;
		//struct constructor
		Node(int val) : data(val), next(nullptr), prev(nullptr) {};
		Node(int val, Node* newPrev, Node* newNext) : data(val), prev(newPrev), next(newNext) {};
	};

private:
	Node* head;
	Node* tail;

public:
	//class constructor
	LinkedList() : head(nullptr), tail(nullptr) {};

	
	//DEFINITIONS


	//Setters

	int randInt() {
		random_device rd;
		mt19937 gen(rd());
		uniform_int_distribution<int> dist(1, 30);

		return dist(gen);
	}

	void deleteAllData() {
		Node* fp;
		Node* sp;
		if (head != nullptr) {
			fp = head;
			sp = fp->next;
			while (sp != nullptr) {
				delete fp;
				fp = sp;
				sp = sp->next;
			}
			delete fp;
			head = nullptr;
			tail = nullptr;
		}
	}

	void deleteElement(int num) {
		Node* fp = head;
		Node* sp = head->next;

		//empty list check
		if (!head) {
			cout << "List empty." << endl << endl;
			return;
		}

		//element is first in list
		if (head->data == num) { 
			head = sp;
			delete fp;
			return;
		}

		//traversing to the target or end of list
		while (sp->data != num) { 
			if (sp == nullptr) {
				cout << "Target not found." << endl << endl;
				return;
			}
			else {
				fp = fp->next;
				sp = sp->next;
			}
		}

		if (sp->data == num && sp == tail) {
			tail = fp;
			fp->next = nullptr;
			delete sp;
			return;
		}
		else {
			fp->next = sp->next;
			delete sp;
			return;
		}

	}

	void insertElement(int num) {

	}

	void push(int num) {

		if (!head) {
			Node* newNode = new Node(num);
			tail = newNode;
			head = newNode;
			return;
		}

		Node* newNode = new Node(num, nullptr, head);
		head->prev = newNode;
		head = newNode;
		return;
	}

	void sortList() {

	}

	void generateList(int numNodes) {

		deleteAllData();

		head = nullptr;
		tail = nullptr;

		for (int i = 0; i < numNodes; i++) {
			push(randInt());
		}

	}

	//Getters

	void printData() {

		Node* nextNode = head;

		if (nextNode == nullptr) {
			cout << "List Empty" << endl << endl;
		}
		else {
			while (nextNode != nullptr) {
				cout << nextNode->data << " ";
				nextNode = nextNode->next;
				
			}
			cout << endl << endl;
		}
	}

	void pop() {

		if (!head) {
			cout << "List empty." << endl << endl;
			return;
		}
		else if (!head->next) {
			Node* temp = head;
			int data = head->data;
			head = nullptr;
			tail = nullptr;
			delete temp;

			cout << data << endl << endl;

			return;
		}

		int data = tail->data;
		tail->prev->next = nullptr;
		Node* temp = tail;
		tail = tail->prev;
		delete temp;

		cout << data << endl << endl;

		return;
	}
};