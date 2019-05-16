/** My custom fast input
 */

import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

class MyScanner {
    BufferedReader br;
    StringTokenizer st;

    public MyScanner(InputStream isr) {
        br = new BufferedReader(new InputStreamReader(isr));
    }
    public String next() {
        while(st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return st.nextToken();
    }
    public int nextInt(){
        return Integer.parseInt(next());   
    }
    public long nextLong() {
        return Long.parseLong(next());
    }
    public double nextDouble() {
        return Double.parseDouble(next());
    }


}