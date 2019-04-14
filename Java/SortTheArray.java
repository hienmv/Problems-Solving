/*	https://codeforces.com/contest/451/problem/B
	idea: sort the original array (A) to having the sorted array (B)
	find out the segment in A that differ the corresponding segment in B.
	and revert the segment,	compare reverted segment with the corresponding segment in B.
*/
	
import java.util.Scanner;
import java.util.Arrays;

public class SortTheArray {

	static void canSortArrayWithRevertingOneSegment() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] orgArr = new int[n];
		int[] sortedArr = new int[n];

		for(int i=0; i < n; i++) {
			int temp = sc.nextInt();
			orgArr[i] = temp;
			sortedArr[i] = temp;
		} 
		Arrays.sort(sortedArr);

		int l = 0;
		int r = 0;
		boolean startFlg = true;
		for (int i=0; i < n ; i++){
			if(orgArr[i] != sortedArr[i]){
				if (startFlg) {
					l = i;
					startFlg = false;
				} else {
					r = i;
				}
			}
		}

		String resultSegment = "1 1";
		if (l != r) {
			for(int i = l, j = r; i <= r; i++, j--) {
				if(orgArr[i] != sortedArr[j]){
					System.out.println("no");
					return;
				}
			}
			resultSegment = (l+1) + " " + (r+1);
		}
		
		System.out.println("yes");
		System.out.println(resultSegment);
	}

	public static void main(String[] args) {
		canSortArrayWithRevertingOneSegment();	
	}
}