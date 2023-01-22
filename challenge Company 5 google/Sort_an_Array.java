/*
 
Given an array of integers nums, sort the array in ascending order and return it.

You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.

 

Example 1:

Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).
Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
Explanation: Note that the values of nums are not necessairly unique.
 

Constraints:

1 <= nums.length <= 5 * 104
-5 * 104 <= nums[i] <= 5 * 104


 */

public class Sort_an_Array {
    public int[] sortArray(int[] nums) {
        mergesort(nums, 0, nums.length - 1);
        return nums;
    }

    public void mergesort(int[] nums, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergesort(nums, start, mid);
            mergesort(nums, mid + 1, end);
            merge(nums, start, mid, end);
        }
    }

    public void merge(int[] nums, int start, int mid, int end) {
        int i = start, j = mid + 1, k = 0;
        int[] temp = new int[end - start + 1];
        while (i <= mid && j <= end) {
            if (nums[i] < nums[j])
                temp[k++] = nums[i++];
            else
                temp[k++] = nums[j++];
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        } // copy remaining elements
        while (j <= end) {
            temp[k++] = nums[j++];
        } // copy remaining elements
        for (int pointer = start; pointer <= end; pointer++) {
            nums[pointer] = temp[pointer - start];
        }
    }
}
