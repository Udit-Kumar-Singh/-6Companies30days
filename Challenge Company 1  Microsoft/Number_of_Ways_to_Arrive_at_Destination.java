/*
You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.

You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.

Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.

 

Example 1:


Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
Output: 4
Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
The four ways to get there in 7 minutes are:
- 0 ➝ 6
- 0 ➝ 4 ➝ 6
- 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
- 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
Example 2:

Input: n = 2, roads = [[1,0,10]]
Output: 1
Explanation: There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.
 

Constraints:

1 <= n <= 200
n - 1 <= roads.length <= n * (n - 1) / 2
roads[i].length == 3
0 <= ui, vi <= n - 1
1 <= timei <= 109
ui != vi
There is at most one road connecting any two intersections.
You can reach any intersection from any other intersection.


*/


class Number_of_Ways_to_Arrive_at_Destination {
   public int countPaths(int n, int[][] roads) {
		List<Edge>[] graph = constructGraph(n, roads); // returns adjacency List for given road[][]

		PriorityQueue<Pair> pq = new PriorityQueue<>();
		int[] dist = new int[n]; // dist array stores the min. cost/weight/time to reach from sourceVtx(0) to every other node in the graph
		Arrays.fill(dist, Integer.MAX_VALUE); // since we want to calculate the Min. distance/time ..intialize each dist[i] with infinity.

		int[] path = new int[n]; // this is the extra storage which stores the count of paths from src to every other node in the graph

		pq.add(new Pair(0, 0)); // Pair(vtx, weight)
		dist[0] = 0; // we are starint from source : 0, therefore 0 distance/cost/time to reach 0 from 0.
		path[0] = 1; // Initial/Base Value  for source vtx : 1 path to reach source while standing on source.

		int modVal = (int)1e9 + 7; // required by question.
		while(pq.size() > 0) {
			Pair rem = pq.remove();

			for(Edge e : graph[rem.vtx]) {
				int nbr = e.nbr; // V
				int nbrdist = e.wt; // nbrdist is the wt between rem.vtx(U) and its neighbour(V)
				if(rem.wsf + nbrdist < dist[nbr]) { // if this conditions is true : it means we are on a path which reach neighbour of rem.vtx i.e U at a lesser cost/time that what is stored in the distance array for this neighbour... so update it and add neighbour im queue
					dist[nbr] = rem.wsf + nbrdist;
					path[nbr] = path[rem.vtx]; // this line means that if we can reach node : U in X ways then we can also reach the neighbours of U in X ways.... therefore path[nbr] = path[U] U here means rem.vtx
					pq.add(new Pair(nbr, dist[nbr]));
				} else if(rem.wsf + nbrdist == dist[nbr]) {
				// this check denotes that we came at this nbr previously and now again at same cost as previous time...
				// therefore we have explored a new path for reaching this nbr via current vtx i.e U....
				// therefore new ways of reaching nbr would be path[nbr] + path[rem.vtx]  (Basic DP structure, dry run it once, you will get it)
					path[nbr] += path[rem.vtx]; 
					path[nbr] = path[nbr] % modVal;
				}
			}
		}

		return path[n-1]; // n-1 is the destination vtx
	}

	// this class is for BFS traversing using MinHeap 
	private class Pair implements Comparable<Pair>{ 
		int vtx;
		int wsf;
		Pair(int vtx, int wsf) {
			this.vtx = vtx;
			this.wsf = wsf; // weightSoFar
		}

		public int compareTo(Pair o) {
			return this.wsf - o.wsf;
		}
	}

	private class Edge {
		int src; // U
		int nbr; // V
		int wt; // cost of travelling from U to V or vice versa
		Edge(int src, int nbr, int wt) {
			this.src = src;
			this.nbr = nbr;
			this.wt = wt;
		}
	}

	private List<Edge>[] constructGraph(int n, int[][] roads) {
		List<Edge>[] graph = new ArrayList[n];

		for(int i = 0; i < n; i++) { // initializing graph[] with empty arrayLists
			graph[i] = new ArrayList<>();
		}

		for(int i = 0; i < roads.length; i++) {
			int u = roads[i][0];
			int v = roads[i][1];
			int wt = roads[i][2];

			graph[u].add(new Edge(u, v, wt)); // add Edge at Uth node which goes from U to V at wt cost
			graph[v].add(new Edge(v, u, wt)); // add Edge at Vth node which goes from V to U at wt cost
		}
		return graph;
	}
}