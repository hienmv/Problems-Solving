/**
 * #dp #knapsack
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
            for(int i=1; i <= n; i++) {
                a = sc.nextInt();
                v = sc.nextInt();
                items[i] = new Item(a, v);
            }
            long result;
            // only one item
            if (n == 1) {
                result = items[1].value;
            }
            else {
                result = Knapsack(items, L);
            }
            System.out.println("Case #" + t + ": " + result);
        }
    }
    public static long Knapsack(Item[] items, int L) {
        int n = items.length;
        long result = 0;

        for (int j=1; j < n; j++) {
            for (int k=j+1; k < n; k++) {
                int w =  L - ((items[j].length - 1) / 2 + 1 + (items[k].length - 1) / 2 + 1);
                if (w == 0) {
                    result = Math.max(result, items[j].value + items[k].value);
                    continue;
                } 
                else if (w < 0) {
                    result = Math.max(result, Math.max(items[j].value, items[k].value));
                    continue;
                }

                // normal case
                long val = items[j].value + items[k].value;
                long[][] K = new long[n][w+1];
                for(int i=1; i < n; i++) {
                    for (int h=0; h <= w; h++) {
                        if (items[i].length > h || i == j || i == k) {
                            K[i][h] = K[i-1][h];
                        }
                        else {
                            long val1 = K[i-1][h];
                            long val2 = items[i].value + K[i-1][h - items[i].length];
                            K[i][h] = Math.max(val1, val2);
                        }
                    }
                }
                result = Math.max(result, val + K[n-1][w]);
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