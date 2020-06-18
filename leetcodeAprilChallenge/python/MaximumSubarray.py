# Kadane's algo

import sys


class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        maxSum = nums[0]
        i = 1
        while i < len(nums):
            if nums[i - 1] > 0:
                nums[i] += nums[i - 1]
            maxSum = max(maxSum, nums[i])
            i = i+1
        return maxSum
