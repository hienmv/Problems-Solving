// https://leetcode.com/problems/bitwise-and-of-numbers-range/
// #bit-manipulation
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        // 1111000 <- remove Least significant: '1' 
        while (n > m) {
            // reduce extremely n
            // log n 
            n = n & (n - 1);
        }
        return n & m;
    }
    
    /* Time limited
    public int rangeBitwiseAnd(int m, int n) {
        int res = m;
        // simplest AND BIT in range [m, n];
        for (int i = m; i <= n; i++) {
            // liner
            res = res & i;
        }
        return res;
    }
    */
    /*
    1000
    0111
        => 0
    
        |
    1XXX10000
    1XXX1YYYY
    100
    101
    
        n <= m
        1xx000
        1xxyyy
    
    => m
     |
    100  
    101
    110
    111
    
         110 => 
                 |
         100 => 10X => 
         |
         0XX

           
  m        100100000
           1001XXXXX
  n        100111111
          
          
          
    
    
   12 -> 9
    |     |
    1100  10XX
    1011
    1010
    1001      
       
  
       
       |
    1100
    1111
    0111
    
    /*
    5->9
    9 & 8 & 7 & 6 & 5
       
       1
    1101
    1100
    1011
    1010
    1001
    1000
    0111
    
    m = 7 => n = 13
    
    |
1:  1100
2:  1000
    10XX
     
3:  0000
    0XXX
    
        => 1000 => 8
            0XXX
    
    n & (n-1)
    => xoá bit có giá trị là 1 đầu tiên của input (n)

    n = 1010
      = 1001
      
      => n & (n-1) = 1000
 
    => 
    */
}