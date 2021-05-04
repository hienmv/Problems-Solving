// https://www.interviewbit.com/problems/min-steps-in-infinite-grid/
// #array
public class MinStepsInInfiniteGrid {
  public int coverPoints(int[] A, int[] B) {
    int minimums = 0;

    for (int i = 1; i < A.length; i++) {
      if (Math.abs(A[i] - A[i - 1]) > Math.abs(B[i] - B[i - 1])) {
        minimums += Math.abs(A[i] - A[i - 1]);
      } else {
        minimums += Math.abs(B[i] - B[i - 1]);
      }
    }
    return minimums;
  }
}
