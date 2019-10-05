/*  https://codeforces.com/problemset/problem/762/B
 #greedy #two-pointer #sorting
*/
import java.util.Scanner;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;

public class UsbAndPS {
    public static boolean helper( int idx1, int idx2, int[] inputArr) {
        if (inputArr[idx1] > 0) {
            inputArr[idx1] -= 1;
            return true;
        } else if (inputArr[idx2] > 0) {
            inputArr[idx2] -= 1;
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] inputArr = new int[3];
        for (int i=0; i < inputArr.length; i++) {
            int tmp = sc.nextInt();
            inputArr[i] = tmp;
        }
        int m = sc.nextInt();
        ArrayList<Long> usbMouseArr = new ArrayList<>();
        ArrayList<Long> psMouseArr = new ArrayList<>();
        
        int[] countArr = new int[2];
        for(int i=0; i < m; i++) {
            long cost = sc.nextLong();
            String name = sc.next();
            if (name.equals("USB")) {
                usbMouseArr.add(cost);
            } else {
                psMouseArr.add(cost);
            }
        }
        Collections.sort(usbMouseArr);
        Collections.sort(psMouseArr);
        long[] sumArr = new long[2]; // 0 - sum of mouse, 1 - sum of cost.
        int idxUsb = 0;
        int idxPS = 0;
        while (idxUsb < usbMouseArr.size() && idxPS < psMouseArr.size()) {
            if (inputArr[0] == 0 && inputArr[1] == 0 && inputArr[2] == 0 ) break;
           
            if (psMouseArr.get(idxPS) < usbMouseArr.get(idxUsb)) {
                if (helper(1, 2, inputArr)) {
                    sumArr[0] += 1;
                    sumArr[1] += psMouseArr.get(idxPS);
                    idxPS++;
                } else {
                    break;
                }
               
            } else  {
                if (helper(0, 2, inputArr)) {
                    sumArr[0] += 1;
                    sumArr[1] += usbMouseArr.get(idxUsb);
                    idxUsb++;
                } else {
                    break;
                }
            }
        }
        
        while (idxUsb < usbMouseArr.size()) {
            if (helper(0, 2, inputArr)) {
                sumArr[0] += 1;
                sumArr[1] += usbMouseArr.get(idxUsb);
                idxUsb++;
            } else {
                break;
            }
        } 
        
        while (idxPS < psMouseArr.size()) {
            if (helper(1, 2, inputArr)) {
                sumArr[0] += 1;
                sumArr[1] += psMouseArr.get(idxPS);
                idxPS++;
            } else {
                break;
            }
        }

        System.out.println(sumArr[0] +  " " + sumArr[1]);
    }
}