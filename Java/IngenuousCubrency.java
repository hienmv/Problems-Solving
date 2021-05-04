/** #dynamic-programming */
import java.util.Scanner;

class IngenuousCubrency {

  public static long calculate(int amounts, int[] elements) {
    int len = elements.length;
    long[] result = new long[amounts + 1];
    result[0] = 1;
    for (int i = 0; i < len; i++) {
      for (int j = elements[i]; j <= amounts; j++) {
        result[j] += result[j - elements[i]];
      }
    }
    return result[amounts];
  }

  public static void main(String[] args) {
    int[] arr = new int[21];
    for (int i = 1; i <= arr.length; i++) {
      arr[i - 1] = i * i * i;
    }
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextInt()) {
      int amounts = sc.nextInt();
      long result = calculate(amounts, arr);
      System.out.println(result);
    }
  }
}
