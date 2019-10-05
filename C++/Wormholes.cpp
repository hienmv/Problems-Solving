/*  https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=499
#bellman-ford #shortest-path
*/

#include<iostream>
#include<vector>
#include<climits>

using namespace std;

class Edge {
public:
    int u, v, w;
    Edge() {}
    Edge(int u, int v, int w) {
        this->u = u;
        this->v = v;
        this->w = w;
    }
};

bool BellmanFord(vector<Edge>& graph, int n, int source) {
    vector<int> dist(n, INT_MAX);
    
    dist[source] = 0;

    for (int i=1; i <= n -1; i++) {
        for(int k=0; k < graph.size(); k++) {
            int u = graph[k].u;
            int v = graph[k].v;
            int w = graph[k].w;
            if (dist[u] != INT_MAX && dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
            }
        }
    }
   
    // check chu trinh am
    for (int k=0; k < graph.size(); k++) {
        int u = graph[k].u;
        int v = graph[k].v;
        int w = graph[k].w;
        if (dist[u] != INT_MAX && dist[u] + w < dist[v]) {
            return false;
        } 
    }

    return true;
}

int main() {
    int testcases;
    cin >> testcases;
    for (int t=0; t < testcases; t++) {
        int n, m;
        cin >> n >> m;
        vector<Edge> graph;
        int u, v, w;
        for (int i=0; i < m; i++) {
            cin >> u >> v >> w;
            Edge e(u, v, w);
            graph.push_back(e);
        }
        bool ret = BellmanFord(graph, n, 0);
        string str = (ret == true) ? "not possible" : "possible";
        cout << str << endl;
    }

}
