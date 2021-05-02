/*
Given a list of numbers, can you find the median?

Input
    There will be two lines of input:

    n – the size of the array
    ar – n numbers that makes up the array
    1 ≤ n ≤ 1000001
    n is odd
    -100000 ≤ x ≤ 100000, x ∈ ar
Output
    Output one integer, the median.

#priority-queue #heap
*/

#include<iostream>
#include<queue>
using namespace std;

int main() {
	int n;
    cin >> n;
    int len = n / 2 + 1; 
	priority_queue<int, vector<int>, greater<int> > pq;

	for (int i=0; i < n; i++) {
		int tmp;
		cin >> tmp;
        
		if ((int)pq.size() >= len) {
            if (tmp > pq.top()){
                pq.pop();
                pq.push(tmp);
            }
        } else {
		    pq.push(tmp); 
        }   
    }  
	cout << pq.top() << endl;
	return 0;  
}