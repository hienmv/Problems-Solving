/*	https://www.spoj.com/problems/PHONELST/
#trie
*/

import java.util.Scanner;

class PhoneList {

    static final int MAX = 10;
    static class TrieNode {
        TrieNode[] childNode;
        boolean isEndNumber;

        TrieNode() {
            childNode = new TrieNode[MAX];
            isEndNumber = false;
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
            if(pTmp.isEndNumber && level < length - 1) {
                ans = false;
            }
        } 
        pTmp.isEndNumber = true;
        
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

        for (int t=0; t < testcases; t++) {
            TrieNode root = new TrieNode();
            boolean ans = true;

            numbers = sc.nextInt();
            for (int i=0; i < numbers; i++) {
                number = sc.next();
                if (!addNumber(root, number)) {
                    ans = false;
                }
            }
            
            System.out.println((ans ? "YES" : "NO"));
        }
    }
}