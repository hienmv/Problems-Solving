/*  https://codeforces.com/contest/677/problem/A
    idea: compare the height of each person with the height of fence.
*/

import java.util.Scanner;

public class VanyaAndFence {
    static int caculate() {
        Scanner scanner = new Scanner(System.in);
        int numberOfFriends = scanner.nextInt();
        int heightOfFence = scanner.nextInt();
        int minValidWidth = 0;
        for (int i=0; i < numberOfFriends; i++) {
            int heightOfPerson = scanner.nextInt();
            if (heightOfPerson > heightOfFence){
                minValidWidth += 2;
            } else {
                minValidWidth += 1;
            }
        }
        return minValidWidth;      
    }

    public static void main(String args[]) {
        System.out.println(caculate());        
    }
}
