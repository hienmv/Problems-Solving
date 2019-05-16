/** https://www.spoj.com/problems/SHPATH/en/
 *  idea: use Dijkstra
 */
#include<iostream>
#include<vector>
#include<map>
#include<queue>
#include<climits>
using namespace std;


class Node {
public:
    int id;
    int dist;
    Node(int idx, int distx) {
        id = idx;
        dist = distx;
    }
    
    friend bool operator<(const Node& n1, const Node& n2) {
        return n1.dist < n2.dist;
    }
    friend bool operator>(const Node& n1, const Node& n2) {
        return n1.dist > n2.dist;
    }
};

void Dijkstra(vector<vector<Node> > graph, int source, int** miniCostArr ) {
    int len = graph.size();
    int distArr[len];
    for(int i=0; i < len; i++) {
        distArr[i] = INT_MAX;
    }
    distArr[source] = 0;
    priority_queue<Node, vector<Node>, greater<Node> > pq;
    pq.push(Node(source, 0));
#ifndef LOGIC_CODE
    while (!pq.empty()) {
        Node node = pq.top();
        pq.pop();
        int id = node.id;
        int w = node.dist;
        for (Node neighbour : graph.at(id)) {
            if (w + neighbour.dist < distArr[neighbour.id]) {
                distArr[neighbour.id] = w + neighbour.dist;
                pq.push(Node(neighbour.id, distArr[neighbour.id]));
            }
        }
    }
    for (int i=0; i < len; i++) {
        miniCostArr[source][i] = distArr[i];
        miniCostArr[i][source] = distArr[i]; 
    }
#else 
    while (!pq.empty()) {
        Node node = pq.top();
        pq.pop();
        int id = node.id;
        int w = node.dist;
        if (w == distArr[id]) {
            miniCostArr[source][i] = w;
            miniCostArr[i][source] = w;

            for (Node neighbour : graph.at(id)) {
                if (w + neighbour.dist < distArr[neighbour.id]) {
                    distArr[neighbour.id] = w + neighbour.dist;
                    pq.push(Node(neighbour.id, distArr[neighbour.id]));
                }
            }
        }
    }
#endif
}

int main() 
{

    int testcases;
    cin >> testcases;
    for (int i=0; i < testcases; i++) {
        int n;
        cin >> n;
        vector<vector<Node> > graph(n+1);
        map<string, int> nameMap;
        for (int k=1; k < graph.size(); k++) {
            string name;
            cin >> name;
            nameMap.insert(pair<string, int>(name, k));
            int numberOfNeighbour;
            cin >> numberOfNeighbour;
            for (int j=0; j < numberOfNeighbour; j++) {
                
                int neighbourId, neighbourDist;
                cin >> neighbourId;
                cin >> neighbourDist;
                graph.at(k).push_back(Node(neighbourId, neighbourDist));
            }
        }
        int numberOfPath;
        cin >> numberOfPath;
        int** miniCostArr;
        miniCostArr = new int *[n+1];
        for(int v1=0; v1 < n+1; v1++) {
            miniCostArr[v1] = new int[n+1];
            for (int v2 = 0; v2 < n+1; v2++) {
                miniCostArr[v1][v2] = -1;
            }
        }
        for (int k=0; k < numberOfPath; k++) {
            string city1, city2;
            cin >> city1;
            cin >> city2;
            int src = nameMap.at(city1);
            int dest = nameMap.at(city2);
            int minimumCost = miniCostArr[src][dest];
            if (minimumCost == -1) {
                Dijkstra(graph, src, miniCostArr);
                minimumCost = miniCostArr[src][dest];
            }
            cout << minimumCost << endl;
        }
        for(int v1=0; v1 < n+1; v1++) {
            delete[] miniCostArr[v1];
        }
        delete[] miniCostArr;

        string s;
        getline(cin, s);
    }
}

