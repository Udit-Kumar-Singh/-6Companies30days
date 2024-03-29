/*
 
Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.

 

Example 1:



Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation: 
Islands in gray are closed because they are completely surrounded by water (group of 1s).
Example 2:



Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1
Example 3:

Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2
 

Constraints:

1 <= grid.length, grid[0].length <= 100
0 <= grid[i][j] <=1
 */

public class Number_of_Closed_Islands {
    public int closedIsland(int[][] grid) {
        int cnt = 0;
        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[0].length; ++j)
                if (grid[i][j] == 0)
                    cnt += dfs(i, j, grid);
        return cnt;
    }
    
    private int dfs(int i, int j, int[][] g) {
        if (i < 0 || i >= g.length || j < 0 || j >= g[0].length)
            return 0;
        if (g[i][j] > 0)
            return 1;
        g[i][j] = 2;
        return dfs(i + 1, j, g) * dfs(i - 1, j, g) * dfs(i, j + 1, g) * dfs(i, j - 1, g);
    }  
}
