/** https://codeforces.com/problemset/problem/493/C
 *  #binary-search #brute-force #sorting #two-pointer
 * 
 * other:
 *  /* method 1
        (1) 
        xet arrA[i], i->n
        A: 2 2 2
        B: 1 3 1 1

        A[0] - 1
        d : 4
        6 : 8
        for i : n
            idxA = upperBound(arrA, arrA[i])
            idxB = upperBound(arrB, arrA[i])

            optimize(idxA * 2 + (n - idxA) * 3, idxB * 2 + (m - idxB) * 3)
        (2)
        xet arrB[i], i->m;

    method 2
        d -> n * 3, m * 3 
        d -> m * 2, n*2
        compare -> val
        for i : n
                    idxA = upperBound(arrA, arrA[i])
                    idxB = upperBound(arrB, arrA[i])

                    optimize(idxA * 2 + (n - idxA) * 3, idxB * 2 + (m - idxB) * 3) with val

    method 3
         A: 1 1 1
         B: 1 3 1 1

            không có 3đ
        d : 

        A[0] - 1
        d : 4
        6 : 8
        giả sử từ i đến n là 3đ, tới i: giả sử các lần ném từ 0 .. (i-1) là 2đ, sau đó là 3đ
        for i : n
            idxA = upperBound(arrA, arrA[i] - 1)
            idxB = upperBound(arrB, arrA[i] - 1)

            optimize(idxA * 2 + (n - idxA) * 3, idxB * 2 + (m - idxB) * 3)
 */

import java.util.Scanner;
import java.util.Arrays;

public class VasyaBasketball {

    public static int upperBound(int[] arr, int val) {
        int ret = arr.length;
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] >= val) {
                ret = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arrA = new int[n];
        int tmp;
        for (int i=0; i < n; i++) {
            tmp = sc.nextInt();
            arrA[i] = tmp;
        }
        Arrays.sort(arrA);

        int m = sc.nextInt();
        int[] arrB = new int[m];
        for (int i=0; i < m; i++) {
            tmp = sc.nextInt();
            arrB[i] = tmp;
        }
        Arrays.sort(arrB);

        int valA = n * 3;
        int valB = m * 3;
        int val = Integer.MIN_VALUE;
        int idxA = 0, idxB = 0;
        int a = 0, b = 0;
        int mid = 0;
        while (idxA < n || idxB < m) {
            if (idxA < n) {
                if (mid < arrA[idxA]) {
                    mid = arrA[idxA];
                } else {
                    mid++;
                    idxA = upperBound(arrA, mid); 
                }
            } else {
                if (mid < arrB[idxB]) {
                    mid = arrB[idxB];
                } else {
                    mid++;
                }
            }
            idxB = upperBound(arrB, mid);
           
            if ((valA - idxA) - (valB - idxB) > val) {
                val = (valA - idxA) - (valB - idxB);
                a = valA - idxA;
                b = valB - idxB;
            }
            if (idxA < n) {
                idxA++;
            }
        }
        
        System.out.println(a + ":" + b);
    }
}