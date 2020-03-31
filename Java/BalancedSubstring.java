/*
https://codeforces.com/problemset/problem/873/B
#implement
max =
  -----------
  1 1 0 1 0 1 1 1
  1 2 1 2 1 2 3 4 => gap

|  1  0  0  1
0  1  0 -1  0

  BALANCE => THINK ABOUT PREFIX SUM.
  A -> C <- B
    => A->B "no meaning"
*/
import java.util.Scanner;
import java.util.Arrays;
public class BalancedSubstring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = 0;
        String s = sc.next();
        // other way : hash map (store only existing values)
        int[] arr = new int[2 * n + 1];
        Arrays.fill(arr, -1);
        int idx = 0;
        arr[n + 0] = 0;
        // index from 1-> s.length()
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                idx++;
            } 
            else {
                idx--;
            }
            if (arr[idx + n] == -1) {
                arr[idx + n] = i+1;
            }
            else {
                result = Math.max(result, i+1 - arr[idx + n]);
            }
        }

        System.out.println(result);
    }
}
