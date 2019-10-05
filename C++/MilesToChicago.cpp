/* https://www.urionlinejudge.com.br/judge/en/problems/view/1655
#bellman-ford #shortest-path
*/

#include<iostream>
#include<vector>
#include<cfloat>
#include<iomanip>

using namespace std;

class Edge {
public:
    int u, v;
    double percentage;
    Edge() {}
    Edge(int _u, int _v, double _percentage ): u(_u), v(_v), percentage(_percentage) {}

};

double min_val = -DBL_MAX;

bool BellmanFord(vector<Edge>& graph, int startVertex, int numVertex, vector<double>& percentageArr) {
    // initilize
    percentageArr[startVertex] = 100.0;
    
    // relax edges repeatedly
    for (int i=1; i <= numVertex -1; i++) {
        for(Edge e : graph) {
            if (percentageArr[e.u] != min_val) {
                double newVal = percentageArr[e.u] * e.percentage / 100.0;
                if (newVal> percentageArr[e.v]) {
                    percentageArr[e.v] = newVal;
                }
            }
        }
    }

    // check for negative-weight cycles
    for(Edge e : graph) {
        if (percentageArr[e.u] != min_val) {
            double newVal = percentageArr[e.u] * e.percentage / 100.0;
            if (newVal> percentageArr[e.v]) {
                return false;
            }
        }
    }
    return true;
}

int main() {
    int n, m;
    while (cin >> n) {
        if (n == 0) {
            break;
        }
        cin >> m;
        vector<Edge> graph;
        for(int i=0; i < m; i++) {
            int u, v, percentage;
            cin >> u >> v >> percentage;
            Edge e1(u, v, percentage);
            Edge e2(v, u, percentage);
            graph.push_back(e1);
            graph.push_back(e2);
        }
        vector<double> percentageArr(n+1, min_val);
        BellmanFord(graph, 1, n, percentageArr);

        cout << std::setprecision(6) << fixed << percentageArr[n] << " percent" << endl;
    }

    return 0;
}