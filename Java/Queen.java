/** https://codeforces.com/problemset/problem/1143/C
 *  idea: only check vertices that can be deleted.
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Queen {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] canBeDeletedArr = new int[n+1]; // -1 : can not be deleted, 0: initilize, 1: can be deleted. 
        for (int i=1; i < n+1; i++) {
            int parent = sc.nextInt();
            int respectParents = sc.nextInt();
        
            if(parent != -1) {
                if (respectParents == 0) { // respect Parents
                    canBeDeletedArr[parent] = -1;
                    canBeDeletedArr[i] = -1;
                } else {
                    if (canBeDeletedArr[i] == 0) {
                        canBeDeletedArr[i] = 1;
                    }
                }
                if (canBeDeletedArr[parent] == 0) {
                    canBeDeletedArr[parent] = 1;
                }
            } else {
                canBeDeletedArr[i] = -1;
            }
        }
        boolean haveVertexDeletedFlg = false;
        for (int i=1; i < canBeDeletedArr.length; i++) {
            if (canBeDeletedArr[i] == 1) {
                haveVertexDeletedFlg = true;
                System.out.print(i + " ");
            }
        }
        System.out.println( haveVertexDeletedFlg ? "" : "-1");
    }
}