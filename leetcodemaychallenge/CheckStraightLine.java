/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/535/week-2-may-8th-may-14th/3323/
You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.
Constraints:

2 <= coordinates.length <= 1000
coordinates[i].length == 2
-10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
coordinates contains no duplicate point.
=====================================================================================================================
1) One pass
Check if every other point results the same gradient to first point
*/

import java.util.*;

public class CheckStraightLine {
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates == null || coordinates.length <= 2) {
            return true;
        }

        int x0 = coordinates[0][0];
        int y0 = coordinates[0][1];
        int x1 = coordinates[1][0];
        int y1 = coordinates[1][1];
        double grad = (1.0 * y1 - y0) / (x1 - x0);
        for (int i = 2; i < coordinates.length; i++) {
            int xi = coordinates[i][0];
            int yi = coordinates[i][1];
            if (((1.0 * yi - y0) / (xi - x0)) != grad) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        sc.nextLine();
        int[][] coordinates = new int[c][2];
        for (int i = 0; i < c; i++) {
            String[] point = sc.nextLine().split(" ");
            coordinates[i][0] = Integer.parseInt(point[0]);
            coordinates[i][1] = Integer.parseInt(point[1]);
        }
        sc.close();

        CheckStraightLine csl = new CheckStraightLine();
        System.out.println(csl.checkStraightLine(coordinates));
    }
}