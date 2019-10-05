/*  https://codeforces.com/problemset/problem/762/B
   #greedy #two-pointer #sorting
*/
#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

bool helper(int& t1, int& t2) {
    if (t1 > 0) {
        t1--;
        return true;
    } else if (t2 > 0) {
        t2--;
        return true;
    }
    return false;
}

int main() {

    int a, b, c;
    cin >> a >> b >> c;
    int m;
    cin >> m;
    vector<int> usbMouseVector(m);
    vector<int> psMouseVector(m); 
    int countUSB=0, countPS = 0;
    int cost;
    string name;
    for(int i=0; i < m; i++) {
        cin >> cost >> name;
        if (name == "USB") {
            usbMouseVector[countUSB] = cost;
            countUSB++;
        } else {
            psMouseVector[countPS] = cost;
            countPS++;
        }
    }
    usbMouseVector.resize(countUSB);
    psMouseVector.resize(countPS);

    sort(usbMouseVector.begin(), usbMouseVector.end());
    sort(psMouseVector.begin(), psMouseVector.end());

    int sumComputer = 0;
    long long sumCost = 0;
   
    int idxUSB = 0;
    int idxPS = 0;

    while (idxPS < psMouseVector.size() && idxUSB < usbMouseVector.size()) {
        if ( a == 0 && b == 0 && c == 0 ) break;
        if ((psMouseVector[idxPS]) < usbMouseVector[idxUSB]) {
            if (helper(b, c)) {
                sumComputer += 1;
                sumCost += psMouseVector[idxPS];
                ++idxPS; 
            } else {
                break;
            }
        } else  {
            if (helper(a, c)){
                sumComputer += 1;
                sumCost += usbMouseVector[idxUSB]; 
                ++idxUSB;
            } else {
                break;
            }
        }
    }
    
    while (idxUSB < usbMouseVector.size()) {
        if (helper(a, c)){
            sumComputer += 1;
            sumCost += usbMouseVector[idxUSB]; 
            ++idxUSB;
        } else {
            break;
        }
    } 
    
    while (idxPS != psMouseVector.size() ) {
        if (helper(b, c)) {
            sumComputer += 1;
            sumCost += psMouseVector[idxPS];
            ++idxPS; 
        } else {
            break;
        }
       
    } 
    
    cout << sumComputer << " " << sumCost << endl;

    return 0;
}