/*
    
On an n x n chessboard, a knight starts at the cell (row, column) and attempts to make exactly k moves. The rows and columns are 0-indexed, so the top-left cell is (0, 0), and the bottom-right cell is (n - 1, n - 1).

A chess knight has eight possible moves it can make, as illustrated below. Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.


Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

The knight continues moving until it has made exactly k moves or has moved off the chessboard.

Return the probability that the knight remains on the board after it has stopped moving.

 

Example 1:

Input: n = 3, k = 2, row = 0, column = 0
Output: 0.06250
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.
Example 2:

Input: n = 1, k = 0, row = 0, column = 0
Output: 1.00000
 

Constraints:

1 <= n <= 25
0 <= k <= 100
0 <= row, column <= n - 1

 */



public class KnightProbability_in_Chessboard {
        public double knightProbability(int N, int K, int sr, int sc) {
            double[][] dp = new double[N][N];
            int[] dr = new int[]{2, 2, 1, 1, -1, -1, -2, -2};
            int[] dc = new int[]{1, -1, 2, -2, 2, -2, 1, -1};
    
            dp[sr][sc] = 1;
            for (; K > 0; K--) {
                double[][] dp2 = new double[N][N];
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        for (int k = 0; k < 8; k++) {
                            int cr = r + dr[k];
                            int cc = c + dc[k];
                            if (0 <= cr && cr < N && 0 <= cc && cc < N) {
                                dp2[cr][cc] += dp[r][c] / 8.0;
                            }
                        }
                    }
                }
                dp = dp2;
            }
            double ans = 0.0;
            for (double[] row: dp) {
                for (double x: row) ans += x;
            }
            return ans;
        }
    }

