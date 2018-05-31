#include <iostream>
#include <vector>
#include<map>
using namespace std;
/*  Find the Duplicate Number - https://leetcode.com/problems/find-the-duplicate-number/description/
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
Example 1:
Input: [1,3,4,2,2]
Output: 2
Example 2:
Input: [3,1,3,4,2]
Output: 3

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
*/

int findDuplicate(vector<int> &nums)
{
    map<int,int> hashMap;
    for(int i = 0; i < nums.size(); i++) {
        map<int,int>::iterator it = hashMap.find(nums.at(i));
        if(it != hashMap.end())
            return nums.at(i);
        hashMap.insert(pair<int, int>(nums.at(i), i));
    }
    return -1;
}
//test
int main()
{
    vector<int> vt;
    vt.push_back(1);
    vt.push_back(5);
    vt.push_back(3);
    vt.push_back(2);
    vt.push_back(3);

    cout << findDuplicate(vt) << endl;
    return 0;
}