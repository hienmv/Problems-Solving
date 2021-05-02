/**
 * #bit-manipulation #refactor #todo
 other way:
    a = [1, 2, 3, 4, 13] -> 79
    s = [   1,    3,    0,    4,    9]
    b = [0001, 0011, 0000, 0110, 1001]
    
    0001 XOR 0110
    (0 XOR 0) << 3 + (0 XOR 1) << 2 + (0 XOR 1) << 1 + (1 XOR 0) << 0

    3 way: O(n^2) -> O(nlogn) -> O(n)
 */

import java.util.Scanner;

class PoloThePenguinAndTheXOR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        for (int t=0; t < testcase; t++) {
            int n = sc.nextInt();
            int sz = n+1;
            int[] input = new int[sz];
            int[] cal = new int[sz];
            long sum = 0;
            for (int i=1; i < sz; i++) {
                input[i] = sc.nextInt();
                cal[i] = cal[i-1] ^ input[i];
                sum += cal[i];
            }
            /* O(n^2)
            for (int i=1; i < sz; i++) {
                for(int j=i+1; j < sz; j++) {
                    cal[j] ^= cal[i];
                    sum += cal[j];
                }
            } */

            // calculate
            int[] bitArr = new int[32];
            for (int i=1; i < sz; i++) {
               
                int number = cal[i];
                int idx = 0;
                while (number > 0) {
                    bitArr[idx++] += (number & 1);
                    number >>= 1;
                }

                int subSum = 0;
                for (int j=0; j < bitArr.length; j++) {
                    subSum += bitArr[j] * (1 << j);
                }
                sum += subSum;
            }

            System.out.println(sum);
        }
    }
}