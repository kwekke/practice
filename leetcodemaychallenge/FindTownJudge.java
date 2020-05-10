
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/535/week-2-may-8th-may-14th/3325/
In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.

If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.
Note:

1 <= N <= 1000
trust.length <= 10000
trust[i] are all different
trust[i][0] != trust[i][1]
1 <= trust[i][0], trust[i][1] <= N
=========================================================================================================================
1) One pass
keep count of the number of incoming and outgoing relation for each vertice (person)
Time complexity: O(n)
Space Complexity: O(n)
*/
import java.util.*;

public class FindTownJudge {

    public int findJudge(int N, int[][] trust) {
        int[] isTrusted = new int[N + 1];
        int[] isTrusting = new int[N + 1];

        for (int i = 0; i < trust.length; i++) {
            isTrusting[trust[i][0]]++;
            isTrusted[trust[i][1]]++;
        }

        for (int i = 1; i < isTrusted.length; i++) {
            if (isTrusted[i] == N - 1 && isTrusting[i] == 0) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int N = sc.nextInt();
        sc.nextLine();
        int[][] trust = new int[r][2];
        for (int i = 0; i < r; i++) {
            String[] tokens = sc.nextLine().split(" ");
            trust[i][0] = Integer.parseInt(tokens[0]);
            trust[i][1] = Integer.parseInt(tokens[1]);
        }
        sc.close();
        FindTownJudge ftj = new FindTownJudge();
        System.out.println(ftj.findJudge(N, trust));
    }
}