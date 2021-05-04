/*
https://codeforces.com/problemset/problem/868/C
#implementation
#bit-manipulation
/*
=> xay dung trang thai truoc
  => tim .....
1100
0100
0010
0001
=> select 3 => optimize => select 2 only.


Min(2^k, n).

subset(n)

  1 cot = 0

  k = 1
    0110 -> 6 => cnt[6]

    X Y

    cnt[X] > 0 && cnt[Y] > 0


  cnt[s] số bài chung là tập team trạng thái s cùng giải được

  - cnt[s] += 1 với s là trạng thái các team giải được problem i
    example: 1 0 1 => s = 5 (1*2^0 + 0 * 2^1 + 1*2^2)
  - ton tai 1 dong = 0: -> YES, (cnt[0] > 0)
  - tìm 2 tập con X, Y của K teams (X, Y không có phần chung) -> X & Y = 0
    for X : 0 .. 2^K-1
      for Y: X+1 .. 2^K-1
        Cond: X & Y = 0
        cnt[X] > 0 && cnt[Y] > 0 ==> YES
*/

import java.util.Scanner;

public class QualificationRounds {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();
    int size = (int) Math.pow(2, k);
    // optimize true/false
    int[] count = new int[size];
    for (int i = 0; i < n; i++) {
      int idx = 0;
      int val = 0;
      for (int j = 0; j < k; j++) {
        val = sc.nextInt();
        // j is integer. Math.pow is slow
        // idx += val * (1 << j); or use 2 * *
        idx += val * (int) Math.pow(2, j);
      }
      count[idx] += 1;
    }
    // corner case
    if (count[0] > 0) {
      System.out.println("YES");
      return;
    }

    // normal case
    for (int x = 0; x < size; x++) {
      for (int y = x + 1; y < size; y++) {
        if ((x & y) == 0) { // 2 tap X, Y phan biet
          if (count[x] > 0 && count[y] > 0) {
            System.out.println("YES");
            return;
          }
        }
      }
    }
    System.out.println("NO");
  }
}
