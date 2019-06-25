/*  https://www.hackerearth.com/practice/algorithms/graphs/shortest-path-algorithms/practice-problems/algorithm/monks-business-day/description/
    idea: bellman ford, find negative-weight cycles
*/

#include<iostream>
#include<vector>
#include<climits>

using namespace std;

class Edge {
public:
    int u, v, units;
    Edge() {}
    Edge(int _u, int _v, int _units) : u(_u), v(_v), units(_units) {}
};

bool BellmanFord(vector<Edge>& graph, int startVertex, int numVertex) {
    // initilize
    vector<int> unitsArr(numVertex+1, INT_MIN);
    unitsArr[startVertex] = 0;

    // relax edges repeatedly
    for (int i=1; i <= numVertex-1; i++) {
        for (Edge& e: graph) {
            if (unitsArr[e.u] != INT_MIN && unitsArr[e.u] + e.units > unitsArr[e.v]) {
                unitsArr[e.v] = unitsArr[e.u] + e.units;
            }
        }
    }
    
    // check for negative-weight cycles
    for (Edge& e: graph) {
        if (unitsArr[e.u] != INT_MIN && unitsArr[e.u] + e.units > unitsArr[e.v]) {
            return true;
        }
    }

    return false;
}
int main() {
    int testcases;
    cin >> testcases;
    for (int t=0; t < testcases; t++) {
        int n, m;
        cin >> n >> m;
        vector<Edge> graph;
        for(int i=0; i < m; i++) {
            int u, v, units;
            cin >> u >> v >> units;
            Edge e(u, v, units);
            graph.push_back(e);
        }
        bool r = BellmanFord(graph, 1, n);
        if (r) {
            cout << "Yes" << endl;
        } else {
            cout << "No" << endl; 
        }
    }
}