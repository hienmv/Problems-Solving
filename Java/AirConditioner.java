/**
 * https://codeforces.com/problemset/problem/1304/C
 * #implementation
 * #two-pointer
 * find the range can change: 
    ownRange.l -= gap;
    ownRange.r += gap;

    => find common segment between ownRange and target range.
 */
import java.util.Scanner;
public class AirConditioner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        for (int test = 0; test < q; test++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            Range ownRange = new Range(m, m);
            int preMinute = 0;
            int t, l, r;
            boolean possible = true;
            int gap = 0;
            for (int i = 0; i < n; i++) {
                t = sc.nextInt();
                l = sc.nextInt();
                r = sc.nextInt();
                
                if (!possible) continue;
                
                gap = t - preMinute;
                if (gap != 0) {
                    preMinute = t;
                    ownRange.l -= gap;
                    ownRange.r += gap;
                }

                // find comment segment between ownRange and [l, r];
                if (ownRange.l > r || ownRange.r < l) {
                    possible = false;
                }
                else if (ownRange.l <= l && ownRange.r >= r) {
                    ownRange.l = l;
                    ownRange.r = r;
                }
                else if (l < ownRange.l && ownRange.r < r) {
                    continue;
                }
                else {
                    ownRange.l = Math.max(ownRange.l, l);
                    ownRange.r = Math.min(ownRange.r, r);
                }

            }

            System.out.println(possible ? "YES" : "NO");
        }
    }
}
class Range {
    int l, r; 
    Range(int l, int r) {
        this.l = l;
        this.r = r;
    }
}