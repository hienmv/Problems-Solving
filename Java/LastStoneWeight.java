// https://leetcode.com/problems/last-stone-weight/
// #heap #greedy
class Solution {
    /*
        get two heaviest => max heap
    */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> (y - x));
        for (int stone : stones) {
            pq.add(stone);
        }
        while (pq.size() > 1) {
            int x = pq.poll();
            int y = pq.poll();
            if (x != y) {
                pq.add(x - y);
            }
        }
        
        int ret = 0;
        if (pq.size() > 0) {
            ret = pq.poll();
        }
        return ret;
    }
}