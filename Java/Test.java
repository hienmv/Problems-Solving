/* https://www.spoj.com/problems/STPAR/
* #queue #stack #ad-hoc-1 #todo
*/
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Deque;
class Test {
    static String calSub(int[] arr) {
        Deque<Integer> sideStreet = new LinkedList<Integer>(); //stack
        int lastCar = 0;

        for (int i=0; i < arr.length; i++) {
            if (arr[i] == 1) {
                // mainStreet.add(arr[i]);
                lastCar = 1;
            } else if(lastCar != 0) {
                while (!sideStreet.isEmpty() && sideStreet.peekLast() == lastCar + 1) {
                    sideStreet.pollLast();
                    lastCar++;
                }
                if (!sideStreet.isEmpty() && arr[i] > sideStreet.peekLast()){
                    return "no";
                }
                if (arr[i] == lastCar + 1) {
                    lastCar++;
                } else {
                    sideStreet.addLast(arr[i]);
                }
            } else {
                if (!sideStreet.isEmpty() && sideStreet.peekLast() < arr[i]) {
                    return "no";
                }
                sideStreet.addLast(arr[i]);
            }
        }
        if (!sideStreet.isEmpty() && sideStreet.peekLast() < lastCar) {
            return "no";
        }

        return "yes";
    }

    static void calculate() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            int[] arr = new int[n];
            for(int i= 0; i < n; i++) {
                int temp = sc.nextInt();
                arr[i] = temp;
            }
            System.out.println(calSub(arr));
        }
    }
    public static void main(String[] args) {
        calculate();
    }
}