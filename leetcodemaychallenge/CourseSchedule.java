
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/538/week-5-may-29th-may-31st/3344/
There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
Constraints:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
1 <= numCourses <= 10^5

Hint #1  
This problem is equivalent to finding if a cycle exists in a directed graph. 
If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.

Hint #2  
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
https://class.coursera.org/algo-003/lecture/52

Hint #3  
Topological sort could also be done via BFS.
https://en.wikipedia.org/wiki/Topological_sorting#Algorithms

================================================================================================================
1) dfs check for cycle


*/
import java.util.*;

public class CourseSchedule {
    class Solution1 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            Map<Integer, List<Integer>> adjList = new HashMap();
            for (int i = 0; i < numCourses; i++) {
                adjList.put(i, new ArrayList());
            }
            for (int[] dir : prerequisites) {
                int nextCourse = dir[0];
                int prerequisite = dir[1];
                adjList.get(prerequisite).add(nextCourse);
            }
            int[] visited = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                if (visited[i] == 0) {
                    if (!dfs(adjList, visited, i)) {
                        return false;
                    }
                }
            }
            return true;
        }

        public boolean dfs(Map<Integer, List<Integer>> adjList, int[] visited, int key) {
            if (visited[key] == 2) {
                return true;
            }
            // if this node has been checked ,skip

            if (visited[key] == 1) {
                return false;
            }
            // if during the dfs this key get visted twice, cycle

            visited[key] = 1;
            // visiting
            if (!adjList.containsKey(key)) {
                visited[key] = 2;
                return true;
                // if no dependents
            }
            for (int dependency : adjList.get(key)) {
                if (!dfs(adjList, visited, dependency)) {
                    return false;
                }
                // check all the incoming dependencies
            }
            visited[key] = 2;
            return true;
        }
    }

    class Solution2 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            if (numCourses == 0 || prerequisites.length == 0) {
                return true;
            }
            ArrayList[] graph = new ArrayList[numCourses];
            // populating the graph
            for (int i = 0; i < numCourses; i++) {
                graph[i] = new ArrayList<Integer>();
            }
            // populating the prerequisites
            for (int i = 0; i < prerequisites.length; i++) {
                graph[prerequisites[i][1]].add(prerequisites[i][0]);
            }
            int[] visited = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                if (!dfs(i, graph, visited)) {
                    return false;
                }
            }
            return true;
        }

        private boolean dfs(int course, ArrayList[] children, int[] visited) {
            if (visited[course] == 1) {
                return false;
            }
            visited[course] = 1;
            for (int i = 0; i < children[course].size(); i++) {
                int val = (int) children[course].get(i);
                if (visited[val] == 1) {
                    return false;
                }
                if (visited[val] == 0 && !dfs(val, children, visited)) {
                    return false;
                }
            }
            visited[course] = 2;
            return true;
        }
    }

    /*
     * input takes in number of courses, number of edges, and the edges
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCourses = sc.nextInt();
        int numEdges = sc.nextInt();
        int[][] prerequisites = new int[numEdges][2];
        for (int i = 0; i < numEdges; i++) {
            prerequisites[i][0] = sc.nextInt();
            prerequisites[i][1] = sc.nextInt();
        }
        sc.close();
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.canFinish(numCourses, prerequisites));
    }
}