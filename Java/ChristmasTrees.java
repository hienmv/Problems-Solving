/** https://codeforces.com/problemset/problem/1283/D
 * #bfs #graph #greedy
 */

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;
public class ChristmasTrees {
    private static class Node {
        int id, dist;
        Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Queue<Node> q = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            visited.add(id);
            q.add(new Node(id, 0));
        }

        long sumDist = 0;
        LinkedList<Integer> people = new LinkedList<>();
        while(!q.isEmpty()) {
            Node node = q.poll();
            int newDist = node.dist + 1;

            for (int i = 0; i < 2; i++) {
                int person = (i == 0) ? (node.id + newDist) : (node.id - newDist);
                if (!visited.contains(person)) {
                    sumDist += newDist;
                    m--;
                    people.add(person);
                    visited.add(person);
                    q.add(new Node(node.id, newDist));
                }
                if (m == 0) {
                    System.out.println(sumDist);
                    people.forEach(x -> System.out.print(x + " "));
                    return;
                }
            }
        }
    }
}