/** https://www.spoj.com/problems/MICEMAZE/
 *  idea: use Dijktra
 */

import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;

class MiceMaze {
    public static void Dijktra(ArrayList<ArrayList<Node>> graph, int[] exitVertexArr, int startVertex, int exitVertex, int limitTime) {
        int len = graph.size();
        int[] timeArr = new int[len];
        for (int i=0; i < timeArr.length; i++) {
            timeArr[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startVertex, 0));
        
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int id = node.id;
            int w = node.time;
            for (int i=0; i < graph.get(id).size(); i++) {
                Node neighbor = graph.get(id).get(i);
                int newTime = w + neighbor.time;
                if (newTime <= limitTime && newTime <  timeArr[neighbor.id]) {
                    timeArr[neighbor.id] = newTime;
                    if (neighbor.id == exitVertex){
                        exitVertexArr[startVertex] = 1;
                        return;
                    } else if (exitVertexArr[neighbor.id] == -1) {
                        continue;
                    }
                    pq.add(new Node(neighbor.id, timeArr[neighbor.id]));
                }
            }
        }
        exitVertexArr[startVertex] = -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int exitVertex = sc.nextInt();
        int limitTime = sc.nextInt();
        int m = sc.nextInt();
        
        ArrayList<ArrayList<Node>> graph = new ArrayList<>(n+1);
        for (int i=0; i < n+1; i++){
            graph.add(new ArrayList<Node>());
        }
        for (int i=0; i < m; i++) {
            int sr = sc.nextInt();
            int ds = sc.nextInt();
            int time = sc.nextInt();
            graph.get(sr).add(new Node(ds, time));
        }
        int[] exitVertexArr = new int[n+1];
        for (int i=1; i < graph.size(); i++){
            if (i != exitVertex) {
                Dijktra(graph, exitVertexArr, i, exitVertex, limitTime);
            }
        }
        
        // result
        int sum = 1;
        for (int i : exitVertexArr){
            if (i == 1) sum += 1;
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