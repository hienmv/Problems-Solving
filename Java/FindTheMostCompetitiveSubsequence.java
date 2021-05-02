// https://leetcode.com/problems/find-the-most-competitive-subsequence/
// #stack #heap #greedy #queue
class Solution {
    public int[] mostCompetitive(int[] array, int k) {
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {

          while(!deque.isEmpty() && array[i] < array[deque.getLast()] && (deque.size() - 1 + array.length - i >= k)) {
            deque.removeLast();
          }
          deque.addLast(i);
        }

        int[] result = new int[k];
        for(int i = 0; i < k; i++) {
          result[i] = array[deque.getFirst()];
          deque.removeFirst();
        }

        return result;
    }
}