// https://www.interviewbit.com/problems/next-permutation/
// #array
public class Solution {
    public int[] nextPermutation(int[] arr) {
    int k = arr.length - 1;
      while(k > 0 && arr[k] <= arr[k-1]){
        k--;
      }

      if (k != 0) {
        //  2 3 6 5 4 1
        int target = k-1;
        int left = k;
        int right = arr.length-1;
        while(left <= right) {
          int mid = left + (right - left) / 2;
          if (arr[mid] > arr[k-1]) {
            target = mid;
            left = mid + 1;
          } else {
            right = mid - 1;
          }
        }
        // swap
        int temp = arr[k-1];
        arr[k-1] = arr[target];
        arr[target] = temp;
      }
      
      // revert [k, n)
      for(int i=k, j=arr.length - 1; i < j; i++, j--) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
      return arr;
    }
}
