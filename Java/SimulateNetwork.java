/* https://www.hackerearth.com/ja/challenges/hiring/globalsoft-backend-hiring-challenge/algorithm/efficient-network/
use #prim algorithm and then adjust dist.
*/

import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;

class SimulateNetwork {
	public static void Prim(ArrayList<ArrayList<Node>> graph, int[] distArr, int src) {
		int sz = graph.size();
		boolean[] visitedArr = new boolean[sz];
		Arrays.fill(visitedArr, false);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(src, 0));
		distArr[src] = 0;
		Node neighbour;
		int id, w;
		while (!pq.isEmpty()) {
			id = pq.poll().id;
			visitedArr[id] = true;
			for(int i=0; i < graph.get(id).size(); i++) {
				neighbour = graph.get(id).get(i);
				w = neighbour.latency;
				if (!visitedArr[neighbour.id] && w < distArr[neighbour.id]) {
					distArr[neighbour.id] = w;
					pq.add(new Node(neighbour.id, w));
				}
			}
		}
	}
	
	public static long getMinimumLatency(int[] distArr, int[] availableCableArr) {
		long ans = 0;
		if (availableCableArr != null) {
			Arrays.sort(distArr);
			Arrays.sort(availableCableArr);
			int idx = 0;
			int val = 0;
			for (int i= distArr.length - 1; i > 0; i--) {
				val = distArr[i];
				if (idx < availableCableArr.length && val > availableCableArr[idx]) {
					val = availableCableArr[idx];
					idx++;
				}
				ans += val;
				
			}
		} else {
			for(int val : distArr) {
				ans += val;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();
		int sz = n+1;
		for (int i=0; i < sz; i++) {
			graph.add(new ArrayList<>());
		}
		int src, dest, latency;
		for (int i=0; i < m; i++) {
			src = sc.nextInt();
			dest = sc.nextInt();
			latency = sc.nextInt();
			if (src != dest) {
				graph.get(src).add(new Node(dest, latency));
				graph.get(dest).add(new Node(src, latency));
			}
		}
		int[] distArr = new int[sz];
		Arrays.fill(distArr, Integer.MAX_VALUE);
		distArr[0] = 0;

		Prim(graph, distArr, 1);
		long ans;
		int q = sc.nextInt();
		if (q > 0) {
			int[] availableCableArr = new int[q];
			int tmp;
			for (int i=0; i < q; i++) {
				tmp = sc.nextInt();
				availableCableArr[i] = tmp;
			}
			ans = getMinimumLatency(distArr, availableCableArr);
		} else {
			ans = getMinimumLatency(distArr, null);
		}
		
		System.out.println(ans);

	}
}

class Node implements Comparable<Node> {
	int id, latency;
	Node (int id, int latency) {
		this.id = id;
		this.latency = latency;
	}

	public int compareTo(Node other) {
		return this.latency - other.latency;
	}
}