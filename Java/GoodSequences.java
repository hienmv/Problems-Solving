/** https://codeforces.com/problemset/problem/264/B
 * #dp
 * refactor => get all dividable prime of a number ->
 * map:[prime:max divided number]
 * 
 * number: 
 *   => primes
 *   for prime in primes:
 *      => count[number] = max(count[number], count[map.get(prime)] + 1)
 *      update map[prime] = number
 * result = max(result, count[number])
 * 
*/
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
public class GoodSequences {
    private static ArrayList<Integer> getDiviablePrimes(int n) {
        ArrayList<Integer> primes = new ArrayList<>();
        if (n % 2 == 0) {
            primes.add(2);
            while (n % 2 == 0) {
                n /= 2;
            }
        }
        int max = (int)Math.sqrt(n);
        for (int i = 3; i <= max; i += 2) {
            if (n % i == 0) {
                primes.add(i);
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        if (n > 2) {
            primes.add(n);
        }
        return primes;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int ret = 1;
        int[] count = new int[100001];
        // [prime: max_divided_number]
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int v = arr[i];
            count[v] = 1;

            // get all dividable primes
            ArrayList<Integer> dividablePrimes = getDiviablePrimes(v);
            for (int prime : dividablePrimes) {
                if (map.containsKey(prime)) {
                    int val = count[map.get(prime)] + 1;
                    count[v] = Math.max(count[v], val);
                    map.replace(prime, v);
                }
                else {
                    map.put(prime, v);
                }
            }
            ret = Math.max(ret, count[v]);
        }
        System.out.println(ret);
    }
}