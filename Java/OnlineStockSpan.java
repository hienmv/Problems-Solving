// https://leetcode.com/problems/online-stock-span/
// #stack
class StockSpanner {
  /* User array to store consecutive days at i-th => LOOP TO CHECK
  [100, 65, 60, 70, 60, 75, 85],
    1.   1.  1.  3.  1.  5   6
  */
  /*
  optimize: use stack instead of list, and only store the greater item than current.
  65 60 70 60 => not need anymore after current 75
  */
  private ArrayList<Integer> stockSpanList;
  private ArrayList<Integer> stockPriceList;

  public StockSpanner() {
    stockSpanList = new ArrayList<>();
    stockPriceList = new ArrayList<>();
  }

  public int next(int price) {
    if (stockPriceList.isEmpty()) {
      stockPriceList.add(price);
      stockSpanList.add(1);
      return 1;
    }
    int idx = stockPriceList.size() - 1;
    int ret = 1;
    while (idx >= 0) {
      if (stockPriceList.get(idx) <= price) {
        ret += stockSpanList.get(idx);
        idx -= stockSpanList.get(idx);
      } else {
        break;
      }
    }
    stockPriceList.add(price);
    stockSpanList.add(ret);
    return ret;
  }
}

/**
 * Your StockSpanner object will be instantiated and called as such: StockSpanner obj = new
 * StockSpanner(); int param_1 = obj.next(price);
 */
