/* https://codeforces.com/problemset/problem/1107/C
   idea: two pointer
*/
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.ArrayList;

public class Brutality {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] unitsArr = new int[n];
        
        for (int i=0; i < n; i++) {
            int tmp = sc.nextInt();
            unitsArr[i] = tmp;
        }

        String str = sc.next();
        ArrayList<ArrayList<Integer> > vt = new ArrayList<>();
        for (int i=0; i < 'z' - 'a'+ 1; i++) {
            vt.add(new ArrayList<>());
        }
        char[] strChar = str.toCharArray();
        for (int i=0; i < strChar.length; i++) {
            vt.get(strChar[i] - 'a').add(i);
        }

        long sum = 0;
        for(ArrayList<Integer> vtc : vt) {
            int pre_idx = -10;
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            for (int idx = 0; idx < vtc.size(); idx++) {
                if (vtc.get(idx) != pre_idx+1) {
                    int count = 0;
                    while (!pq.isEmpty() && count < k) {
                        sum += pq.poll();
                        count++;
                    }
                    pre_idx = vtc.get(idx);
                    pq = new PriorityQueue<>(Collections.reverseOrder());
                    pq.add(unitsArr[vtc.get(idx)]);
                    
                } else {
                    pre_idx = vtc.get(idx);
                    pq.add(unitsArr[vtc.get(idx)]);
                }
            }
            int count = 0;
            while (!pq.isEmpty() && count < k) {
                sum += pq.poll();
                count++;
            }
        }
        System.out.println(sum);
    }

}