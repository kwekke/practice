class Solution:
    def happyNumber(self, n):
        while (n != 1 and n != 4):
            sum = 0
            while (n >= 10):
                sum += (n % 10) ** 2
                n /= 10
            sum += n * n
            n = sum
        return n == 1

    def happyNumberSet(self, n):
        sets = set([n])
        re = n
        while 1:
            re = self.helper(re)
            if re == 1:
                return True
            if re in sets:
                return False
            sets.add(re)

    def helper(self, n):
        res = 0
        while n != 0:
            res += (n % 10) ** 2
            n //= 10
        return res
