/*
 
Given two positive integers left and right, find the two integers num1 and num2 such that:

left <= nums1 < nums2 <= right .
nums1 and nums2 are both prime numbers.
nums2 - nums1 is the minimum amongst all other pairs satisfying the above conditions.
Return the positive integer array ans = [nums1, nums2]. If there are multiple pairs satisfying these conditions, return the one with the minimum nums1 value or [-1, -1] if such numbers do not exist.

A number greater than 1 is called prime if it is only divisible by 1 and itself.

 

Example 1:

Input: left = 10, right = 19
Output: [11,13]
Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19.
The closest gap between any pair is 2, which can be achieved by [11,13] or [17,19].
Since 11 is smaller than 17, we return the first pair.
Example 2:

Input: left = 4, right = 6
Output: [-1,-1]
Explanation: There exists only one prime number in the given range, so the conditions cannot be satisfied.
 

Constraints:

1 <= left <= right <= 106

 */
import java.util.*;

public class Closest_Prime_Numbers_in_Range {
    public int[] closestPrimes(int left, int right) {
        boolean[] IsPrime = new boolean[right+1];
        Arrays.fill(IsPrime, true);
        IsPrime[0]=IsPrime[1]=false;

        int min = Integer.MAX_VALUE;
        //finding prime numbers in range [2,right];
        findPrimes(right,IsPrime);
        
        //storing prime numbers in range[left,right] in list
        ArrayList<Integer> primes = new ArrayList<>();
        for(int i=left;i<=right;i++)
        {
            if(IsPrime[i])
                primes.add(i);
        }
      
        int[] res=null;
        //finding two prime numbers with smallest absolute difference
        for(int i=0;i<primes.size()-1;i++)
        {
            if(primes.get(i+1)-primes.get(i)<min)
            {
                min=primes.get(i+1)-primes.get(i);
                res=new int[]{primes.get(i),primes.get(i+1)};
            }
        }
        //if no result found return [-1,-1]
        return res==null? new int[]{-1,-1}:res;
    }
    
    private void findPrimes(int n,boolean[] IsPrime)
    {
       for(int i = 2; i*i<=n; i++){
           if(IsPrime[i]){
               for(int j=i*i; j<=n; j+=i)
                   IsPrime[j] = false;
           }
       }  
    }
}
