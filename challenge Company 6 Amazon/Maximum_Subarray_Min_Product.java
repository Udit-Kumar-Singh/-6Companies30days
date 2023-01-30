/*
 
The min-product of an array is equal to the minimum value in the array multiplied by the array's sum.

For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 * (3+2+5) = 2 * 10 = 20.
Given an array of integers nums, return the maximum min-product of any non-empty subarray of nums. Since the answer may be large, return it modulo 109 + 7.

Note that the min-product should be maximized before performing the modulo operation. Testcases are generated such that the maximum min-product without modulo will fit in a 64-bit signed integer.

A subarray is a contiguous part of an array.

 

Example 1:

Input: nums = [1,2,3,2]
Output: 14
Explanation: The maximum min-product is achieved with the subarray [2,3,2] (minimum value is 2).
2 * (2+3+2) = 2 * 7 = 14.
Example 2:

Input: nums = [2,3,3,1,2]
Output: 18
Explanation: The maximum min-product is achieved with the subarray [3,3] (minimum value is 3).
3 * (3+3) = 3 * 6 = 18.
Example 3:

Input: nums = [3,1,5,6,4,2]
Output: 60
Explanation: The maximum min-product is achieved with the subarray [5,6,4] (minimum value is 4).
4 * (5+6+4) = 4 * 15 = 60.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 107

 */
import java.util.*;

public class Maximum_Subarray_Min_Product {
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
       int mod = (int)(1e9 + 7);
       if (n == 1) return (int)(((long)nums[0] * (long)nums[0]) % mod);
       int[] left = new int[n];
       Stack<Integer> st = new Stack<>();
       for (int i = 0; i < n; i++) {
           while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
               st.pop();
           }
           if (st.isEmpty()) left[i] = -1;
           else left[i] = st.peek();
           st.add(i);
       }
       int[] right = new int[n];
       st = new Stack<>();
       for (int i = n-1; i >= 0; i--) {
           while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
               st.pop();
           }
           if (st.isEmpty()) right[i] = n;
           else right[i] = st.peek();
           st.add(i);
       }

       long res = 0L;
       long[] preSum = new long[n];
       preSum[0] = (long)nums[0];
       for (int i = 1; i < n; i++) {
           preSum[i] = preSum[i-1] + (long)nums[i];
       }
       for (int i = 0; i < n; i++) {
           long sum = left[i] == -1 ? preSum[right[i]-1] : preSum[right[i]-1] - preSum[left[i]];
           long cur = (long)nums[i] * sum;
           res = Math.max(cur, res);
       }
       return (int)(res % mod);
   }
}
