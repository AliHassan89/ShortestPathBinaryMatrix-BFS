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
        int rows = grid.length;
        int cols = grid[0].length;

        if (grid[0][0] == 1 || grid[rows-1][cols-1] == 1)
            return -1;

        Queue<Cell> queue = new LinkedList<>();
        queue.offer(new Cell(0, 0, 1));
        grid[0][0] = 1;
        int minPathLen = Integer.MAX_VALUE;

        int[] xaxis = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] yaxis = {-1, 0, 1, -1, 1, -1, 0, 1};

        while (!queue.isEmpty())
        {
            Cell currCell = queue.poll();
            if (currCell.x == rows-1 && currCell.y == cols - 1) {
                minPathLen = Math.min(minPathLen, currCell.lenSoFar);
            }
            for (int i=0; i<xaxis.length; i++)
            {
                int x = currCell.x + xaxis[i];
                int y = currCell.y + yaxis[i];
                if (canMove(grid, x, y))
                {
                    queue.offer(new Cell(currCell.x + xaxis[i], currCell.y + yaxis[i], currCell.lenSoFar + 1));
                    grid[currCell.x + xaxis[i]][currCell.y + yaxis[i]] = 1;
                }
            }
        }

        return minPathLen == Integer.MAX_VALUE ? -1 : minPathLen;
    }

    private boolean canMove(int[][] grid, int newX, int newY)
    {
        if (newX >= grid.length || newY >= grid[0].length || newX < 0 || newY < 0 || grid[newX][newY] == 1)
            return false;

        return true;
    }

    private static class Cell {
        public int x, y, lenSoFar;
        public Cell(int x, int y, int lenSoFar)
        {
            this.x = x;
            this.y = y;
            this.lenSoFar = lenSoFar;
        }
    }
}
