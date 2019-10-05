/* https://leetcode.com/problems/rectangle-overlap/description/
#math #implementation
*/

#include<iostream>
#include<vector>
using namespace std;

// Check whether each x or y of coordinate is overlap
bool isCoordinateOverLap(int a1, int a2, int b1, int b2) {
  if (( a1 <= a2 && a2 < b1) || (a2 <= a1 && a1 < b2))
    return true;
  return false;
}
bool isRectangleOverlap(vector<int>& rec1, vector<int>& rec2) {   
      bool xCoordinateOverlap = isCoordinateOverLap(rec1.at(0), rec2.at(0), rec1.at(2), rec2.at(2));
      bool yCoordinateOverlap = isCoordinateOverLap(rec1.at(1), rec2.at(1), rec1.at(3), rec2.at(3));

      if(xCoordinateOverlap && yCoordinateOverlap)
        return true;

      return false;
}

//test
int main() {
  std::vector<int> v1;
  v1.push_back(2);
  v1.push_back(17);
  v1.push_back(6);
  v1.push_back(20);

  std::vector<int> v2;
  v2.push_back(3);
  v2.push_back(8);
  v2.push_back(6);
  v2.push_back(20);

  if (isRectangleOverlap(v1, v2)) {
    std::cout << "YES";
  } else
    std::cout << "NO";
    
  return 0;
}