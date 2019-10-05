/*
 find expressions combined with 4 main operators and elements of Array arr that its result is to target.
 example : 
 input : arr = {5, 2, 3, 4, 1, 6, 7, 8, 9}; target = 100.
 one of output expression: 5 - 2 + 3 * 4 + 1 * 6 + 7 + 8 * 9 
 explain: 5 - 2 + 3 * 4 + 1 * 6 + 7 + 8 * 9  = 100

#recursion #implementation
*/

#include <string>
#include <cstdio>
using namespace std;

// arr : input Array
// size Of arr
// ith: element ith of arr
// lTh: last seperate value of cal
// cal: result of caculated expression
// target:
// result: result expresssion
// preOperator: the previous Operator in expression.
void sub(int arr[], int sizeOfArr, int ith, float lTh, float cal, float target, string result, char preOperator='*')
{
    if (ith > sizeOfArr && cal == target) {
        printf("%s \n", result.c_str());
    } else if ( ith <= sizeOfArr) {
        // add
        string s = result + " + " + to_string(arr[ith]);
        float lastElement = arr[ith];
        sub(arr,sizeOfArr, ith + 1, lastElement , (float)cal + arr[ith], target, s, '+');
        
        // substract
        s = result + " - " + to_string(arr[ith]);
        lastElement = -1 * arr[ith];
        sub(arr, sizeOfArr, ith + 1, lastElement, (float)cal - arr[ith], target, s, '-');
        
        // times
        s = result + " * " + to_string(arr[ith]);
        lastElement = (float)lTh * arr[ith];
        if (preOperator == '-' || preOperator == '+')
            sub(arr, sizeOfArr, ith + 1, lastElement, cal  - lTh + lastElement, target, s, preOperator);
        else 
            sub(arr, sizeOfArr, ith + 1, lastElement , (float)cal * arr[ith], target, s, '*');
        
        // divide
        s = result + " / " + to_string(arr[ith]);
        lastElement = (float) lTh/ arr[ith];
        if (preOperator == '-' || preOperator == '+')
            sub(arr, sizeOfArr, ith + 1, lastElement, cal - lTh + lastElement, target, s, preOperator);
        else
            sub(arr, sizeOfArr, ith + 1, lastElement, (float)cal / arr[ith], target, s, '/');
    }
}

// find expressions combined with 4 main operators and elements of Array arr that its result is to target.
void findExpression(int arr[], int sizeOfArr, int target) {
    
    string s = to_string(arr[0]);
    sub(arr, sizeOfArr, 1, 1, (float)arr[0], target, s);
    
}

//test
int main() {
    int arr[] = {5, 2, 3, 4, 1, 6, 7, 8, 9};
    findExpression(arr, 8, 100);
    
    return 0;
}