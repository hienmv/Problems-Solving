/**
It's time polar bears Menshykov and Uslada from the zoo of St. Petersburg and elephant Horace from the zoo of Kiev got down to business. In total, there are 
N tasks for the day and each animal should do each of these tasks. For each task, they have evaluated its difficulty. Also animals decided to do the tasks in order of their difficulty. Unfortunately, some tasks can have the same difficulty, so the order in which one can perform the tasks may vary.
Menshykov, Uslada and Horace ask you to deal with this nuisance and come up with individual plans for each of them. The plan is a sequence describing the order in which an animal should do all the 
N tasks. Besides, each of them wants to have its own unique plan. Therefore three plans must form three different sequences. You are to find the required plans, or otherwise deliver the sad news to them by stating that it is impossible to come up with three distinct plans for the given tasks.
Input
The first line contains integer N(1≤N≤2000) — the number of tasks. The second line contains 
N integers h1,h2,...,hN (1≤ hi​​ ≤2000), where hi​​ is the difficulty of the i​th​​ task. The larger number hi is, the more difficult the 
i​th task is.

Output
In the first line print "YES" (without the quotes), if it is possible to come up with three distinct plans of doing the tasks. Otherwise print in the first line 
"NO" (without the quotes). If three desired plans do exist, print in the second line N distinct integers that represent the numbers of the tasks in the order they are done according to the first plan. In the third and fourth line print two remaining plans in the same form.
If there are multiple possible answers, you can print any of them.

idea: Greedy
*/

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

class MUHImportantThings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        int key;
        int sameDifficultycount = 0;
        for (int i=1; i < n+1; i++) {
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
            for (int i=0; i < 3; i++) {
                for (int keyMap : hashMap.keySet()) {
                    if (hashMap.get(keyMap).size() > 1) {
                        if (selectedKey == keyMap) {
                            if (selectedPos >= hashMap.get(keyMap).size() - 1) {
                                selectedPos = 0;
                                selectedKey = -1;
                                for (int val: hashMap.get(keyMap)) {
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
                                for (int val: hashMap.get(keyMap)) {
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