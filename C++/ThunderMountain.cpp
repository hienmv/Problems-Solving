/* https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1744
   #floyd-warshall #shortest-path
*/

#include<iostream>
#include<vector>
#include<algorithm>
#include<iomanip>
#include<cmath>

using namespace std;

typedef pair<int, int> Node;

const double maxVal = 10.0;
const double INF = 5000.0;

bool FloydWarShall(vector<Node>& graph, vector<vector<double> >& dist) {
    int i, j, k;
    int V = graph.size();

    for (k=0; k < V; k++) {
        for (int i=0; i < V; i++) {
            for (int j=0; j < V; j++) {
                if(dist[i][j] > dist[i][k] + dist[k][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }

    // no need to check negative-cycles

    return true;
}

int main() {
    int testcases;
    cin >> testcases;
    for (int t = 1; t < testcases + 1; t++) {
        int n;
        cin >> n;
        vector<Node> arr(n);
        int x, y;

        for (int i=0; i < n; i++) {
            cin >> x >> y;
            arr[i].first = x;
            arr[i].second = y;
        }

        vector<vector<double> > dist(n, vector<double>(n, INF));
        int xij, yij, distIJ;
        for (int i=0; i < n; i++) {
            for (int j=0; j < n; j++) {
                if (i==j) {
                    dist[i][j] = 0;
                    continue;
                }

                xij = abs(arr[i].first - arr[j].first);
                yij = abs(arr[i].second - arr[j].second);
                distIJ = xij * xij + yij * yij;
                if (distIJ > maxVal * maxVal) continue;
                dist[i][j] = sqrt(distIJ);
            }
        }
        
        FloydWarShall(arr, dist);

        double result = 0.0;
        for (auto& subDist : dist) {
            for (auto& d : subDist) {
                if (d > result) {
                    result = d;
                }
            }
        }
        cout << "Case #" << t << ":"<< endl;
        if (result == INF) {
            cout << "Send Kurdy" << endl;
        } else {
            cout << setprecision(4) << fixed << result << endl;
        }
        cout << endl;
    }
    return 0;
}
