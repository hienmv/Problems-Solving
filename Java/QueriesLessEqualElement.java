/** https://codeforces.com/problemset/problem/600/B
 *  idea: sort the first array, use binarySearch each element of second array.
 */

import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class QueriesLessEqualElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Integer[] firstArr = new Integer[n];
        int[] result = new int[m];
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i=0; i < n; i++) {
            int tmp = sc.nextInt();
            firstArr[i] = tmp;
        }
        Arrays.sort(firstArr, Collections.reverseOrder());
        for (int i=0; i < n; i++){
            int tmp = firstArr[i];
            if (!hashMap.containsKey(tmp)) {
                hashMap.put(tmp, i);
            }
        }
        for (int i=0; i < m; i++) {
            int tmp = sc.nextInt();
            int r = Arrays.binarySearch(firstArr, tmp, Collections.reverseOrder());
            if (r < 0){
                result[i] = n + r + 1;
            } else {
                result[i] = n - hashMap.get(tmp);
            }
        }
        for (int r: result) {
            System.out.print(r + " ");
        }
    }
}