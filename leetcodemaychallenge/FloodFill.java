
/*
https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/535/week-2-may-8th-may-14th/3326/
An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

To perform a "flood fill", consider the starting pixel, 
plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, 
plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. 
Replace the color of all of the aforementioned pixels with the newColor.

At the end, return the modified image.
Note:

The length of image and image[0] will be in the range [1, 50].
The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
==============================================================================================================================
1)BFS
implemented using queue
2)DFS
*/
import java.util.*;

public class FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int r = image.length;
        int c = image[0].length;

        Queue<MyTuple> q = new LinkedList<MyTuple>();
        int initialColor = image[sr][sc];
        q.add(new MyTuple(sr, sc));
        while (!q.isEmpty()) {
            MyTuple t = q.poll();
            if (image[t.x][t.y] == newColor) {

            } else {
                image[t.x][t.y] = newColor;
                // left
                if (t.y > 0 && image[t.x][t.y - 1] == initialColor) {
                    q.add(new MyTuple(t.x, t.y - 1));
                }
                // right
                if (t.y < c - 1 && image[t.x][t.y + 1] == initialColor) {
                    q.add(new MyTuple(t.x, t.y + 1));
                }
                // up
                if (t.x > 0 && image[t.x - 1][t.y] == initialColor) {
                    q.add(new MyTuple(t.x - 1, t.y));
                }
                if (t.x < r - 1 && image[t.x + 1][t.y] == initialColor) {
                    q.add(new MyTuple(t.x + 1, t.y));
                }
            }
        }
        return image;
    }

    class MyTuple {
        int x;
        int y;

        MyTuple(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[sr][sc] == newColor) {
            return image;
        }
        int r = image.length;
        int c = image[0].length;

        int startColor = image[sr][sc];
        dfs(image, sr, sc, startColor, newColor);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int startColor, int newColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != startColor) {
            return;
        }

        if (image[sr][sc] == startColor) {
            image[sr][sc] = newColor;
            dfs(image, sr - 1, sc, startColor, newColor);
            dfs(image, sr + 1, sc, startColor, newColor);
            dfs(image, sr, sc - 1, startColor, newColor);
            dfs(image, sr, sc + 1, startColor, newColor);
        }
    }
}
