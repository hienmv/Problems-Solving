/*	https://codeforces.com/contest/161/problem/A
#binary-search #greedy #two-pointer
	compare min value of array a and array b.
	max value of array a and array b.
	note: when it's satisfying the condition
*/

import java.util.Scanner;
import java.util.ArrayList;

public class DressEmInVests {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int x = scanner.nextInt();
		int y = scanner.nextInt();

		int[] a = new int[n+1];
		for (int i = 1; i < n + 1; i++) {
			int temp = scanner.nextInt();
			a[i] = temp;
		}

		int[] b = new int[m+1];
		for (int i = 1; i < m + 1; i++) {
			int temp = scanner.nextInt();
			b[i] = temp;
		}

		// result;
		int numPairs = 0;	
		ArrayList<String> listResult = new ArrayList<>();
		int i = 1;
		int j = 1;
		while ((i < n+1) && (j < m+1)) {
			if (a[i] - x <= b[j] && b[j] <= a[i] + y) {
				listResult.add(i + " " + j);
				i++;
				j++;
				numPairs++;
			}
			else {
				if (a[i] + y < b[j]) {
					i++;
				} else {
					j++;
				}
			}
		}

		System.out.println(numPairs);
		for (String s: listResult) {
			System.out.println(s);
		}
	}
}