class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        my_dict = dict()
        for string in strs:
            # tmp = ''.join(sorted(string))
            t = list(string)
            t.sort()
            tmp = "".join(t)

            if tmp in my_dict:
                my_dict[tmp].append(string)
            else:
                my_dict[tmp] = [string]

        return list(my_dict.values())
