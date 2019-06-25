/*  https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1112
    idea: Floyd Warshall
*/
#include<iostream>
#include<map>
#include<set>
using namespace std;

int INF = 100000000;
const int V = 'Z' - 'A' + 1;

bool FloydWarShall(int dist[V][V]) {
    int i,j,k;
    for (k=0; k < V; k++ ) {
        for(i=0; i < V; i++) {
            for(j=0; j < V; j++) {
                if (dist[i][j] > dist[i][k] + dist[k][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }

    // check negative-weight cycles
    for(i=0; i < V; i++) {
        for(j=0; j < V; j++) {
            if (dist[i][j] < 0) {
                return false;
            }
        }
    }
    return true;
}

int main() {
    int n;

    while (cin >> n) {
        if (n==0) break;
        int graphYoung[V][V];
        int graphProf[V][V];
        for (int i=0; i < V; i++) {
            for (int j=0; j < V; j++) {
                graphYoung[i][j] = INF;
                graphProf[i][j] = INF;
            }
            graphYoung[i][i] = 0;
            graphProf[i][i] = 0;
        }
        char ym, ub, x, y;
        int c;
        for (int i=0; i < n; i++) {
            cin >> ym >> ub >> x >> y >> c;
            x -= 'A';
            y -= 'A';
            if (x == y) continue;
            if (ym == 'Y') { // young
                graphYoung[x][y] = c;
                if (ub == 'B') {
                    graphYoung[y][x] = c;
                }
            } else { // middle
                graphProf[x][y] = c;
                if (ub == 'B') {
                    graphProf[y][x] = c;
                }
            }
        }
        char addrMe, addrProf;
        cin >> addrMe >> addrProf;
        addrMe -= 'A';
        addrProf -= 'A';

        FloydWarShall(graphYoung);
        FloydWarShall(graphProf);

        int minimumCost = INF;
        map<int, set<char> > mapAddress;
        for (int i=0; i < V; i++) {
            if (graphProf[addrProf][i] >= INF ||  graphYoung[addrMe][i] >= INF) continue;
            
            if (graphProf[addrProf][i] + graphYoung[addrMe][i] < minimumCost) {
                minimumCost = graphProf[addrProf][i] + graphYoung[addrMe][i];
            }
            if (graphProf[addrProf][i] + graphYoung[addrMe][i] == minimumCost) {
                if (mapAddress.find(minimumCost) != mapAddress.end()) {
                    mapAddress[minimumCost].insert(i+'A');
                } else {
                    set<char> s;
                    s.insert(i+'A');
                    mapAddress.insert(pair<int, set<char> >(minimumCost, s));
                }
            }
        }
        if (minimumCost >= INF) {
            cout << "You will never meet." << endl;
        } else {
            cout << minimumCost;
            for (char c : mapAddress[minimumCost]) {
                cout << " " << c;
            }
            cout << endl;
        }
    }

    return 0;
}