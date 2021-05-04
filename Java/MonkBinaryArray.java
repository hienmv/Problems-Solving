/**
 * https://www.hackerearth.com/practice/basic-programming/bit-manipulation/basics-of-bit-manipulation/practice-problems/algorithm/monk-and-binary-array-1/
 * #bit-manipulation
 */
/*
        0 1 2 3 4 5 6 7 8 9
        1 1 0 0 1 1 0 0 0 1

preSum  1 2 2 2 3 4 4 4 4 5
              L       R
        ---------------
        -----
        0 1 1 1 2 3 3 3 3 4

------------
        [L, R] preSum[R] - preSum[L - 1] - > XOR = 1

         0 1 2 3 4
oneList: 0 1 4 5 9

-> flip bit
        0 1 2 3 4 5 6 7 8 9
        1 1 0 0 1 1 0 0 0 1
        |
*/

import java.util.Scanner;

class MonkBinaryArray {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int sz = n + 1;
    int[] arr = new int[sz];
    int[] oneArr = new int[sz];
    int val;
    for (int i = 1; i < sz; i++) {
      val = sc.nextInt();
      arr[i] = val;
    }
    int ans = 0;

    for (int i = 1; i < arr.length; i++) {
      // attempt to flip bit
      arr[i] ^= 1;

      // calculate
      int sum = 0;
      // for (int j=1; j < arr.length; j++) {
      //     if (arr[j] == 1) {
      //         oneArr[j] = oneArr[j-1] + 1;
      //     } else {
      //         oneArr[j] = oneArr[j-1];
      //     }
      // }
      int numEven = 1;
      int numOdd = 0;
      int tot = 0;
      for (int r = 1; r < oneArr.length; r++) {
        if (arr[r] == 1) {
          tot++;
        }
        if ((tot & 1) == 1) { // odd
          sum += numEven;
          numOdd++;
        } else {
          sum += numOdd;
          numEven++;
        }
      }

      if (ans < sum) {
        ans = sum;
      }

      // reset
      arr[i] ^= 1;
    }
    System.out.println(ans);

    // Complexity:
    //  Space: O(N)
    //  Time: O(N^2)
    // for (int i=1; i < arr.length; i++) {
    //     // attempt to flip bit
    //     arr[i] ^= 1;

    //     // calculate
    //     int sum = 0;
    //     for (int j=1; j < arr.length; j++) {
    //         if (arr[j] == 1) {
    //             oneArr[j] = oneArr[j-1] + 1;
    //         } else {
    //             oneArr[j] = oneArr[j-1];
    //         }
    //     }
    //     int numEven = 1;
    //     int numOdd = 0;
    //     for (int r = 1; r < oneArr.length; r++) {
    //         if (oneArr[r] & 1 == 1) { // odd
    //             sum += numEven;
    //             numOdd++;
    //         }
    //         else {
    //             sum += numOdd;
    //             numEven++;
    //         }

    //         // for (int l = 1; l <= r; l++) {
    //         //     if ((oneArr[r] - oneArr[l - 1]) & 1 == 1) {
    //         //         sum += 1;
    //         //     }
    //         // }
    //     }

    //     if (ans < sum) {
    //         ans = sum;
    //     }

    //     // reset
    //     arr[i] ^= 1;
    // }

  }
}
