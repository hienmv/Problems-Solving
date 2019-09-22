/*	http://lightoj.com/volume_showproblem.php?problem=1129
	idea: TRIE
*/

import java.util.Scanner;

class ConsistencyChecker {

    static final int MAX = 10;
    static class TrieNode {
        TrieNode[] childNode;
        boolean isEndOfWord;

        TrieNode() {
            childNode = new TrieNode[MAX];
            isEndOfWord = false;
        }
    }

    public static boolean addNumber(TrieNode root, String number) {
        TrieNode pTmp = root;
        boolean ans = true;
        boolean addNewNodeFlg = false;
        int level, idx;
        int length = number.length();
        for (level=0; level < length; level++) {
            idx = number.charAt(level) - '0';
            if (pTmp.childNode[idx] == null) {
                addNewNodeFlg = true;
                pTmp.childNode[idx] = new TrieNode();
            }
            pTmp = pTmp.childNode[idx];
            if(pTmp.isEndOfWord && level < length - 1) {
                ans = false;
            }
        } 
        pTmp.isEndOfWord = true;
        
        if (!addNewNodeFlg) {
            ans = false;
        }
        
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases, numbers;
        String number;
        
        testcases = sc.nextInt();

        for (int t=1; t < testcases+1; t++) {
            TrieNode root = new TrieNode();
            boolean ans = true;

            numbers = sc.nextInt();
            for (int i=0; i < numbers; i++) {
                number = sc.next();
                if (!addNumber(root, number)) {
                    ans = false;
                }
            }
            
            System.out.println("Case " + t + ": " + (ans ? "YES" : "NO"));
        }
    }
}