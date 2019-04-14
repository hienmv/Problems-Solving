/*	https://codeforces.com/contest/224/problem/B
	idea: find the last index that satisfying the given property
	move the first index to the right side to make sure
	no [x,y] segment inside [f,l] that satisfying the given property

	two way to check an element inside a segment.
	1. use data structure: map
	2. use array, the element treated as index.
*/
import java.util.Scanner;

public class Array {
	static String findSatisfyingSegment() {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		
		// special case
		if  (k == 1) {
			return "1 1";
		}

		int firstIdx = 0;
		int lastIdx = 0;
		int numDistinct = 0;
		int[] arrNum = new int[100001];
		int[] arr = new int[n];
		for(int i=0; i < n; i++) {
			int temp = scanner.nextInt();
			arr[i] = temp;
			if (arrNum[arr[i]] == 0) {
				numDistinct += 1;
			}
			arrNum[arr[i]] +=1;

			// find out exactly distinct k numbers
			if (numDistinct == k) {
				lastIdx = i;
				break;
			}
		}
		// find out the best firstIdx
		if (lastIdx != 0) {
			while (firstIdx < lastIdx) {
				if (arrNum[arr[firstIdx]] == 1) {
					return (firstIdx + 1) + " " + (lastIdx + 1);
				} else {
					arrNum[arr[firstIdx]] -= 1;
					firstIdx++;
				}
			}
		}

		return "-1 -1";
	}

	public static void main(String[] args) {
		System.out.println(findSatisfyingSegment());
	}
}