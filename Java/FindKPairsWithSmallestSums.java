// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
// #heap
/*
  // idea: initialize n elements that min value: i [0..nums1.len] & j : 0 (nums2)
    n = nums1.length;
    m = nums2.length;
    for i = 0 .. n-1:
        pq.add(nums1[i] + nums2[0], i, 0)

    (i, j) -> (i, j+1)

*/
class Solution {
  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<List<Integer>> result = new ArrayList<>();
    if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;

    PriorityQueue<Node> pq = new PriorityQueue<>();
    // min nums1 start i-th with nums2
    for (int i = 0; i < nums1.length; i++) {
      pq.add(new Node(i, 0, nums1[i] + nums2[0]));
    }

    while (!pq.isEmpty() && k > 0) {
      Node node = pq.poll();
      ArrayList<Integer> ar = new ArrayList<>();
      ar.add(nums1[node.idx1]);
      ar.add(nums2[node.idx2]);
      result.add(ar);
      k--;

      // add next value from group [i, j] => [i, j+1]
      int i = node.idx1, j = node.idx2;
      if (j + 1 < nums2.length) {
        pq.add(new Node(i, j + 1, nums1[i] + nums2[j + 1]));
      }
    }
    return result;
  }

  private static class Node implements Comparable<Node> {
    int idx1, idx2, sum;

    Node(int idx1, int idx2, int sum) {
      this.idx1 = idx1;
      this.idx2 = idx2;
      this.sum = sum;
    }

    @Override
    public int compareTo(Node other) {
      return this.sum - other.sum;
    }
  }
}
