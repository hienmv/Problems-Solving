// https://www.interviewbit.com/problems/maximum-absolute-difference/
// #array
public class MaximumAbsoluteDifference {
  public int maxArr(int[] A) {
    /*
    idea: break the expression to two case, and generate new Array for each case to calculate.
    i < j:  two case:
    |A[i] - A[j]| + |i - j|
        Case 1: A[i] > A[j]
            -> A[i] - A[j] + j - i = (A[i] - i) - (A[j] - j)
            => new Array with element: A[i] - i
        Case 2: A[i] < A[j]
            -> A[j] - A[i] + j - i = -(A[i] + i) - (-(A[j] + j))
            => new Array with element: -(A[i] + i)
    */

    // return solution1(A);
    return solution2(A);
  }

  /*
  - Time: 0(n)
  - Space: 0(n)
  */
  private int solution1(int[] A) {
    // case 1
    int[] case1_arr = new int[A.length];
    for (int i = 0; i < A.length; i++) {
      case1_arr[i] = A[i] - i;
    }
    int result1 = helper(case1_arr);

    // case 2
    int[] case2_arr = new int[A.length];
    for (int i = 0; i < A.length; i++) {
      case2_arr[i] = -1 * (A[i] + i);
    }
    int result2 = helper(case2_arr);
    return Math.max(result1, result2);
  }

  private int helper(int[] arr) {
    int min = arr[0];
    int max = arr[0];
    int result = 0;
    for (int element : arr) {
      min = Math.min(min, element);
      max = Math.max(max, element);
      result = Math.max(result, max - min);
    }
    return result;
  }

  /*
  - Time: O(n)
  - Space: O(1)
  */
  private int solution2(int[] A) {
    int result = 0;
    int current_min1 = A[0];
    int current_max1 = A[0];
    int current_min2 = -A[0];
    int current_max2 = -A[0];
    int result1 = 0;
    int result2 = 0;
    for (int i = 1; i < A.length; i++) {
      // case 1
      int val1 = A[i] - i;
      current_min1 = Math.min(current_min1, val1);
      current_max1 = Math.max(current_max1, val1);
      result1 = Math.max(result1, current_max1 - current_min1);

      // case 2
      int val2 = -(A[i] + i);
      current_min2 = Math.min(current_min2, val2);
      current_max2 = Math.max(current_max2, val2);
      result2 = Math.max(result2, current_max2 - current_min2);

      result = Math.max(result1, result2);
    }

    return result;
  }
}
