/*	https://codeforces.com/contest/242/problem/B
	idea: assume each column as a min values array and max values array respectively.
	find the minimum value and maximum value.
	and find out if having an index i that 
	min-array[i] = min-value, max-array[i] = max-value
*/

import java.util.Scanner;

public class BigSegment {

	static int findIndexBigSegment() {

		Scanner scanner = new Scanner(System.in);
		int length = scanner.nextInt();
		int minArr[] = new int[length];
		int maxArr[] = new int[length];
		
		minArr[0] = scanner.nextInt();
		maxArr[0] = scanner.nextInt();
		int minValue = minArr[0];
		int maxValue = maxArr[0];
		
		for (int i = 1; i < length; i++) {
			minArr[i] = scanner.nextInt();
			if (minArr[i] < minValue) {
				minValue = minArr[i];

			}
			maxArr[i] = scanner.nextInt();
			if (maxArr[i] > maxValue) {
				maxValue = maxArr[i];

			}
		}
		
		for (int i = 0; i < length; i++) {
			if (minArr[i] == minValue && maxArr[i] == maxValue) 
				return i + 1;
		}

		return -1;
	}
	public static void main( String args[]) {
		System.out.println(findIndexBigSegment());
	}
}