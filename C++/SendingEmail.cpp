/** https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1927
 *  idea: use dijkstra
 *  can use pair that faster than Node object
*/

#include<iostream>
#include<queue>
#include<vector>
#include<climits>

using namespace std;
class Node {
public:    
    int id;
    int take_time;
    Node(int id, int take_time) {
        this->id = id;
        this->take_time = take_time;
    }
    // friend bool operator<(const Node& l, const Node& r) {
    //     return l.take_time < r.take_time;
    // }
    // friend bool operator>(const Node& l, const Node& r) {
    //     return l.take_time > r.take_time;
    // }
    bool operator>(const Node& other) const{ // faster than the above
         return this->take_time > other.take_time;
    }
};

int Dijkstra(vector<vector<Node> > graph, int src, int dest) {
    int len = graph.size();
    int timeArr[len];
    for (int i=0; i < len; i++) {
        timeArr[i] = INT_MAX;
    } 
    timeArr[src] = 0;
    priority_queue<Node, vector<Node>, greater<Node> > pq; 
    pq.push(Node(src, timeArr[src]));
    while(!pq.empty()) {
        Node node = pq.top();
        pq.pop();
        int id = node.id;
        int w = node.take_time;
        for (Node& neighbour : graph.at(id)) {
            if ( w + neighbour.take_time < timeArr[neighbour.id]) {
                timeArr[neighbour.id] = w + neighbour.take_time;
                pq.push(Node(neighbour.id, timeArr[neighbour.id]));
            }
        }
    }
    return timeArr[dest] == INT_MAX ? (-1) : timeArr[dest];
}

int main() {
    int testcases;
    cin >> testcases;
    for (int case_num=1; case_num < testcases+1; case_num++) {
        int n, m, s, t;
        cin >> n >> m >> s >> t;
        vector<vector<Node> > graph(n);
        for(int i=0; i < m; i++){
            int sr, dst, take_time;
            cin >> sr >> dst >> take_time;
            graph.at(sr).push_back(Node(dst, take_time));
            graph.at(dst).push_back(Node(sr, take_time));
        }
        int shortestTime = Dijkstra(graph, s, t);
        if (shortestTime == -1) {
            cout << "Case #" << case_num  << ": unreachable" << endl;
        } else {
             cout << "Case #" << case_num << ": " << shortestTime << endl;
        }
    }
    return 0;
}