/* https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3252
*  #priorityqueue #queue
*/

#include<queue>
#include<iostream>
#include<vector>

using namespace std;


class Node {
public:
    int val;
    int idx;
    Node(int _val, int _idx) {
        this->val = _val;
        this->idx = _idx;
    }
};

int main() {
    int testcases;
    cin >> testcases;
    for(int i=0; i < testcases; i++) {
        int n, m;
        cin >> n >> m;
        priority_queue<int> pq;
        queue<Node> q;

        for (int j=0; j < n; j++) {
            int tmp;
            cin >> tmp;
            q.push(Node(tmp, j));
            pq.push(tmp);
        }
        int count = 0;
        while (!q.empty()) {
            Node node = q.front();
            q.pop();
            if(node.val == pq.top()) {
                pq.pop();
                count++;
                if(node.idx == m) {
                    break;
                }
            } else {
                q.push(node);
            }
        }
        cout << count << endl;
    }
}
