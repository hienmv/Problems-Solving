/** #segment-tree */
import java.util.Scanner;

class Brackets {
  public static double log2(int number) {
    return Math.log(number) / Math.log(2);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testcase = 1;
    while (sc.hasNext()) {
      int n = sc.nextInt();
      String s = sc.next();
      char[] arr = s.toCharArray();
      // height of segment Tree
      int h = (int) Math.ceil(log2(n));
      // max size of segment tree
      int sizeTree = 2 * (int) Math.pow(2, h) - 1;
      Node[] segmentTree = new Node[sizeTree];
      // build segment Tree
      buildTree(arr, segmentTree, 0, n - 1, 0);

      int m = sc.nextInt();
      System.out.println("Test " + (testcase++) + ":");
      for (int i = 0; i < m; i++) {
        int c = sc.nextInt();
        if (c == 0) { // check
          if (n % 2 == 0
              && segmentTree[0].numCloseBrackets == 0
              && segmentTree[0].numOpenBrackets == 0) {
            System.out.println("YES");
          } else {
            System.out.println("NO");
          }
        } else {
          // update brakets expression.
          updateQuery(arr, segmentTree, 0, n - 1, 0, c - 1);
        }
      }
    }
  }

  private static void buildTree(char[] arr, Node[] segmentTree, int left, int right, int idx) {
    if (left == right) {
      segmentTree[idx] = new Node(0, 0);
      if (arr[left] == '(') {
        segmentTree[idx].numOpenBrackets = 1;
      } else {
        segmentTree[idx].numCloseBrackets = 1;
      }
      return;
    }
    int mid = left + (right - left) / 2;
    buildTree(arr, segmentTree, left, mid, 2 * idx + 1);
    buildTree(arr, segmentTree, mid + 1, right, 2 * idx + 2);

    int newMatch =
        Math.min(
            segmentTree[2 * idx + 1].numOpenBrackets, segmentTree[2 * idx + 2].numCloseBrackets);
    int numOpenBrackets =
        segmentTree[2 * idx + 2].numOpenBrackets
            + segmentTree[2 * idx + 1].numOpenBrackets
            - newMatch;
    int numCloseBrackets =
        segmentTree[2 * idx + 2].numCloseBrackets
            + segmentTree[2 * idx + 1].numCloseBrackets
            - newMatch;
    segmentTree[idx] = new Node(numOpenBrackets, numCloseBrackets);
  }

  private static void updateQuery(
      char[] arr, Node[] segmentTree, int left, int right, int idx, int pos) {
    if (pos < left || pos > right) {
      return;
    }
    if (left == right) {
      if (arr[pos] == '(') {
        segmentTree[idx].numOpenBrackets = Math.max(0, segmentTree[idx].numOpenBrackets - 1);
        segmentTree[idx].numCloseBrackets++;
        arr[pos] = ')';
      } else { // ')'
        segmentTree[idx].numOpenBrackets++;
        segmentTree[idx].numCloseBrackets = Math.max(0, segmentTree[idx].numCloseBrackets - 1);
        arr[pos] = '(';
      }
      return;
    }
    int mid = left + (right - left) / 2;
    if (pos <= mid) {
      updateQuery(arr, segmentTree, left, mid, 2 * idx + 1, pos);
    } else { // pos > mid
      updateQuery(arr, segmentTree, mid + 1, right, 2 * idx + 2, pos);
    }

    int newMatch =
        Math.min(
            segmentTree[2 * idx + 1].numOpenBrackets, segmentTree[2 * idx + 2].numCloseBrackets);
    segmentTree[idx].numOpenBrackets =
        segmentTree[2 * idx + 2].numOpenBrackets
            + segmentTree[2 * idx + 1].numOpenBrackets
            - newMatch;
    segmentTree[idx].numCloseBrackets =
        segmentTree[2 * idx + 2].numCloseBrackets
            + segmentTree[2 * idx + 1].numCloseBrackets
            - newMatch;
  }
}

class Node {
  // number of opening brackets that is not matching
  int numOpenBrackets;
  // number of closing brackets that is not matching
  int numCloseBrackets;

  public Node(int numOpenBrackets, int numCloseBrackets) {
    this.numOpenBrackets = numOpenBrackets;
    this.numCloseBrackets = numCloseBrackets;
  }
}
