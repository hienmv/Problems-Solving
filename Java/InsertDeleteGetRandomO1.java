// https://leetcode.com/problems/insert-delete-getrandom-o1/
// #array #hash-table #design
class RandomizedSet {
  /*
  use an arraylist => get idx
  use map => key: value, value:idx

  remove: swap removed element with last element in arraylist
  */
  ArrayList<Integer> list;
  int lastIdx;
  HashMap<Integer, Integer> map;

  /** Initialize your data structure here. */
  public RandomizedSet() {
    list = new ArrayList<>();
    lastIdx = -1;
    map = new HashMap<>();
  }

  /**
   * Inserts a value to the set. Returns true if the set did not already contain the specified
   * element.
   */
  public boolean insert(int val) {
    if (map.containsKey(val)) {
      return false;
    }
    lastIdx++;
    if (list.size() > lastIdx) {
      list.set(lastIdx, val);
    } else {
      list.add(val);
    }
    map.put(val, lastIdx);
    return true;
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
    if (!map.containsKey(val) || lastIdx < 0) {
      return false;
    }
    int idx = map.get(val);
    map.remove(val);
    int lastValue = list.get(lastIdx);
    list.set(idx, lastValue);
    map.replace(lastValue, idx);
    lastIdx--;

    return true;
  }

  /** Get a random element from the set. */
  public int getRandom() {
    Random rand = new Random();
    int upperbound = lastIdx + 1;
    int idx = rand.nextInt(upperbound);
    return list.get(idx);
  }
}

/**
 * Your RandomizedSet object will be instantiated and called as such: RandomizedSet obj = new
 * RandomizedSet(); boolean param_1 = obj.insert(val); boolean param_2 = obj.remove(val); int
 * param_3 = obj.getRandom();
 */
