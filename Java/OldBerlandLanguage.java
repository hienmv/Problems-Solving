/** https://codeforces.com/contest/37/problem/C
 *  idea: TRIE
 *  prefix -> think about TRIE ? 
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class OldBerlandLanguage {
    static final int MAX = 2;
    
    static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;
        TrieNode() {
            children = new TrieNode[MAX];
            isEndOfWord = false;
        }
    }
/**
    khu de quy:

    TrieNode {
        child: Array
        isFull: boolean
        parent: TrieNode
    }

    addWord(root, length) {
        tmp = root
        if root.isFull:
            return false
        s = ""
        for i : 0 .. length - 1
            canMove = false
            check = -1
            for c : [0, 1]
                if tmp.child[c] != null:
                    if !tmp.child[c].isFull:
                        s += c
                        canMove = true
                        break
                else // tmp.child[c] == null:
                    check = c // need to create new branch
            
            if canMove == false:
                if check == -1
                    return false
                else 
                    s += check
                    tmp.child[check] = new TrieNode()
                    tmp.child[check].parent = tmp
                    tmp = temp.chid[check]
                    
        temp.isFull = true

        while tmp != null:
            if tmp.child[0] != null & tmp.child[1] != null:
                if tmp.child[0].isFull && tmp.child[1].isFull:
                    tmp.isFull = true
            tmp = tmp.parent
    }
 */
    static boolean addWord(TrieNode root, String curString, int curPos, int length, int resIdx, ArrayList<String> result) {
        if (root.isEndOfWord) {
            return false;
        }

        if (curPos == length) {
            result.set(resIdx, curString);
            root.isEndOfWord = true;
            return true;
        }

        // try left
        if (root.children[0] == null) {
            root.children[0] = new TrieNode();
        }
        boolean flg = addWord(root.children[0], curString + '0', curPos + 1, length, resIdx, result);
        if (flg) {
            return true;
        }

        // try right
        if (root.children[1] == null) {
            root.children[1] = new TrieNode();
        }
        return addWord(root.children[1], curString + '1', curPos + 1, length, resIdx, result);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int length=0;
        boolean ans = true;
    
        TrieNode root = new TrieNode();
        
        Array<Node> lengthArr = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        for (int i=0; i < n; i++) {
            length = sc.nextInt();
            lengthArr.add(new Node(length, i));
            result.add("");
        }
  
        Collections.sort(lengthArr);

        for (Node e : lengthArr) {
            String word = "";
            if (!addWord(root, word, 0, e.length, e.idx, result)) {
                ans = false;
                break;
            }
        }

        if (ans) {
            System.out.println("YES");
            for (String s : result) {
                System.out.println(s);
            }
        } else {
            System.out.println("NO");
        }
    }

}
class Node implements Comparable<Node> {
    int length;
    int idx;
    Node (int length, int idx) {
        this.length = length;
        this.idx = idx;
    }

    public int compareTo(Node other) {
        return this.length - other.length;
    }
}