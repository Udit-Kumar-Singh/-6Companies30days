/*
 
You are given two string arrays creators and ids, and an integer array views, all of length n. The ith video on a platform was created by creator[i], has an id of ids[i], and has views[i] views.

The popularity of a creator is the sum of the number of views on all of the creator's videos. Find the creator with the highest popularity and the id of their most viewed video.

If multiple creators have the highest popularity, find all of them.
If multiple videos have the highest view count for a creator, find the lexicographically smallest id.
Return a 2D array of strings answer where answer[i] = [creatori, idi] means that creatori has the highest popularity and idi is the id of their most popular video. The answer can be returned in any order.

 

Example 1:

Input: creators = ["alice","bob","alice","chris"], ids = ["one","two","three","four"], views = [5,10,5,4]
Output: [["alice","one"],["bob","two"]]
Explanation:
The popularity of alice is 5 + 5 = 10.
The popularity of bob is 10.
The popularity of chris is 4.
alice and bob are the most popular creators.
For bob, the video with the highest view count is "two".
For alice, the videos with the highest view count are "one" and "three". Since "one" is lexicographically smaller than "three", it is included in the answer.
Example 2:

Input: creators = ["alice","alice","alice"], ids = ["a","b","c"], views = [1,2,2]
Output: [["alice","b"]]
Explanation:
The videos with id "b" and "c" have the highest view count.
Since "b" is lexicographically smaller than "c", it is included in the answer.
 

Constraints:

n == creators.length == ids.length == views.length
1 <= n <= 105
1 <= creators[i].length, ids[i].length <= 5
creators[i] and ids[i] consist only of lowercase English letters.
0 <= views[i] <= 105

 */

 import java.util.*;

public class Most_Popular_Video_Creator {
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        Map<String, List<String>> creatorsWithIds = new HashMap<>();
        Map<String, Long> creatorsWithViews = new HashMap<>();
        Map<String, Map<String, Integer>> creatorsWithSeparate = new HashMap<>();
        int count = creators.length;

        for(int i = 0; i < count; i++){
            creatorsWithIds.putIfAbsent(creators[i],new ArrayList<>());
            creatorsWithIds.get(creators[i]).add(ids[i]);
            creatorsWithViews.put(creators[i], creatorsWithViews.getOrDefault(creators[i], 0l)+ views[i]);
            creatorsWithSeparate.putIfAbsent(creators[i], new HashMap<>());
            creatorsWithSeparate.get(creators[i]).put(ids[i], views[i]);
        }

        long highestView = creatorsWithViews.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .findFirst().get().getValue();

        List<String> keys = creatorsWithViews.entrySet().stream().filter(x -> x.getValue() == highestView).map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<List<String>> finalList = new ArrayList<>();

        keys.forEach(x -> {
            List<String> l = new ArrayList<>();
            l.add(x);
            int val = creatorsWithSeparate.get(x).entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                            .findFirst().get().getValue();
            l.add(creatorsWithSeparate.get(x).entrySet().stream().filter(y -> y.getValue() == val)
                    .map(Map.Entry::getKey)
                    .sorted().findFirst().get());
            //l.add(creatorsWithIds.get(x).stream().sorted(Comparator.reverseOrder()).findFirst().get());
            finalList.add(l);
        });
        return finalList;
    }
}
