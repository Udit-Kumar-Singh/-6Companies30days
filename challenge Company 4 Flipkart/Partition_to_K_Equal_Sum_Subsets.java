/*
 
Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.

 

Example 1:

Input: nums = [4,3,2,3,5,2,1], k = 4
Output: true
Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Example 2:

Input: nums = [1,2,3,4], k = 3
Output: false
 

Constraints:

1 <= k <= nums.length <= 16
1 <= nums[i] <= 104
The frequency of each element is in the range [1, 4].


 */
import java.util.*;

 class Partition_to_K_Equal_Sum_Subsets {
    int n, subsetSum;
   int[] memo = new int[1 << 16];
   public boolean canPartitionKSubsets(int[] nums, int k) {
       n = nums.length;
       subsetSum = Arrays.stream(nums).sum() / k;
       Arrays.fill(memo, -2);
       return dp(nums, (1 << n) - 1) == 0;
   }
   int dp(int[] nums, int mask) {
       if (memo[mask] != -2) return memo[mask];
       if (mask == 0) return 0;
       for (int i = 0; i < n; ++i) {
           if (((mask >> i) & 1) == 0) continue;
           int newMask = mask ^ (1 << i);
           int remain = dp(nums, newMask);
           if (remain == -1) continue;
           if (remain + nums[i] <= subsetSum)
               return memo[mask] = (remain + nums[i]) % subsetSum;
       }
       return memo[mask] = -1;
   }
}