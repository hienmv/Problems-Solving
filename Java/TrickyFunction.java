/** https://codeforces.com/problemset/problem/429/D #geometry #divide-and-conquer */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class TrickyFunction {
  public static long distance(Node n1, Node n2) {
    int v1 = n1.idx - n2.idx;
    int v2 = n1.val - n2.val;
    return power2(v1) + power2(v2);
  }

  public static long power2(int n) {
    return (long) n * n;
  }

  public static long minMidFunction(Node[] nodeArr, int left, int mid, int right, long minVal) {
    Node midNode = nodeArr[mid];
    ArrayList<Node> splittedNodeArr = new ArrayList<>();
    for (int i = left; i < right; i++) {
      if (power2(nodeArr[i].idx - midNode.idx) <= minVal) {
        splittedNodeArr.add(nodeArr[i]);
      }
    }

    // sort by val
    Collections.sort(splittedNodeArr);

    long minValue = Long.MAX_VALUE;
    int l = splittedNodeArr.size();
    for (int i = 0; i < l; i++) {
      for (int j = i + 1;
          j < l && power2(splittedNodeArr.get(j).val - splittedNodeArr.get(i).val) < minVal;
          j++) {
        long d = distance(splittedNodeArr.get(i), splittedNodeArr.get(j));
        minValue = Math.min(minValue, d);
      }
    }

    return minValue;
  }

  public static long bruteForce(Node[] nodeArr, int left, int right) {
    long min_dist = Long.MAX_VALUE;
    for (int i = left; i < right; i++) {
      for (int j = i + 1; j < right; j++) {
        min_dist = Math.min(min_dist, distance(nodeArr[i], nodeArr[j]));
      }
    }
    return min_dist;
  }

  public static long minSubArrayFunction(Node[] nodeArr, int left, int right) {
    if (right - left <= 3) {
      return bruteForce(nodeArr, left, right);
    }
    int mid = (left + right) / 2;
    long minFunctionLeft = minSubArrayFunction(nodeArr, left, mid);
    long minFunctionRight = minSubArrayFunction(nodeArr, mid + 1, right);
    long minFunction = Math.min(minFunctionLeft, minFunctionRight);

    return Math.min(minFunction, minMidFunction(nodeArr, left, mid, right, minFunction));
  }

  public static void main(String[] args) {
    // input
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int sz = n + 1;
    int preVal = 0;
    int cur = 0;
    Node[] nodeArr = new Node[sz];
    nodeArr[0] = new Node(-1, -1);
    for (int i = 1; i < sz; i++) {
      cur = sc.nextInt();
      nodeArr[i] = new Node(i, preVal + cur);
      preVal += cur;
    }

    // calculate
    long minFuction = minSubArrayFunction(nodeArr, 1, sz);
    System.out.println(minFuction);
  }
}

class Node implements Comparable<Node> {
  int idx;
  int val;

  Node(int idx, int val) {
    this.idx = idx;
    this.val = val;
  }

  public int compareTo(Node other) {
    return this.val - other.val;
  }
}
