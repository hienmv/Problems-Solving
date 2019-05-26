/** http://www.lightoj.com/volume_showproblem.php?problem=1002
 *  idea: dijkstra
 * */

#include<iostream>
#include<vector>
#include<queue>
#include<climits>

using namespace std;

typedef pair<int, int> Node;
struct Comp {
    bool operator()(const Node& l, const Node& r) {
        return l.second > r.second;
    }
};


int Dijkstra(vector<vector<Node> >& graph, int startVertex, int distArr[]) {
        
    distArr[startVertex] = 0;
    priority_queue<Node, vector<Node>, Comp> pq;
    pq.push(Node(startVertex, 0));
    while(!pq.empty()) {
        Node node = pq.top();
        pq.pop();
        int id = node.first;
        int dist = node.second;
        
        if (dist > distArr[id]) {
            continue;
        }
        for(Node neighbour : graph.at(id)) {
            int newDist = max(dist, neighbour.second);
            if (newDist < distArr[neighbour.first]) {
                distArr[neighbour.first] = newDist;
                pq.push(Node(neighbour.first, newDist));
            }
        }
    }
}

int main() 
{
    int testCases;
    cin >> testCases;
    for (int testcase=1; testcase < testCases+1; testcase++) {
        int n, m;
        cin >> n >> m;

        vector<vector<Node> > graph(n);
        
        for(int i=0; i < m; i++) {
            int u, v, w;
            cin >> u >> v >> w; 

            graph.at(u).push_back(Node(v, w));
            graph.at(v).push_back(Node(u, w));
        }
        int startVertex;
        cin >> startVertex;
    
        int distArr[n];
        for (int i=0; i < n; i++) {
            distArr[i] = INT_MAX;
        } 
        
        Dijkstra(graph, startVertex, distArr);

        cout << "Case " << testcase << ":" << endl;
        for (int eachDist : distArr) {
            if(eachDist == INT_MAX) {
                cout << "Impossible" << endl;
            } else {
                cout << eachDist << endl;
            }
        }
    }
}



