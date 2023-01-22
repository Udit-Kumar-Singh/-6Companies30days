/*
 
Return the number of distinct non-empty substrings of text that can be written as the concatenation of some string with itself (i.e. it can be written as a + a where a is some string).

 

Example 1:

Input: text = "abcabcabc"
Output: 3
Explanation: The 3 substrings are "abcabc", "bcabca" and "cabcab".
Example 2:

Input: text = "leetcodeleetcode"
Output: 2
Explanation: The 2 substrings are "ee" and "leetcodeleetcode".
 

Constraints:

1 <= text.length <= 2000
text has only lowercase English letters.

 */

import java.util.*;

public class Distinct_Echo_Substrings {
    public int distinctEchoSubstrings(String text) {
        HashSet<String> set = new HashSet<>();
       int n = text.length();
       for (int i = 0; i < n; i++) {
           for (int len = 2; i + len <= n; len += 2) {
               int mid = i + len / 2;
               String subStr1 = text.substring(i, mid);
               String subStr2 = text.substring(mid, i + len);
               if (subStr1.equals(subStr2)) set.add(subStr1);
           }
       }
       return set.size();
   } 
}
