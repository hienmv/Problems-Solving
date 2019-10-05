/** https://www.hackerrank.com/challenges/contacts/problem
 * #trie
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Contact {
    static final int MAX = 26;

    static class TrieNode {
        TrieNode[] children;
        int numContact;

        TrieNode() {
            children = new TrieNode[MAX];
            numContact = 0;
        }        
    }

    public static void addContact(TrieNode root, String contactName) {
        TrieNode pTmp = root;
        
        int level, idx;
        int length = contactName.length();
        
        for(level=0; level < length; level++) {
            idx = contactName.charAt(level) - 'a';
            if (pTmp.children[idx] == null) {
                pTmp.children[idx] = new TrieNode();
            }
            pTmp = pTmp.children[idx];
            pTmp.numContact += 1;
        }
    }
    
    public static int findContactNumber(TrieNode root, String contactName) {
        TrieNode pTmp = root;
        int level, idx;
        int length = contactName.length();
        
        for(level=0; level < length; level++) {
            idx = contactName.charAt(level) - 'a';
            if (pTmp.children[idx] == null) {
                return 0;    
            }
            pTmp = pTmp.children[idx];
        }

        return pTmp.numContact;
    }

    /*
     * Complete the contacts function below.
     */
    static int[] contacts(String[][] queries) {
        int n = queries.length;
        TrieNode root = new TrieNode();
        int ans;
        ArrayList<Integer> retArr = new ArrayList<>();
        for (int i=0; i < n; i++) {
            if (queries[i][0].equals("add")) {
                addContact(root, queries[i][1]);
            } else if (queries[i][0].equals("find")) {
                ans = findContactNumber(root, queries[i][1]);
                retArr.add(ans);
            }
        }

        int sz = retArr.size();
        int[] result = new int[sz];
        for (int i=0; i < sz; i++) {
            result[i] = retArr.get(i);
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int queriesRows = Integer.parseInt(scanner.nextLine().trim());

        String[][] queries = new String[queriesRows][2];

        for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");

            for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
                String queriesItem = queriesRowItems[queriesColumnItr];
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }

        int[] result = contacts(queries);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}

