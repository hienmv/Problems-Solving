/*
https://codeforces.com/problemset/problem/1105/C
n l r
2 1 3

=> 3
[l, r]

sum of elements divided by 3.
=> [l * n, r * n]
1.      % 3 == 0
2.   k:    arr[k - 1] + 3 = arr[k]      

=> 


divisible by 3:
    numWays(n, 0):
        (x % 3)  numWays(n - 1, (3 - (x % 3)) % 3)

    O(n * (r - l + 1))
    
    
  x -> [l, r] : x % 3 -> 0, 1, 2
  
  
  [l, r ] = > 0 .... m
              1
              2

*/