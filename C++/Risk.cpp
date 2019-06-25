/** https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=508
 *  use: BFS
 */
#include<iostream>
#include<vector>
#include<queue>
#include<iomanip>

using namespace std;
const int V = 21;
const int INF = 100;
void BFS(vector<vector<int> >& graph, int src, vector<vector<int> >& distArr) {
    vector<int> dist(V, INF);
    vector<bool> visitiedArr(V, false);
    queue<int> q;

    dist[src] = 0;
    q.push(src);
    visitiedArr[src] = true;
    
    while (!q.empty()) {
        int node = q.front();
        q.pop();
        for (int& neighbour : graph[node]) {
            if(!visitiedArr[neighbour]) {
                visitiedArr[neighbour] = true;
                dist[neighbour] = dist[node] + 1;
                q.push(neighbour);
            }
        }
    }

    for (int i=0; i < V; i++) {
        distArr[src][i] = dist[i];
        distArr[i][src] = dist[i];
    }
}

int main() {
    int firstNum;
    int testcase = 0;

    while (cin >> firstNum) {
        testcase++;
        cout << "Test Set #" << testcase << endl;


        vector<vector<int> > graph(V);
        vector<vector<int> > distArr(V, vector<int>(V, INF));
        int tmp;
        for (int i=0; i < firstNum; i++) {
            cin >> tmp;
            graph[1].push_back(tmp);
            graph[tmp].push_back(1);
        }
        
        for (int i=2; i < V-1; i++) {
            cin >> firstNum;
            for (int j=0; j < firstNum; j++) {
                cin >> tmp;
                graph[i].push_back(tmp);
                graph[tmp].push_back(i);
            } 
        }
        int n;
        cin >> n;
        int src, dest;
        for (int i=0; i < n; i++) {
            cin >> src >> dest;
            if (distArr[src][dest] == INF) {
                BFS(graph, src, distArr);
            }
            
            // string str1 = src > 9 ? "" : " ";
            // string str2 = dest > 9 ? "" : " ";
            // cout << str1 << src << " to " << str2 << dest << ": " << distArr[src][dest] << endl;
            
            // use setw, setfill
            // setw : width : seteprecision, setfill
            cout << setw(2) << src << " to " << setw(2) << dest << ": " << distArr[src][dest] << endl;
            
        }
        cout << endl;
    }

    return 0;
}