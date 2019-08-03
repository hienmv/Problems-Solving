/** https://codeforces.com/problemset/problem/75/C
 *  idea: binary search 
 */
import java.util.Scanner;

public class ModifiedGCD {
    public static int getGCD(int a, int b) {
        int tmp;
        while (b != 0) {
            tmp = b;
            b = a%b;
            a = tmp;
        }
        return a;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int n = sc.nextInt();
       
        int GDC = getGCD(a, b);

        int low, high, val;
        
        for (int i=0; i < n; i++) {
            low = sc.nextInt();
            high = sc.nextInt();
            val = -1;
            if(low <= GDC) {
                if (high >= GDC) {
                    val = GDC;
                } else {
                    while (high >= low) {
                        
                        if (a % high == 0 && b % high == 0) {
                            val = high;
                            break;
                        } 
                
                        high = a / ((a / high) + 1);
                    }
                }
            }
            System.out.println(val);
        }
    }
}