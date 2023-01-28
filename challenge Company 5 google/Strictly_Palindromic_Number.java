/*
 
An integer n is strictly palindromic if, for every base b between 2 and n - 2 (inclusive), the string representation of the integer n in base b is palindromic.

Given an integer n, return true if n is strictly palindromic and false otherwise.

A string is palindromic if it reads the same forward and backward.

 

Example 1:

Input: n = 9
Output: false
Explanation: In base 2: 9 = 1001 (base 2), which is palindromic.
In base 3: 9 = 100 (base 3), which is not palindromic.
Therefore, 9 is not strictly palindromic so we return false.
Note that in bases 4, 5, 6, and 7, n = 9 is also not palindromic.
Example 2:

Input: n = 4
Output: false
Explanation: We only consider base 2: 4 = 100 (base 2), which is not palindromic.
Therefore, we return false.

 

Constraints:

4 <= n <= 105

 */

public class Strictly_Palindromic_Number {
    public boolean isStrictlyPalindromic(int n) {
        //     for(int i=2;i<=n-2;i++){
        //       if( !checkPalindrome(Integer.toString(n,i)) )   
        //       return false;
        //   }
        //   return true;
        // }
        
        // public boolean checkPalindrome(String s){
            
        //     for(int i=0;i<s.length()/2;i++){
                
        //         if( !(s.charAt(i) == s.charAt(s.length()-i-1)) )
        //         return false;
        //     }
        //     return true;
        return false; 
    }
}
