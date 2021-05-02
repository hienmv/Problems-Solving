// https://leetcode.com/problems/find-the-town-judge/
// #graph
class Solution {
    public int findJudge(int N, int[][] trust) {
        int[] trusted = new int[N+1];
        for (int[] arr : trust) {
            trusted[arr[1]] += 1;
            trusted[arr[0]] -= 1;
        }
        for (int i = 1; i <= N; i++) {
            if (trusted[i] == N - 1) {
                return i;
            }
        }
        return -1;
    }
    /*
        public int findJudge(int N, int[][] trust) {
        int[] trustedP = new int[N+1];
        int[] trustP = new int[N+1];
        for (int[] arr : trust) {
            trustedP[arr[1]] += 1;
            trustP[arr[0]] += 1;
        }
        for (int i = 1; i <= N; i++) {
            if (trustP[i] == 0 && trustedP[i] == N - 1) {
                return i;
            }
        }
        return -1;
    }
    */
}