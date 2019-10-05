/*	https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=989
	tag: #dijkstra #shortest-path #prim
	another way : creat a prim tree, find the path from one vertex to the prim tree.
	tao 1 cay khung. Tim duong di tu 1 dinh toi cay khung do.
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

class Audiophobia {
	public static void Dijkstra(ArrayList<ArrayList<Node>> graph, int[][] soundArr, boolean[][] visitedArr, int src) {
		int sz = graph.size();
		int[] dArr = new int[sz];
		Arrays.fill(dArr, Integer.MAX_VALUE);
		dArr[src] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(src, 0));
		
		Node node;
		int id, w;
		while(!pq.isEmpty()) {
			node = pq.poll();
			id = node.id;
			for (Node neighbour : graph.get(id)) {
				w = Math.max(neighbour.sound, dArr[id]);
				if (w < dArr[neighbour.id]) {
					dArr[neighbour.id] = w;
					pq.add(new Node(neighbour.id, w));
				}
			}
		}
		for (int idx=0; idx < dArr.length; idx++) {
			soundArr[src][idx] = dArr[idx];
			soundArr[idx][src] = dArr[idx];
			visitedArr[src][idx] = true;
			visitedArr[idx][src] = true;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = 1;
		int c, s, q;
		while (true) {
			c = sc.nextInt();
			s = sc.nextInt();
			q = sc.nextInt();
			if ( c == 0 && s == 0 && q == 0) break;
			if (tc > 1) {
				System.out.println("");
			}
			
			int sz = c + 1;
			ArrayList<ArrayList<Node>> graph = new ArrayList<>();
			int[][] soundArr = new int[sz][sz];
			boolean[][] visitedArr = new boolean[sz][sz];
			for (int i=0; i < sz; i++) {
				graph.add(new ArrayList<>());
				Arrays.fill(soundArr[i], Integer.MAX_VALUE);
				Arrays.fill(visitedArr[i], false);
			}						
						
			int u, v, w;
			for (int i=0; i < s; i++) {
				u = sc.nextInt();
				v = sc.nextInt();
				w = sc.nextInt();	
				graph.get(u).add(new Node(v, w));
				graph.get(v).add(new Node(u, w));
			}
			
			System.out.println("Case #" + tc);
			tc++;
			for (int i=0; i < q; i++) {
				u = sc.nextInt();
				v = sc.nextInt();
				if (!visitedArr[u][v]) {
					Dijkstra(graph, soundArr, visitedArr, u);
				}
				if (soundArr[u][v] == Integer.MAX_VALUE) {
					System.out.println("no path");
				} else {
					System.out.println(soundArr[u][v]);
				}
			}
			
		}
	}
}

class Node implements Comparable<Node> {
	int id, sound;
	public Node(int id, int sound) {
		this.id = id;
		this.sound = sound;
	}
	public int compareTo(Node other) {
		return this.sound - other.sound;
	}
}