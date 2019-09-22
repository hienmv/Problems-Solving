/**
There are so many different religions in the world today that it is difficult to keep track of them all. You are interested in finding out how many different religions students in your university believe in.
You know that there are n students in your university (0<n≤50000). It is infeasible for you to ask every student their religious beliefs. Furthermore, many students are not comfortable expressing their beliefs. One way to avoid these problems is to ask 
m(0≤m≤​ n(n−1) / 2) pairs of students and ask them whether they believe in the same religion (e.g. they may know if they both attend the same church). From this data, you may not know what each person believes in, but you can get an idea of the upper bound of how many different religions can be possibly represented on campus.
You may assume that each student subscribes to at most one religion.

Input
The input consists of a number of cases. Each case starts with a line specifying the integers n and m. The next m lines each consists of two integers 
i and j, specifying that students i and j believe in the same religion.
The students are numbered 1 to n. The end of input is specified by a line in which n=m=0.

Output
For each test case, print on a single line the case number (starting with 1) followed by the maximum number of different religions that the students in the university believe in.

idea: DSU
 */

import java.util.Scanner;

class UbiquitousReligions {

    public static void makeSet(int[] parent, int[] rank) {
        for (int i=0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
 
    public static int findSet(int u, int[] parent) {
        if (parent[u] != u) {
            parent[u] = findSet(parent[u], parent);
        }
        return parent[u];
    }
 
    public static boolean unionSet(int u, int v, int[] parent, int[] rank) {
        int up = findSet(u, parent);
        int vp = findSet(v, parent);
 
        if (up == vp) {
            return false;
        }
 
        if (rank[up] > rank[vp]) {
            parent[vp] = up;
        } else if (rank[up] < rank[vp]) {
            parent[up] = vp;
        } else {
            parent[up] = vp;
            rank[vp] += 1;
        }
 
        return true;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = 1;
        
        while (true) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            if (n == 0 && m == 0) break;

            int[] parent = new int[n+1];
            int[] rank = new int[n+1];
            makeSet(parent, rank);

            int u, v;
            int ans = n;
            for (int i=0; i < m; i++) {
                u = sc.nextInt();
                v = sc.nextInt();
                if (unionSet(u, v, parent, rank)) {
                    ans -= 1;
                }
            }
            System.out.println("Case " + testcase + ": " + Math.max(ans, 1));
            testcase++;
        }
    }
}