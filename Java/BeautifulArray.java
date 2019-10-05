/** https://codeforces.com/problemset/problem/1155/D
idea: divide and conquer -> TODO: DP

//
        L, R MAX
        
        
        L1     R1           L2 R2L3 R3L4 R4
        negative                 negative (L3 R3) -> but (L3 R3) is better than (L1 R1).
        */

import java.util.Scanner;
import java.util.function.*;

public class BeautifulArray {
    
    public static Node getMaxMinMidSubArr(long[] arr, int left, int mid, int right, BiPredicate<Long, Long> compare) {
        // initialize
        long maxmin_sub_left = compare.test(2l,1l) ? Long.MIN_VALUE : Long.MAX_VALUE;
        long maxmin_sub_right = maxmin_sub_left;
        
        int leftIdx = 0;
        long sum_sub = 0l;
        for (int i=mid; i >= left; i--) {
            sum_sub += arr[i];
            if (compare.test(sum_sub, maxmin_sub_left)) {
                leftIdx = i;
                maxmin_sub_left = sum_sub;
            }
        }

        int rightIdx = 0;
        sum_sub = 0l;
        for (int i=mid+1; i <= right; i++) {
            sum_sub += arr[i];
            if (compare.test(sum_sub, maxmin_sub_right)) {
                rightIdx = i;
                maxmin_sub_right = sum_sub;
            }
        }

        long val = (leftIdx == rightIdx) ? maxmin_sub_left : (maxmin_sub_left + maxmin_sub_right);
        return new Node(leftIdx, rightIdx, val);
    }

    public static Node getMaxMinSubArr(long[] arr, int left, int right, BiPredicate<Long, Long> compare) {
        if (left == right) {
            return new Node(left, right, (long) arr[right]);
        }

        int mid = (left + right) / 2;
        Node resultLeft  = getMaxMinSubArr(arr, left, mid, compare);
        Node resultRight = getMaxMinSubArr(arr, mid+1, right, compare);
        Node resultMid   = getMaxMinMidSubArr(arr, left, mid, right, compare);
        
        if (compare.test(resultRight.val, resultLeft.val)) {
            return compare.test(resultMid.val, resultRight.val) ? resultMid : resultRight;
        } else {
            return compare.test(resultMid.val, resultLeft.val) ? resultMid : resultLeft;
        }
    }
    public static void main(String[] args) {
        // input
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        long[] arr = new long[n];
        for (int i=0; i < n; i++) {
            arr[i] = sc.nextLong();
        }

        // calculate
        BiPredicate<Long, Long> greater = (l1, l2) -> (l1 > l2);
        BiPredicate<Long, Long> lower   = (l1, l2) -> (l1 < l2);
        Node result;
        if (x < 0) {
            result = getMaxMinSubArr(arr, 0, n-1, lower);
            if (result.val < 0) {
                for (int i=result.left; i <= result.right; i++) {
                    arr[i] *= x;
                }
            }
            result = getMaxMinSubArr(arr, 0, n-1, greater);
        } else { // x >= 0
            result = getMaxMinSubArr(arr, 0, n-1, greater);
            if (x > 0 && result.val > 0) {
                for (int i=result.left; i <= result.right; i++) {
                    arr[i] *= x;
                }
                result = getMaxMinSubArr(arr, 0, n-1, greater);
            } 
        }
        result.val = Math.max(result.val, 0);

        // result
        System.out.println(result.val);
    }
}

class Node {
    int left, right;
    long val;
    Node(int left, int right, long val) {
        this.left = left;
        this.right = right;
        this.val = val;
    }
}