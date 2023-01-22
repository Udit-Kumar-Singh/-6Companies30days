/*      

Given a positive integer n, return the number of the integers in the range [0, n] whose binary representations do not contain consecutive ones.

 

Example 1:

Input: n = 5
Output: 5
Explanation:
Here are the non-negative integers <= 5 with their corresponding binary representations:
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule. 
Example 2:

Input: n = 1
Output: 2
Example 3:

Input: n = 2
Output: 3
 

Constraints:

1 <= n <= 109


 */

 public class Non_negative_Integers_without_Consecutive_Ones {
    public int findIntegers(int n) {
        String binary = Integer.toBinaryString(n);
        int k = binary.length();
    
        int[] fib = new int[k+1];
        fib[0] = 1;
        fib[1] = 2;
        for(int i=2;i<=k;i++){
            fib[i] = fib[i-1]+fib[i-2];
        }
        
        boolean isLastBitOne = false;
        int res=0;
        int bit = k-1;
        while(bit>=0){
            if((n & (1<<bit))==0){
                isLastBitOne=false;
            } else {
                res+=fib[bit];
                if(isLastBitOne){
                    return res;
                }
                isLastBitOne=true;
            }
            bit--;
        }
        
        return res+1; // Since we started from one lesser bit, we add one to include 'n' itself
    }
 }
