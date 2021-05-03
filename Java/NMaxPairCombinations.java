// https://www.interviewbit.com/problems/n-max-pair-combinations/
// #heap #map
public class Solution {
    class Node {
        int i;
        int j;
        Node (int i, int j) {
            this.i = i;
            this.j = j;
        }

        public String toString() {
            return "{" + this.i + ", " + this.j + "}";
        }

        public boolean equals(Object o)
        {
            if (o == null) {
                return false;
            }
            if (!(o instanceof Node)) {
                return false;
            }
            Node obj = (Node)o;
            return (this.i == obj.i && this.j == obj.j);
        }

        public int hashCode()
        {
            return Objects.hash(i, j);
        }
    }
    
    // idea: sort two arrays, and move from right -> left
    //  each move: we can move with two previous index.
    public int[] solve(int[] A, int[] B) {
        Comparator<Node> comparator = (Node node1, Node node2) -> ((A[node2.i] + B[node2.j]) - (A[node1.i] + B[node1.j]));
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int N = A.length;
        int[] result = new int[N];
        int i = N - 1;
        int j = N - 1;
        
        Node node = new Node(i, j);
        HashSet<Node> set = new HashSet<>();
        set.add(node);
        
        PriorityQueue<Node> pq = new PriorityQueue(comparator);
        pq.offer(node);
        
        int len = 0;
        while(!pq.isEmpty()) {
            node = pq.poll();
            
            result[len] = A[node.i] + B[node.j];
            len++;
            if (len == N) {
                break;
            }
            
            if (node.i > 0) {
                Node newNode = new Node(node.i - 1, node.j);
                if (!set.contains(newNode)) {
                    pq.offer(newNode);
                    set.add(newNode);
                }
            }
            if (node.j > 0) {
                Node newNode = new Node(node.i, node.j - 1);
                if (!set.contains(newNode)) {
                    pq.offer(newNode);
                    set.add(newNode);
                }
            }
        }
        
        return result;
    }
}
