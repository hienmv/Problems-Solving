/**
 * https://codeforces.com/problemset/problem/687/C
 * #dp
f(n, k, x) = true -> ton tai chon subset trong n phan tu, sum = k va subset con sum = x
    f(n-1, k, x) = true
    
    f(n-1, k-a[n], x) = true
    
    f(n-1, k-a[n], x-a[n]) = true

for u = 0 .. n-1
    for v = 0 .. k
        for w = 0 .. v

 */

import java.util.Scanner;
import java.util.ArrayList;
public class TheValuesYouCanMake {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] org = new int[n+1];
        for (int i = 1; i <= n; i++) {
            org[i] = sc.nextInt();
        }

        boolean[][][] L = new boolean[n+1][k+1][k+1];
        L[0][0][0] = true;
        for (int u = 1; u <= n; u++) {
            for (int v = 0; v <= k; v++) {
                for (int w = 0; w <= v; w++) {
                    int val = org[u];
                    // not need current value
                    if (L[u - 1][v][w]) {
                        L[u][v][w] = true;
                    }
                    else if (v >= val) {
                        if (L[u - 1][v - val][w]) {
                            L[u][v][w] = true;
                        } 
                        else if (w >= val) {
                            if (L[u - 1][v - val][w - val]) {
                                L[u][v][w] = true;
                            }
                        }
                    }
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < L[n][k].length; i++) {
            if (L[n][k][i]) {
                result.add(i);
            }
        }

        /** refactor space complexity
        boolean[][][] L = new boolean[2][k+1][k+1];
        L[0][0][0] = true;
        for (int u = 1; u <= n; u++) {
            for (int v = 0; v <= k; v++) {
                for (int w = 0; w <= v; w++) {
                    int val = org[u];
                    // not need current value
                    if (L[(u - 1) % 2][v][w]) {
                        L[u % 2][v][w] = true;
                    }
                    else if (v >= val) {
                        if (L[(u - 1) % 2][v - val][w]) {
                            L[u % 2][v][w] = true;
                        } 
                        else if (w >= val) {
                            if (L[(u - 1) % 2][v - val][w - val]) {
                                L[u % 2][v][w] = true;
                            }
                        }
                    }
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < L[n][k].length; i++) {
            if (L[n % 2][k][i]) {
                result.add(i);
            }
        }
        */

        System.out.println(result.size());
        result.forEach(x -> System.out.print(x + " "));
    }
}