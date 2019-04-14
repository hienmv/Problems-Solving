/*	https://codeforces.com/contest/169/problem/A
	idea: sort the array and find b-th element.
	result arr[b-th] -  arr[b-1]
*/

import java.util.Scanner;
import java.util.Arrays;

public class Chores {
	static int calMaxWay() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();

		int[] arr = new int[n];
		for (int i=0; i < n; i++){
			int temp = sc.nextInt();
			arr[i] = temp;
		}
		Arrays.sort(arr);

		return arr[b] - arr[b-1];
	}

	public static void main(String[] args) {
		System.out.println(calMaxWay());
	}
}