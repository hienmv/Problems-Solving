/*	https://codeforces.com/contest/279/problem/B
	tag:    #binary-search #implementation #two-pointers
	max (get number of books can be read that counted from i-th element)
*/
/* Example:
  10 15
  10 9 1 1 5 10 5 3 7 2
f |
l |
*/

import java.util.Scanner;

public class Books {

	static int calMaxNumBooks() {
		Scanner scanner = new Scanner(System.in);
		int numberOfBooks = scanner.nextInt();
		int freeMinutes = scanner.nextInt();

		int[] bookMinuteArr = new int[numberOfBooks];
		for (int i=0; i < numberOfBooks; i++) {
			int minute = scanner.nextInt();
			bookMinuteArr[i] = minute;
		}

		int maxBooksBeRead = 0;
		int count = 0;
		int firstIdx = 0;
		int lastIdx = 0;
		while (lastIdx < numberOfBooks) {
			if (bookMinuteArr[lastIdx] <= freeMinutes) {
				freeMinutes -= bookMinuteArr[lastIdx];
				count++;
				lastIdx++;
				if(count > maxBooksBeRead) {
					maxBooksBeRead = count;
				}
			} else {
				freeMinutes += bookMinuteArr[firstIdx];
	        	count--;
	        	firstIdx++;
	        	/*
				if (count > 0) {
					freeMinutes += bookMinuteArr[firstIdx];
					firstIdx++;
					count--;
				} else {
					lastIdx++;
				}
				*/
			}
		}
		return maxBooksBeRead;
	}

	public static void main(String[] args) {
		System.out.println(calMaxNumBooks());
	}
}
