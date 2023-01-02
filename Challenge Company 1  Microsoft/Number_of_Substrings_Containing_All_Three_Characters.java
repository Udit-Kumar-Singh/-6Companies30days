/*
Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.

 

Example 1:

Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 
Example 2:

Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb". 
Example 3:

Input: s = "abc"
Output: 1
 

Constraints:

3 <= s.length <= 5 x 10^4
s only consists of a, b or c characters.

*/

class Number_of_Substrings_Containing_All_Three_Characters {
    public static int numberOfSubstrings(String s) {
        int[] count = new int[3];
        int ans = 0;
        for (int lo = -1, hi = 0; hi < s.length(); ++hi) {
            ++count[s.charAt(hi) - 'a'];
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                ans += s.length() - hi; // number of valid substrings all start from lo + 1, but end at hi, hi + 1, ..., s.length() - 1, respectively.
                --count[s.charAt(++lo) - 'a'];
            }
        } 
        return ans;        
    }


    
     public static void main(String[] args){
        String s= "abcabc";
        int ans = numberOfSubstrings(s);
        System.out.println(ans);
     }

}