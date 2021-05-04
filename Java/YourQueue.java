/**
 * https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=3359 #heap
 * #queue note: not all elements shown
 */
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

class YourQueue {

  static String calculate(int p, int c, ArrayList<String> arr) {
    Deque<Integer> queue = new LinkedList<>();
    for (int i = 1; i < Math.min(c, p) + 1; i++) {
      queue.addLast(i);
    }
    StringBuilder result = new StringBuilder();
    for (int idx = 0; idx < c; idx++) {
      if (arr.get(idx).equals("N")) {
        result.append(queue.peekFirst() + "\n");
        queue.addLast(queue.pollFirst());
      } else {
        Scanner sc = new Scanner(arr.get(idx));
        String s = sc.next();
        int mark = sc.nextInt();
        sc.close();

        Deque<Integer> helpQ = new LinkedList<>();
        while (!queue.isEmpty() && queue.peekLast() != mark) {
          helpQ.addLast(queue.pollLast());
        }
        queue.pollLast();
        while (!helpQ.isEmpty()) {
          queue.addLast(helpQ.pollLast());
        }
        queue.addFirst(mark);
      }
    }
    return result.toString();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int idx = 1;
    StringBuilder result = new StringBuilder();
    while (true) {
      int p = sc.nextInt();
      int c = sc.nextInt();
      if (p == 0 && c == 0) {
        break;
      }
      sc.nextLine();

      ArrayList<String> arr = new ArrayList<>();
      for (int i = 0; i < c; i++) {
        String temp = sc.nextLine();
        arr.add(temp);
      }
      result.append("Case " + idx + ":" + "\n");
      idx++;
      result.append(calculate(p, c, arr));
    }
    sc.close();
    System.out.println(result);
  }
}
