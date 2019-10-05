/*
Given a List of numbers, return whether any two sums to k.
For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.

#map #hash-table
*/

#include<iostream>
#include<map>
#include<vector>
using namespace std;

// solution 1: use map. O(n)
bool foundTwoNumber( vector<int>& numbers, int target) {
	map<int, int> hashMap;
	for(int i = 0; i < numbers.size(); i++) {
		map<int, int>::iterator iter = hashMap.find(target - numbers.at(i));
		if(iter != hashMap.end()) {
			return true;
		}
		hashMap.insert(pair<int, int>(numbers.at(i), i));
	}
	return false;
}

//test
int main() {
	vector<int> vect;
	vect.push_back(10);
	vect.push_back(-5);
	vect.push_back(3);
	vect.push_back(7);

	int target = 2;
	if(foundTwoNumber(vect, target)) {	
		std::cout << "Found" << endl;
	} else {
		std::cout << "Not Found" << endl;
	}

	return 0;
}