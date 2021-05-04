/** https://codeforces.com/problemset/problem/471/B #sorting */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class MUHImportantThings {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
    int key;
    int sameDifficultycount = 0;
    for (int i = 1; i < n + 1; i++) {
      key = sc.nextInt();
      if (!hashMap.containsKey(key)) {
        hashMap.put(key, new ArrayList<Integer>());
      } else {
        sameDifficultycount++;
      }
      hashMap.get(key).add(i);
    }

    if (sameDifficultycount <= 1) {
      System.out.println("NO");
    } else {
      System.out.println("YES");
      int selectedKey = -1;
      int selectedPos = -1;
      for (int i = 0; i < 3; i++) {
        for (int keyMap : hashMap.keySet()) {
          if (hashMap.get(keyMap).size() > 1) {
            if (selectedKey == keyMap) {
              if (selectedPos >= hashMap.get(keyMap).size() - 1) {
                selectedPos = 0;
                selectedKey = -1;
                for (int val : hashMap.get(keyMap)) {
                  System.out.print(val + " ");
                }
              } else {
                selectedPos++;
                System.out.print(hashMap.get(keyMap).get(selectedPos) + " ");
                for (int k = 0; k < hashMap.get(keyMap).size(); k++) {
                  if (k != selectedPos) {
                    System.out.print(hashMap.get(keyMap).get(k) + " ");
                  }
                }
              }
            } else {
              if (selectedKey == -1) {
                selectedKey = keyMap;
                selectedPos++;
                System.out.print(hashMap.get(keyMap).get(selectedPos) + " ");
                for (int k = 0; k < hashMap.get(keyMap).size(); k++) {
                  if (k != selectedPos) {
                    System.out.print(hashMap.get(keyMap).get(k) + " ");
                  }
                }
              } else {
                for (int val : hashMap.get(keyMap)) {
                  System.out.print(val + " ");
                }
              }
            }
          } else {
            System.out.print(hashMap.get(keyMap).get(0) + " ");
          }
        }
        System.out.println();
      }
    }
  }
}
