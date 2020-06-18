
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/537/week-4-may-22nd-may-28th/3342/
Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
Each person may dislike some other people, and they should not go into the same group. 
Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
Return true if and only if it is possible to split everyone into two groups in this way.

Note:
1 <= N <= 2000
0 <= dislikes.length <= 10000
1 <= dislikes[i][j] <= N
dislikes[i][0] < dislikes[i][1]
There does not exist i != j for which dislikes[i] == dislikes[j].
============================================================================================================

1) dfs and 2 coloring algo

2) people that dislikes anyone in the other group, is in the same group

*/
import java.util.*;

public class PossibleBipartition {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> adjList = new HashMap();
        for (int i = 1; i <= N + 1; i++) {
            adjList.put(i, new ArrayList());
        }
        for (int[] dislike : dislikes) {
            int start = dislike[0];
            int end = dislike[1];

            adjList.get(start).add(end);
            adjList.get(end).add(start);
        }
        boolean[] discovered = new boolean[N + 1];
        boolean[] color = new boolean[N + 1];

        discovered[1] = true;
        color[1] = false;
        for (int i = 1; i <= N; i++) {
            if (!dfs(adjList, i, discovered, color))
                return false;
        }
        return true;
    }

    public boolean dfs(Map<Integer, List<Integer>> adjList, int v, boolean[] discovered, boolean[] color) {
        for (int neighbor : adjList.get(v)) {
            if (!discovered[neighbor]) {
                discovered[neighbor] = true;
                color[neighbor] = !color[v];
                if (!dfs(adjList, neighbor, discovered, color)) {
                    return false;
                }
            } else if (color[neighbor] == color[v]) {
                return false;
            }
        }
        return true;
    }

    public boolean possibleBipartition2(int N, int[][] dislikes) {
        int[] group = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            group[i] = i; // initial
        }

        for (int[] dislike : dislikes) {
            int a = dislike[0];
            int b = dislike[1];

            if (group[a] == a && group[b] == b) {
                group[a] = b;
                group[b] = a;
            } else if (group[a] == a && group[b] != b) {
                // let a go to group that all hate b;
                group[a] = group[group[b]];
            } else if (group[b] == b && group[a] != a) {
                group[b] = group[group[a]];
            } else if (group[b] == group[a]) {
                return false;
            }
        }
        return true;
    }

}