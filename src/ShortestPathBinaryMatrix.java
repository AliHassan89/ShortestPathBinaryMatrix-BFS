/*
In an N by N square grid, each cell is either empty (0) or blocked (1).

A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:

Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
C_1 is at location (0, 0) (ie. has value grid[0][0])
C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.

Example 1:
Input: [[0,1],[1,0]]
Output: 2


Example 2:
Input: [[0,0,0],[1,1,0],[1,1,0]]
Output: 4


Note:
1 <= grid.length == grid[0].length <= 100
grid[r][c] is 0 or 1
 */

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1 || grid[grid.length-1][grid[0].length-1] == 1) {
            return -1;
        }

        Queue<Location> queue = new LinkedList<>();
        int[][] directions = new int[][]{{-1,-1},{-1,0}, {-1,1}, {0,-1}, {0,1}, {1,0},{1,-1}, {1,1}};
        int m = grid.length;
        int n = grid[0].length;
        int count = 1;
        queue.add(new Location(0,0));
        grid[0][0] = 1;
        boolean reached = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                Location loc = queue.poll();
                if (loc.x == m-1 && loc.y == n-1) {
                    reached = true;
                    return count;
                }
                for (int[] dir : directions) {
                    int x = loc.x + dir[0];
                    int y = loc.y + dir[1];
                    if (x >=0 && x < m && y >= 0 && y < n && grid[x][y] == 0) {
                        queue.add(new Location(x, y));
                        grid[x][y] = 1;
                    }
                }
            }
            ++count;
        }

        return reached ? count : -1;
    }

}

class Location {
    int x;
    int y;
    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
