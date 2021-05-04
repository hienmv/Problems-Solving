/*	https://codeforces.com/contest/381/problem/A
#greedy #implementation #two-pointer
	have two iterator: fist, last
	compare the elements at first-index and last-index.
	note: the last step when n - odd number / even number.
*/

import java.util.Scanner;

public class SerejaAndDima {

  static String calFinalPoints() {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] arrCard = new int[n];
    for (int i = 0; i < n; i++) {
      int card = scanner.nextInt();
      arrCard[i] = card;
    }
    scanner.close();

    int firstIdx = 0;
    int lastIdx = n - 1;
    int sumSereja = 0;
    int sumDima = 0;
    int chooseIdx = 0;
    boolean turnFlg = false; // false: turn of Sereja, true: turn of Dima.
    while (firstIdx < lastIdx) {
      if (arrCard[firstIdx] > arrCard[lastIdx]) {
        chooseIdx = firstIdx;
        firstIdx += 1;
      } else {
        chooseIdx = lastIdx;
        lastIdx -= 1;
      }

      if (turnFlg) {
        sumDima += arrCard[chooseIdx];
      } else {
        sumSereja += arrCard[chooseIdx];
      }
      turnFlg = !turnFlg;
    }
    // last step
    if (firstIdx >= lastIdx) {
      if (turnFlg) {
        sumDima += arrCard[firstIdx];
      } else {
        sumSereja += arrCard[firstIdx];
      }
    }

    return sumSereja + " " + sumDima;
  }

  public static void main(String[] args) {
    System.out.println(calFinalPoints());
  }
}
