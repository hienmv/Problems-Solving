/*	https://codeforces.com/contest/387/problem/B
	idea: compare min-element of arrays B and arrays A, 
	max-element of arrays B and arrays A,
*/

import java.util.Scanner;

public class GeorgeAndRound {
	static int calMinNeededProblem() {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int[] arrA = new int[n];
		for (int i=0; i < n; i++) {
			int temp = scanner.nextInt();
			arrA[i] = temp;
		}

		int result = n;
		int j = 0;
		for (int i=0; i < m ; i++) {
			int temp = scanner.nextInt();
			if(temp >= arrA[j]) {
				result--;
				j++;
			}
			if (j >= n || result == 0) {
				break;
			}
		}

		return result;
	}

	public static void main(String args[]) {
		System.out.println(calMinNeededProblem());
	}
}