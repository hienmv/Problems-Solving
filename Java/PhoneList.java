/*	
Given a list of phone numbers, determine if it is consistent in the sense that no number is the prefix of another. Let’s say the phone catalogue listed these numbers:
Emergency 911
Alice 97625999
Bob 911254 26
In this case, it’s not possible to call Bob, because the central would direct your call to the emergency line as soon as you had dialled the first three digits of Bob’s phone number. So this list would not be consistent.
Input
The first line of input gives a single integer, 1≤T≤40, the number of test cases. Each test case starts with 
N, the number of phone numbers, on a separate line, 1≤N≤10000. Then follows 
N lines with one unique phone number on each line. A phone number is a sequence of at most ten digits.
Output
For each test case, output “YES” if the list is consistent, or “NO” otherwise.

idea: TRIE
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