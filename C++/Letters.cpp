/** https://codeforces.com/problemset/problem/978/C
 *  #binary-search #two-pointer
 */

#include<iostream>
#include<vector>

using namespace std;

typedef long long ll;

int main() {
    int n, m;
    cin >> n >> m;
    vector<ll> arr(n+1);
    ll roomsInDormitory;
    for (int i=1; i < n+1; i++) {
        cin >> roomsInDormitory;
        arr[i] = roomsInDormitory;
    }
    int dormitoryIdx = 0;
    ll allRoomsToDormitory = 0;
    ll roomIdx;
    for (int i=0; i < m; i++) {
        cin >> roomIdx;
        while(roomIdx > allRoomsToDormitory) {
           dormitoryIdx++;
           allRoomsToDormitory += arr[dormitoryIdx];
        }        
    
        cout << dormitoryIdx << " " << roomIdx - (allRoomsToDormitory - arr[dormitoryIdx])<< endl; 
    }
    return 0;
}