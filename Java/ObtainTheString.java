/**
 * https://codeforces.com/problemset/problem/1295/C
    store positions of a character in s.
    find the most substring of t that can be created from s.
    ...
    count
*/
import java.util.Scanner;
import java.util.ArrayList;
public class ObtainTheString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int testcase = 0; testcase < cases; testcase++) {
            String s = sc.next();
            String t = sc.next();
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            for (int i = 0; i < 'z' - 'a' + 1; i++) {
                list.add(new ArrayList<Integer>());
            }
            for (int i = 0; i < s.length(); i++) {
                list.get(s.charAt(i) - 'a').add(i);
            }
/*
            int ret = 0;
            for(int i = 0; i < t.length(); i++) {
                int idx = t.charAt(i) - 'a';
                int cur = upperBound(list.get(idx), 0);
                if (cur == -1) {
                    ret = -1;
                    break;
                }
                // update result
                ret++;
                // skip
                while(i < t.length() - 1) {
                    i++;
                    idx = t.charAt(i) - 'a';
                    cur = upperBound(list.get(idx), cur + 1);
                    if (cur == -1) {
                        i--;
                        break;
                    }
                }
            }
*/
            // refactor
            int ret = 1;
            int cur = -1;
            int i = 0;
            while (i < t.length()) {
                int idx = t.charAt(i) - 'a';
                int next_pos = upperBound(list.get(idx), cur + 1);
                if (next_pos == -1) {
                    if (cur == -1) {
                        ret = -1;
                        break;
                    }
                    ret++;
                }
                else {
                    i++;
                }
                cur = next_pos;
            }
            System.out.println(ret);
        }
    }
    private static int upperBound(ArrayList<Integer> list, int val) {
        int ret = -1;
        int l = 0;
        int r = list.size() - 1;
        if (r < 0) return ret;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if (val <= list.get(mid)) {
                ret = list.get(mid);
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return ret;
    }
}