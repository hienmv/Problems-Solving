/* https://www.spoj.com/problems/UCV2013B/
    idea: Bellman Ford
*/

#include<iostream>
#include<vector>
#include<climits>

using namespace std;

class Edge {
public:
    int u, v, dist;
    Edge() {}
    Edge(int _u, int _v, int _dist): u(_u), v(_v), dist(_dist) {}
};

bool BellmanFord(vector<Edge>& graph, int startVertex, int numVertex, vector<int> & distArr) {
    // initilize
    for(int i=0; i < distArr.size(); i++) {
        distArr[i] = INT_MAX;
    }
    distArr[startVertex] = 0;

    // relax edges repeatedly
    for (int i=1; i <= numVertex -1; i++ ) {
        for(Edge& e: graph) {
            if (distArr[e.u] != INT_MAX && distArr[e.u] + e.dist < distArr[e.v]) {
                distArr[e.v] = distArr[e.u] + e.dist;
            }
        }
    }

    // check negative-weight cycles
    for(Edge& e: graph) {
        if (distArr[e.u] != INT_MAX && distArr[e.u] + e.dist < distArr[e.v]) {
            return false;
        }
    }

    return true;
}

int main() {
    int n;
    int numcase = 1;
    while(cin >> n) {
        if (n == 0) break;
        vector<string> monumentArr;
        vector<Edge> graph;
        for(int u=0; u < n; u++) {
            string name;
            cin >> name;
            monumentArr.push_back(name);
            for (int v=0; v < n; v++) {
                int dist;
                cin >> dist;
                if (dist == 0 && u != v) continue;
                Edge e(u, v, dist);
                graph.push_back(e);
            }
        }
        cout << "Case #" << numcase << ":" << endl;
        int q;
        cin >> q;
        int prev_src = -1;
        bool r = false;
        vector<int> distArr(n, INT_MAX);
        for(int i=0; i < q; i++) {
            int src, dest;
            cin >> src >> dest;
            /* should use array to save 
               if distArr[src].size == 0
               resize distArr
               bellman
            */
            if (prev_src != src) {
                prev_src = src;
                r = BellmanFord(graph, prev_src, n,  distArr);
            }
            if (r) {
                if (distArr[dest] == INT_MAX) {
                    cout << monumentArr[src] << "-" << monumentArr[dest] << " NOT REACHABLE" << endl;
                } else {
                    cout << monumentArr[src] << "-" << monumentArr[dest] << " " << distArr[dest] << endl; 
                }
            } else {
                cout << "NEGATIVE CYCLE" << endl;
            }

        }
        numcase++;
    }
    return 0;
}