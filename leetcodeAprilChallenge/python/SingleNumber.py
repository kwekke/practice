class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        ans = 0
        for i in nums:
            ans ^= i
        return ans

    def singleNumberDict(self, nums: List):
        my_dict = dict()
        for i in nums:
            if i in my_dict:
                my_dict.pop(i)
            else:
                my_dict[i] = 1

        for i in my_dict:
            return i
