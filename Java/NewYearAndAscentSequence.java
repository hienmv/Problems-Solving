/**
 * #sorting #binary-search
 *
 * <p>x y x+y 1 1 [2 0 2 0 2 0 2 0]
 *
 * <p>2 0 2 0 9 9 8 8 7 7 6
 *
 * <p>2 0 2 0 2 0 2 0 2 0 2 0 9 9 8 8 7 7 2 0 2 0 6 6 9 9 8 8 7 7 9 9 8 8 7 7 9 9 8 8 7 7
 *
 * <p>/* 1. approaching: count number of non-ascent sequences isAscent: exists
 *
 * <p>(A) = ways of p + q: nonAscent -> nonAscent nonAscent: minP >= maxQ non-increasing
 *
 * <p>=>>> n^2 - (A) ===============================================================
 *
 * <p>2. approaching: count number of ascent sequences for each sequence: if isAscent: calculate:
 * ascent + ascent = numAscent * numAscent ascent + nonAscent = numAscent * (n - numAscent)
 * nonAscent + ascent = numAscent * (n - numAscent) => numAscent * (2n - numAscent) else: append min
 * value to minArray append max value to maxArray
 *
 * <p>sort maxArray for each element in minArray: find upperBound in maxArray
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NewYearAndAscentSequence {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int number = sc.nextInt();
    int[][] data = new int[number][];
    for (int i = 0; i < number; i++) {
      int l = sc.nextInt();
      data[i] = new int[l];
      for (int k = 0; k < l; k++) {
        data[i][k] = sc.nextInt();
      }
    }

    long result = 0;
    // result = approachingCountNumAscent(data);
    result = approachingCountNumNonAscent(data);
    System.out.println(result);
  }

  private static long approachingCountNumNonAscent(int[][] data) {
    ArrayList<Integer> minList = new ArrayList<>();
    ArrayList<Integer> maxList = new ArrayList<>();
    int number = data.length;
    for (int i = 0; i < number; i++) {
      int[] arr = data[i];
      int max = Integer.MIN_VALUE;
      int min = Integer.MAX_VALUE;
      boolean ascent = false;
      for (int k = 0; k < arr.length; k++) {
        if (arr[k] > max) {
          max = arr[k];
        }
        if (arr[k] < min) {
          min = arr[k];
        }
        if (k > 0 && arr[k] > arr[k - 1]) {
          ascent = true;
        }
      }
      if (!ascent) {
        minList.add(min);
        maxList.add(max);
      }
    }
    // sort
    Collections.sort(maxList);
    long cnt = 0;
    for (int i = 0; i < minList.size(); i++) {
      int pos = lowerBound(maxList, 0, maxList.size(), minList.get(i));
      cnt += pos + 1;
    }
    long result = (long) number * number - cnt;
    return result;
  }

  private static int lowerBound(ArrayList<Integer> list, int l, int r, int val) {
    int ret = -1;
    while (l < r) {
      int mid = l + (r - l) / 2;
      int cur = list.get(mid);
      if (cur <= val) {
        ret = mid;
        l = mid + 1;
      } else {
        r = mid;
      }
    }
    return ret;
  }

  private static long approachingCountNumAscent(int[][] data) {
    ArrayList<Integer> minList = new ArrayList<>();
    ArrayList<Integer> maxList = new ArrayList<>();
    int ascentCnt = 0;
    int number = data.length;
    for (int i = 0; i < number; i++) {
      int[] arr = data[i];

      int max = Integer.MIN_VALUE;
      int min = Integer.MAX_VALUE;
      for (int k = 0; k < arr.length; k++) {
        if (arr[k] > max) {
          max = arr[k];
        }
        if (arr[k] < min) {
          min = arr[k];
        }
      }
      boolean ascent = false;
      for (int k = 1; k < arr.length; k++) {
        if (arr[k] > arr[k - 1]) {
          ascent = true;
          break;
        }
      }
      if (ascent) {
        ascentCnt++;
      } else {
        minList.add(min);
        maxList.add(max);
      }
    }

    long result = 0;

    /* ascent sequences included
        ascent + ascent = numAscent * numAscent
        ascent + nonAscent = numAscent * (n - numAscent)
        nonAscent + ascent = numAscent * (n - numAscent)
    => numAscent * (2n - numAscent)
    */
    result = (long) ascentCnt * (2 * number - ascentCnt);

    /* only non-ascent sequences */
    // sort
    Collections.sort(maxList);
    for (int i = 0; i < minList.size(); i++) {
      int pos = upperBound(maxList, 0, maxList.size(), minList.get(i));
      result += maxList.size() - pos;
    }

    return result;
  }

  private static int upperBound(ArrayList<Integer> list, int l, int r, int val) {
    int ret = r;
    while (l < r) {
      int mid = l + (r - l) / 2;
      int cur = list.get(mid);
      if (cur > val) {
        ret = mid;
        r = mid;
      } else {
        l = mid + 1;
      }
    }
    return ret;
  }
}
