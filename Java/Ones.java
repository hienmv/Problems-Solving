/**
 * #number-theory
 * 
 * 
 #simple way
    x = 111111 = 1111110 + 1
    gcd(n, x) = 1
    
    bx = 1 (mod n)
    
    x = 1
    x = 10 * x + 1 = 11
    
    cnt = 1;
    x = 1
    while (x % n != 0) {
        x = (10 * x + 1) % n
        cnt += 1
    }
 */

import java.util.Scanner;

class Ones {

    public static Value initialMultipleValue(int n) {
        int val = 0;
        int digits = 1;
        while (n / 10 > 0) {
            val = (val + 1)*10;
            n /= 10;
            digits++;
        }
        return new Value(digits, val+1);
    }
    public static int modularExponentiation(int a, int b, int m) {
        int result = 1;
        a %= m;
        while (b > 0) {
            if (b % 2 == 1) {
                result = (result * a) % m;
            }
            b = b / 2;
            a = (a * a) % m;
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, digits, addVal, mod;
        while (sc.hasNextInt()) {
            n = sc.nextInt();
            Value multipleVal = initialMultipleValue(n);
            digits = multipleVal.digits;
            mod = multipleVal.val % n;
            addVal = 0;
            while ((mod + addVal) % n != 0) {
                addVal = (mod + addVal) % n;
                mod = modularExponentiation(10, digits, n);
                digits++;
            }
            System.out.println(digits);
        }
    }
}
class Value {
    int digits;
    int val;
    public Value(int digits, int val) {
        this.digits = digits;
        this.val = val;
    }
}