/**
 * https://www.spoj.com/problems/STPAR/ #queue #stack #ad-hoc-1 use queue for main street. use stact
 * for side street
 */
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class StreetParade {
  static String calSub(int[] arr) {
    Deque<Integer> mainStreet = new LinkedList<Integer>();
    Deque<Integer> sideStreet = new LinkedList<Integer>();

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == 1) {
        mainStreet.add(arr[i]);
      } else if (!mainStreet.isEmpty()) {
        while (!sideStreet.isEmpty() && sideStreet.peek() == mainStreet.peek() + 1) {
          mainStreet.add(sideStreet.pop());
        }
        if (!sideStreet.isEmpty() && arr[i] > sideStreet.peek()) {
          return "no";
        }
        if (arr[i] == mainStreet.peek() + 1) {
          mainStreet.add(arr[i]);
        } else {
          sideStreet.push(arr[i]);
        }
      } else {
        if (!sideStreet.isEmpty() && sideStreet.peek() < arr[i]) {
          return "no";
        }
        sideStreet.push(arr[i]);
      }
    }
    if (!sideStreet.isEmpty() && sideStreet.peek() < mainStreet.peek()) {
      return "no";
    }

    return "yes";
  }

  static void calculate() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      int n = sc.nextInt();
      if (n == 0) {
        break;
      }
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        int temp = sc.nextInt();
        arr[i] = temp;
      }
      System.out.println(calSub(arr));
    }
  }

  public static void main(String[] args) {
    calculate();
  }
}

/**
 * import java.util.Scanner; import java.util.Deque; import java.util.LinkedList;
 *
 * <p>class StreetParade { static String calSub(int[] arr) { Deque<Integer> sideStreet = new
 * LinkedList<Integer>(); //stack int lastCar = 0;
 *
 * <p>for (int i=0; i < arr.length; i++) { while (!sideStreet.isEmpty() && sideStreet.peekLast() ==
 * lastCar + 1) { sideStreet.pollLast(); lastCar++; } if (!sideStreet.isEmpty() && arr[i] >
 * sideStreet.peekLast()){ return "no"; } if (arr[i] == lastCar + 1) { lastCar++; } else {
 * sideStreet.addLast(arr[i]); } } if (!sideStreet.isEmpty() && sideStreet.peekLast() < lastCar) {
 * return "no"; }
 *
 * <p>return "yes"; }
 *
 * <p>static void calculate() { Scanner sc = new Scanner(System.in); while (true) { int n =
 * sc.nextInt(); if (n == 0) { break; } int[] arr = new int[n]; for(int i= 0; i < n; i++) { int temp
 * = sc.nextInt(); arr[i] = temp; } System.out.println(calSub(arr)); } } public static void
 * main(String[] args) { calculate(); } }
 */
