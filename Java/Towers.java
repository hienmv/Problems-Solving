/*	https://codeforces.com/contest/37/problem/A
#sorting #map
	two way (use map / plain array)
*/
import java.util.HashMap;
import java.util.Scanner;

public class Towers {

  static String calHeightAndLength() {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    HashMap<Integer, Integer> map = new HashMap<>();
    int height = 1;
    int length = 0;
    for (int i = 0; i < n; i++) {
      int temp = sc.nextInt();
      if (!map.containsKey(temp)) {
        length += 1;
        map.put(temp, 1);
      } else {
        int val = map.get(temp);
        if (val + 1 > height) {
          height = val + 1;
        }
        map.replace(temp, val + 1);
      }
    }
    return height + " " + length;
  }

  public static void main(String[] args) {
    System.out.println(calHeightAndLength());
  }
}
