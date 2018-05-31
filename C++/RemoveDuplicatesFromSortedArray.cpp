#include<iostream>
#include<map>
#include<vector>
using namespace std;

/*
 Remove Duplicates from Sorted Array - https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/727/
Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.
*/
// Implement with unsorted / sorted Array with O(n)
int removeDuplicates(vector<int>& nums) {
    map<int,int> hashMap;
    vector<int>::iterator it_nums = nums.begin();
    while(it_nums != nums.end()) {

        map<int,int>::iterator it_map = hashMap.find(*it_nums);
        if ( it_map == hashMap.end()) {
            hashMap.insert(pair<int,int>(*it_nums, 1));
            ++it_nums;
        } else {
        	nums.erase(it_nums);
        }
    }
    return nums.size();
}

//test
int main() {

	vector<int> vt;
	vt.push_back(0); 
	vt.push_back(0); 
	vt.push_back(0); 
	vt.push_back(1); 
	vt.push_back(1); 
	vt.push_back(1); 
	vt.push_back(2); 
	vt.push_back(2); 
	vt.push_back(2); 
	vt.push_back(3); 
	vt.push_back(3); 
	vt.push_back(4); 

	cout << removeDuplicates(vt) << endl;
	for (int i = 0; i < vt.size(); i++)
		cout << vt.at(i) << "  ";
	cout <<endl;
	return 0;
}