/**
 * https://codeforces.com/problemset/problem/71/C
 * #implementation
 */
import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
public class RoundTableKnights {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> goodMood = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int v = sc.nextInt();
            if (v == 1) {
                goodMood.add(i);
            }
        }
        // corner case
        if (goodMood.size() < 3) {
            System.out.println("NO");
            return;
        }

        HashSet<Integer> set = new HashSet<>();
        goodMood.forEach(x -> set.add(x));
        int maxEdgeLen = n / 3;
        for (int edgeLen = 1; edgeLen <= maxEdgeLen; edgeLen++) {
            if (n % edgeLen != 0) continue;

            int edgeNum = n / edgeLen;
            for (int i = 1; i <= edgeLen; i++) {
                int cnt = 0;
                if (goodMood.size() < i) break;
                for (int point = goodMood.get(i - 1); set.contains(point); point += edgeLen) {
                    cnt++;
                }
                if (cnt == edgeNum) {
                    System.out.println("YES");
                    return;
                }
            }
        }
        System.out.println("NO");
    }
}