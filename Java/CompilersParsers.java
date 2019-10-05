/** https://www.codechef.com/problems/COMPILER 
#stack
*/

 import java.util.Scanner;
 import java.util.Deque;
 import java.util.LinkedList;
 import java.util.Arrays;

class CompilersParsers {
    static int calValidPair(String str) {
        Deque<Integer> stack = new LinkedList<>();
        char[] arr = str.toCharArray();
        byte[] resultArr = new byte[arr.length];
        int sumResult = 0;
        for (int i=0; i < arr.length; i++) {
            if (arr[i] == '<') {
                stack.addLast(i);
            }
            else { // equals '>'
                if(!stack.isEmpty()) {
                    resultArr[i] = 1;
                    resultArr[stack.pollLast()] = 1;
                    sumResult += 2;
                }
            }
        }
        if (sumResult == 0) {
            return 0;
        }
        int maxLength = 0;
        int count = 0;
        int lastIdx = 0;
        for (int i=0; i < resultArr.length;  i++){
            if (resultArr[i] == 1) {
                lastIdx = i;
                count += 1;
                if (count > maxLength) {
                    maxLength = count;
                }
            } else {
                count = 0;
            }
        }
        // check "><><"
        if (sumResult == 2) {
            if (lastIdx + 1 < str.length() && lastIdx >= 2  
                && str.substring(lastIdx - 2, lastIdx+1).equals("><>")) {
                    if (arr[lastIdx+1] == '<')
                        return 0;
            }
        }
        return maxLength;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        String[] arr = new String[t];
        for (int i=0; i < t; i++) {
            String temp = sc.nextLine();
            arr[i] = temp;
        }
        for (String str: arr) {
            System.out.println(calValidPair(str));
        }
    }

}   
/** another way: can apply for character balance
 *      Deque<Integer> stack = new LinkedList<>();
        char[] arr = str.toCharArray();
        int sumResult = 0;
        int balance = 0;
        for (int i=0; i < arr.length; i++) {
            if (arr[i] == '<') {
                stack.addLast(i);
                balance++;
            }
            else { // equals '>'
                // balance > 0
                if(!stack.isEmpty()) {
                    sumResult += 2;
                    balance--;
                }
                else {
                    break;
                }
            }
        }
        // <<>
        if (balance > 0) {
            return 0;
        }
        return sumResult;
 * 
 * 
 */