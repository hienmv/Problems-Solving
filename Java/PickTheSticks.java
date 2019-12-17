/**
 * #dp #knapsack
 */

import java.util.Scanner;
import java.util.Arrays;

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
                // sort
                Arrays.sort(items);
                result = calculate(items, L);
            }
            System.out.println("Case #" + t + ": " + result);
        }
    }
    public static long calculate(Item[] items, int L) {
        long result = 0, val, val1, val2;
        int i = 1, j = items.length - 1;
        while (i < j) {
            val = subCalculate(items, i, j, L);
            val1 = 0; // i, j-1
            val2 = 0; // i+1, j
            if (i + 1 < j)
            {
                val1 = subCalculate(items, i, j-1, L);
                val2 = subCalculate(items, i+1, j, L);
            }
            System.out.printf("%d - %d - %d - %d - %d\n", i,  j, val1, val2, val);
            if (val1 >= val2) {
                j--;
            }
            if (val2 >= val1) {
                i++;
            }
            val = Math.max(val, Math.max(val1, val2));
            result = Math.max(result, val);
        }
        return result;
    }
    
    public static long subCalculate(Item[] items, int i, int j, int L) {
        int w =  L - ((items[i].length + 1) / 2 + (items[j].length + 1) / 2);
        long val = 0;
        if (w < 0) {
            val = Math.max(items[i].value, items[j].value);
        }
        else if (w == 0) {
            val = items[i].value + items[j].value;
        } 
        else {
            val = items[i].value + items[j].value + Knapsack(items, w, i, j);
        }
        return val;
    }

    public static long Knapsack(Item[] items, int w, int invalidIdx1, int invalidIdx2) {
        int n = items.length;
        long[][] K = new long[n][w+1];
        for(int i=1; i < n; i++) {
            for (int j=0; j <= w; j++) {
                if (items[i].length > j || i == invalidIdx1 || i == invalidIdx2) {
                    K[i][j] = K[i-1][j];
                }
                else {
                    long val1 = K[i-1][j];
                    long val2 = items[i].value + K[i-1][j - items[i].length];
                    K[i][j] = Math.max(val1, val2);
                }
            }
        }
        return K[n-1][w];
    }
}

class Item implements Comparable<Item> {
    int length;
    int value;
    Item(int length, int value) {
        this.length = length;
        this.value = value;
    }
    public int compareTo(Item other) {
        if (this.length != other.length) {
            return this.length - other.length;
        }
        return this.value - other.value;
    }
}