/** https://www.hackerrank.com/challenges/camelCase/problem
 * tag: #implementation
 * just count uppercase Chacter.
 */
import java.util.Scanner;
public class CamelCase {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int count = 1;
        for(char c : str.toCharArray()) {
            if ( c >= 'A' && c <= 'Z') {
                count++;
            }
        }
        System.out.println(count);
    }
}