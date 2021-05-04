/**
 * https://onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1338 #prim /*
 *
 * <p>4 -> number of building 103 104 104 100 104 103 100 100 1 -> number of cable 4 2 4 1 103 104 2
 * 104 100 3 104 103 4 100 100
 *
 * <p>1: 2:W, 3:W, 4:W
 *
 * <p>1 4 2 4 2 0
 *
 * <p>3 4 4 0 4 3 0 0
 *
 * <p>4 x \ 3 x | 2 | | 1 | | 0 x---------------x x---------------X
 *
 * <p>0 1 2 3 4
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ConnectCampus {
  public static void inputConnectedCampusMap(
      HashMap<Integer, HashSet<Integer>> connectedCampusMap, int idx1, int idx2) {
    HashSet<Integer> set;
    if (!connectedCampusMap.containsKey(idx1)) {
      set = new HashSet<>();
      set.add(idx2);
      connectedCampusMap.put(idx1, set);
    } else {
      set = connectedCampusMap.get(idx1);
      set.add(idx2);
    }

    if (!connectedCampusMap.containsKey(idx2)) {
      set = new HashSet<>();
      set.add(idx1);
      connectedCampusMap.put(idx2, set);
    } else {
      set = connectedCampusMap.get(idx2);
      set.add(idx1);
    }
  }

  public static void Prim(
      ArrayList<ArrayList<Node>> graph,
      HashMap<Integer, HashSet<Integer>> connectedCampusMap,
      int src,
      int[] distArr) {
    int sz = graph.size();
    boolean[] visitedArr = new boolean[sz];
    int[] pathArr = new int[sz];
    Arrays.fill(visitedArr, false);
    Arrays.fill(pathArr, -1);

    distArr[src] = 0;
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(new Node(src, 0));

    Node node, neighbour;
    int id, w;
    HashSet<Integer> set;
    while (!pq.isEmpty()) {
      node = pq.poll();
      id = node.id;
      visitedArr[id] = true;
      for (int i = 0; i < graph.get(id).size(); i++) {
        neighbour = graph.get(id).get(i);
        if (visitedArr[neighbour.id] == false) {
          w = neighbour.dist;

          if (connectedCampusMap.containsKey(id)) {
            set = connectedCampusMap.get(id);
            if (set.contains(neighbour.id)) {
              w = 0;
            }
          }

          if (w < distArr[neighbour.id]) {
            distArr[neighbour.id] = w;
            pq.add(new Node(neighbour.id, w));
            pathArr[neighbour.id] = id;
          }
        }
      }
    }
  }

  public static double getWeightPrim(int[] distArr) {
    double ans = 0.0;
    for (int i = 1; i < distArr.length; i++) {
      ans += Math.sqrt(distArr[i]);
    }
    return ans;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextInt()) {
      int n = sc.nextInt();
      int sz = n + 1;
      Campus[] arrCampus = new Campus[sz];
      int x, y;
      for (int i = 1; i < sz; i++) {
        x = sc.nextInt();
        y = sc.nextInt();
        arrCampus[i] = new Campus(x, y);
      }

      int distance = 0;
      /** better way: use double[][] graph = new double[sz][sz]; */
      ArrayList<ArrayList<Node>> graph = new ArrayList<>();
      graph.add(new ArrayList<>());
      for (int i = 1; i < sz; i++) {
        graph.add(new ArrayList<>());
        for (int j = 1; j < sz; j++) {
          if (i == j) continue;
          distance =
              (int) Math.pow(arrCampus[i].x - arrCampus[j].x, 2)
                  + (int) Math.pow(arrCampus[i].y - arrCampus[j].y, 2);
          graph.get(i).add(new Node(j, distance));
        }
      }

      int m = sc.nextInt();
      int idx1, idx2;
      HashMap<Integer, HashSet<Integer>> connectedCampusMap = new HashMap<>();
      for (int i = 0; i < m; i++) {
        idx1 = sc.nextInt();
        idx2 = sc.nextInt();
        inputConnectedCampusMap(connectedCampusMap, idx1, idx2);
      }
      int[] distArr = new int[sz];
      Arrays.fill(distArr, Integer.MAX_VALUE);

      Prim(graph, connectedCampusMap, 1, distArr);

      System.out.println(String.format("%.2f", getWeightPrim(distArr)));
    }
  }
}

class Campus {
  int x, y;

  Campus(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

class Node implements Comparable<Node> {
  int id;
  int dist;

  Node(int id, int dist) {
    this.id = id;
    this.dist = dist;
  }

  public int compareTo(Node other) {
    return (this.dist - other.dist);
  }
}
