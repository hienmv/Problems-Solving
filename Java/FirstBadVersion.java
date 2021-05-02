// https://leetcode.com/problems/first-bad-version/
// #binary-search
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

      public class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int ret = n;
            int l = 1, r = n;
            while(l <= r) {
                int mid = l + (r - l) / 2;
                if (isBadVersion(mid)) {
                    ret = mid;
                    r = mid - 1;
                }
                else {
                    l = mid + 1;
                }
            }
            return ret;
        }
    }