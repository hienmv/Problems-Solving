/** http://acm.timus.ru/problem.aspx?space=1&num=1837 #bfs */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class IsenbaevsNumber {
  public static int getIdx(
      HashMap<String, Integer> hashMap,
      ArrayList<String> arr,
      ArrayList<ArrayList<Integer>> graph,
      String name) {
    if (!hashMap.containsKey(name)) {
      arr.add(name);
      int idx = arr.size() - 1;
      graph.add(new ArrayList<>());
      hashMap.put(name, idx);
      return idx;
    } else {
      return hashMap.get(name);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    String name1, name2, name3;
    int idx1, idx2, idx3;
    HashMap<String, Integer> hashMap = new HashMap<>();
    ArrayList<String> arr = new ArrayList<>();
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      name1 = sc.next();
      idx1 = getIdx(hashMap, arr, graph, name1);
      name2 = sc.next();
      idx2 = getIdx(hashMap, arr, graph, name2);
      name3 = sc.next();
      idx3 = getIdx(hashMap, arr, graph, name3);
      graph.get(idx1).add(idx2);
      graph.get(idx1).add(idx3);
      graph.get(idx2).add(idx1);
      graph.get(idx2).add(idx3);
      graph.get(idx3).add(idx1);
      graph.get(idx3).add(idx2);
    }

    ArrayList<Integer> distArr = new ArrayList<>();
    ArrayList<Boolean> visitedArr = new ArrayList<>();
    for (int i = 0; i < arr.size(); i++) {
      distArr.add(Integer.MAX_VALUE);
      visitedArr.add(false);
    }

    if (hashMap.containsKey("Isenbaev")) {
      int startIdx = hashMap.get("Isenbaev");
      BFS(graph, distArr, visitedArr, startIdx);
    }
    // result
    Collections.sort(arr);
    int idx;
    for (String str : arr) {
      idx = hashMap.get(str);
      if (distArr.get(idx) == Integer.MAX_VALUE) {
        System.out.println(str + " undefined");
      } else {
        System.out.println(str + " " + distArr.get(idx));
      }
    }
  }

  public static void BFS(
      ArrayList<ArrayList<Integer>> graph,
      ArrayList<Integer> dist,
      ArrayList<Boolean> visitedArr,
      int startNode) {
    visitedArr.set(startNode, true);
    dist.set(startNode, 0);
    Deque<Integer> queue = new LinkedList<>();
    queue.add(startNode);
    while (!queue.isEmpty()) {
      int node = queue.pollFirst();
      for (int otherNode : graph.get(node)) {
        if (!visitedArr.get(otherNode)) {
          visitedArr.set(otherNode, true);
          dist.set(otherNode, dist.get(node) + 1);
          queue.add(otherNode);
        }
      }
    }
  }
}
