/** #dynamic-programming #knapsack */
import java.util.Scanner;

class ScubaDiver {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int c = sc.nextInt();
    for (int i = 0; i < c; i++) {
      int t = sc.nextInt();
      int a = sc.nextInt();
      int n = sc.nextInt();
      Item[] items = new Item[n];
      int ti, ai, wi;
      for (int j = 0; j < n; j++) {
        ti = sc.nextInt();
        ai = sc.nextInt();
        wi = sc.nextInt();
        items[j] = new Item(ti, ai, wi);
      }
      int result = Knapsack(items, t, a);
      System.out.println(result);
    }
  }

  public static int Knapsack(Item[] items, int t, int a) {
    // K[n][oxy][nitro] : find minumum weight in n items, to get i oxy, j nitro
    int[][][] K = new int[items.length][t + 1][a + 1];
    for (int k = 0; k < items.length; k++) {
      for (int i = 0; i <= t; i++) {
        for (int j = 0; j <= a; j++) {
          if (i == 0 && j == 0) {
            K[k][i][j] = 0;
            continue;
          }

          int newOxyNeed = Math.max(i - items[k].oxygen, 0);
          int newNitroNeed = Math.max(j - items[k].nitrogen, 0);
          if (k == 0) {
            if (newOxyNeed == 0 && newNitroNeed == 0) {
              K[k][i][j] = items[k].weight;
            } else {
              K[k][i][j] = 1659001; // max value
            }
          } else {
            K[k][i][j] = K[k - 1][i][j];
            K[k][i][j] = Math.min(K[k][i][j], K[k - 1][newOxyNeed][newNitroNeed] + items[k].weight);
          }
        }
      }
    }
    return K[items.length - 1][t][a];
  }
}

class Item {
  int oxygen;
  int nitrogen;
  int weight;

  Item(int oxygen, int nitrogen, int weight) {
    this.oxygen = oxygen;
    this.nitrogen = nitrogen;
    this.weight = weight;
  }
}
