/**
 * https://www.hackerrank.com/contests/womens-codesprint-2/challenges/minimum-loss
 * #binary-search-tree
 */
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

  // Complete the minimumLoss function below.
  static int minimumLoss(long[] prices) {
    TreeSet<Long> pricesSet = new TreeSet<>();
    for (long price : prices) {
      pricesSet.add(price);
    }
    long gaps = Long.MAX_VALUE;
    for (long price : prices) {
      pricesSet.remove(price);
      Long lowerPrice = pricesSet.lower(price);
      if (lowerPrice != null && (price - lowerPrice) < gaps) {
        gaps = price - lowerPrice;
      }
    }
    return (int) gaps;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    long[] price = new long[n];

    String[] priceItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      long priceItem = Long.parseLong(priceItems[i]);
      price[i] = priceItem;
    }

    int result = minimumLoss(price);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
