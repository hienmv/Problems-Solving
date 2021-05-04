/*	https://codeforces.com/contest/609/problem/B
	tag: #constructive-algorithms #implementation
	find out A - the number of ways choosing any 2 book in n books.
	B - the number of ways choosing any 2 book in n books that these books are same genre.
	result A - B
*/

import java.util.Scanner;

public class BestGift {

  static long calBestGift() {
    Scanner scanner = new Scanner(System.in);
    int numberOfBooks = scanner.nextInt();
    int numberOfGenders = scanner.nextInt();
    int[] arrBookGenres = new int[numberOfGenders];
    for (int i = 0; i < numberOfBooks; i++) {
      int m = scanner.nextInt();
      arrBookGenres[m - 1] += 1;
    }
    scanner.close();
    // the number of ways choosing any 2 book in n books.
    long result = (long) (numberOfBooks) * (numberOfBooks - 1) / 2;
    for (int i = 0; i < numberOfGenders; i++) {
      // the number of ways choosing any 2 book in n books that these books are same genre.
      long temp = (long) arrBookGenres[i] * (arrBookGenres[i] - 1) / 2;
      result -= temp;
    }

    return result;
  }

  public static void main(String args[]) {
    System.out.println(calBestGift());
  }
}
