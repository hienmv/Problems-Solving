/* https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/727/
#array #hash-table #map
*/

#include<iostream>
#include<map>
#include<vector>
using namespace std;

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