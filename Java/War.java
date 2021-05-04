/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1099
 * #dsu /* (u, v) c = 1, setFriends - conflict: u >><< v --> areEnemies(u, v) == true - ok: - find
 * up, vp - union(up, vp) + parent[up] = vp - union(enemy[up], enemy[vp]) + eup = enemy[up] + evp =
 * enemy[vp] + parent[eup] = evp + enemy[vp] = evp + enemy[evp] = vp c = 2, setEnemies - conflict: u
 * ~~~~ v --> areFriends(u, v) == true - ok: - find up, vp - eup = enemy[up] - evp = enemy[vp]
 *
 * <p>- union up - evp - union vp - eup - update enemy c = 3, areFriends - find up, vp c = 4,
 * areEnemies - find up, vp - check enemy[vp] == up
 *
 * <p>enemyArr: enemyArr[u] là phần tử đại diện cho tập enemy của tập u (enemyArr[up] - vp)
 */
/**
 * other way: two version (1->n) (n+1->2n) is Friend ? connect is enemy ? can not connect
 *
 * <p>=>> in the same version -> friends =>> not in the same version -> enemies
 *
 * <p>(u, v) friend: (u - v) friend (u + n, v + n) friend
 *
 * <p>(u, v) enemy: (u, v + n) friend (u + n, v) friend
 *
 * <p>A - C enemy: (A, C+n) (A+n, C)
 *
 * <p>B - D nemey: (B, D+n) (B+n, D)
 *
 * <p>A - B friend (A, B) -> (A, B, C+n, D+n) (A+n,B+n) -> (A+n, B+n, C, D)
 */
import java.util.Scanner;

class War {

  public static void makeSet(int[] parent, int[] enemyArr) {
    int sz = parent.length;
    for (int i = 0; i < sz; i++) {
      parent[i] = i;
      enemyArr[i] = i;
    }
  }

  public static int findSet(int u, int[] parent) {
    if (u != parent[u]) {
      parent[u] = findSet(parent[u], parent);
    }
    return parent[u];
  }

  public static void unionEnemySet(int u, int v, int[] parent, int[] enemyArr) {
    int up = findSet(u, parent);
    int vp = findSet(v, parent);
    int enemy_up = enemyArr[up];
    int enemy_vp = enemyArr[vp];

    if (enemy_up == up) {
      enemyArr[up] = vp;
    }
    if (enemy_vp == vp) {
      enemyArr[vp] = up;
    }
    if (enemyArr[up] == vp && enemyArr[vp] == up) {
      return;
    }
    if (enemyArr[up] != vp) {
      unionSet(enemyArr[up], vp, parent, enemyArr);
    }
    if (enemyArr[vp] != up) {
      unionSet(up, enemyArr[vp], parent, enemyArr);
    }
  }

  public static void unionSet(int u, int v, int[] parent, int[] enemyArr) {
    int up = findSet(u, parent);
    int vp = findSet(v, parent);
    int enemy_up = enemyArr[up];
    int enemy_vp = enemyArr[vp];

    if (up == vp) {
      return;
    }

    parent[vp] = up;
    if (enemy_up != up && enemy_vp != vp && enemy_up != enemy_vp) {
      parent[enemy_vp] = enemy_up;
      enemyArr[up] = enemy_up;
      enemyArr[enemy_up] = up;
    } else if (enemy_up != up) {
      enemyArr[up] = enemy_up;
      enemyArr[enemy_up] = up;
    } else if (enemy_vp != vp) {
      enemyArr[up] = enemy_vp;
      enemyArr[enemy_vp] = up;
    }
  }

  public static boolean areFriend(int x, int y, int[] parent) {
    boolean ans = false;
    int xp = findSet(x, parent);
    int yp = findSet(y, parent);
    if (xp == yp) {
      ans = true;
    }
    return ans;
  }

  public static boolean areEnemy(int x, int y, int[] parent, int[] enemyArr) {
    boolean ans = false;
    int xp = findSet(x, parent);
    int yp = findSet(y, parent);
    if (xp == enemyArr[yp] || yp == enemyArr[xp]) {
      ans = true;
    }
    return ans;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] parent = new int[n];
    int[] enemyArr = new int[n];
    makeSet(parent, enemyArr);

    int c, x, y;
    boolean ans;
    while (true) {
      c = sc.nextInt();
      x = sc.nextInt();
      y = sc.nextInt();
      if (c == 0 && x == 0 && y == 0) break;

      if (c == 1) { // add friend
        if (areEnemy(x, y, parent, enemyArr)) {
          System.out.println(-1);
          continue;
        }
        unionSet(x, y, parent, enemyArr);
      } else if (c == 2) { // add enemy
        if (areFriend(x, y, parent)) {
          System.out.println(-1);
          continue;
        }
        unionEnemySet(x, y, parent, enemyArr);
      } else if (c == 3) { // check friends
        ans = areFriend(x, y, parent);
        if (ans) {
          System.out.println(1);
        } else {
          System.out.println(0);
        }
      } else if (c == 4) { // check enemy
        ans = areEnemy(x, y, parent, enemyArr);
        if (ans) {
          System.out.println(1);
        } else {
          System.out.println(0);
        }
      }
    }
  }
}
