/** https://icpcarchive.ecs.baylor.edu/index.php?option=onlinejudge&page=show_problem&problem=3803
 *  #trie #todo
*/
/*
2 1
abc
abd
acd
0 0

output: 11

[ aacd, acd, ad,
  abacd, abcd, abd,
  abcacd, abccd,
  abdacd, abdcd, abdd
]

p trong P, tim s trong S

a
ab
abc
abd


d
cd
acd

p      s
abc    Xuvw -> abcXuvw
abcX   uvw  -> abcXuvw

       Xyt   - yt
*/

#include<iostream>

using namespace std;

const int MAX = 'z' - 'a' + 1;

struct Node {
    Node* childNode[MAX];
    Node() {
        for (int i=0; i < MAX; i++) {
            childNode[i] = nullptr;
        }
    }
};
typedef struct Node TrieNode;

int addWord(TrieNode* root, string word, int* wordCount) {
	TrieNode* pTmp = root;
	int level, idx;
	int length = word.length();
    int ans = 0;
	for (level=0; level < length; level++) {
		idx = word[level] - 'a';
		if (pTmp->childNode[idx] == nullptr) {
			pTmp->childNode[idx] = new TrieNode;
            ans += 1;
		}
		pTmp = pTmp->childNode[idx];
        if (wordCount != nullptr) {
            wordCount[idx] += 1;
        }
	}
    return ans;
}

int getDuplicatedWordNum(TrieNode* preRoot, int* sufWordCount) {
    int ans = 0;

    TrieNode* preTmp = preRoot;

    return ans;
}

int main() {
    int p, s;
    
    while(true) {
        cin >> p  >> s;
        if (p == 0 && s == 0) break;

        string preStr, sufStr;
        
        TrieNode* preRoot = new TrieNode;
        int numPre = 0;
        for (int i=0; i < p; i++) {
            cin >> preStr;
            numPre += addWord(preRoot, preStr, nullptr);
        }

        // array: [i] is number of word that start by character (i + 'A')
        int sufWordCount[MAX];
        memset(sufWordCount, 0, sizeof(sufWordCount));

        TrieNode* sufRoot = new TrieNode;
        int numSuf = 0;
        for (int i=0; i < s; i++) {
            cin >> sufStr;
            reverse(sufStr.begin(), sufStr.end());
            numSuf += addWord(sufRoot, sufStr, sufWordCount);
            
        }

        int duplicatedWords = 0;
        duplicatedWords = getDuplicatedWordNum(preRoot, sufWordCount);
        cout << numPre * numSuf - duplicatedWords << endl;
    }

    return 0;
}