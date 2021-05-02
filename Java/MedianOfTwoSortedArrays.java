// https://leetcode.com/problems/median-of-two-sorted-arrays/
// #array #binary-search #divide-and-conquer
class Solution {
    //      |         |
    // [1,2,3,5,7] [2,4,6]
    // 1,2,2,3,4,5,6,7 len=8
    // median = 4
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        ArrayList<Integer> arr = new ArrayList<>();
        int total_len = (nums1.length + nums2.length);
        if (total_len == 0) {
            return 0;
        }
        int idx1 = 0;
        int idx2 = 0;
        int count = 0;
        int mid = total_len / 2 + 1;
        int pre = 0;
        int cur = 0;
        while(count < mid) {
            pre = cur;
            if (idx1 < nums1.length && idx2 < nums2.length) {
                if (nums1[idx1] < nums2[idx2]) {
                    cur = nums1[idx1];
                    idx1++;
                } else {
                    cur = nums2[idx2];
                    idx2++;
                }
            } else if (idx1 < nums1.length) {
                cur = nums1[idx1];
                idx1++;
            } else if (idx2 < nums2.length) {
                cur = nums2[idx2];
                idx2++;
            }
            count++;
        }

        if (total_len % 2 == 0) {
            return (pre + cur) / 2.0;
        }

        return cur;

        /*
        int total_len = (nums1.length + nums2.length);
        int median = total_len / 2;
        if (total_len % 2 == 0) {
            median += 1;
        }
        int idx1 = 0;
        int idx2 = 0;
        int count = 0;
        int number1 = nums1.length > 0 ? nums1[idx1] : 0;
        int number2 = nums2.length > 0 ? nums2[idx2] : 0;
        while(count <= median) {
            if (idx1 < nums1.length && idx2 < nums2.length) {
                if (nums1[idx1] < nums2[idx2]) {
                    number1 = nums1[idx1];
                    idx1++;
                } else {
                    number2 = nums2[idx2];
                    idx2++;
                }
            } else if (idx1 < nums1.length) {
                if (idx1 != 0) {
                    number2 = number1;
                }
                number1 = nums1[idx1];
                idx1++;
            } else if (idx2 < nums2.length) {
                if (idx2 != 0) {
                    number1 = number2;
                }
                number2 = nums2[idx2];
                idx2++;
            }
            count++;
        }
        System.out.println(number1 + ", " + number2);
        if (total_len % 2 == 0) {
            return (number1 + number2) / 2.0;
        }

        return Math.max(number1, number2);
        */
        /*
        int iter1 = nums1.length / 2;
        int iter2 = nums2.length / 2;
        while (iter1 >= 0 && iter2 >= 0) {
            if (iter1 + iter2 == median) {
                break;
            } else if (iter1 + iter2 < median) {
                iter1 += 1;
                iter2 += 1;
            } else {
                if (nums1[iter1] > nums2[iter2]) {
                    iter1 -= 1;
                } else {
                    iter2 -= 1;
                }
            }
        }
        
        System.out.println(iter1 + ", " + iter2 + ", " + median + ", " + nums1[iter1] + ", " + nums2[iter2]);
        
        if ((nums1.length + nums2.length) % 2 == 0) {
            return (nums1[iter1] + nums2[iter2]) / 2.0;
        }

        return nums1[iter1] > nums2[iter2] ? nums1[iter1] : nums2[iter2];
        */
    }
}
