/**
 * #dynamic-programming #lis
 */

import java.util.Scanner;
import java.util.ArrayList;
class TestingTheCATCHER {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = 1;
        int first, next;
        while (sc.hasNextInt()) {
            first = sc.nextInt();
            if (first == -1) {
                break;
            }
            if (testcase > 1) {
                System.out.println();
            }
            ArrayList<Integer> list = new ArrayList<>();
            list.add(first);
            while(sc.hasNextInt()) {
                next = sc.nextInt();
                if (next == -1) {
                    break;
                }
                list.add(next);
            }
            int result = LIS(list);
            System.out.println("Test #" + (testcase++) + ":");
            System.out.println("  maximum possible interceptions: " + result);
        }
    }

    public static int LIS(ArrayList<Integer> list) {
        int len = 1;
        ArrayList<Integer> result = new ArrayList<>();
        result.add(0);
        for (int i=1; i < list.size(); i++) {
            if(list.get(i) > list.get(result.get(0))) {
                result.set(0, i);
            }
            else if (list.get(i) <= list.get(result.get(len - 1))) {
                result.add(i);
                len++;
            }
            else {
                int pos = lowerBound(list, result, len, list.get(i));
                result.set(pos, i);
            }
        }
        return len;
    }
    public static int lowerBound(ArrayList<Integer> list, ArrayList<Integer> sub, int n, int v) {
        int left = 0, right = n;
        int pos = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int idx = sub.get(mid);
            if (list.get(idx) < v) {
                pos = mid;
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return pos; 
    }
}