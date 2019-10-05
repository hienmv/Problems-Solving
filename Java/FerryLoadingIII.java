/** https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1842
 *  #queue #todo
 */

import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

class FerryLoadingIII {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        for (int i=0; i < c; i++) {
            int n = sc.nextInt();
            int t = sc.nextInt();
            int m = sc.nextInt();
            Deque<Integer>[] leftQueue = new LinkedList[2];
            for (int j=0; j < m; j++) {
                int temp = sc.nextInt();
                String pos = sc.next();
                if (pos.equals("left")) {
                    leftQueue.addLast(temp);
                } else {
                    rightQueue.addLast(temp);
                }
            }
            
            int curTime = 0;
            boolean leftFlg = false;
            ArrayList<Integer> result = new ArrayList<>();
            int leftVal = leftQueue.isEmpty() ? Integer.MAX_VALUE : leftQueue.peekFirst();
            int rightVal = rightQueue.isEmpty() ? Integer.MAX_VALUE : rightQueue.peekFirst();

            while (!leftQueue.isEmpty() || !rightQueue.isEmpty()) {
                int temp = startTime;

                while (!leftQueue.isEmpty() && leftQueue.pollFirst() <= startTime) {
                    if (leftFlg) {
                        temp += t;
                        leftFlg = false;
                    } else {
                        temp += 2*t;
                        leftFlg = true;
                    }
                    result.add(temp);
                }
                if (startTime < temp ){
                    startTime = temp;
                }
                temp = startTime;
                while (!rightQueue.isEmpty() && rightQueue.pollFirst() <= startTime) {
                    if (leftFlg) {
                        temp += t;
                        leftFlg = false;
                    } else {
                        temp += 2*t;
                        leftFlg = true;
                    }
                    result.add(temp);
                }
                if (startTime < temp ){
                    startTime = temp;
                }
            }
            
            System.out.println(result);
        }
    }
}


/**
 * /*
2
n  t  m
2 10 10
0 left              10
10 left             30
20 left             30
30 left             50
40 left             50
50 left             70
60 left             70
70 left             90
80 left             90
90 left             110

2 10 3
10 right            30
25 left             40
40 left             60

0 left

0 -> 10 left -> right

*/

/** https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1842
 *  use queue
 */

import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Arrays;

class Pair {
    Integer id, time;

    public Pair(int _id, int _time) {
        this.id = _id;
        this.time = _time;
    }
}

class FerryLoadingIII {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        for (int i=0; i < c; i++) {
            int n = sc.nextInt();
            int t = sc.nextInt();
            int m = sc.nextInt();
            Deque<Pair> queue[] = new LinkedList[2];
            for (int j = 0; j < 2; j++)
                queue[j] = new LinkedList<>();
            for (int j=0; j < m; j++) {
                int temp = sc.nextInt();
                String pos = sc.next();
                if (pos.equals("left")) {
                    queue[0].addLast(new Pair(j, temp));
                } else {
                    queue[1].addLast(new Pair(j, temp));
                }
            }
            
            int curTime = 0;
            int curSide = 0;
            int nextTime;
            Integer result[] = new Integer[m];

            while (!queue[0].isEmpty() || !queue[1].isEmpty()) {
                // System.out.println(queue[0].isEmpty() + " " + queue[1].isEmpty());
                int leftTime = queue[0].isEmpty() ? Integer.MAX_VALUE : queue[0].peekFirst().time;
                int rightTime = queue[1].isEmpty() ? Integer.MAX_VALUE : queue[1].peekFirst().time;
                nextTime = Math.min(leftTime, rightTime);
                if (curTime < nextTime) {
                    curTime = nextTime;
                }
                int countCar = 0;
                while (!queue[curSide].isEmpty() && queue[curSide].peekFirst().time <= curTime && countCar < n) {
                    result[queue[curSide].pollFirst().id] = curTime + t;
                    countCar++;
                }
                curTime += t;
                curSide = 1 - curSide;
            }

            
            System.out.println(Arrays.toString(result));
        }
    }
}
 * 
 * 
 * 
 * 
 */