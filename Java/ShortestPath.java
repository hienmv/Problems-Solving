/** https://www.spoj.com/problems/SHPATH/en/ #shortest-path #dijkstra */
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

class ShortestPath {
  public static void Dijkstra(ArrayList<ArrayList<Node>> graph, int source, int[][] miniCostArr) {
    int len = graph.size();
    int[] distArr = new int[len];
    for (int i = 0; i < distArr.length; i++) {
      distArr[i] = Integer.MAX_VALUE;
    }
    distArr[source] = 0;
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(new Node(source, distArr[source]));
    while (!pq.isEmpty()) {
      Node node = pq.poll();
      int id = node.id;
      int w = node.dist;
      for (Node neighbour : graph.get(id)) {
        if (w + neighbour.dist < distArr[neighbour.id]) {
          distArr[neighbour.id] = w + neighbour.dist;
          pq.add(new Node(neighbour.id, distArr[neighbour.id]));
        }
      }
    }
    for (int i = 0; i < distArr.length; i++) {
      miniCostArr[source][i] = distArr[i];
      miniCostArr[i][source] = distArr[i];
    }
  }

  public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
      int testcases = Integer.parseInt(br.readLine());
      for (int i = 0; i < testcases; i++) {
        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Node>> graph = new ArrayList<>(n + 1);
        HashMap<String, Integer> nameMap = new HashMap<>();
        for (int k = 0; k < n + 1; k++) {
          graph.add(new ArrayList<Node>());
        }
        for (int k = 1; k < graph.size(); k++) {
          String name = br.readLine();
          nameMap.put(name, k);
          int numberOfNeighbour = Integer.parseInt(br.readLine());
          for (int j = 0; j < numberOfNeighbour; j++) {
            String[] str = br.readLine().split("\\s");
            int neighbourId = Integer.parseInt(str[0]);
            int neighbourDist = Integer.parseInt(str[1]);
            graph.get(k).add(new Node(neighbourId, neighbourDist));
          }
        }
        int numberOfPath = Integer.parseInt(br.readLine());
        int[][] miniCostArr = new int[n + 1][n + 1];
        for (int v1 = 0; v1 < miniCostArr.length; v1++) {
          for (int v2 = 0; v2 < miniCostArr[v1].length; v2++) {
            miniCostArr[v1][v2] = -1;
          }
        }
        for (int k = 0; k < numberOfPath; k++) {
          String[] str = br.readLine().split("\\s");
          int src = nameMap.get(str[0]);
          int dest = nameMap.get(str[1]);
          int minimumCost = miniCostArr[src][dest];
          if (minimumCost == -1) {
            Dijkstra(graph, src, miniCostArr);
            minimumCost = miniCostArr[src][dest];
          }
          System.out.println(minimumCost);
        }
        br.readLine();
      }
    } catch (Exception e) {

    }
  }
}

class Node implements Comparable<Node> {
  Integer id;
  Integer dist;

  Node(int id, int dist) {
    this.id = id;
    this.dist = dist;
  }

  @Override
  public int compareTo(Node other) {
    return Integer.compare(this.dist, other.dist);
  }
}
