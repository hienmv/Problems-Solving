/*  https://codeforces.com/contest/673/problem/A
    tag: #implementation
    get the last minute that Bear Limark can watch,
    and compare with the next interesting minute.
*/

import java.util.Scanner;

public class BearAndGame {
  static int watchingTvMininues() {
    Scanner obj = new Scanner(System.in);
    int number = obj.nextInt();
    int lastMinuteCanWatchTv = 15;
    for (int i = 0; i < number; i++) {
      int nextInterestingMinute = obj.nextInt();
      if (nextInterestingMinute > lastMinuteCanWatchTv) {
        break;
      }
      lastMinuteCanWatchTv = nextInterestingMinute + 15;
    }
    // the game lasts 90 minutes
    if (lastMinuteCanWatchTv > 90) {
      return 90;
    }
    return lastMinuteCanWatchTv;
  }

  public static void main(String argrs[]) {
    System.out.println(watchingTvMininues());
  }
}
