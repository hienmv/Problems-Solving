/** https://codeforces.com/problemset/problem/26/B #greedy #stack */
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class RegularBracketSequence {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String str = sc.next();
    Deque<Character> stack = new LinkedList<>();
    int count = 0;
    for (char c : str.toCharArray()) {
      if (c == '(') {
        stack.addLast(c);
      } else {
        if (!stack.isEmpty()) {
          count += 2;
          stack.pollLast();
        }
      }
    }
    System.out.println(count);
  }
}
