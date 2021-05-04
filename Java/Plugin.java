/** https://codeforces.com/problemset/problem/81/A #deque #implementation */
import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

public class Plugin {

  public static void main(String[] args) {
    BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in)); // faster than scanner.
    // Scanner sc = new Scanner(System.in);
    // String str = sc.nextLine();
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    try {
      String str = br.readLine();
      Deque<Character> queue = new LinkedList<>();
      for (char c : str.toCharArray()) {
        if (!queue.isEmpty()) {
          if (queue.peekLast() == c) {
            queue.pollLast();
          } else {
            queue.addLast(c);
          }
        } else {
          queue.addLast(c);
        }
      }
      while (!queue.isEmpty()) {
        // System.out.print(queue.pollFirst());
        pw.print(queue.pollFirst); // faster than System.out.print
      }
    } catch (IOException e) {

    }
  }
}
