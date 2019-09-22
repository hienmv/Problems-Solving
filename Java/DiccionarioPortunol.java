/** https://icpcarchive.ecs.baylor.edu/index.php?option=onlinejudge&page=show_problem&problem=3803
 *  idea: TRIE
 */
//TODO
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