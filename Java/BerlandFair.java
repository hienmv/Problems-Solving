/** https://codeforces.com/problemset/problem/1073/D
 *  tag: #binary-search #greedy
 *  // t = 100
    // n = 1, 
    // a[] = [1, 2, 3, 4, 5, 6, 7, 8, 9 10]
      
    0 ... n-1

    T'
    sum previous buy turn
        T' = T mod sum
            sum ~= T/2 + 1
            T mod sum ~= T - (T / 2 + 1) ~= T/2 - 1 
        T' < T/2
    
    log2(T)*N
    
 */
 
import java.util.Scanner;
import java.util.Arrays;
 
 
public class BerlandFair {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long t = sc.nextLong();
        int[] arr = new int[n];
        int tmp;
        for (int i=0; i < n; i++) {
            tmp = sc.nextInt();
            arr[i] = tmp;
        }
        long sum, count;
        long maxCount = 0;
        while (t > 0) {
            sum = 0;
            count = 0;
            for (int i=0; i < n; i++) {
                if (t >= arr[i]) {
                    sum += arr[i];
                    t -= arr[i];
                    count++;
                }
            } 
            if (sum == 0) {
                break;
            }
            maxCount += count + (t / sum) * count;
            t = t % sum;
        } 
        System.out.println(maxCount);

    }
}