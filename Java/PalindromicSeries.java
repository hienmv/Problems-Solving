/** #number-theory */
import java.util.Scanner;

class PalindromicSeries {
  public static boolean isPalindromicSeries(int n) {
    char[] digitsArr = String.valueOf(n).toCharArray();
    int digits = digitsArr.length;
    int digitsSum = 0;
    for (int i = 0; i < digits; i++) {
      digitsSum += digitsArr[i] - '0';
    }
    // check
    for (int left = 0, right = digitsSum - 1; left < right; left++, right--) {
      if (digitsArr[left % digits] != digitsArr[right % digits]) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testcases = sc.nextInt();
    int n;
    boolean ret;
    for (int t = 0; t < testcases; t++) {
      n = sc.nextInt();
      ret = isPalindromicSeries(n);
      System.out.println(ret ? "YES" : "NO");
    }
  }
}
