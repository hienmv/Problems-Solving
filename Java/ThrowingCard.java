/** https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1876
 *  #queue
 */
import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

class ThrowingCard {
    static void calculate(int n) {
        Deque<Integer> queue = new LinkedList<Integer>();
        for (int i=1; i < n + 1; i++) {
            queue.offerLast(i);
        }
        System.out.print("Discarded cards: ");
        while (queue.size() > 1) {
            System.out.print(queue.pollFirst());
            if (queue.size() > 1) {
                System.out.print(", ");
            }
            queue.offerLast(queue.pollFirst());
        }
        System.out.println("");

        String remainingCard = "Remaining card: ";
        System.out.println(remainingCard + queue.pollFirst());
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>();
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            arr.add(n);
        }   
        sc.close();

        arr.forEach(i -> calculate(i));
    }
}