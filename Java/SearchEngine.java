/**
 * https://www.hackerearth.com/practice/data-structures/advanced-data-structures/trie-keyword-tree/practice-problems/algorithm/search-engine/
 * #string #trie
 */
import java.util.Scanner;

class SearchEngine {
  static final int MAX = 26;

  static class Node {
    Node[] children;
    int countLeaf;
    int maxWeight;

    Node() {
      countLeaf = 0;
      maxWeight = 0;
      children = new Node[MAX];
    }
  }

  public static void addWord(Node root, String word, int weight) {
    int level;
    int length = word.length();
    int idx;

    Node tmp = root;
    for (level = 0; level < length; level++) {
      idx = word.charAt(level) - 'a';
      if (tmp.children[idx] == null) {
        tmp.children[idx] = new Node();
      }
      tmp = tmp.children[idx];
      tmp.maxWeight = Math.max(tmp.maxWeight, weight);
    }
    tmp.countLeaf = weight;
  }

  public static int findWord(Node root, String word) {
    int level;
    int length = word.length();
    int idx;

    Node tmp = root;
    for (level = 0; level < length; level++) {
      idx = word.charAt(level) - 'a';
      if (tmp.children[idx] == null) {
        return -1;
      }
      tmp = tmp.children[idx];
    }

    if (tmp == null) {
      return -1;
    }
    return tmp.maxWeight;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int q = sc.nextInt();

    Node root = new Node();

    String word;
    int weight;
    for (int i = 0; i < n; i++) {
      word = sc.next();
      weight = sc.nextInt();
      addWord(root, word, weight);
    }

    for (int i = 0; i < q; i++) {
      word = sc.next();
      System.out.println(findWord(root, word));
    }
  }
}
