/** https://www.hackerrank.com/challenges/no-prefix-set/problem
 *  idea : TRIE
 */

import java.util.Scanner;

public class NoPrefixSet {
    static final int MAX = 'j' - 'a' + 1;
    static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;
        TrieNode() {
            children = new TrieNode[MAX];
            isEndOfWord = false;
        }
    }

    public static boolean addWord(TrieNode root, String word) {
        TrieNode pTmp = root;
        int length = word.length();
        int idx, level;
        boolean addNewNodeFlg = false;
        boolean ret = true;
        for (level=0; level < length; level++ ) {
            idx = word.charAt(level) - 'a';
            if (pTmp.children[idx] == null) {
                addNewNodeFlg = true;
                pTmp.children[idx] = new TrieNode();
            }
            pTmp = pTmp.children[idx];
            if (pTmp.isEndOfWord && level < length - 1) {
                ret = false;
            }
        }
        pTmp.isEndOfWord = true;

        if (!addNewNodeFlg) {
            ret = false;
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        TrieNode root = new TrieNode();
        String word, ans="";
        for (int i=0; i < n; i++) {
            word = sc.next();
            if (ans.isEmpty()) {
                if (!addWord(root, word)) {
                    ans = word;
                }
            }
        }
        if (ans.isEmpty()) {
            System.out.println("GOOD SET");
        } else {
            System.out.println("BAD SET");
            System.out.println(ans);
        }
    }
}