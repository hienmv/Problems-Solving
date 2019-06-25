/** https://codeforces.com/contest/1176/problem/E
 *  idea: dfs and related
 */
#include<iostream>
#include<vector>

using namespace std;

int main() {
    int testcases;
    cin >> testcases;
    for (int t=0; t < testcases; t++) {
        int n, m;
        cin >> n >> m;
        int node, neighbour;
        vector<vector<int> > graph(n+1);
        for (int i=0; i < m; i++) {
            cin >> node >> neighbour;
            graph[node].push_back(neighbour);
            graph[neighbour].push_back(node);
        }
        
        vector<int> chooseArr;
        vector<int> escapeArr;
        vector<bool> checkGraph(n+1);
        for (int i=1; i < graph.size(); i++) {
            if (!checkGraph[i]) {
                chooseArr.push_back(i);
                checkGraph[i] = true;
                for (int adjacent : graph[i]) {
                    if (!checkGraph[adjacent]) {
                        escapeArr.push_back(adjacent);
                        checkGraph[adjacent] = true; 
                    }
                }
            }
        } 

        if (chooseArr.size() < escapeArr.size()) {
            cout << chooseArr.size() << endl;  
            for ( int v : chooseArr){
                cout << v << " ";
            }
        } else {
            cout << escapeArr.size() << endl;
            for ( int v : escapeArr){
                cout << v << " ";
            }
        }
        cout << endl;
    }
    return 0;
}