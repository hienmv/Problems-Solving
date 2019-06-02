/** https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=370
 *  idea: Dijkstra
*/

#include<iostream>
#include<queue>
#include<vector>
#include<map>
#include<set>
#include<sstream>

using namespace std;

bool canTransform(string& str1, string& str2) {
    int i=0;
    int j= str1.length() - 1;
    int count = 0;
    while (i <= j) {
        if (str1[i] != str2[i]) {
            count++;
        }
        if (i != j && str1[j] != str2[j]) {
            count++;
        }
        i++;
        j--;
        if (count > 1) {
            return false;
        }
    }
    return true;
}

typedef pair<string, int> Node;
struct comp {
    bool operator()(Node& n1, Node& n2) {
        return n1.second > n2.second;
    }
};

int Dijkstra(map<string, set<string> >& graph, string& source, string& destination) {

    map<string, int> dist;
    dist.insert(pair<string, int>(source, 0));

    priority_queue<Node, vector<Node>, comp> pq;
    pq.push(Node(source, 0));
    while(!pq.empty()) {
        Node node = pq.top();
        pq.pop();
        int w = node.second;
        if (dist.find(node.first) != dist.end() && w > dist[node.first]) {
            continue;
        }
        if (graph.find(node.first) != graph.end()) {
            for (string str : graph[node.first]) {
                if (canTransform(str, node.first)) {
                    if (dist.find(str) == dist.end()) {
                        dist.insert(pair<string, int>(str, w+1));
                        pq.push(Node(str, w+1));
                    } else {
                        if (w+1 < dist.at(str)){
                            dist[str] = w+1;
                            pq.push(Node(str, w+1));
                        }
                    }
                }
            }
        }
    }
    
    return dist.at(destination);
}

int main() {
    int testcases;
    cin >> testcases;
    for (int t=0; t < testcases; t++) {
        string str_tmp;
        vector<vector<string> > dictionary(11);
        map<string, set<string> > graph;
        cin >> str_tmp;
        while (str_tmp != "*") {
            int idx = str_tmp.length();
            for(string& str : dictionary[idx]) {
                if (canTransform(str, str_tmp)) {
                    if (graph.find(str) == graph.end()) {
                        set<string> s;
                        s.insert(str_tmp);
                        graph.insert(pair<string, set<string> >(str, s));
                    } else {
                        graph[str].insert(str_tmp);
                    }
                     
                    if (graph.find(str_tmp) == graph.end()) {
                        set<string> s;
                        s.insert(str);
                        graph.insert(pair<string, set<string> >(str_tmp, s));
                    } else {
                        graph[str_tmp].insert(str);
                    }
                }
            }
            dictionary[idx].push_back(str_tmp);
            cin >> str_tmp;
        }

        string line;
        getline(cin, line);
        getline(cin, line);
        while (!line.empty()) {
            stringstream ss(line);
            string source, destination;
            ss >> source;
            ss >> destination;
            int ret = Dijkstra(graph, source, destination);
            cout << source << " " << destination << " " << ret << endl;
           
            getline(cin, line);
        }
        if (t < testcases - 1) {
            cout << endl;
        }
    }

    return 0;
}