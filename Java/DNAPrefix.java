/** http://lightoj.com/volume_showproblem.php?problem=1224 #trie */
import java.util.Scanner;

class DNAPrefix {
  static final int MAX = 4;

  static int getIndex(char c) {
    int idx = 0;
    switch (c) {
      case 'A':
        idx = 0;
        break;
      case 'C':
        idx = 1;
        break;
      case 'G':
        idx = 2;
        break;
      case 'T':
        idx = 3;
        break;
    }

    return idx;
  }

  static class TrieNode {
    TrieNode[] children;
    int countCommonPrefix;

    TrieNode() {
      children = new TrieNode[MAX];
      countCommonPrefix = 0;
    }
  }

  public static int addDNA(TrieNode root, String dna) {
    TrieNode pTmp = root;
    int length = dna.length();
    int idx, ans = 0;

    for (int level = 0; level < length; level++) {
      idx = getIndex(dna.charAt(level));
      if (pTmp.children[idx] == null) {
        pTmp.children[idx] = new TrieNode();
      }
      pTmp = pTmp.children[idx];
      pTmp.countCommonPrefix += 1;
      ans = Math.max(ans, pTmp.countCommonPrefix * (level + 1));
    }

    return ans;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testcases = sc.nextInt();
    for (int t = 1; t < testcases + 1; t++) {
      int n = sc.nextInt();
      TrieNode root = new TrieNode();
      int ans = 0;
      String dna;
      for (int i = 0; i < n; i++) {
        dna = sc.next();
        ans = Math.max(ans, addDNA(root, dna));
      }
      System.out.println("Case " + t + ": " + ans);
    }
  }
}
