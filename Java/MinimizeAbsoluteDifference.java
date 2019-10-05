/*
You are given a int[] x that contains exactly five positive integers. 
You want to put four of them instead of the question marks into the following expression: 
∣(?/?)−(?/?)∣. Which numbers should you put there and in what order, 
if your goal is to make the value of the expression as small as possible?
More formally, you have the following expression: 
∣(xa/xb)−(xc/xd)∣. Here, / denotes real division (e.g., 7/2=3.5) and ∣∣ denotes absolute value. 
You want to find the four distinct subscripts a,b,c,d for which the value of the expression is minimized.
Output a int[] with four elements: the optimal subscripts {a,b,c,d}. 
If there are multiple optimal answers, return the lexicographically smallest one among them.
Notes

Given two distinct arrays A and B with the same number of elements, we say that 
A is lexicographically smaller than B if A has a smaller value at the first index at which they differ.
Input
Input contains five integers, each separated by space. Each integer will be between 1 and 10000, inclusive.

Output four integers, separated by space, that is the optimal answer.
Ví dụ

input
1 1 1 1 1
output
0 1 2 3
--
input
2 3 5 7 11
output
0 3 1 4
--
input
8 2 4 2 6
output
1 0 3 4
--
input
124 182 9 33 70
output
2 4 3 1
--
input
9947 3 7 11 13
output
1 4 3 0
--
input
10000 4 10 4 10
output
1 2 3 4
--
Explanation:
- In the first example, regardless of our choice of subscripts, the expression always evaluates to 
∣(1/1)−(1/1)∣=∣1−1∣=0. The lexicographically smallest optimal solution is {0,1,2,3}
- In the second example, both {0,3,1,4} and {1,4,0,3} would minimize the given expression, but 
{0,3,1,4} comes lexicographically first, so that is the only correct answer. The corresponding expression is 
∣(2/7)−(3/11)∣=0.012987
- In the third example, there are four sets of subscripts that minimize the given expression: 
{1,0,3,4},{1,4,3,0},{3,0,1,4}, and {3,4,1,0}.
- In the last example, the smallest possible value of our expression is ∣(4/10)−(4/10)∣=0.

#backtracking
*/
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class MinimizeAbsoluteDifference {

    public static void calculate(HashMap<Integer, ArrayList<Integer>> map, int[] orgArr, int len
            , int[] result, ArrayList<Fraction> resultFraction, int[] visitedArr) {
        // success
        if (len == 4) {
            resultFraction.add(new Fraction(result[0], result[1], result[2], result[3]));
            return;
        }

        // try
        for (int i=0; i < visitedArr.length; i++) {
            if (visitedArr[i] == 0) {
                visitedArr[i] = 1;
                result[len] = i;
                for(int x : map.get(i)) {
                    if (orgArr[x] < orgArr[i]) continue;
                    if (visitedArr[x] == 0) {
                        result[len+1] = x;
                        visitedArr[x] = 1;
                        calculate(map,orgArr, len + 2, result, resultFraction, visitedArr);
                        result[len+1] = -1;
                        visitedArr[x] = 0;
                    }
                }
                result[len] = -1;
                visitedArr[i] = 0;
            }
        }
    }
    public static void main(String[] args) {
        // get input
        Scanner sc = new Scanner(System.in);
        int[] orgArr = new int[5];
        for (int i=0; i < orgArr.length; i++) {
            int number = sc.nextInt();
            orgArr[i] = number;
        }

        // calculate
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i=0; i < orgArr.length; i++) {
            map.put(i, new ArrayList<Integer>());
            for(int j=0; j < orgArr.length; j++) {
                if (i != j && orgArr[i] <= orgArr[j]) {
                    map.get(i).add(j);
                }
            }
        }
        int[] fracArr = new int[4];
        int[] visitedArr = new int[orgArr.length];
        ArrayList<Fraction> resultFraction = new ArrayList<>();
        calculate(map, orgArr, 0, fracArr, resultFraction, visitedArr);
        
        // result
        Fraction.arr = orgArr;
        Collections.sort(resultFraction);
        Fraction ans = resultFraction.get(0);
        Fraction fraction;
        for(int i=1; i < resultFraction.size(); i++) {
            fraction = resultFraction.get(i);
            if (ans.compareTo(fraction) == 0) {
                if (Fraction.compareOrder(ans, fraction) > 0) {
                    ans = fraction;
                }
            } else {
                break;
            }
        }
        System.out.println(ans);
    }
}

class Fraction implements Comparable<Fraction> {
    public static int compareOrder(Fraction f1, Fraction f2) {
        if (f1.a != f2.a) return f1.a - f2.a;
        if (f1.b != f2.b) return f1.b - f2.b;
        if (f1.c != f2.c) return f1.c - f2.c;
        return f1.d - f2.d;
    }

    int a, b, c, d;
    static int[] arr = null;
    public Fraction(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    public int compareTo(Fraction other) {
        long v1 = (long)Math.abs(arr[this.a] * arr[this.d] - arr[this.c] * arr[this.b]) * arr[other.b] * arr[other.d];
        long v2 = (long)Math.abs(arr[other.a] * arr[other.d] - arr[other.c] * arr[other.b]) * arr[this.b] * arr[this.d];
        if (v1 > v2) {
            return 1;
        } else if (v1 < v2) {
            return -1;
        }
        return 0;
    }

    public String toString() {
        return (a + " " + b + " " + c + " " + d); 
    }
}