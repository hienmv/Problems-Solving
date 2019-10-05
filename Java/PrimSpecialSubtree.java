/* https://www.hackerrank.com/challenges/primsmstsub/problem
#prim
*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;

class PrimSpecialSubtree {
	public static int Prim(ArrayList<ArrayList<Node>> graph, int[] weightArr, int src) {
		int minusVal = 0;
		int sz = graph.size();
		boolean[] visitedArr = new boolean[sz];
		Arrays.fill(visitedArr, false);
		
		boolean[][] edgeArr = new boolean[sz][sz];
		for (int i=0; i < sz; i++) {
			Arrays.fill(edgeArr[i], false);
		}

		weightArr[src] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(src, 0));
		
		Node node;
		int w, id;
		while(!pq.isEmpty()) {
			node = pq.poll();
			id = node.id;
			if (visitedArr[id]) continue; // ??

			visitedArr[id] = true;

			Collections.sort(graph.get(id));
			for (Node neighbour : graph.get(id)) {
				w = neighbour.weight;
				if (!visitedArr[neighbour.id] && w < weightArr[neighbour.id]) {
					if (!edgeArr[id][neighbour.id]) {
						edgeArr[id][neighbour.id] = true;
						edgeArr[neighbour.id][id] = true;
					} else {
						minusVal += w;	
					}
					weightArr[neighbour.id] = w;
					pq.add(new Node(neighbour.id, w));
				}
			}
		}
		return minusVal;
	}
	
	public static int getTotalWeight(int[] weightArr, int minusVal) {
		int ans = 0;
		for (int weight : weightArr) {
			ans += weight;
		}
		return ans - minusVal;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n, m, x, y, r, start;
		n = sc.nextInt();
		m = sc.nextInt();
		int sz = n+1;
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();
		for (int i=0; i < sz; i++) {
			graph.add(new ArrayList<Node>());
		}
		for (int i=0; i < m; i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			r = sc.nextInt();
			graph.get(x).add(new Node(y, r));
			graph.get(y).add(new Node(x, r));
		}
		
		start = sc.nextInt();
		int[] weightArr = new int[sz];
		Arrays.fill(weightArr, Integer.MAX_VALUE);
		weightArr[0] = 0;
		int minusVal = Prim(graph, weightArr, start);
		System.out.println(getTotalWeight(weightArr, minusVal));
	}
}

class Node implements Comparable<Node> {
	int id, weight;
	public Node (int id, int weight) {
		this.id = id;
		this.weight = weight;
	}
	public int compareTo(Node other) {
		return this.weight - other.weight;
	}
}