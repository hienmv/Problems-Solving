/** https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3296
 * idea: use dijkstra k times
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

int Dijkstra(vector<vector<Node> > graph, int source, int destination, 
    vector<int> pathArr, vector<int> distArr, vector<bool> visitedPath) 
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
        if (w > distArr[id] || visitedPath[id]) {
            continue;
        }
        for (Node& neighbour : graph.at(id)) {
            if ( !visitedPath[neighbour.id] && (w + neighbour.dist < distArr[neighbour.id])) {
                distArr[neighbour.id] = w + neighbour.dist;
                pathArr[neighbour.id] = id;
                pq.push(Node(neighbour.id, distArr[neighbour.id]));
            }
        }
    }

    int st = pathArr[destination];
    while (st != -1 && st != source) {
        visitedPath[st] = true;
        st = pathArr[st];
    }
    return distArr[destination] == max_val ? (-1) : distArr[destination];
}

int main() {
    while(true) {
        int n, m;
        cin >> n >> m;
        if (n == 0 && m == 0) break;

        int source, destination;
        cin >> source >> destination;
        vector<vector<Node> > graph(n);
        for (int i=0; i < m; ++i) {
            int u, v, p;
            cin >> u >> v >> p;
            graph.at(u).push_back(Node(v,p));
        }
        // bool visitedPath[n];
        // int pathArr[n];
        // int distArr[n];
        // for (int i=0; i < n; i++) {
        //     visitedPath[i] = false;
        //     pathArr[i] = -1;
        //     distArr[i] = max_val;
        // }
        vector<bool> visitedPath(n, false);
        vector<int> pathArr(n, -1);
        vector<int> distArr(n, max_val);
        vector<int> dist(n, max_val);
        
        int minimumCost = Dijkstra(graph, source, destination, pathArr, dist, visitedPath);
        int almostMinimumCost = minimumCost;
        while(minimumCost != -1 && almostMinimumCost == minimumCost) {
            std::copy(distArr.begin(), distArr.end(), dist.begin());
            almostMinimumCost = Dijkstra(graph, source, destination, pathArr, dist, visitedPath);
        }
        
        cout << almostMinimumCost << endl;
    }
    return 0;
}