/**
* #dynamic-programming #lcs 
lcs s and reverse of s. 

abccbaazo

tfft
tff

abca
acba

ozaabccbaazo
ozaabccba
*/
import java.util.Scanner;
class Aibohphobia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        char[] arr1 = s.toCharArray();
        int n = arr1.length;
        char[] arr2 = new char[n];
        for(int i=0; i < n; i++) {
            arr2[n - 1 - i] = arr1[i];
        }
        int lcs = getLcs(arr1, arr2, n);
        System.out.println(n - lcs);
    }
    public static int getLcs(char[] arr1, char[] arr2, int sz) {
        int[][] L = new int[sz+1][sz+1];
        for(int i=0; i <= sz; i++) {
            for(int j=0; j <= sz; j++) {
                if (i==0 || j==0) {
                    L[i][j] = 0;
                }
                else if (arr1[i-1] == arr2[j-1]) {
                    L[i][j] = L[i-1][j-1] + 1;
                }
                else {
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
                }
            }
        }
        return L[sz][sz];
    }
}