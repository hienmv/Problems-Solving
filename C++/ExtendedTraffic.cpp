/* http://www.lightoj.com/volume_showproblem.php?problem=1074
#bellman-ford
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

bool BellmanFord(vector<Edge>& graph, int n, vector<int>& dist) {

    for(int i=1; i <= n-1; i++) {
        for(int k=0; k < graph.size(); k++) {
            int u = graph[k].u;
            int v = graph[k].v;
            int w = graph[k].w;
            if(dist[u] != INT_MAX && dist[u] + w < dist[v]){
                dist[v] = dist[u] + w;
            }
        }
    }

    // check chu trinh am
    for(int k=0; k < graph.size(); k++) {
        int u = graph[k].u;
        int v = graph[k].v;
        int w = graph[k].w;
        if(dist[u] != INT_MAX && dist[u] + w < dist[v]){
            return false;
        }
    }
    return true;
}

int main() {
    int testcases;
    cin >> testcases;
    for(int t=1; t < testcases + 1; t++) {
        int n,m;
        cin >> n;
        int busynessArr[n+1];
        for(int i=1; i < n+1; i++){
            int tmp;
            cin >> tmp;
            busynessArr[i] = tmp;
        } 
        cin >> m;
        vector<Edge> graph;
        for(int i=0; i < m; i++){
            int u, v;
            cin >> u >> v;
            int w = busynessArr[v] - busynessArr[u];
            Edge e(u, v, w*w*w);
            graph.push_back(e);
        }

        vector<int> dist(n+1, INT_MAX);
        dist[1] = 0;
        bool r = BellmanFord(graph, n, dist);
        
        cout << "Case " << t << ":" << endl;
        int q;
        cin >> q;
        for (int i=0; i < q; i++) {
            int num;
            cin >> num;
           
            if( dist[num] < 3 || dist[num] == INT_MAX) {
                cout << "?" << endl;
            } else {
                cout << dist[num] << endl;
            }
        }
    }

    return 0;
}