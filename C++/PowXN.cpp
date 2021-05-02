// https://leetcode.com/problems/powx-n/submissions/
// #math #binary-search
class Solution {
public:

    double fastPow(double x, int n) {
        if (n==0)
            return 1.0;
       
        double haft = fastPow(x, n/2);
        if (n%2 == 0)
            return haft * haft;
        else
            return haft * haft * x;
    }
    
    double myPow(double x, int n) {
        if (x == 0)
            return 0;
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        return fastPow(x,n);      
    }
        
};