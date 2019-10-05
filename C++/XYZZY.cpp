/*  https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1498
#bellman-ford #bfs #shortest-path
*/

#include<iostream>
#include<vector>
#include<climits>
#include<queue>

using namespace std;

class Edge {
public:
    int u, v, energy;
    Edge() {}
    Edge(int _u, int _v, int _energy): u(_u), v(_v), energy(_energy) {}
};

// check whether there is a path from negative-weight cycles to target vertex or not 
bool BFS(int vertex, int numVertex, vector<vector<int> >& neighbourGraph, int targetVertex) {
    queue<int> q;
    vector<bool> visitedVertex(numVertex+1, false);
    q.push(vertex);
    visitedVertex[vertex] = true;
    while(!q.empty()) {
        int v = q.front();
        q.pop();
        for (int neighbour : neighbourGraph[v]) {
            if (neighbour == targetVertex) return true;
            if (!visitedVertex[neighbour]) {
                q.push(neighbour);
                visitedVertex[neighbour] = true;
            }
        }
    }
    return false;
}

bool BellmanFord(vector<Edge>& graph, vector<vector<int> >& neighbourGraph, int startVertex, int numVertex) {
    // initilize
    vector<int> energyArr(numVertex+1, INT_MIN);
    energyArr[startVertex] = 100;
    int targetVertex = numVertex;
    vector<int> path(numVertex+1, -1);

    // relax edges repeatedly
    for(int i=1; i <= numVertex - 1; i++) {
        for (Edge& e : graph) {
            if (energyArr[e.u] != INT_MIN && energyArr[e.u] + e.energy > 0 && energyArr[e.u] + e.energy > energyArr[e.v]) {
                energyArr[e.v] = energyArr[e.u] + e.energy;
                path[e.v] = e.u;
            }
        }
    }

    if (energyArr[targetVertex] > 0) {
        return true;
    }
    // check for negative-weight cycles
    for (Edge& e : graph) {
        if (energyArr[e.u] != INT_MIN && energyArr[e.u] + e.energy > 0 && energyArr[e.u] + e.energy > energyArr[e.v]) {
            // check whether there is a path from negative-weight cycles to target vertex or not
            if (BFS(e.v, numVertex, neighbourGraph, targetVertex)) {
                return true;
            }
        }
    }

    return false;
}

int main() {
    int n;

    while (cin >> n) {
        if (n == -1 ) break;
   
        vector<Edge> graph;
        vector<vector<int> > neighbourGraph(n+1);
        for (int u=1; u <= n; u++) {
            int energy, numNeighbour;
            cin >> energy >> numNeighbour;
            for (int k=0; k < numNeighbour; k++) {
                int v;
                cin >> v;
                Edge e(u, v, energy);
                graph.push_back(e);
                neighbourGraph[u].push_back(v);
            }
        }

        bool r = BellmanFord(graph, neighbourGraph, 1, n);
        if (r) {
            cout << "winnable" << endl;
        } else {
            cout << "hopeless" << endl;
        }
    }

    return 0;
}