/*
 
Alice manages a company and has rented some floors of a building as office space. Alice has decided some of these floors should be special floors, used for relaxation only.

You are given two integers bottom and top, which denote that Alice has rented all the floors from bottom to top (inclusive). You are also given the integer array special, where special[i] denotes a special floor that Alice has designated for relaxation.

Return the maximum number of consecutive floors without a special floor.

 

Example 1:

Input: bottom = 2, top = 9, special = [4,6]
Output: 3
Explanation: The following are the ranges (inclusive) of consecutive floors without a special floor:
- (2, 3) with a total amount of 2 floors.
- (5, 5) with a total amount of 1 floor.
- (7, 9) with a total amount of 3 floors.
Therefore, we return the maximum number which is 3 floors.
Example 2:

Input: bottom = 6, top = 8, special = [7,6,8]
Output: 0
Explanation: Every floor rented is a special floor, so we return 0.
 

Constraints:

1 <= special.length <= 105
1 <= bottom <= special[i] <= top <= 109
All the values of special are unique.

 */
import java.util.*;

public class Maximum_Consecutive_Floors_Without_Special_Floors{
    public int maxConsecutive(int bottom, int top, int[] A) {
        Arrays.sort(A);
        int n = A.length, res = Math.max(A[0] - bottom, top - A[n - 1]);
        for (int i = 1; i < n; ++i)
            res = Math.max(res, A[i] - A[i - 1] - 1);
        return res;
    }
}