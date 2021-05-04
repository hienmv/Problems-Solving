/**
 * #kmp a b c a b c d e a b c a b c 0 0 0 1 2 3 0 0 1 2 3 4 5 6
 *
 * <p>a b c a b c -> kmp = 2 < 3 -> no -> no a b c a b c 0 0 0 1 2 3 a b c -> kmp = 4 >= 3 => yes.
 */
/* refactor
case 1:
while (!p.isEmpty()) {
  int value = kmpSearch(s, p, prefix);
  if (value >= 3) {
    System.out.println(p);
    return;
  }

  p = p.substring(0, prefix[p.length() - 1]);
}

case 2:
while (!p.isEmpty()) {
  int len = p.lengh(); // 0 => break
  for (int i=0; i < s.length() - 1; i++) {
      if ( prefix[i] == prefix[s.length() - 1])
      {
        // print;
        return;
      }
  }
  p = p.substring(0, prefix[p.length() - 1]);
}

case 3:
  int len = p.lengh(); // 0 => break
  for (int i=0; i < s.length() - 1; i++) {
      if ( prefix[i] == prefix[s.length() - 1])
      {
        // print;
        return;
      }
  }
  p = p.substring(0, prefix[p.length() - 1]);
  if (!p.isEmpty()) {
    // print;
    return;
  }

*/
import java.util.Scanner;

class Password {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s = sc.nextLine();

    // calculate prefix
    int[] prefix = new int[s.length()];
    calculatePrefix(s, prefix);
    //
    String p = s.substring(0, prefix[s.length() - 1]);
    while (!p.isEmpty()) {
      int value = kmpSearch(s, p, prefix);
      if (value >= 3) {
        System.out.println(p);
        return;
      }

      p = p.substring(0, prefix[p.length() - 1]);
    }

    System.out.println("Just a legend");
  }

  public static int kmpSearch(String s, String p, int[] prefix) {
    int n = s.length();
    int m = p.length();
    int i = 0, j = 0;
    int result = 0;
    while (i < n) {
      if (s.charAt(i) == p.charAt(j)) {
        i++;
        j++;
      }
      if (j == m) {
        result++;
        j = prefix[j - 1];
      } else if (i < n && s.charAt(i) != p.charAt(j)) {
        if (j != 0) {
          j = prefix[j - 1];
        } else {
          i++;
        }
      }
    }
    return result;
  }

  public static void calculatePrefix(String p, int[] prefix) {
    prefix[0] = 0;
    int j = 0, i = 1;
    while (i < p.length()) {
      if (p.charAt(i) == p.charAt(j)) {
        j++;
        prefix[i] = j;
        i++;
      } else {
        if (j != 0) {
          j = prefix[j - 1];
        } else {
          prefix[i] = 0;
          i++;
        }
      }
    }
  }
}
