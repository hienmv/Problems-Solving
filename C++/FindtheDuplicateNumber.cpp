/*  https://leetcode.com/problems/find-the-duplicate-number/description/
#arrays #binary-search #two-pointer
*/

#include <iostream>
#include <vector>
#include<map>
using namespace std;

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