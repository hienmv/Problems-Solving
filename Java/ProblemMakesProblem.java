/**
 * #number-theory
 * 
1 ++ 1 1 1 = 4

1  0  3

1 + 1 + 1 1 

n = 4, k = 3

0 1 3

+ 1 + 1 1 1

1 0 3
1 + 0 + 1 1 1

1 + + 1 1 1


1 1 + 1 1 1 + 1 1 -> '1' and '+' have same corresponding position.

N+K-1

Select (K-1) position in  (N+K-1) position

C(N)(N+K-1) = (N+K-1)! / (N! * (K - 1)!) % (1e9 + 7)

1 0 0 1 0 0 1 0 0 1 (codeforce)

------
 * other : not effective
    backtracking(N, K):
        if K == 0:
            if N == 0:
                return 1
            return 0
        result = 0
        for x = 0 ... N:
            result += backtracking(N - x, K - 1)
        return result
 */

import java.util.Scanner;
import java.util.ArrayList;

class ProblemMakesProblem {
    static int max = 1_000_000_007;

    public static ArrayList<Integer> extendedEuclid(int b, int m) {
        ArrayList<Integer> result = new ArrayList<>();
        int x1 = 0, y1 = 1;
        int x2 = 1, y2 = 0;
        int q, r;
        int x= 1, y = 0;
        while (m != 0) {
            q = b / m;
            r = b % m;
            x = x2 - q * x1;
            y = y2 - q * y1;
            x2 = x1;
            y2 = y1;
            x1 = x;
            y1 = y;
            b = m;
            m = r;
        }
        result.add(b);
        result.add(x2);
        result.add(y2);
        return result;
    }
    public static int modInverse(int b, int m) {
        ArrayList<Integer> result = extendedEuclid(b,m);
        int gcd = result.get(0);
        int x = result.get(1);
        int y = result.get(2);
        if (gcd != 1) {
            return -1;
        }
        return (x + m) % m;
    }
    public static void main(String[] args) {
        int maxSize = 2 * 1000000;
        long[] exponential = new long[maxSize];
        exponential[0] = 1;
        exponential[1] = 1;
        for (int i=2; i < maxSize; i++) {
            exponential[i] = (exponential[i-1] * i) % max;
        }

        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        for (int t=1; t< testcases + 1; t++) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            
            // C(N)(N+K-1) = (N+K-1)! / (N! * (K - 1)!) % (1e9 + 7)
            // (N+K-1)! % (1e9 + 7)
            long numerator = exponential[n+k-1];
            
            // (N! * (K - 1)!) % (1e9 + 7)
            long denominator = (exponential[n] * exponential[k-1]) % max;
           
            int modInverseDenominator = modInverse((int)denominator, max);
            int val = (int)(numerator * modInverseDenominator % max);
            System.out.println("Case " + t + ": " + val);
        }
    }
}