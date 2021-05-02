/**
 * #dynamic-programming
 */
/*
import java.util.Scanner;
import java.util.ArrayList;
class Alphacode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String s = sc.next();
            if (s.equals("0")) break;
            // split string
            int sz = s.length();
            ArrayList<String> arrStr = new ArrayList<>();
            for (int i=0; i < sz; i++) {
                if (s.charAt(i) == '0') {
                    arrStr.set(arrStr.size() - 1, s.substring(i-1, i+1));
                }
                else {
                    arrStr.add(s.substring(i, i+1));
                }
            }
            // calculate
            int num;
            sz = arrStr.size();
            long[] arr = new long[sz+1];
            arr[0] = 1;
            arr[1] = 1;
            for (int i=1; i < sz; i++) {
                num = Integer.parseInt(arrStr.get(i-1) + arrStr.get(i));
                if(num < 27) {
                    arr[i+1] = arr[i] + arr[i-1];
                }
                else {
                    arr[i+1] = arr[i];
                }
            }
            System.out.println(arr[sz]);
        }
    }
}
*/
// better way
import java.util.Scanner;
class Alphacode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String s = sc.next();
            if (s.equals("0")) break;
            int sz = s.length();
            long[] arr = new long[sz + 1];
            arr[0] = 1;
            arr[1] = 1;
            int num;
            for (int i=1; i < sz; i++) {
                num = Integer.parseInt(s.substring(i-1, i+1));
                // 1-digits
                if (s.charAt(i) != '0') {
                    arr[i+1] += arr[i];
                }
                // 2-digits
                if (num > 9 && num < 27) {
                    arr[i + 1] += arr[i - 1];
                }
            }
            System.out.println(arr[sz]);
        }
    }
}