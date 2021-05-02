# https://leetcode.com/problems/hand-of-straights/
# #map
class Solution:
    def isNStraightHand(self, hand: List[int], W: int) -> bool:
        length = len(hand)
        if (length % W != 0):
            return False
        
        hand.sort() # O(nlogn)
        dic = dict()
        for v in hand: # O(n)
            if (v not in dic):
                dic[v] = 0
            dic[v] += 1
        
        for i in range(length): # O(n + n)
            if (length - i < W):
                break
            if dic[hand[i]] == 0:
                continue
            dic[hand[i]] -= 1
            for j in range(1, W):
                new_val = hand[i] + j
                if (new_val not in dic or dic[new_val] == 0):
                    return False
                dic[new_val] -= 1
        return True