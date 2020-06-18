class Solution:
    def lastStoneWeight(self, stones: List[int]) -> int:
        while len(stones) > 1:
            stones.append(stones.pop(stones.index(max(stones))) -
                          stones.pop(stones.index(max(stones))))
        return stones[0]

    def lastStoneWeight2(self, stones: List[int]) -> int:
        if len(stones) == 1:
            return stones[0]
        stones = sorted(stones)
        y = stones.pop()
        x = stones.pop()
        if len(stones) == 0:
            return y - x
        elif y - x > 0:
            stones.append(y-x)
        return self.lastStoneWeight(stones)
