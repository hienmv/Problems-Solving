/*
#dynamic-programming
-> 
base k 
N digits 
 N - 1 digits,
  ....

example: k = 7
       105
 left -> right: 1 -> 10 -> 105
        1 digits: k numbers:
        2 digits: k * numbers of way to create numbers having 1 digits - 1 * <ways creating numbers having 1 digits and last suffix is 0>
        3 digits: k * cach tao 2 digits - 1 * <số cách tạo 2 digits có số 0 tận cùng>
          ->

            g1: numbers of numbers that these last suffix is 0              g2: cac so khong chua so 0 tan cung
1-digits:   0                                         6
2-digits:   g2(1-digits)                              (k-1) * (g1 + g2)
3-digits:   g2(2-digits)                              (k-1) * (g1 + g2)
*/

import java.util.Scanner;

class KbasedNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long lastSuffix0Nums = 0;
        long lastSuffixNot0Nums = k - 1;
        long temp;
        for (int i=2; i <= n; i++) {
            temp = lastSuffixNot0Nums;
            lastSuffixNot0Nums = (k - 1) * (lastSuffixNot0Nums + lastSuffix0Nums); 
            lastSuffix0Nums = temp;
        }
        System.out.println(lastSuffix0Nums + lastSuffixNot0Nums);
    }
}
