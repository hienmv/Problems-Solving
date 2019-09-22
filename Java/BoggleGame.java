/**
 * idea: backtracking
 other implements: 
//  // set state -> initilize state

// ...

// reset

 */
import java.util.Scanner;
import java.util.TreeSet; // can use HashSet

class  BoggleGame {
    static TreeSet<Character> vowelsSet = new TreeSet<>();
    static final int[] dx = { 0, 0, -1, -1, -1, 1, 1,  1 };
    static final int[] dy = {-1, 1,  1, -1,  0, 0, 1, -1 };

    public static boolean checkValid(char[][] matrix, int x, int y, int[][] visitedGraph)
    {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[x].length) return false;
        if (visitedGraph[x][y] == 1) return false;
        return true;
    }
    public static void calculate(char[][] matrix, int startX, int startY, int len, char[] word, TreeSet<String> result, int[][] visitedGraph) {
        // success
        if (len == -1) {
            int count = 0;
            for(char c : word) {
                if (vowelsSet.contains(c)) {
                    count++;
                }
            }
            if (count == 2) {
                result.add(new String(word));
            }
            return;
        }

        // try
        for (int i=0; i < dx.length; i++) {
            int x = startX + dx[i];
            int y = startY + dy[i];
            if (checkValid(matrix, x, y, visitedGraph)) {
                visitedGraph[x][y] = 1;
                word[len] = matrix[x][y];
                calculate(matrix, x, y, len - 1, word, result, visitedGraph);
                word[len] = '\0';
                visitedGraph[x][y] = 0;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] vowels = {'U', 'E', 'O', 'A', 'I', 'Y'};
        
        for (char c : vowels) {
            vowelsSet.add(c);
        }

        char[][] matrix1 = new char[4][4];
        char[][] matrix2 = new char[4][4];
        String str;
        int testcase = 0;
        while(true) {
            str = sc.next();
            if (str.equals("#")) break;
            if (testcase > 0) System.out.println();

            int len = 4;

            // get input
            matrix1[0][0] = str.charAt(0);
            for(int i = 1; i < 8; i++) {
                str = sc.next();
                if (i < len) {
                    matrix1[0][i] = str.charAt(0);
                } else {
                    matrix2[0][i%len] = str.charAt(0);
                }
            }
            for (int i = 1; i < len; i++) {
                for(int j = 0; j < 8; j++) {
                    str = sc.next();
                    if (j < len) {
                        matrix1[i][j] = str.charAt(0);
                    } else {
                        matrix2[i][j%len] = str.charAt(0);
                    }
                }
            }

            TreeSet<String> result1 = new TreeSet<>();
            int[][] visitedGraph1 = new int[len][len];
            char[] wordChar1 = new char[len];
            for (int i=0; i < matrix1.length; i++) {
                for (int j=0; j < matrix1[i].length; j++) {
                    wordChar1[len-1] = matrix1[i][j];
                    visitedGraph1[i][j] = 1;
                    calculate(matrix1, i, j, len - 2, wordChar1, result1, visitedGraph1);
                    wordChar1[len-1] = '\0';
                    visitedGraph1[i][j] = 0;
                }
            }

            TreeSet<String> result2 = new TreeSet<>();
            int[][] visitedGraph2 = new int[4][4];
            char[] wordChar2 = new char[len];
            for (int i=0; i < matrix2.length; i++) {
                for (int j=0; j < matrix2[i].length; j++) {
                    wordChar2[len-1] = matrix2[i][j];
                    visitedGraph2[i][j] = 1;
                    calculate(matrix2, i, j, len - 2, wordChar2, result2, visitedGraph2);
                    wordChar2[len-1] = '\0';
                    visitedGraph2[i][j] = 0;
                }
            }
            int ans = 0;
            for (String word : result1) {
                if (result2.contains(word)) {
                    System.out.println(word);
                    ans++;
                }
            }
            if (ans == 0) {
                System.out.println("There are no common words for this pair of boggle boards.");
            }
            testcase++;
        }
    }
}