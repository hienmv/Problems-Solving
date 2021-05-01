/**
 * https://codingcompetitions.withgoogle.com/codejam/round/000000000019fd27/000000000020993c
 */
import java.util.Scanner;
class Vestigium {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = sc.nextInt();
            int sum = n * (n + 1) / 2;
            int[] rows = new int[n];
            int[] cols = new int[n];
            int trace = 0;
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    int v = sc.nextInt();
                    rows[r] += v;
                    cols[c] += v;
                    if (r == c) {
                        trace += v;
                    }
                }
            }
            
            int r = 0, c = 0;
            for (int j = 0; j < n; j++) {
                if (rows[j] != sum) r++;
                if (cols[j] != sum) c++;
            }
            
            System.out.println("Case #" + i + ": " + trace + " " + r + " " + c);
        }
    }
}