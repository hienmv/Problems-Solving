/** https://codeforces.com/problemset/problem/268/B
 * tag: #implementation #math
 *  n, calculate worse-case from n->1
 *  n = 3; res = 3 + (2 + (2-1)*1) + (1 + (1-1)*2)
 *  n = 4; res = 4 + (3 + (3-1)*1) + (2 + (2-1)*2) + (1 + (1-1)*3)
 */

 import java.util.Scanner;
 public class Buttons {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = 0;
        for (int i = n; i > 0; i--) {
            result += i + (n-i) * (i-1);
        }
        System.out.println(result);
    }
 }

 /**
    Better way (Mathematic)
    Tim so lan sai, trong 4 so:
    n = 4, toi da sai 3 lan, so lan bam: 1.
    n = 3, toi da sai 2 lan, so lan bam: 2. 
    n = 2, toi da sai 1 lan, so lan bam: 3.
    n = 1, toi da sai 0 lan, so lan bam: 4. 
    .....


    (n - i) * i
    n * i - i^2

    k = k(k+1)(2k+1)/6

    n(1 + 2 + .. n-1)
    [3 * n * n *(n-1) - (n-1) * n * (2n - 1)] / 6
    [n * (n - 1) * (3n - (2n - 1))] / 6

    n * (n - 1) * (n + 1) / 6

  */