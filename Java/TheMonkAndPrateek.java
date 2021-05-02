/**
 * #hash-table #hash-table
 */
import java.util.Scanner;
import java.util.HashMap;

class TheMonkAndPrateek {
    public static int getHashValue(int n) {
        int ret = n;
        int digitsSum = 0;
        while (n > 0) {
            digitsSum += n % 10;
            n /= 10;
        }
        return (ret ^ digitsSum);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxValue = 0;
        int collisions = 0;
        int maxCollisionsMinValue = 0;
        int collisionsTimes = 0;
        int val, key, times;
        for (int i=0; i < n; i++) {
            val = sc.nextInt();
            key = getHashValue(val);
            if (map.containsKey(key)) {
                collisions++;
                times = map.get(key) + 1;
                map.replace(key, times);
                if (times == collisionsTimes) {
                    maxCollisionsMinValue = Math.min(maxCollisionsMinValue, key);
                } 
                else if (times > collisionsTimes) {
                    maxCollisionsMinValue = key;
                    collisionsTimes = times;
                }
            }
            else {
                map.put(key, 0);
                maxValue = Math.max(key, maxValue);
            }
        }
        if (collisions == 0) {
            System.out.println(maxValue + " " + 0);
        }
        else {
            System.out.println(maxCollisionsMinValue + " " + collisions);
        }
    } 
}