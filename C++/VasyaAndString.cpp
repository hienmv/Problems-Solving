/** http://codeforces.com/problemset/problem/676/C
    idea: two pointer, queue
    other: 
        // nA + k
        // đổi thành 'a': left -> right lớn nhất mà số kí tự b = k
 */
 #include<iostream>
 #include<string>
 #include<queue>
 #include<cmath>

 using namespace std;

 int main() {
     int n, k;
     cin >> n >> k;
     string str;
     cin >> str;

     int max_length_a = 0;
     int len_a = 0;
     int max_length_b = 0;
     int len_b = 0;
     queue<int> qa;
     queue<int> qb;
     int startIdx = 0;
     for (int i=0; i < n; i++) {
        if (str[i] == 'a') {
            len_a++;
            if (len_a > max_length_a) {
                max_length_a = len_a;
            }
        } else {
            if (k == 0) {
                len_a = 0;
                continue;
            }
            if (qa.size() < k) {
                qa.push(i);
                len_a++;
                if (len_a > max_length_a) {
                    max_length_a = len_a;
                }
            } else {
                len_a -= qa.front() - startIdx + 1;
                startIdx = qa.front() + 1;
                qa.pop();
                qa.push(i);
                len_a++;
            }
        }
     }

     startIdx = 0;
     for (int i=0; i < n; i++) {
        if (str[i] == 'b') {
            len_b++;
            if (len_b > max_length_b) {
                max_length_b = len_b;
            }
        } else {
            if (k == 0) {
                len_b = 0;
                continue;
            }
            if (qb.size() < k) {
                qb.push(i);
                len_b++;
                if (len_b > max_length_b) {
                    max_length_b = len_b;
                }
            } else {
                len_b -= qb.front() - startIdx + 1;
                startIdx = qb.front() + 1;
                qb.pop();
                qb.push(i);
                len_b++;
            }
        }
     }
     cout << max(max_length_a, max_length_b) << endl;
     return 0;
 }