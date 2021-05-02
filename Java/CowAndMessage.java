/**
 * http://codeforces.com/problemset/problem/1307/C
 * #dynamic-programming #math
 
len(1 cap so cong) > 2: -> 1 day cap so cong khac chua 2 phan tu ban dau.
abc > nhieu nhat
  => ab > nhieu nhat.
  
  do dai = 2 => so cach chon toi da n2
  do dai = 1 => so cach chon toi da n.
    => try and error.
 */
import java.util.Scanner;
import java.util.HashMap;
public class CowAndMessage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        HashMap<Integer, Long> map = new HashMap<>();
        // otherway use long dp[26][26] instead of map.
        long[] distincArr = new long[26];
        long max = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            for (int j = 0; j < distincArr.length; j++)
            {
                if (distincArr[j] > 0) {
                    int k = j * 31 + c;
                    if (map.containsKey(k)) {
                        long v = distincArr[j] + map.get(k);
                        map.replace(k, v);
                        if (max < v) {
                            max = v;
                        }
                    }
                    else {
                        map.put(k, distincArr[j]);
                    }
                }
            }
            distincArr[c] += 1;
            
        }
        for (int i = 0; i < distincArr.length; i++) {
            if (max < distincArr[i]) {
                max = distincArr[i];
            }
        }
        System.out.println(max);
    }
}