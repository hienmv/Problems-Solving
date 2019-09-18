/**
 * idea: divide and conquer //doing
 */

import java.util.Scanner;

class PaintingFence {
    static int MAX_STROKES = 5001;
    static int bruteForce(int[] arr, int left, int right) {
        int minStrokes = MAX_STROKES;
        int minHeight = Integer.MAX_VALUE;
        for (int i=left; i < right; i++) {
            minHeight = Math.min(minHeight, arr[i]);
        }

    }
    static int getMinimumStrokes(int[] arr, int left, int right) {
        if (right - left <= 2) {

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int maxStrokes = n;
        int numStrokes = getMinimumStrokes(arr, 0, n);
        System.out.println(Math.min(maxStrokes, numStrokes));
    }
}