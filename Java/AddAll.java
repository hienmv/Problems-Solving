/**
 * https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1895 tag:
 * #heap #priority-queue
 */
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class AddAll {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ArrayList<Long> resultArr = new ArrayList<>();
    while (true) {
      int n = sc.nextInt();
      if (n == 0) {
        break;
      }
      PriorityQueue<Long> pq = new PriorityQueue<>(n);
      for (int i = 0; i < n; i++) {
        int tmp = sc.nextInt();
        pq.add((long) tmp);
      }
      long result = 0;
      while (pq.size() > 1) {
        long tmp = pq.poll() + pq.poll();
        result += tmp;
        pq.add(tmp);
      }
      resultArr.add(result);
    }
    for (long i : resultArr) {
      System.out.println(i);
    }
  }
}
