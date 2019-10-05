/** https://www.spoj.com/problems/ARBITRAG/
 *  #floyd-warshall #shortest-path
 */

#include<iostream>
#include<vector>
#include<map>
using namespace std;

const int maxElements = 30;
bool FloydWarShall(vector< vector<double> >& graph) {
    int i, j, k;
    int V = graph.size();
    for (k = 0; k < V; k++) {
        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                if (graph[i][j] < graph[i][k] * graph[k][j]) {
                    graph[i][j] = graph[i][k] * graph[k][j];
                }
            }
        }
    }

    // no need to check negative-cycles
    
    return true;
}

int main() {
    int n; 
    int testcase = 0;
    while (cin >> n) {
        if (n == 0) break;
        testcase++;

        map<string, int> currencyMap;
        string tmp;
        for (int i=0; i < n; i++) {
            cin >> tmp;
            currencyMap[tmp] = i;
            //currencyMap.insert(pair<string, int>(tmp, i));
            // note: if use [] of map/ unorder_map, if not exist -> auto create.
        } 
        int m;
        cin >> m;
        string org, exch;
        double rate;
        vector< vector<double> > graph(n, vector<double>(n, 0.0));
        for (int i=0; i < n; i++) {
            graph[i][i] = 1.0;
        }
        int i, j;
        for (int k=0; k < m; k++) {
            cin >> org >> rate >> exch;
            i = currencyMap[org];
            j = currencyMap[exch];
            if (rate > graph[i][j]) {
                graph[i][j] = rate;
            }
        }
        
        FloydWarShall(graph);

        bool possibleFlg = false;
        for (int i=0; i < n; i++) {
            if (graph[i][i] > 1.0) {
                possibleFlg = true;
                break;
            }
        }
        cout << "Case " << testcase << ": " << (possibleFlg ? "Yes" : "No") << endl;
    }


    return 0;
}