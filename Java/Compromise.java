/**
 * #dp #lcs
 */
import java.util.Scanner;
import java.util.ArrayList;
class Compromise {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            ArrayList<ArrayList<String>> list = new ArrayList<>();
            for (int i=0; i < 2; i++) {
                ArrayList<String> options = new ArrayList<String>();
                String option;
                while(sc.hasNext()) {
                    option = sc.next();
                    if(option.equals("#")) {
                        break;
                    }
                    options.add(option);
                }
                list.add(options);
            }
            // find the longest common subsequences
            int m = list.get(0).size();
            int n = list.get(1).size();
            int[][] L = new int[m+1][n+1];
            lcs(list.get(0), list.get(1), L, m, n);
            // print result
            printLcs(list.get(0), list.get(1), L, m, n);
        }
    }
    public static void lcs(ArrayList<String> list1, ArrayList<String> list2, int[][] L, int m, int n) {
        for(int i=0; i <=m; i++) {
            for(int j=0; j <= n; j++) {
                if (i==0 || j==0) {
                    L[i][j] = 0;
                }
                else if(list1.get(i-1).equals(list2.get(j-1))) {
                    L[i][j] = L[i-1][j-1] + 1;
                }
                else {
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
                }
            }
        }
    }
    public static void printLcs(ArrayList<String> list1, ArrayList<String> list2, int[][] L, int m, int n) {
        int sz = L[m][n];
        String[] result = new String[sz];
        int i=m, j=n;
        while(i > 0 && j > 0) {
            if (list1.get(i-1).equals(list2.get(j-1))) {
                result[sz-1] = list1.get(i-1);
                i--;
                j--;
                sz--;
            }
            else if (L[i-1][j] > L[i][j-1]) {
                i--;
            }
            else {
                j--;
            }
        }
        for(i=0; i < result.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(result[i]);
        }
        System.out.println();
    } 
}