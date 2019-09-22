/*	http://lightoj.com/volume_showproblem.php?problem=1129
	idea: TRIE
*/

#include<iostream>
#include<string>

using namespace std;

const int MAX = 10;
struct Node {
	Node* childNode[MAX];
	bool isLeaf;
	
	Node() {
		for (int i=0; i < MAX; i++) {
			childNode[i] = nullptr;
		}
		isLeaf = false;
	}
};
typedef struct Node TrieNode;

bool addNumber(TrieNode* root, string number) {
	TrieNode* pTmp = root;
	bool ans = true;
	int level;
	int idx;
    bool addNewNodeFlg = false;	
	int length = number.length();
	for (level=0; level < length; level++) {
		idx = number[level] - '0';
		if (pTmp->childNode[idx] == nullptr) {
            addNewNodeFlg = true;
			pTmp->childNode[idx] = new TrieNode;
		}
		pTmp = pTmp->childNode[idx];
		if(pTmp->isLeaf && level < length - 1) {
			ans = false;
    }      
	} 
	pTmp->isLeaf = true;

    if(!addNewNodeFlg) {
        ans = false;
    }
    
	return ans;
}

int main() {
	int testcases, numbers;
	string number;
	
	cin >> testcases;

	for (int t=1; t < testcases+1; t++) {
		TrieNode* root = new TrieNode;
		bool ans = true;

		cin >> numbers;
		for (int i=0; i < numbers; i++) {
			cin >> number;
			if (!addNumber(root, number)) {
				ans = false;
			}
		}
		
		cout << "Case " << t << ": " << (ans ? "YES" : "NO") << endl;
	}
	return 0;
}