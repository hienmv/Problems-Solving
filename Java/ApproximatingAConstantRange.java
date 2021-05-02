/*	https://codeforces.com/problemset/problem/602/B
 *	tag: #dynamic-programming #two-pointer
	check i-th element to update the max, min of [first index, i]
	if (max - min > 1), update first index, max, min
*/
import java.util.Scanner;

public class ApproximatingAConstantRange {
	
	static int callApproximatingAConstantRange() {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] arr = new int[n];
		arr[0] = scanner.nextInt();
		int maxVal = arr[0];
		int minVal = arr[0];
		int firstIdx = 0;
		int maxLen = 1;
		int count = 1;

		for (int i = 1; i < n; i++) {
			int temp = scanner.nextInt();
			arr[i] = temp;
			if (arr[i] < minVal) {
				minVal = arr[i];
			}
			if (arr[i] > maxVal) {
				maxVal = arr[i];
			}
			if (maxVal - minVal <= 1) {
				count += 1;
				if (count > maxLen) {
					maxLen = count;
				}
			} else {
				boolean equalMinValFlg = false;
				for (int j = firstIdx; j < i; j++ ){
					if (arr[j] == minVal) {
						equalMinValFlg = true;
						firstIdx = j + 1;
					} else if (arr[j] == maxVal) {
						firstIdx = j + 1;
					}
				}
				if (equalMinValFlg) {
					minVal += 1;
				} else {
					maxVal -= 1;
				}
				count = i - firstIdx + 1;
			}
		}
		return maxLen;
	}

	public static void main(String[] args) {
		System.out.println(callApproximatingAConstantRange());
	}
}