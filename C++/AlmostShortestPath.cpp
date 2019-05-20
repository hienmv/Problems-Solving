/** https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3296
 * idea: use dijkstra k from S and T.
 * each (u, v, w)
 *      if distS[u] + w + distT[v] == minDist -> (u,v,w) belong to the minimum path
 * create new Graph with edge exclude all above edge
 * 
 *  <<break down the path>
*/
#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>

using namespace std;

class Node {
public:    
    int id;
    int dist;
    Node(int id, int dist) {
        this->id = id;
        this->dist = dist;
    }
    bool operator>(const Node& other) const {
        return this->dist > other.dist;
    }
};

int max_val = (int)1e8;

void Dijkstra(vector<vector<Node> >& graph, int source, vector<int>& distArr) 
{
    distArr[source] = 0;
    priority_queue<Node, vector<Node>, greater<Node> > pq;
    pq.push(Node(source, distArr[source]));
    
    while(!pq.empty()) 
    {
        Node node = pq.top();
        pq.pop();
        int id = node.id;
        int w = node.dist;
        if (w > distArr[id]) {
            continue;
        }
        for (Node& neighbour : graph.at(id)) {
            if (w + neighbour.dist < distArr[neighbour.id]) {
                distArr[neighbour.id] = w + neighbour.dist;
                pq.push(Node(neighbour.id, distArr[neighbour.id]));
            }
        }
    }
}

int main() {
    while(true) {
        int n, m;
        cin >> n >> m;
        if (n == 0 && m == 0) break;

        int source, destination;
        cin >> source >> destination;
        vector<vector<Node> > graphS(n);
        vector<vector<Node> > graphT(n);
        for (int i=0; i < m; ++i) {
            int u, v, p;
            cin >> u >> v >> p;
            graphS[u].push_back(Node(v,p));
            graphT[v].push_back(Node(u,p));
        }

        vector<int> distArrS(n, max_val);
        vector<int> distArrT(n, max_val);
        Dijkstra(graphS, source, distArrS);
        Dijkstra(graphT, destination, distArrT);

        int minimumCost = distArrS[destination];
        if (minimumCost == -1) {
            cout << -1 << endl;
        } else {
            // get all edge is valid to findout the almost shortest path
            vector<vector<Node> > graph(n);
            for (int i=0; i < n; i++) {
                for (Node node : graphS[i]) {
                    if (distArrS[i] + node.dist + distArrT[node.id] != minimumCost) {
                        graph[i].push_back(Node(node.id, node.dist));
                    }
                }
            }

            vector<int> distArr(n, max_val);
            Dijkstra(graph, source, distArr);
            int almostMinimumCost = (distArr[destination] != max_val) ? distArr[destination] : (-1);
            cout << almostMinimumCost << endl;
        }
    }
    return 0;
}