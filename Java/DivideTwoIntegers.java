// https://leetcode.com/problems/divide-two-integers/
// #math #binary-search #todo
class Solution {
    /*
    idea: re-implement //
            a = kb + c (c < b)
            result = k
            
            kb = 2* (k/2) * b = (k/2) * (2*b)
    */
    public int divide(int dividend, int divisor) {
        // corner case
        if (dividend == Integer.MAX_VALUE && divisor == -1) {
            return Integer.MIN_VALUE + 1;
        }
        if (dividend == Integer.MIN_VALUE && divisor == 1) {
            return Integer.MIN_VALUE;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (dividend == Integer.MAX_VALUE && divisor == 1) {
            return Integer.MAX_VALUE;
        }
        
        
        boolean flip = false;
        if (dividend < 0 && divisor > 0) {
            flip = true;
        }
        if (dividend > 0 && divisor < 0) {
            flip = true;
        }
        if (dividend < 0) {
            dividend = -dividend;
        }
        if (divisor < 0) {
            divisor = -divisor;
        }
System.out.println("fff " + dividend + ", " + divisor);
        int[] array = new int[3]; 
        array[0] = dividend;
        array[1] = divisor;
        
        int result = 0;
        while (array[0] >= array[1]) {
            helper(array);
            array[1] = divisor;
            result += array[2];
            array[2] = 0;
            System.out.println("fff " + Arrays.toString(array));
        }
        
        if (flip) {
            result = -result;
        }
        return result;
    }
    
    private void helper(int[] array) {
        int shift = 0;
        while (array[0] >= array[1]) {
            array[2] += 1 << shift; //count
            array[0] -= array[1]; // dividend 
            array[1] = array[1] << 1; // divisor
            shift += 1;
            if (array[1] == Integer.MIN_VALUE) {
                break;
            }
            System.out.println("HHH " + Arrays.toString(array));
        }
    }
    
}