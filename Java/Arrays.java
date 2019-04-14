/*  https://codeforces.com/contest/572/problem/A
    idea: find the first element (called j-th) in arrays B that 
    greater than the k-th element of arrays A and, 
    check if nB - j >= m

*/

import java.util.Scanner;

public class Arrays {

    static String checkPossible() {
        String result = "NO";
        Scanner scanner = new Scanner(System.in);
        int nA = scanner.nextInt();
        int nB = scanner.nextInt();
        int k = scanner.nextInt();
        int m = scanner.nextInt();

        int kThA = 0;
        for(int i=0; i < nA; i++) {
            int temp = scanner.nextInt();
            if (i == k - 1) {
                kThA = temp;
            }    
        }
        for (int i=0; i < nB; i++) {
            int temp = scanner.nextInt();
            if (temp > kThA) {
                if (nB - i >= m) {
                    result = "YES";
                }
                break;
            }
        }
        
        return result;
    }

    public static void main (String args[]) {
        System.out.println(checkPossible());
    }
}
