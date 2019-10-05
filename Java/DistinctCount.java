/** https://www.hackerearth.com/practice/data-structures/trees/binary-search-tree/practice-problems/algorithm/distinct-count/
 *  #binary-search-tree
 */
import java.util.Scanner;
import java.util.TreeSet;

public class DistinctCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        int x, n, tmp;
        for(int t=0; t < tc; t++) {
            n = sc.nextInt();
            x = sc.nextInt();
            TreeSet<Integer> ts = new TreeSet<>();
            for(int i=0; i < n; i++) {
                tmp = sc.nextInt();
                ts.add(tmp);
            }
            if (x == ts.size()) {
                System.out.println("Good");
            } else if ( x < ts.size()) {
                System.out.println("Average");
            } else {
                System.out.println("Bad");
            }
        }
    }
}