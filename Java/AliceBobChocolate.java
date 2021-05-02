/*	https://codeforces.com/problemset/problem/6/C
 *	tag: #greedy  #two-pointer
 *	count sumLeft, sumRight, 
 *	the smaller sum += nextElement based on its side.
*/

import java.util.Scanner;

public class AliceBobChocolate {
	static String calAmountOfBars() {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			int temp = scanner.nextInt();
			arr[i] = temp;
		}
		scanner.close();
		// special case 
		if (n == 1) {
			return "1 0";
		}

		int sumAlice = arr[0];
		int sumBob = arr[n-1];
		int countAlice = 1;
		int countBob = 1;
		int left = 1;
		int right = n - 2;
		while (left <= right) {
			if (sumAlice <= sumBob) {
				sumAlice += arr[left];
				countAlice += 1;
				left++;
			} else {
				sumBob += arr[right];
				countBob += 1;
				right--;
			}
		}

		return countAlice + " " + countBob;

	}

	public static void main(String[] args) {
		System.out.println(calAmountOfBars());
	}
}