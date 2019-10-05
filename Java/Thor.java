/** https://codeforces.com/problemset/problem/704/A
 *  #brute-force  #implementation #queue
 */
/*

1: them vao x
2: xóa loại x
Set/HashSet
3: xóa p noti đầu tiên


giả sử có P noti, xóa p noti, mình còn lại P - max(prev_p, p)


unread = 0
cnt_noti = 0
noti_app: array of hashset/set
noti: deque/queue chứa danh sách noti: (x, id)

với mỗi event:
  Nếu type == 1:
    unread++;
    x = noti
    id = ++cnt_noti
    noti_app[x].insert(id)
    app.add({x, id})
  Nếu type == 2:
    unread -= noti_app[x].size()
    noti_app[x].clear()
  Nếu type == 3:
    p = time
    while !noti.empty():
      tmp = noti.pop
      if tmp.id <= p:
        if noti_app[tmp.x] contains tmp.id:
          unread--
          noti_app[tmp.x].remove(tmp.id)

*/

import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.*;

public class Thor {
    public static void main(String[] args)  throws Exception {
        MyScanner sc = new MyScanner(System.in);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = sc.nextInt();
        int q = sc.nextInt();
		int[] addedArr = new int[n+1];
		int[] minusArr = new int[n+1];
		int[] readArr = new int[n+1];
		int prev_read = 0;

		Deque<Node> qu = new LinkedList<>();
		
		int tmp;
		int type;
		int val;
		Node node;
		int prev_nodeVal = 0;
		int allNotifications = 0;
		int unreadNotifications = 0;
		int read = 0;
		for (int i=0; i < q; i++) {
			type = sc.nextInt();
			tmp = sc.nextInt();
			if (1 == type) {
				addedArr[tmp] += 1;	
				if (qu.isEmpty() || prev_nodeVal != tmp) {
					prev_nodeVal = tmp;
					node = new Node(tmp, 1);
					qu.addLast(node);
				} else {
					node = qu.peekLast();
					node.repeated += 1;
				}
				allNotifications += 1;
				unreadNotifications += 1;
			} else if (2 == type) {				
				unreadNotifications -= addedArr[tmp] - minusArr[tmp];
				minusArr[tmp] = addedArr[tmp];
			} else {
				int times = Math.min(tmp, allNotifications) - prev_read;
				prev_read = Math.max(prev_read, Math.min(tmp, allNotifications));
				while (!qu.isEmpty() && times > 0) {
					node = qu.peekFirst();
					val = node.val;
					if (node.repeated <= times) {
						read = node.repeated;
						qu.pollFirst();
					} else {
						node.repeated -= times;
						read = times;
					}
					readArr[val] += read;
					if (readArr[val] > minusArr[val]) {
						unreadNotifications -= readArr[val] - minusArr[val];
						minusArr[val] = readArr[val];
					}
					times -= read;
				}
            }	
            out.write(String.format("%d\n", unreadNotifications));
        }
        out.flush();
    }
}

class Node {
	int val, repeated;
	Node (int val, int repeated) {
		this.val = val;
		this.repeated = repeated;
	}
}

class MyScanner {
    BufferedReader br;
    StringTokenizer st;

    public MyScanner(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
    }
    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
    int nextInt() {
        return Integer.parseInt(next());
    }
    long nextLong() {
        return Long.parseLong(next());
    }
    double nextDouble() {
        return Double.parseDouble(next());
    }
    String nextLine() {
        String str = "";
        try {
                str = br.readLine();
        } catch (IOException e) {
                e.printStackTrace();
        }
        return str;
    }
}