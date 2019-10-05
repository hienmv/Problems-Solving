/** https://codeforces.com/problemset/problem/424/B
 *  #binary-search #greedy
 */

 import java.util.Scanner;
 import java.util.ArrayList;
 import java.util.Collections;

 public class Megacity {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        ArrayList<City> arrList = new ArrayList<>();
        int x, y, population;
        for(int i=0; i < n; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            population = sc.nextInt();
            arrList.add(new City(x, y, population));
        }
        Collections.sort(arrList);
        int target = 1000000 - s;
        ArrayList<Integer> sumArrList = new ArrayList<>();
        sumArrList.add(arrList.get(0).population);
        int curSum = 0;
        int idx = -1;
        for(int i=0; i < arrList.size(); i++) {
            curSum += arrList.get(i).population;
            if (curSum >= target) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            System.out.println(-1);
        } else {
            x = arrList.get(idx).x;
            y = arrList.get(idx).y;
            double val = Math.sqrt(x*x + y*y);
            System.out.println(val);
        }
     } 
 }

 class City implements Comparable<City> {
     int x, y, population;
     City(int x, int y, int population) {
         this.x = x;
         this.y = y;
         this.population = population;
     }
     public int compareTo(City other) {
        return (x*x + y*y  - (other.x * other.x + other.y * other.y));
     }
 }