
/*
https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3360/
There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Constraints:
The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.
============================================================================================================
1) dfs limited depth
*/
import java.util.*;

public class CheapestFlightsWithinKStops {
    class Solution {
        int minPathCost;

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            minPathCost = Integer.MAX_VALUE; // initialize the minPathCost with Integer.MAX_VALUE
            Map<Integer, List<int[]>> graph = createGraph(flights); // create graph

            dfs(graph, src, dst, K + 1, 0); // K stop => K + 1 edges
            return minPathCost == Integer.MAX_VALUE ? -1 : minPathCost;
        }

        private void dfs(Map<Integer, List<int[]>> graph, int src, int dst, int k, int currentCost) {
            if (k < 0)
                return; // if covered k stops and not reached destination
            if (src == dst) { // if reached destination currentCost is minPathCost
                minPathCost = currentCost;
                return;
            }
            if (!graph.containsKey(src))
                return;
            for (int[] flight : graph.get(src)) {
                if (currentCost + flight[1] > minPathCost) // cost + current flight cost > minPathCost discard that path
                                                           // ~ pruning
                    continue;
                dfs(graph, flight[0], dst, k - 1, currentCost + flight[1]);
            }
        }

        private Map<Integer, List<int[]>> createGraph(int[][] flights) {
            Map<Integer, List<int[]>> graph = new HashMap<>(); // direct stops + cost to reach that stop
            for (int[] flight : flights) {
                graph.putIfAbsent(flight[0], new ArrayList<>());
                graph.get(flight[0]).add(new int[] { flight[1], flight[2] });
            }
            return graph;
        }
    }
}