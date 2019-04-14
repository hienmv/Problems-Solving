/*  https://codeforces.com/contest/691/problem/A
    idea: count the number of 0 in the array.
    special case: when only have an array with length 1.
*/
import java.util.Scanner;

public class FashionInBerland {

   static String myFunction() {
        Scanner obj = new Scanner(System.in);
        int number = obj.nextInt();
        
        // special case
        if (number == 1) {
            if (0 == obj.nextInt()) {
                return "NO";
            } else {
                return "YES";    
            }
        }

        int countZero = 0;        
        for (int i=0; i < number; i++) {
            if (0 == obj.nextInt()) {
                countZero++;
            }
            if (countZero > 1) {
                break;
            }
        }

        if (countZero != 1) {
            return "NO";
        }        

        return "YES";
    }
    public static void main( String argr[]) {
       System.out.println(myFunction());
    }
}
