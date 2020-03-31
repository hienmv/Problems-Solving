/** https://codeforces.com/contest/1328/problem/D
 * #implementation
 */
/**
1 2 3 3 2 3 1 2
1 2 1 1 2 1 2 3 
1 2 1 2 1 2 1 2

k = 3

k = 1, 2, 3

n even: k = 1, 2
  k = 1 ? arr[i] = arr[i+1]
  other k = 2 ?
    => 1, 2, 1, 2...

n odd : K = 1, 2, 3

      => Find 1 pair (i, i+1) that has same type. => n even.
      
  k = 1 ? arr[i] = arr[i+1]
  other:
    fill: k = 2
        1, 2, 1, 2 .. => OK? => return:
        else:
          K = 3
          fill: 1 2 1 2 ... 3  (fixed 0-th:1, n-1 th:3)
 */
import java.util.Scanner;
public class Carousel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        for (int t = 0; t < q; t++) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            int[] result = new int[n];
            int k = 1;
            result[0] = k;
            for (int i = 1; i < n; i++) {
                result[i] = k;
                if (arr[i] != arr[i - 1]) {
                    k = 2;
                    for(int j = i; j < n; j++) {
                        if (n % 2 != 0 && arr[j] == arr[j - 1]) {
                            result[j] = result[j - 1];
                        }
                        else {
                            result[j] = 3 - result[j - 1];
                        }
                    }
                    if (arr[n - 1] != arr[0] && result[n - 1] == result[0]) {
                        result[n - 1] = 3;
                        k = 3;
                    }
                    break;
                }
            }
            System.out.println(k);
            for(int i = 0; i < n; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
        }
    }
}