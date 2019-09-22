/**
Given an array A. Is there any subset of array A in which if we do AND of all elements of that subset then output should be in power of two 
(for example: 1,2,4,8,16 and so on).
Input
First line contains number of test cases T. Each test first line contains N size of array A and next line contains 
N space separated integers.
CONSTRAINTS:
1≤T≤1000
1≤N≤200
0≤ A​i ≤10^9
Output
For each test case print "YES" if there is any subset of array 
A in which if we do AND of all elements of that subset then output should be in power of two else print "NO".

/*

32bit: -> 32 pattern, only k - > 1.
chia arr -> 2 phan: 1. k -> 0,  2. k->1.

xet k->1:
2. -> 


  2^k + X
  
  101011
& xyzxyz
________
  10 0
*/

import java.util.Scanner;
import java.util.ArrayList;

class PowerOfTwo {
    static boolean isPowerOfTwo(int num) {
        int oneCnt = 0;
        while (num > 0) {
            if ((1 & num) == 1) {
                oneCnt++;
                if (oneCnt > 1) {
                    break;
                }
            }
            num >>= 1;
        }
        if (oneCnt == 1) return true;
        return false;
    }
    static void analysisNumber(ArrayList<ArrayList<Integer>> matrix, int number) {
        int idx = 0;
        int num = number;
        while (num > 0) {
            if ((1 & num) == 1) {
                matrix.get(idx).add(number);
            }
            idx++;
            num >>= 1;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        int n, number;
        
        for (int t=0; t < testcases; t++) {
            n = sc.nextInt();
            ArrayList<ArrayList<Integer>> matrix = new ArrayList<>(); // 32 bit
            for (int i=0; i < 32; i++) {
                matrix.add(new ArrayList<>());
            }
            for (int i=0; i < n; i++) {
                number = sc.nextInt();
                analysisNumber(matrix, number);
            }

            boolean ans = false;
            for (int i=0; i < matrix.size(); i++) {
                int val = -1;
                for (int num : matrix.get(i)) {
                    if (val == -1) {
                        val = num;
                    } else {
                        val &= num;
                    }
                }
                if (isPowerOfTwo(val)) {
                    ans = true;
                    break;
                }
            }

            System.out.println(ans ? "YES" : "NO");
        }
    }
}