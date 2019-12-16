/*
#dp #01knapsack
*/

import java.util.Scanner;
import java.util.ArrayList;

class DivingForGold {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = 0;
        while(sc.hasNextInt()) {
            if (testcase++ > 0) {
                System.out.println();
            }
            int t = sc.nextInt();
            int w = sc.nextInt();
            int n = sc.nextInt();
            Item[] items = new Item[n+1];
            items[0] = new Item(0, 0);
            int d, v;
            for(int i=1; i <= n; i++) {
                d = sc.nextInt();
                v = sc.nextInt();
                items[i] = new Item(d,v);
            }
            int[][] K = new int[n+1][t+1];
            int maxGold = Knapsack(items, K, w, t);
            System.out.println(maxGold);
            ArrayList<Item> result = getSelectedItems(items, K, w, t);
            System.out.println(result.size());
            for(int i=result.size() - 1; i >= 0; i--) {
                Item item = result.get(i);
                System.out.println(item.depth + " " + item.gold);
            }
        }
    }
    public static int Knapsack(Item[] items, int[][] K, int w, int t) {
        int n = items.length;
        for(int i=1; i < n; i++) {
            for (int j=0; j <= t; j++) {
                int val = 3 * w * items[i].depth;
                if (val > j) {
                    K[i][j] = K[i-1][j];
                }
                else {
                    int tmp1 = items[i].gold + K[i-1][j-val];
                    int tmp2 = K[i-1][j];
                    K[i][j] = Math.max(tmp1, tmp2);
                }
            }
        }
        return K[n-1][t];
    }
    public static ArrayList<Item> getSelectedItems(Item[] items, int[][] K, int w, int t) {
        ArrayList<Item> result = new ArrayList<>();
        for(int i=items.length - 1; i > 0; i--) {
            if (K[i][t] != K[i-1][t]) {
                result.add(items[i]);
                t -= 3 * w * items[i].depth;
            }
        }
        return result;
    }
}
class Item {
    int depth;
    int gold;
    Item(int depth, int gold) {
        this.depth = depth;
        this.gold = gold;
    }
}