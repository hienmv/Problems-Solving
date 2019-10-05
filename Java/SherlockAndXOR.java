/** https://www.hackerearth.com/practice/basic-programming/bit-manipulation/basics-of-bit-manipulation/practice-problems/algorithm/sherlock-and-xor/description/
#bit-manipulation #math
 */

import java.util.Scanner;

class SherlockAndXOR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for (int t=0; t < testCases; t++) {
            int n = sc.nextInt();
/*
            int sz = n+1;
            int[] oddArr = new int[sz];
            int[] evenArr = new int[sz];
            int[] arr = new int [sz];
            int number;
            for (int i=1; i < sz; i++) {
                number = sc.nextInt();
                arr[i] = number;
                if (number % 2 == 0) {
                    oddArr[i] = oddArr[i-1];
                    evenArr[i] = evenArr[i-1] + 1;
                } else {
                    oddArr[i] = oddArr[i-1] + 1;
                    evenArr[i] = evenArr[i-1];
                }
            }
            int maxOddNumber = oddArr[oddArr.length - 1];
            int maxEvenNumber = evenArr[evenArr.length - 1];
            long ans = 0;
            for (int i=1; i < sz; i++) {
                if (arr[i] % 2 == 0) {
                    ans += maxOddNumber - oddArr[i];
                } else {
                    ans += maxEvenNumber - evenArr[i];
                }
            }
            System.out.println(ans);
*/

/* Better way */ 
            int numOdd = 0;
            int numEven = 0;
            long ans = 0;
            int number = 0;
            for (int j=0; j < n; j++) {
                number = sc.nextInt() & 1;
                if (number == 1) {
                    ans += numEven;
                    numOdd += 1;
                } else {
                    ans += numOdd;
                    numEven += 1;
                }
            }
            System.out.println(ans);
        }
    }
}