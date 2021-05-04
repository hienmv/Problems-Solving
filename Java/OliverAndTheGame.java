/** #graph #dfs */
import java.util.ArrayList;
import java.util.Scanner;

class OliverAndTheGame {
  public static void main(String[] args) {
    // input
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int sz = n + 1;
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    ArrayList<Time> timeTable = new ArrayList<>();
    for (int i = 0; i < sz; i++) {
      graph.add(new ArrayList<>());
      timeTable.add(new Time(0, 0));
    }
    int u, v;
    for (int i = 0; i < n - 1; i++) {
      u = sc.nextInt();
      v = sc.nextInt();
      graph.get(u).add(v);
      graph.get(v).add(u);
    }
    // calculate start and finish time of each vertex.
    Time curTime = new Time(1, 0);
    dfs(graph, 1, curTime, timeTable);

    // query
    int q = sc.nextInt();
    int direct, x, y;
    for (int i = 0; i < q; i++) {
      direct = sc.nextInt();
      x = sc.nextInt();
      y = sc.nextInt();

      if (direct == 0) {
        if (timeTable.get(x).start < timeTable.get(y).start
            && timeTable.get(x).finish > timeTable.get(y).finish) {

          System.out.println("YES");
          continue;
        }
      } else { // direct == 1
        if (timeTable.get(x).start > timeTable.get(y).start
            && timeTable.get(x).finish < timeTable.get(y).finish) {

          System.out.println("YES");
          continue;
        }
      }
      System.out.println("NO");
    }
  }

  public static void dfs(
      ArrayList<ArrayList<Integer>> graph, int source, Time curTime, ArrayList<Time> timeTable) {
    timeTable.get(source).start = curTime.start;
    for (int neighbour : graph.get(source)) {
      if (timeTable.get(neighbour).start == 0) {
        ++curTime.start;
        dfs(graph, neighbour, curTime, timeTable);
      }
    }
    timeTable.get(source).finish = ++curTime.start;
  }
}

class Time {
  int start;
  int finish;

  Time(int start, int finish) {
    this.start = start;
    this.finish = finish;
  }
}
