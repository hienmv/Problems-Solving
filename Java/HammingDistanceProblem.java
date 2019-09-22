/**
 * idea: BackTracking
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class HammingDistanceProblem {
    public static void findStringHammingDistance(ArrayList<Integer> result, int curVal, int curIdx, int oneNum, int maxLen) {
        // sucess
        if (oneNum == 0) {
            result.add(curVal);
            return;
        }
        
        // failed
        if (curIdx >= maxLen) {
            return;
        }

        // try
        for (int i=curIdx; i < maxLen; i++) {
            curVal ^= (1 << i);
            findStringHammingDistance(result, curVal, i + 1, oneNum - 1, maxLen);
            curVal ^= (1 << i);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        for (int t=0; t < testcases; t++) {
            int n = sc.nextInt();
            int h = sc.nextInt();
            ArrayList<Integer> result = new ArrayList<>();
            findStringHammingDistance(result, 0, 0, h, n);
            Collections.sort(result);
            // print result
            String format = "%" + n + "s";
            result.forEach(x -> System.out.println(String.format(format, Integer.toBinaryString(x)).replace(' ', '0')));
        }
    }
}