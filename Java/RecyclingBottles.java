/** http://codeforces.com/problemset/problem/671/A
 * #dynamic-programming
Step 1 :
A/B -> X (unvisited)-> T

Step 2 :
T -> X -> T

dist[i] = distance(i, T)
cost = sum(2 * dist[i])
cost = sum(2 * dist[i]) + (Ax - dist[x]) + (By - dist[y])

for x find min(Ax - dist[x])
for y find min(By - dist[y])

if x != y:
    => result
else:
    // Ax
    for y2 find min(By2 - dist[y2]) && x != y2
    // By
    for x2 find min(Ax2 - dist[x2]) && x2 != y

Java tricky:
    Object => timsort
    primative => quicksort (some case O(n2))
*/
import java.util.Scanner;
import java.util.Arrays;
public class RecyclingBottles {
    private static double cost(Point a, Point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }
    private static class Point {
        long x, y;
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    private static class Tuple implements Comparable<Tuple> {
        int idx;
        double cost;
        public Tuple(int idx, double cost) {
            this.idx = idx;
            this.cost = cost;
        }
        public int compareTo(Tuple other) {
            if (this.cost < other.cost) {
                return -1;
            }
            else if (this.cost > other.cost) {
                return 1;
            }
            return 0;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Point a = new Point(sc.nextLong(), sc.nextLong());
        Point b = new Point(sc.nextLong(), sc.nextLong());
        Point t = new Point(sc.nextLong(), sc.nextLong());
        int n = sc.nextInt();
        Point[] list = new Point[n];
        double[] costs = new double[n];
        double sumCost = 0;
        Tuple[] da = new Tuple[n];
        Tuple[] db = new Tuple[n];
        for (int i = 0; i < n; i++) {
            long x = sc.nextLong();
            long y = sc.nextLong();
            list[i] = new Point(x, y);
            costs[i] = cost(t, list[i]);
            da[i] = new Tuple(i, cost(a, list[i]) - costs[i]);
            db[i] = new Tuple(i, cost(b, list[i]) - costs[i]);
            sumCost += 2 * costs[i];
        }

        Arrays.sort(da);
        Arrays.sort(db);

        // min A->X->T
        double minA = da[0].cost;
        int minAIdx = da[0].idx;
        double secondMinA = (da.length > 1) ? da[1].cost : Double.MAX_VALUE;

        // min B->X->T
        double minB = db[0].cost;
        int minBIdx = db[0].idx;
        double secondMinB = (db.length > 1) ? db[1].cost : Double.MAX_VALUE;
       
        double result = 0;
        if (minAIdx != minBIdx) {
            result = sumCost + minA + minB;
        }
        else {
            // fixed Ax
            double cost1 = sumCost + minA + secondMinB;

            // fixed By
            double cost2 = sumCost + secondMinA + minB;

            result = Math.min(cost1, cost2);
        }

        System.out.printf("%.7f", result);
    }
}