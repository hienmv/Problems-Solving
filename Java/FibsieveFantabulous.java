/*
Fibsieve Fantabulous
*/

import java.util.Scanner;

class FibsieveFantabulous {
    static class Node {
        long x, y;
        Node(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static Node getPosition(long time) {
        long bound;
        double val = Math.sqrt(time);
        if (val > (double)(long)(val)) {
            bound = (long)(val) + 1;
        } else {
            bound = (long)(val);
        }

        long x, y;
        long midPos = (long)Math.pow(bound, 2) - bound + 1;
        
        boolean xflg = false;
        if (bound % 2 != 0) {
            xflg = true;
        }

        if (xflg) {
            if (time > midPos) {
                y = bound;
                x = bound - Math.abs(time - midPos);
            } else {
                x = bound;
                y = bound - Math.abs(time - midPos);
            }
        } else {
            if (time > midPos) {
                x = bound;
                y = bound - Math.abs(time - midPos);
            } else {
                y = bound;
                x = bound - Math.abs(time - midPos);
            }
        }

        return new Node(x,y);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        long time;
        Node position;
        for (int t=1; t < testcases + 1; t++) {
            time = sc.nextLong();
            position = getPosition(time);

            System.out.println("Case " + t + ": " + position.x + " " + position.y);
        }
    }
}