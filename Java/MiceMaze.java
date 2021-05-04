/** https://www.spoj.com/problems/MICEMAZE/ #shortest-path #bfs #dijkstra reverse graph */
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class MiceMaze {
  public static void Dijktra(ArrayList<ArrayList<Node>> graph, int[] timeArr, int startVertex) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(new Node(startVertex, 0));
    while (!pq.isEmpty()) {
      Node node = pq.poll();
      int id = node.id;
      int w = node.time;
      for (int i = 0; i < graph.get(id).size(); i++) {
        Node neighbor = graph.get(id).get(i);
        int newTime = w + neighbor.time;
        if (newTime < timeArr[neighbor.id]) {
          timeArr[neighbor.id] = newTime;
          pq.add(new Node(neighbor.id, timeArr[neighbor.id]));
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int exitVertex = sc.nextInt();
    int limitTime = sc.nextInt();
    int m = sc.nextInt();

    ArrayList<ArrayList<Node>> graph = new ArrayList<>(n + 1);
    for (int i = 0; i < n + 1; i++) {
      graph.add(new ArrayList<Node>());
    }
    for (int i = 0; i < m; i++) {
      int sr = sc.nextInt();
      int ds = sc.nextInt();
      int time = sc.nextInt();
      graph.get(ds).add(new Node(sr, time));
    }
    int[] timeArr = new int[n + 1];
    for (int i = 0; i < timeArr.length; i++) {
      timeArr[i] = Integer.MAX_VALUE;
    }
    Dijktra(graph, timeArr, exitVertex);

    // result
    int sum = 1;
    for (int i : timeArr) {
      if (i <= limitTime) sum += 1;
    }
    System.out.println(sum);
  }
}

class Node implements Comparable<Node> {
  Integer id;
  Integer time;

  Node(Integer id, Integer time) {
    this.id = id;
    this.time = time;
  }

  @Override
  public int compareTo(Node other) {
    return Integer.compare(this.time, other.time);
  }
}
