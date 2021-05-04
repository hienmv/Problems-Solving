/**
 * https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=2498
 * #dsu
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class VirtualFriends {

  public static int getIndex(
      HashMap<String, Integer> hashFriendIndex,
      ArrayList<String> peopleArr,
      String person,
      ArrayList<Integer> parentArr,
      ArrayList<Integer> sizeArr) {

    if (!hashFriendIndex.containsKey(person)) {
      peopleArr.add(person);
      int idx = peopleArr.size() - 1;
      hashFriendIndex.put(person, idx);
      parentArr.add(idx);
      sizeArr.add(1);
    }
    return hashFriendIndex.get(person);
  }

  public static int findSet(int u, ArrayList<Integer> parentArr) {
    if (parentArr.get(u) != u) {
      int p = findSet(parentArr.get(u), parentArr);
      parentArr.set(u, p);
    }
    return parentArr.get(u);
  }

  public static int unionSet(
      ArrayList<Integer> parentArr, ArrayList<Integer> sizeArr, int u, int v) {
    int up = findSet(u, parentArr);
    int vp = findSet(v, parentArr);
    if (up == vp) {
      return sizeArr.get(up);
    }
    if (sizeArr.get(up) > sizeArr.get(vp)) {
      parentArr.set(vp, up);
      sizeArr.set(up, sizeArr.get(up) + sizeArr.get(vp));
      return sizeArr.get(up);
    } else if (sizeArr.get(up) < sizeArr.get(vp)) {
      parentArr.set(up, vp);
      sizeArr.set(vp, sizeArr.get(vp) + sizeArr.get(up));
      return sizeArr.get(vp);
    } else {
      parentArr.set(up, vp);
      sizeArr.set(vp, sizeArr.get(vp) + sizeArr.get(up));
      return sizeArr.get(vp);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testCases = sc.nextInt();
    for (int t = 0; t < testCases; t++) {
      int friendships = sc.nextInt();
      String person1, person2;
      HashMap<String, Integer> hashFriendIndex = new HashMap<>();
      ArrayList<String> peopleArr = new ArrayList<>();
      ArrayList<Integer> parentArr = new ArrayList<>();
      ArrayList<Integer> sizeArr = new ArrayList<>();
      int idx1, idx2, ans;

      for (int f = 0; f < friendships; f++) {
        person1 = sc.next();
        person2 = sc.next();
        idx1 = getIndex(hashFriendIndex, peopleArr, person1, parentArr, sizeArr);
        idx2 = getIndex(hashFriendIndex, peopleArr, person2, parentArr, sizeArr);
        System.out.println(unionSet(parentArr, sizeArr, idx1, idx2));
      }
    }
  }
}
