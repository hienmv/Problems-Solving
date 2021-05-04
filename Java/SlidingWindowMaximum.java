// https://www.interviewbit.com/problems/sliding-window-maximum/
// #stack #queue #deque
public class Solution {
  // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
  public int[] slidingMaximum(final int[] array, int k) {

    if (k > array.length) {
      int[] ret = new int[1];
      int max = Integer.MIN_VALUE;
      for (int element : array) {
        max = Math.max(max, element);
      }
      ret[0] = max;
      return ret;
    }

    Deque<Integer> deque = new LinkedList<>();
    int[] result = new int[array.length - k + 1];
    for (int i = 0; i < array.length; i++) {
      // list of potential candidate
      while (!deque.isEmpty() && array[deque.getLast()] < array[i]) {
        deque.removeLast();
      }
      deque.addLast(i);

      // flush candidate to result array
      if (i >= k - 1) {
        // no need to using while => only if : remove the farthest element
        while (!deque.isEmpty() && (i - deque.getFirst() + 1 > k)) {
          deque.removeFirst();
        }
        result[i - k + 1] = array[deque.getFirst()];
      }
    }

    return result;
  }
}
