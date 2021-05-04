// https://leetcode.com/problems/height-checker/
// #array
class Solution {
  public int heightChecker(int[] heights) {
    // // idea: use counter sort
    // int max_number_element = 101;
    // int[] counter_arr = new int[max_number_element];
    // for (int height : heights) {
    //     counter_arr[height] += 1;
    // }
    // int[] sorted_arr = new int[max_number_element];
    // int next_idx = 0;
    // for(int i=0; i < max_number_element; i++) {
    //     while(counter_arr[i] > 0) {
    //         sorted_arr[next_idx] = i;
    //         counter_arr[i] -= 1;
    //         next_idx += 1;
    //     }
    // }
    // int result = 0;
    // for (int i=0; i < heights.length; i++) {
    //     if (heights[i] != sorted_arr[i]) {
    //         result++;
    //     }
    // }
    // return result;

    // idea: use counter sort
    int max_number_element = 101;
    int[] counter_arr = new int[max_number_element];
    for (int height : heights) {
      counter_arr[height] += 1;
    }
    int next_idx = 0;
    int result = 0;
    for (int i = 0; i < max_number_element; i++) {
      while (counter_arr[i] > 0) {
        if (heights[next_idx] != i) {
          result++;
        }
        counter_arr[i] -= 1;
        next_idx += 1;
      }
    }
    return result;
  }
}
