/** https://www.hackerearth.com/practice/data-structures/trees/binary-search-tree/practice-problems/algorithm/monk-and-his-friends/description/
 *  #binary-search-tree #map
 */
import java.util.TreeSet;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.OutputStream;

public class MonkAndFriends {

    public static void main(String[] args) throws IOException {
        MyScanner sc = new MyScanner(System.in);
        OutputStream out = new BufferedOutputStream(System.out);
        int t = sc.nextInt();
        int n, m; 
        long tmp;
        for (int k=0; k < t; k++) {
            n = sc.nextInt();
            m = sc.nextInt();
            TreeSet<Long> ts = new TreeSet<>();
            for(int i=0; i < n; i++) {
                tmp = sc.nextLong();
                ts.add(tmp);
            }
            for(int i=0; i < m; i++) {
                tmp = sc.nextLong();
                if (ts.contains(tmp)) {
                    out.write("YES \n".getBytes());
                } else {
                    ts.add(tmp);
                    out.write("NO \n".getBytes());
                }
            }
            out.flush();
        }
    }
}

class MyScanner {
    BufferedReader br;
    StringTokenizer st;

    public MyScanner(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
    }
    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
    int nextInt() {
        return Integer.parseInt(next());
    }
    long nextLong() {
        return Long.parseLong(next());
    }
    double nextDouble() {
        return Double.parseDouble(next());
    }
    String nextLine() {
        String str = "";
        try {
                str = br.readLine();
        } catch (IOException e) {
                e.printStackTrace();
        }
        return str;
    }
}