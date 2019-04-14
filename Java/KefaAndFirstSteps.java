/*	https://codeforces.com/contest/580/problem/A
	idea: compare i-th element with the (i - 1) -th to find out 
	whether they are in non-decreasing order
*/

import java.util.Scanner;

public class KefaAndFirstSteps {

	static int calMaxLenNonDecreaseSubSegMent() {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		int previousValue = scanner.nextInt();
		int maxLen = 0;
		int count = 1;
		int temp = 0;
		for (int i = 0; i < number - 1; i++) {
			temp = scanner.nextInt();
			if (previousValue > temp) {
				if (count > maxLen) {
					maxLen = count;
				}
				count = 1;
			} else {
				count++;
			}
			previousValue = temp;
		}

		if (count > maxLen) {
			maxLen = count;
		}
		return maxLen;
	}

	public static void main( String args[]) {
		System.out.println(calMaxLenNonDecreaseSubSegMent());

	}
}