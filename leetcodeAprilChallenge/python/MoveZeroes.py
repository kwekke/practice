class Solution:
    def moveZeroes(self, nums: List[int]) -> None:
        i = 0
        j = 0
        while i < len(nums):
            if nums[i] != 0:
                nums[j] = nums[i]
                j += 1
            i += 1

        i = j
        while (i < len(nums)):
            nums[i] = 0
            i += 1

    def moveZeroes2(self, nums: List[int]) -> None:
        i = 0
        for n in nums:
            if n != 0:
                nums[i] = n
            i += 1
        while i < len(nums):
            nums[i] = 0
            i += 1
