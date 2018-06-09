/*
Given two shorted array, output is array containing common elements of two input arrays.
*/

#include<iostream>
#include<vector>
#include<map>
using namespace std;

vector<int> findCommonElementsInTwoArr(vector<int> nums1, vector<int> nums2) {	
	vector<int> result;
	map<int,int> hashMap;
	map<int,int>::iterator it;

	if (nums1.size() == 0 || nums2.size() == 0)
		return result;

	int limit = nums1.size() > nums2.size() ? nums1.size() : nums2.size();

	for (int i = 0; i < limit; ++i) {
		if(i < nums1.size()) {
			it = hashMap.find(nums1.at(i));
			if (it != hashMap.end())
				result.push_back(nums1.at(i));
			else
				hashMap.insert(pair<int,int>(nums1.at(i), i));
		}

		if(i < nums2.size()) {
			it = hashMap.find(nums2.at(i));
			if (it != hashMap.end())
				result.push_back(nums2.at(i));
			else
				hashMap.insert(pair<int,int>(nums2.at(i), i));
		}
	} 

	return result;
}

//test 
int main() {
	vector<int> v1;
	vector<int> v2;

	v1.push_back(1);
	v1.push_back(19);
	v1.push_back(6);
	v1.push_back(4);
	v1.push_back(3);


	v2.push_back(5);
	v2.push_back(19);
	v2.push_back(3);
	v2.push_back(7);
	v2.push_back(11);
	v2.push_back(6);
	v2.push_back(4);
	v2.push_back(8);
	v2.push_back(2);
	v2.push_back(13);

	std::vector<int> result = findCommonElementsInTwoArr(v1,v2);
	for(int i = 0; i < result.size(); ++i) {
		cout << result.at(i) << "  ";
	}
	cout << endl;

	return 0;

}