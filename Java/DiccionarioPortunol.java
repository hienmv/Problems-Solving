/**
 * https://icpcarchive.ecs.baylor.edu/index.php?option=onlinejudge&page=show_problem&problem=3803
 * #trie #todo
 */
public class DiccionarioPortunol {
  static final int MAX = 'z' - 'a' + 1;

  static class TrieNode {
    TrieNode[] children;
    int countWord;

    TrieNode() {
      children = new TrieNode[MAX];
      countWord = 0;
    }
  }
}
