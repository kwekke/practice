class Solution:
    def backspaceCompare(self, S: str, T: str) -> bool:
        i = len(S) - 1
        j = len(T) - 1
        b1 = b2 = 0
        while (i >= 0 or j >= 0):
            while (i >= 0 and (S[i] == '#' or b1 > 0)):
                if S[i] == '#':
                    b1 += 1
                else:
                    b1 -= 1
                i -= 1
            while (j >= 0 and (T[j] == '#' or b2 > 0)):
                if T[j] == '#':
                    b2 += 1
                else:
                    b2 -= 1
                j -= 1

            if i == -1 and j == -1:
                return True
            if i == -1 or j == -1:
                return False
            if S[i] != T[j]:
                return False
            i -= 1
            j -= 1
        return True
