/**
 * #implementation
 *
 * <p>1 2 3 4
 *
 * <p>1 -> 4 (4,1) 2 -> 4 (4,1,2) 3 -> 4 (4,1,2,3)
 *
 * <p>4 (1,2,3,4)
 *
 * <p>4 -> 1 (1,2,3,4) 4 -> 2 (1,2,3,4) 4 -> 3 (1,2,3,4)
 *
 * <p>=>> 6. (2 * 4 - 2) messages sent.
 *
 * <p>1 => 0 message sent.
 */
import java.util.Scanner;

class MessageSpreading {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testcase = sc.nextInt();
    for (int t = 0; t < testcase; t++) {
      int n = sc.nextInt();
      System.out.println(2 * (n - 1));
    }
  }
}
