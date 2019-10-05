/** https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3146
 * #queue #stack #priorityqueue
 * provided that the data structure is used is queue/stack/priorityQueue(maxHeap). 
 */
#include<iostream>
#include<stack>
#include<queue>

using namespace std;

int main() {
    int n;
    while(cin >> n) {
        if (n == 0) {
            break;
        }
        bool stackFlg = true;
        bool queueFlg = true;
        bool priorityQueueFlg = true;
        stack<int> st;
        queue<int> q;
        priority_queue<int> pq;
        for (int i=0; i < n; i++) {
            int command, val;
            cin >> command;
            cin >> val;
            if (command == 1) {
                if (stackFlg) st.push(val);
                if (queueFlg) q.push(val);
                if (priorityQueueFlg) pq.push(val);
            } else {
                if (stackFlg && !st.empty() && st.top() == val){
                    st.pop();
                } else {
                    stackFlg = false;
                }
                if (queueFlg && !q.empty() && q.front() == val) {
                    q.pop();
                } else {
                    queueFlg = false;
                }
                if (priorityQueueFlg && !pq.empty() && pq.top() == val) {
                    pq.pop();
                } else {
                    priorityQueueFlg = false;
                }
            }
        }

        string result[3];
        int count = 0;
        if (queueFlg) {
            result[count++] = "queue";
        }
        if (stackFlg) {
            result[count++] = "stack";
        }
        if (priorityQueueFlg) {
            result[count++] = "priority queue";
        }
        if (count == 0) {
            cout << "impossible" << endl;
        } else if (count == 1) {
            cout << result[0] << endl;
        } else {
            cout << "not sure" << endl;
        }
    }

    return 0;
}