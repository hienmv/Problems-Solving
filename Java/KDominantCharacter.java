/** https://codeforces.com/problemset/problem/888/C
 *  #two-pointer #binary-search
 */

import java.util.Scanner;

public class KDominantCharacter {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       String str = sc.next();
       int len = 'z' - 'a' + 1;
       int[] arrChar = new int[len];
       int[] arrCharLastIdx = new int[len];
       char[] orgArr = str.toCharArray();
       int idx;
       for (int i=0; i < orgArr.length; i++) {
           idx = orgArr[i] - 'a';
           if (i - arrCharLastIdx[idx]>= arrChar[idx]) {
               arrChar[idx] = (arrChar[idx] == 0) ? (i - arrCharLastIdx[idx] + 1) : (i - arrCharLastIdx[idx]);
           }
           arrCharLastIdx[idx] = i;
       }
       
       int k = orgArr.length;
       for (int i=0; i < arrChar.length; i++) {
           if (orgArr.length - arrCharLastIdx[i] > arrChar[i]) {
                arrChar[i] = orgArr.length - arrCharLastIdx[i];
           }
           if (arrChar[i] < k) {
               k = arrChar[i];
           }
       }

       System.out.println(k);
   } 
}