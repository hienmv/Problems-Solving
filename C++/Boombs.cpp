/*  https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1594
* #dijkstra #shortest-path
*/
#include<iostream>
#include<queue>
#include<vector>
#include<map>
#include<sstream>
#include<set>
#include<utility>
#include<climits>

using namespace std;

struct comp {
    template<typename T>
    bool operator()(const T& l, const T& r) const {
        if (l.first == r.first) {
            return l.second < r.second;
        } else {
            return l.first < r.first;
        }
    }
};

typedef pair<int, int> Point;
typedef vector<set<Point, comp> > BoombsGraph;
typedef map<Point, int, comp> Distance;
typedef pair<Point, int> Node;

struct compNode {
    bool operator()(const Node& l, const Node& r) const {
        return l.second > r.second;
    }
};

bool isValidPoint(BoombsGraph& boombsGraph, Point& point, int max_rows, int max_cols) {
    if (point.first < 0 || point.first >= max_rows) return false;
    if (point.second < 0 || point.second >= max_cols) return false;
    if (boombsGraph[point.first].find(point) != boombsGraph[point.first].end()) { 
        return false;
    }
    return true;
}

int Dijkstra(BoombsGraph& boombsGraph, Point source, Point destination, int max_rows, int max_cols) {
    int ret = INT_MAX;
    int dx[] = {-1, 0, 0, 1};
    int dy[] = {0, -1, 1, 0};

    vector<Distance> distArr(max_rows);
    distArr[source.first].insert(Node(source, 0));
    
    priority_queue<Node, vector<Node>, compNode > pq;
    pq.push(Node(source, 0));
   
    while(!pq.empty()) {
        Node node = pq.top();
        pq.pop();
        Point point = node.first;
        int dist = node.second;
  
        if (distArr[point.first].find(point) != distArr[point.first].end() && dist > distArr[point.first][point]) {
            continue;
        }
        
        for (int i=0; i < 4; ++i) {
            Point p(point.first + dx[i], point.second + dy[i]);
            if (isValidPoint(boombsGraph, p, max_rows, max_cols)) {
                int newdist = dist + 1;
                if(distArr[p.first].find(p) != distArr[p.first].end()) {
                    if (newdist < distArr[p.first][p]) {
                        distArr[p.first][p] = newdist;
                        pq.push(Node(p, newdist));
                    }
                } else {
                    distArr[p.first].insert(pair<Point, int>(p, newdist));
                    pq.push(Node(p, newdist));
                }
            }
        }
    }
    
    if (distArr[destination.first].find(destination) != distArr[destination.first].end()) {
        ret = distArr[destination.first][destination];
    } else {
        BoombsGraph bg(max_rows);
        ret = Dijkstra(bg, source, destination, max_rows, max_cols);
    }
    return ret;
}


int main() {
    int r, c;
    while(true) {
        cin >> r >> c;
        if (r==0 && c==0) {
            break;
        }

        int numberBoombsRow;
        cin >> numberBoombsRow;
        
        // graph of points at which bombs is set.
        BoombsGraph boombsGraph(r); 
        for (int i=0; i < numberBoombsRow; ++i) {
            int row, cols, col;
            cin >> row >> cols;
            for (int c=0; c < cols; c++) {
                cin >> col;
                boombsGraph[row].insert(Point(row, col));
            }
        }

        int sx, sy, dx, dy;
        cin >> sx >> sy;
        cin >> dx >> dy;
        int ret = Dijkstra(boombsGraph, Point(sx, sy), Point(dx, dy), r, c);
        cout << ret << endl;
    }

    return 0;
}