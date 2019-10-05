/** https://www.hackerearth.com/practice/basic-programming/bit-manipulation/basics-of-bit-manipulation/practice-problems/algorithm/samu-and-her-birthday-party-1/
 * #bit-manipulation #dp #bitmask
 * 
 * solution: status pattern?
  10010     18
  10101     21
  10010     18
  01101     13

  a pattern
  10010     ~   2^k
  
  0 -- 2^k-1
  for :1 -> 2^k-1
    for : all input & val -> > 0: -> a pattern -> count bit 1.

 Note: 3     <      4
    011        100
 ? 
 */

import java.util.Scanner;

class SamuBirthdayParty { 
    static int getNumberDist(int number) {
        int cnt = 0;
        while(number > 0) {
            if ((1 & number) == 1) {
                cnt++;
            }
            number >>= 1;
        }
        return cnt;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        
        int n, k, num;
        for (int t=0; t < testcases; t++) {
            n = sc.nextInt();
            k = sc.nextInt();
            
            int[] arr = new int[n];
            for (int i=0; i < n; i++) {
                num = Integer.parseInt(sc.next(), 2);
                arr[i] = num;
            }
            
            int selectedDist = k;
            for (int pattern = 1; pattern < (1 << k); pattern++) {
                boolean selectFlg = true;
                for (int number : arr) {
                    if ((number & pattern) == 0) {
                        selectFlg = false;
                        break;
                    }
                }
                if (selectFlg) {
                    selectedDist = Math.min(selectedDist, getNumberDist(pattern));
                }
            }
            
            System.out.println(selectedDist);
        }   
    }
}