/**
 * https://www.urionlinejudge.com.br/judge/en/problems/view/1610 #dfs
 *
 * <p>X<-------T | ^ | | | | v | Y -----> Z
 *
 * <p>X / \ T Y \ Z
 *
 * <p>visited status: T = processing
 *
 * <p>T = processed
 *
 * <p>false: - unprocess 0 -- iterate (visited[false]) true: -- processing 1 -- cycle \- processed 2
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DuduServiceMaker {
  static boolean DFSRecursion(
      int vertex, HashMap<Integer, ArrayList<Integer>> graphMap, int[] statusArr) {
    // processing
    statusArr[vertex] = 1;
    if (graphMap.containsKey(vertex)) {
      for (int node : graphMap.get(vertex)) {
        // unproccess
        if (statusArr[node] == 0) {
          if (DFSRecursion(node, graphMap, statusArr)) {
            return true;
          }
        } else if (statusArr[node] == 1) {
          return true;
        }
      }
    }
    // processed
    statusArr[vertex] = 2;

    return false;
  }

  static boolean isValid(HashMap<Integer, ArrayList<Integer>> graphMap, int nodeNum) {
    int[] statusArr = new int[nodeNum];
    for (Map.Entry<Integer, ArrayList<Integer>> entry : graphMap.entrySet()) {
      int key = entry.getKey();
      if (statusArr[key] == 0) {
        if (DFSRecursion(key, graphMap, statusArr)) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int t = sc.nextInt();
    ArrayList<String> result = new ArrayList<>();
    for (int i = 0; i < t; i++) {
      int n = sc.nextInt();
      int m = sc.nextInt();
      HashMap<Integer, ArrayList<Integer>> graphMap = new HashMap<>();
      for (int x = 0; x < m; x++) {
        int k = sc.nextInt();
        int v = sc.nextInt();
        if (!graphMap.containsKey(v)) {
          graphMap.put(v, new ArrayList<>());
        }
        graphMap.get(v).add(k);
      }
      if (isValid(graphMap, n + 1)) {
        result.add("SIM");
      } else {
        result.add("NAO");
      }
    }
    result.forEach(s -> System.out.println(s));
  }
}
