/*	https://codeforces.com/contest/486/problem/A
	idea: n - even number, result = n / 2
	n - odd number, resultl = n / 2 - n
*/

import java.util.Scanner;

public class CalculatingFunction {

	static long calculate() {
		Scanner scanner = new Scanner(System.in);
		long input = scanner.nextLong();
		long result = input / 2;
		if (input % 2 != 0) {
			result -= input; 
		}
		return result;
	}
	public static void main( String args[]) {
		System.out.println(calculate());
	}
}