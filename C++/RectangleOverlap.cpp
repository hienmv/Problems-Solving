#include<iostream>
#include<vector>
using namespace std;
/*
https://leetcode.com/problems/rectangle-overlap/description/

A rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) are the coordinates of its bottom-left corner, and (x2, y2) are the coordinates of its top-right corner.

Two rectangles overlap if the area of their intersection is positive.  To be clear, two rectangles that only touch at the corner or edges do not overlap.

Given two (axis-aligned) rectangles, return whether they overlap.

Example 1:

Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
Output: true
Example 2:

Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
Output: false
Notes:

Both rectangles rec1 and rec2 are lists of 4 integers.
All coordinates in rectangles will be between -10^9 and 10^9.

*/

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