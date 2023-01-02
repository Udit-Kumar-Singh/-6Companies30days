/*
Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.

 

Example 1:

Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.
Example 2:

Input: nums = [1,2,4,8]
Output: [1,2,4,8]
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 2 * 109
All the integers in nums are unique.

*/
class  Largest_Divisible_Subset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int[] lis = new int[nums.length];
        int[] hash = new int[nums.length];
        Arrays.fill(hash, -1);
        Arrays.sort(nums);
        int maxIdx = 0;
        for(int i=1; i<nums.length; ++i){
            for(int j=0; j<i; ++j){
                if(nums[i]%nums[j] == 0 && lis[j]+1 > lis[i]){
                    lis[i] = lis[j]+1;
                    hash[i] = j;
                    if(lis[maxIdx] < lis[i])
                        maxIdx = i;
                }
            }
        }
        
        return buildSeq(nums, hash, maxIdx);
    }
    
    public List<Integer> buildSeq(int[] nums, int[] lis, int idx){
        List<Integer> seq = new ArrayList<>();
        while(idx >=0){
            seq.add(nums[idx]);
            idx = lis[idx];
        }
        return seq;
    }
}