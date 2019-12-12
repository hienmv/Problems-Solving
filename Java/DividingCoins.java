/**
#dp #01knapsack
 */

import java.util.Scanner;

public class DividingCoins {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        for (int t=0; t < testcase; t++) {
            int n = sc.nextInt();
            int w = 0;
            int c, p, T;
            int[] arr = new int[n+1];
            Item[] items = new Item[n+1];
            items[0] = new Item(0,0);
            for (int i=1; i <= n; i++) {
                arr[i] = sc.nextInt();
                w += arr[i];
            }
            for (int i=1; i <=n; i++) {
                items[i] = new Item(arr[i], arr[i]);
            }
            int result = Knapsack(items, w / 2);
            System.out.println(Math.abs( w - 2 * result));
        }
    }
    public static int Knapsack(Item[] arr, int W) {
        int[][] K = new int[arr.length][W + 1];
        for (int i=1; i < arr.length; i++) {
            for (int j=0; j <= W; j++) {
                if (arr[i].cost > j) {
                    K[i][j] = K[i-1][j];
                }
                else {
                    int tmp1 = arr[i].profit + K[i-1][j - arr[i].cost];
                    int tmp2 = K[i-1][j];
                    K[i][j] = Math.max(tmp1, tmp2);
                }
            }
        }
        return K[arr.length - 1][W];
/* refactor
        Case 1:
        int[][] K2 = new int[2][W + 1] => reduce space. arr.length -> 2
        for (int i=1; i < arr.length; i++) {
            int cur = i & 1
            int prev = 1 - cur; // (i - 1) & 1
            ->>>> cur/prev ~ i/ i-1
            ..
        }
        
        
        Case 2:
        for (int i=1; i < arr.length; i++) {
            // int cur = i & 1
            // int prev = 1 - cur; 
            for (int j=W; j >= 0; j++) { => duyet ngc, giam space. k thay doi gia tri cu..
                if (arr[i].cost > j) {
                    K[j] = K[j];
                }
                else {
                    int tmp1 = arr[i].profit + K[j - arr[i].cost];
                    int tmp2 = K[j];
                    K[j] = Math.max(tmp1, tmp2);
                }
            }
        }
        
        
*/

/*
    Case 3:
    => reduce space...
    
    K[j] = true/false
    
    K[0] = true
    K[j] = true
        K[j - a[i]] = true
    
    K[0] = true
    for (int i=1; i < arr.length; i++) {
            for (int j=W; j >= 0; j++) { => duyet ngc, giam space. k thay doi gia tri cu..
                if (arr[i].cost <= j) {
                    bool tmp1 = K[j - arr[i].cost];
                    bool tmp2 = K[j];
                    K[j] = tmp1 || tmp2;
                }
            }
        }
    for (int i = W; i >= 0; i--) {
        if (K[i] == true) {
            return i
        }
    }
*/
    }
}

class Item {
    int profit;
    int cost;
    Item(int profit, int cost) {
        this.profit = profit;
        this.cost = cost;
    }
}