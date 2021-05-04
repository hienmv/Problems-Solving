/** https://www.spoj.com/problems/MMASS/ #ad-hoc-1 #stack */
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
// Use int instead of String
// because in stack : only positve value , so "(" => negative value.
// => performance.

class MMass {
  static String getVal(Character c) {
    if (c == 'C') return "12";
    if (c == 'O') return "16";
    if (c == 'H') return "1";

    return "0";
  }

  static int calculate(String str) {
    Deque<String> stack = new LinkedList<String>();
    for (Character c : str.toCharArray()) {
      if (c == '(') {
        stack.addFirst(c.toString());
      } else if (c == ')') {
        int temp = 0;
        while (!stack.isEmpty() && !stack.peekFirst().equals("(")) {
          temp += Integer.parseInt(stack.pollFirst());
        }
        if (!stack.isEmpty() && stack.peekFirst().equals("(")) {
          stack.pollFirst();
        }
        stack.addFirst(String.valueOf(temp));
      } else if (c >= '2' && c <= '9') {
        int temp = Integer.parseInt(stack.pollFirst()) * Integer.parseInt(c.toString());
        stack.addFirst(String.valueOf(temp));
      } else {
        stack.addFirst(getVal(c));
      }
    }
    int sum = 0;
    while (!stack.isEmpty()) {
      sum += Integer.parseInt(stack.pollFirst());
    }
    if (sum > 10000) {
      return 10000;
    }
    return sum;
  }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();
    System.out.println(calculate(str));
  }
}
