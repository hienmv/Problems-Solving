#include<iostream>
#include<math.h>
using namespace std;
/*
	Find numbers in range [0, numbers] containning number 1 in this
	Example: input 22, Output: 12  { 1, 10 , 11, 12, 13, 14, 15, 16, 17, 18, 19, 21}
*/
int sub(int n){
	if ( n < 10){
		return 1;
	} 
 	int numbers = 0;
 	for(int i=n; i > 0; i = i /10){
 		if ( i /10 > 0){
			numbers++;
		}
 	}
	
	int temp = pow(10, numbers);
	if (n/temp > 1) {
		return (temp + (n/temp - 1)* sub(temp - 1) + sub(n%temp));
	} else {
		return (sub(temp - 1) + n%temp);
	}
}

int main(){
	int n;
	cout << "input:"<< endl;
	cin>>n;
	cout << "output:" << sub(n)<<endl;
	return 0;

}
