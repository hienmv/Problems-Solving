/** https://codeforces.com/problemset/problem/165/C
 * tag: #binary-search #brute-force #dp #two-pointers
 *  other
 *  
// prefixsum
// sum[i] = so luong 1 tu vi tri dau den i

/*
1
      0   1   0   1   0   1
sum:  0   1   1   2   2   3
          1   1   2   2   2

    0 2 4
    i  i+k+1


    1010
    012345
    110101

    0  1  2  3
    0  1  3  5 

    index[i]+1 ... index[i+1]
    index[i + k] ... index[i + k + 1] - 1

    1 ... 1
    1 ... 2
*/

import java.util.Scanner;
import java.util.ArrayList;

public class AnotherProblemString {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        String str = sc.next();
        char[] charArr = str.toCharArray();
        ArrayList<Integer> idxOneArr = new ArrayList<Integer>();
        for (int i=0; i < charArr.length; i++) {
            if (charArr[i] == '1') {
                idxOneArr.add(i);
            }
        }
        long sum = 0;
        if (k == 0) {
            if (idxOneArr.isEmpty()) {
                int n = str.length();
                sum = (long)n*(n+1) / 2;
            } else {
                int prev=0;
                int n = 0;
                for (int idx : idxOneArr) {
                    n = idx - prev;
                    if ( n > 0) {
                        sum += (long)n * (n+1) / 2;
                    }
                    prev = idx+1;
                }
                if (prev <= str.length()) {
                    n = str.length() - prev;
                    sum += (long)n * (n+1) / 2;
                }
            }
        } else {
            if (k <= idxOneArr.size()) {
                int startIdx = 0;
                int idx = 0;
                int l=0, r=0;
                for (idx = 0; idx + k < idxOneArr.size(); idx++) {
                    l = idxOneArr.get(idx) - startIdx + 1;
                    startIdx = idxOneArr.get(idx) + 1;
                    r = idxOneArr.get(idx + k) - idxOneArr.get(idx + k - 1);
                    sum += (long)l*r;
                }
                if ( idx + k == idxOneArr.size()) {
                    r = str.length() - idxOneArr.get(idx + k - 1);
                    l = idxOneArr.get(idx) - startIdx + 1;
                    sum += (long)l*r;
                }
            }
        }
        System.out.println(sum);
    }
}