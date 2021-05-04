/*  https://www.spoj.com/problems/ONP/
    #stack
*/
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

class TransformExpression {

  static void transform(String str) {
    Deque<Character> stack = new LinkedList<Character>();
    StringBuilder result = new StringBuilder();
    for (char c : str.toCharArray()) {
      if (c < 97) { // "a"
        if (c != 41) { // ")"
          stack.addFirst(c);
        } else {
          while (!stack.isEmpty() && stack.peekFirst() != 40) { // "("
            result.append(stack.removeFirst());
          }
          if (!stack.isEmpty() && stack.peekFirst() == 40) { // "("
            stack.removeFirst();
          }
        }
      } else {
        result.append(c);
      }
    }
    System.out.println(result);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    String[] arr = new String[n];
    for (int i = 0; i < n; i++) {
      String str = sc.next();
      arr[i] = str;
    }
    for (String str : arr) {
      transform(str);
    }
  }
}
