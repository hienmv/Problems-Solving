/** https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1187
 *  idea: use Dijkstra
 * giả sử U là đỉnh có feast lớn nhất

    đường đi tối ưu đi qua U mà nhận U lớn nhất
        dist[s][U] + dist[U][t] + feast[u]
        dist[U][s] + dist[U][t]
        điều kiện: tất cả đỉnh v trong đường đi sẽ có feast[v] <= feast[u]

    query: x, y
        min(dist[U][x] + dist[U][y] + feast[U]]) với U = [1...N]
    => should use Floyd WarShall
*/

#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>
#include<climits>

using namespace std;

typedef long long ll;
typedef pair<int, ll> Node;
typedef pair<ll, ll> DistNode;
const long long INF = LLONG_MAX;

struct comp {
    bool operator()(Node& n1, Node& n2) {
        return n1.second > n2.second;
    }
};

void Dijkstra(vector<vector<Node> >& graph, int src, vector<vector<ll> >& distArr, vector<ll>& feastCostArr) {
    int V = graph.size();
    vector<ll> dist (V, INF);
    dist[src] = 0;
    priority_queue<Node, vector<Node>, comp> pq;
    pq.push(Node(src, 0));
    while(!pq.empty()) {
        Node node = pq.top();
        pq.pop();
        for (Node& neighbour : graph[node.first] ) {
            if ( dist[node.first] != INF
                && feastCostArr[neighbour.first] <= feastCostArr[src]
                && dist[node.first] + neighbour.second < dist[neighbour.first])
            {
                dist[neighbour.first] = dist[node.first] + neighbour.second;
                pq.push(Node(neighbour.first, dist[neighbour.first]));
            }
        }
    }
    for (int i=0; i < V; i++) {
        distArr[src][i] = dist[i];
    }
}
int main() {
    int c, r, q;
    int testcase = 0;
    while(true) {
        cin >> c >> r >> q;
        if (c==0 && r==0 && q==0) {
            break;
        }
        testcase++;
        if (testcase > 1) {
            cout << endl;
        }
        vector<ll> feastCostArr(c+1);
        ll tmp;
        for (int i=1; i < feastCostArr.size(); i++) {
            cin >> tmp;
            feastCostArr[i] = tmp;
        }
        vector<vector<Node> > graph(c+1);
        int v1, v2;
        ll cost;
        for(int i=0; i < r; i++) {
            cin >> v1 >> v2 >> cost;
            graph[v1].push_back(Node(v2, cost));
            graph[v2].push_back(Node(v1, cost));
        }

        vector<vector<ll> > distArr(c+1, vector<ll>(c+1, INF));
        for (int i=1; i < graph.size(); i++) {
            // tìm đường đi ngắn nhất giữa i tới các đỉnh khác, limit feastCostArr[i]
            Dijkstra(graph, i, distArr, feastCostArr);
        }
        cout << "Case #" << testcase << endl;
        
        int src, dest;
        
        for (int i=0; i < q; i++) {
            ll result = INF;
            cin >> src >> dest;
            for (int i=1; i < graph.size(); i++) {
                if (distArr[i][src] == INF || distArr[i][dest] == INF) continue;
                result = min(result, distArr[i][src] + distArr[i][dest] + feastCostArr[i]);
            }
            if (result == INF) {
                cout << -1 << endl;
            } else {
                cout << result << endl;
            }
        }
    }

    return 0;
}