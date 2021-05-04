/*	https://codeforces.com/contest/676/problem/A
  #constructive-algorithms #implementation

	get the index of max value, min value.
	result is max(length - 1 - the greater index, smaller index)
*/

import java.util.Scanner;

public class NicholasAndPermutation {

  static int calMaxDistance() {
    Scanner scanner = new Scanner(System.in);
    int length = scanner.nextInt();

    int maxIdx = 0;
    int minIdx = 0;
    int maxValue = scanner.nextInt();
    int minValue = maxValue;
    for (int i = 1; i < length; i++) {
      int temp = scanner.nextInt();
      if (temp < minValue) {
        minValue = temp;
        minIdx = i;
      }
      if (temp > maxValue) {
        maxValue = temp;
        maxIdx = i;
      }
    }

    int result = 0;
    // result is max(length - 1 - the greater index, smaller index)
    if (maxIdx > minIdx) {
      result = maxIdx > (length - minIdx - 1) ? maxIdx : (length - minIdx - 1);
    } else {
      result = minIdx > (length - maxIdx - 1) ? minIdx : (length - maxIdx - 1);
    }

    return result;
  }

  public static void main(String args[]) {
    System.out.println(calMaxDistance());
  }
}
