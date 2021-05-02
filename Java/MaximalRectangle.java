// https://leetcode.com/problems/maximal-rectangle/
// #array #hash-table #dynamic-programming #stack
/*
[
    ["0","1","1","0","1"],
    ["1","1","0","1","0"],
    ["0","1","1","1","0"],
    ["1","1","1","1","0"],
    ["1","1","1","1","1"],
    ["0","0","0","0","0"]
]

*/
/*
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]

dpY: [
  [1, 0, 1, 0, 0]
  [2, 0, 2, 1, 1]
  [3, 1, 3, 2, 2]
  [4, 0, 0, 3, 0]
]

 3, 1, 3, 2, 2
 
*/
/*
   _   _
  | | | |___
  | |_| | | |
  | | | | | |

0 * 1 * * 2

0 1 1 2

/* https://leetcode.com/problems/largest-rectangle-in-histogram/ */
/*
- nhieu bien
-> thu co dinh tung bien.
*/
class Solution {
    public int largestRectangleArea(int[] heights, int n) {
        int result = 0;
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for (int i = 1; i <= n; i++) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            int prev = st.isEmpty() ? 0 : st.peek();
            result = Math.max(result, heights[i] * (i - prev));
            st.push(i);
        }
        
        while (!st.isEmpty()) {
            int idx = st.pop();
            int prev = st.isEmpty() ? 0 : st.peek();
            result = Math.max(result, heights[idx] * (n - prev));
        }

        return result;
    }
  
    // fixed one dimension => [x, 1] || [1, y]
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        
        int[] dp = new int[n+1];
        int result = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[j] += 1;
                }
                else {
                    dp[j] = 0;
                }
            }
            result = Math.max(result, largestRectangleArea(dp, n));
        }
        return result;
    }
}

