/** #dynamic-programming #01knapsack */
import java.util.Scanner;

public class PoloPenguinAndTest {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testcase = sc.nextInt();
    for (int t = 0; t < testcase; t++) {
      int n = sc.nextInt();
      int w = sc.nextInt();
      int c, p, T;
      Item[] arr = new Item[n + 1];
      arr[0] = new Item(0, 0);
      for (int i = 1; i <= n; i++) {
        c = sc.nextInt();
        p = sc.nextInt();
        T = sc.nextInt();
        arr[i] = new Item(c * p, T);
      }
      int result = Knapsack(arr, w);
      System.out.println(result);
    }
  }

  public static int Knapsack(Item[] arr, int W) {
    int[][] K = new int[arr.length][W + 1];
    for (int i = 1; i < arr.length; i++) {
      for (int j = 0; j <= W; j++) {
        if (arr[i].cost > j) {
          K[i][j] = K[i - 1][j];
        } else {
          int tmp1 = arr[i].profit + K[i - 1][j - arr[i].cost];
          int tmp2 = K[i - 1][j];
          K[i][j] = Math.max(tmp1, tmp2);
        }
      }
    }
    return K[arr.length - 1][W];
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
