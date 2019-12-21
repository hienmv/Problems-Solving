/**
 * #dp #knapsack
 *  => han che goi bai toan nhieu lan.
 *  => cai tien, chap nhan them 1 tham so nua..
 *
 * #dp
  L = 5
  n = 2
  5 5
  5 5
    -> le idx -> x2

  f[i][j] = i items with capacity j
  ->  f[i][j][k] (k = 0, 1, 2)
        f[i][j][k] = pick i items with capacity j, extend k
          k = 0:  khong co thanh nao du ra
          k = 1: 1 thanh du ra 
          k = 2: 2 thanh du ra 2 dau
      
      
      f[i][j][k]
        khong chon: f[i-1][j][k]
        chon:
          if j >= 2 * a[i].length
            khong bi du:    f[i-1][j - 2*length(i)][k]
          if j >= a[i].length and k > 0
            bi du ra:       f[i-1][j - length(i)][k - 1]
*/

import java.util.Scanner;

class PickTheSticks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        for(int t=1; t <= testcase; t++) {
            int n = sc.nextInt();
            int L = sc.nextInt();
            Item[] items = new Item[n+1];
            items[0] = new Item(0, 0);
            int a, v;
            int maxValue = 0;
            for(int i=1; i <= n; i++) {
                a = sc.nextInt();
                v = sc.nextInt();
                maxValue = Math.max(maxValue, v);
                items[i] = new Item(a, v);
            }
            long result = Math.max(maxValue, Knapsack(items, 2*L));
            System.out.println("Case #" + t + ": " + result);
        }
    }

    public static long Knapsack(Item[] items, int w) {
        int n = items.length;
        long[][][] K = new long[n][w+1][3];
        for(int i=1; i < n; i++) {
            for (int j=0; j <= w; j++) {
                for (int k=0; k < 3; k++) {
                    // not choose
                    K[i][j][k] = K[i-1][j][k];

                    // choose
                    // inside
                    if (j >= 2 * items[i].length) {
                        K[i][j][k] = Math.max(K[i][j][k], items[i].value + K[i-1][j - 2 * items[i].length][k]);
                    }
                    // outside
                    if (j >= items[i].length && k > 0) {
                        K[i][j][k] = Math.max(K[i][j][k], items[i].value + K[i-1][j - items[i].length][k-1]);
                    }
                }
            }
        }
        long result = 0;
        for (int j=0; j <= w; j++) {
            for (int k=0; k < 3; k++) {
                result = Math.max(result, K[n-1][j][k]);
            }
        }
        return result;
    }
}

class Item {
    int length;
    int value;
    Item(int length, int value) {
        this.length = length;
        this.value = value;
    }
}