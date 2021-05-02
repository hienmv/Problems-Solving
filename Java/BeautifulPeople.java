/**
#dynamic-programming #lis

=.. hate
    Si >= Sj && Bi <= Bj
    Si <= Sj && Bj >= Bj
    
    => Si >= Sj && Bi > Bj
       Si <= Sj && Bi < Bj


given 2,3 dimensions. 
    -> co dinh 1 dimensions -> ..
            value de tinh toan co tuong duong nhau hay khong ?
     ---> TheTowerOfBabylon: high la khac nhau, k binary search dc.
     ---> BeautifulPeople: high la 1, giong nhau.
       
                
 */

import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;

class BeautifulPeople {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Node> list = new ArrayList<>();
        int s, b;
        for (int i=1; i < n+1; i++) {
            s = sc.nextInt();
            b = sc.nextInt();
            list.add(new Node(s, b, i));
        }
        Collections.sort(list);
        int[] path = new int[n];
        Arrays.fill(path, -1);
        ArrayList<Integer> result = new ArrayList<>();
        int maxNum = LIS(list, result, path);
        System.out.println(maxNum);
        printLIS(list, maxNum, result, path);
    }
    public static int LIS(ArrayList<Node> list, ArrayList<Integer> result, int[] path) {
        int len = 1;
        int n = list.size();
        result.add(0);
        for (int i=1; i < n; i++) {
            Node node = list.get(i);
            int last = result.get(len - 1);
            if (node.b > list.get(last).b) {
                path[i] = last;
                result.add(i);
                len++;
            }
            else {
                int pos = lowerBound(list, result, len, node);
                if (pos != 0) {
                    path[i] = result.get(pos - 1);
                }
                result.set(pos, i);
            }
        }
        return len;
    }
    public static int lowerBound(ArrayList<Node> list, ArrayList<Integer> sub, int n, Node target) {
        int left = 0, right = n;
        int pos = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int idx = sub.get(mid);
            Node node = list.get(idx);
            if (node.b >= target.b) {
                pos = mid;
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return pos;
    }
    public static void printLIS(ArrayList<Node> list, int len, ArrayList<Integer> result, int[] path) {
        ArrayList<Integer> b = new ArrayList<>();
        int i = result.get(len - 1);
        while(i >= 0) {
            b.add(list.get(i).idx);
            i = path[i];
        }
        for (i=b.size() - 1; i >= 0; i--) {
            System.out.print(b.get(i));
            if (i > 0) {
                System.out.print(" ");
            }
        }
    }
}

class Node implements Comparable<Node>{
    int s, b, idx;
    Node(int s, int b, int idx) {
        this.s = s;
        this.b = b;
        this.idx = idx;
    }
    public int compareTo(Node other) {
        if (this.s != other.s) {
            return this.s - other.s;
        }
        return other.b - this.b;
    }
}