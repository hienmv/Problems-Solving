// https://leetcode.com/problems/first-unique-number/
// #hash-table #design
class FirstUnique {
  private Deque<Integer> uniqueQueue;
  private HashSet<Integer> duplicateSet;
  private HashSet<Integer> uniqueSet;

  private void internalAdd(int num) {
    if (uniqueSet.isEmpty() || !uniqueSet.contains(num)) {
      uniqueSet.add(num);
      uniqueQueue.addLast(num);
    } else {
      duplicateSet.add(num);
    }
  }

  public FirstUnique(int[] nums) {
    uniqueQueue = new LinkedList<>();
    uniqueSet = new HashSet<>();
    duplicateSet = new HashSet<>();
    for (int num : nums) {
      internalAdd(num);
    }
  }

  public int showFirstUnique() {
    int ret = -1;
    while (!uniqueQueue.isEmpty()) {
      int v = uniqueQueue.peekFirst();
      if (duplicateSet.contains(v)) {
        uniqueQueue.pollFirst();
      } else {
        ret = v;
        break;
      }
    }

    return ret;
  }

  public void add(int value) {
    internalAdd(value);
  }
}

/**
 * Your FirstUnique object will be instantiated and called as such: FirstUnique obj = new
 * FirstUnique(nums); int param_1 = obj.showFirstUnique(); obj.add(value);
 */
