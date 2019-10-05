/** https://www.codechef.com/problems/MAXCOMP
 *  #bellman-ford #shortest-path
 */

#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

class Edge {
public:
    int u, v, compensation;
    Edge() {}
    Edge(int _u, int _v, int _compensation): u(_u), v(_v), compensation(_compensation) {}

    bool operator<(const Edge& other) const {
        if (v != other.v) {
            return v < other.v;
        } else {
            return compensation < other.compensation;
        }
    }
};

int V = 49;

int getMaxCompensation(int &hour, vector<int>& compensationVector) {
    int idx = hour - 1;
    int maxVal = compensationVector[hour];
    
    while (idx >= 0) {
        if (compensationVector[idx] > maxVal) {
            maxVal = compensationVector[idx];
        }
        idx--;
    }
    return maxVal;
}
bool BellmanFord(vector<Edge>& edgeVector, vector<int>& compensationVector) {
 
    for (int i=0; i < V; i++) {
        int optimizeFlg = false;
        
        for (Edge& e : edgeVector) {
            int u = e.u;
            int v = e.v;
            int w = e.compensation;
           
            compensationVector[u] = getMaxCompensation(u, compensationVector);
            
            if (compensationVector[u] + w > compensationVector[v]) {
                optimizeFlg = true;
                compensationVector[v] = compensationVector[u] + w;
            }
        }
        if (!optimizeFlg) {
            return true;
        }
    }

    // no need to check negative circles

    return true;
}

int main() {
    int testcases;
    cin >> testcases;
    for (int t=0; t < testcases; t++) {
        int n;
        cin >> n;
        vector<Edge> edgeVector;
        for (int i=0; i < n; i++) {
            int u, v, compensation;
            cin >> u >> v >> compensation;
            Edge e(u, v, compensation);
            edgeVector.push_back(e);
        }
        
        sort(edgeVector.begin(), edgeVector.end());
        /* -> should use Floyd Warshall
        compensation[i][j] -> đường đi từ i đến j
            <-> benefit tổ chức event i -> j
        
        compensation[i][j] = 0
        compensation[1][5] = 10
        compensation[2][6] = 6

        compensation[1][n]
        compensation[x][y]
        compensation[1][x] + compensation[x][y] + compensation[y][n]
              0                     optimal                 0
        */ 
        vector<int> compensationVector(V, 0);
        BellmanFord(edgeVector, compensationVector);
        int max = 0;
        
        for(int com : compensationVector) {
            if (com > max) {
                max = com;
            }
        }
        cout << max << endl;
    }
    return 0;
}