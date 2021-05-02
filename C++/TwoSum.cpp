// https://leetcode.com/problems/two-sum/
// #array #hash-table
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        vector<int> results;
        map<int, int> hashMap;
        for (int i = 0; i < nums.size(); i++) {
            map<int, int>::iterator iter = hashMap.find(target - nums.at(i));
            if (iter != hashMap.end()) {
                
                results.push_back(iter->second);
                results.push_back(i);
                break;
            }
            hashMap.insert(pair<int, int>(nums.at(i), i));
        }
        
        return results;
    }
};