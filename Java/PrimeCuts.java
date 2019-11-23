/**
 * #number-theory
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class PrimeCuts {
    public static int max = 1000;
    public static ArrayList<Integer> sieveOfEratosthenes(int limit) {
        boolean[] mark = new boolean[limit+1];
        ArrayList<Integer> primes = new ArrayList<>();
        Arrays.fill(mark, true);
        mark[0] = false;
        for (int i=2; i * i <= limit; i++) {
            if (mark[i]) {
                for(int j=i*i; j <= limit; j+=i) {
                    mark[j] = false;
                }
            }
        }

        for (int i=1; i <= limit; i++) {
            if (mark[i]) {
                primes.add(i);
            }
        }

        return primes;
    }
    public static void segmentedSive(int limit, int c, ArrayList<Integer> primes) {
        int idx = Collections.binarySearch(primes, limit);
        int maxSize = (idx < 0) ? (-1 *(idx + 1)) : (idx+1);
        int count, left=0, right=0;
        if (maxSize % 2 == 1) {
            count = 2*c - 1;
            right = Math.min(maxSize, maxSize / 2 + count / 2 + 1);
            left = Math.max(0, maxSize / 2 - count / 2);
        } else {
            count = 2*c;
            right = Math.min(maxSize, maxSize / 2 + count / 2);
            left = Math.max(0, maxSize / 2 - count / 2);
        }
        System.out.print(limit + " " + c + ":");
        for (int i= left; i < right; i++) {
            System.out.print(" " + primes.get(i));
        }
        System.out.println();
    }
    public static void main(String[] args) {
        ArrayList<Integer> primes = sieveOfEratosthenes(max);
        Scanner sc = new Scanner(System.in);
        int n, c;
        int count = 0;
        while (sc.hasNext()) {
            if (count > 0) {
                System.out.println();
            }
            count++;

            n = sc.nextInt();
            c = sc.nextInt();

            segmentedSive(n, c, primes);
        }

    }
}