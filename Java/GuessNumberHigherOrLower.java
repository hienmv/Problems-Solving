/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * int guess(int num);
 */
// https://leetcode.com/problems/guess-number-higher-or-lower/
// #binary-search
public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int ret = 0;
        int left = 1;
        int right = n;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            ret = guess(mid);
            if (ret < 0) {
                right = mid - 1;
            }
            else if(ret > 0) {
                left = mid + 1;
            }
            else {
                ret = mid;
                break;
            }
        }
        return ret;
    }
}