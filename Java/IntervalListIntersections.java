// https://leetcode.com/problems/interval-list-intersections/
// #two-pointer
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();

        int m = A.length;
        int n = B.length;
        int idxA = 0, idxB = 0;
        while(idxA < m && idxB < n) {
            int l = Math.max(A[idxA][0], B[idxB][0]);
            int r = Math.min(A[idxA][1], B[idxB][1]);
            if (l <= r) {
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(l);
                arr.add(r);
                ret.add(arr);
            }

            if (A[idxA][1] < B[idxB][1]) {
                idxA++;
            }
            else {
                idxB++;
            }
        }   
        
        int[][] result = new int[ret.size()][2];
        int idx = 0;
        for (ArrayList<Integer> item : ret) {
            result[idx][0] = item.get(0);
            result[idx][1] = item.get(1);
            idx++;
        }
        return result;
    }
}