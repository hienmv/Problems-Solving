/** https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=614
 * idea: use stack
 */
import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;

class ParenthesesBalance {
    static Character validPairChar(Character c) {
        if (c == ']') {
            return '[';
        } else if (c == ')') {
            return '(';
        } else {
            return c;
        }
    }
    static String check(String str) {
        Deque<Character> stack = new LinkedList<>();
        for (Character c: str.toCharArray()) {
            if (c == '(' || c == '[') {
                stack.addFirst(c);
            } else {
                if (stack.isEmpty() || stack.peekFirst() != validPairChar(c)) {
                    return "No";
                } else {
                    stack.pollFirst();
                }
            }
        }
        if (!stack.isEmpty()) {
            return "No";
        }
        return "Yes";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //sc.nextLine();
        
        String[] arr = new String[n];
        for (int i=0; i < n; i++) {
            String str = sc.next(); //sc.nextLine();
            arr[i] = str;
        }
        for (String str: arr) {
            System.out.println(check(str));
        }

    }
}