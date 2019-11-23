/**
 * #divide-and-conquer
 N paper: A -> C
    N-1 A->B
    move A->B
    N-1 B->C
 */
import java.util.Scanner;

class ExaminationPapers {
    public static long modularExponentation(long a, int b, int m) {
        long ret = 1L;
        a %= m;
        while(b > 0) {
            if (b % 2 == 1) {
                ret = (ret * a) % m;
            }
            b /= 2;
            a = (a * a) % m;
        }
        return ret;
    }
    public static long getResult(int n) {
        return modularExponentation(2L, n, 1000000007) - 1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        int n;
        for (int i=0; i < testcase; i++) {
            n = sc.nextInt();
            System.out.println(getResult(n));
        }
    }
}