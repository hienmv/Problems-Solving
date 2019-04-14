/*	https://codeforces.com/contest/158/problem/B
	idea: 
	find out: number of group of 4 people
	min number of (group of 1 person, group of 3 people)
	remained group of 3 people
	number of cars needed to remained group of 1 person and group of 2 people.
*/

import java.util.Scanner;

public class Taxi {

	static int calMinTaxiNum() {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		int[] arr = new int[4];
		int temp = 0;
		for (int i = 0; i < number; i++) {
			temp = scanner.nextInt();
			arr[temp - 1] += 1;
		} 

		// group of 4 people
		int result = arr[3];

		// group of 1 person and 3 people
		if (arr[0] == arr[2]) {
			result += arr[0];
			arr[0] = 0;
			arr[2] = 0;
		}
		if (arr[0] < arr[2]) {
			result += arr[0];
			arr[2] -= arr[0];
			arr[0] = 0;
		} else {
			result += arr[2];
			arr[0] -= arr[2];
			arr[2] = 0;
		}
		result += arr[2];

		// group of 1 person and 2 people
		if (arr[0] != 0 || arr[1] !=0) {
			result += (arr[0] + 2 * arr[1] - 1) / 4 + 1;
		}
		return result;
	}

	public static void main(String args[]) {
		System.out.println(calMinTaxiNum());
	}
}