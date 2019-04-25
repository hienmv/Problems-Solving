/** https://www.hackerearth.com/practice/data-structures/trees/heapspriority-queues/practice-problems/algorithm/roy-and-trending-topics-1/description/
 *  idea: use data structure heap
 */
import java.util.Scanner;
import java.util.PriorityQueue;

class RoyTrendingTopic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PriorityQueue<Topic> pq = new PriorityQueue<>(5);
        for(int i=0; i < n; i++) {
            int topicId = sc.nextInt();
            int curScore = sc.nextInt();
            int posts = sc.nextInt();  
            int likes = sc.nextInt();
            int comments = sc.nextInt();
            int shares = sc.nextInt();
            long newScore = (long)posts*50 + (long)likes*5 + (long)comments*10 + (long)shares*20;
            Topic tmp = new Topic(topicId, newScore, newScore - curScore);
            if (pq.size() < 5) {
                pq.add(tmp);
            } else {
                if (tmp.compareTo(pq.peek()) > 0) {
                    pq.poll();
                    pq.add(tmp);
                }
            }
        }
        // result
        int count = 0;
        Topic[] result = new Topic[5];
        while(count < 5 && !pq.isEmpty()) {
            result[count] = pq.poll();
            count++;
        }
        for (int i=count-1; i >= 0; i--) {
            System.out.println(result[i]);
        }
    }
}

class Topic implements Comparable<Topic> {
    int topicId;
    long zScore;
    long changedScore;
    Topic(int topicId, long zScore, long changedScore) {
        this.topicId = topicId;
        this.zScore = zScore;
        this.changedScore = changedScore;
    }

    public int compareTo(Topic other) {
        if(this.changedScore == other.changedScore) {
            return this.topicId - other.topicId;
        } else {
            if (this.changedScore < other.changedScore) {
                return -1;
            } else if (this.changedScore > other.changedScore) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    public String toString() {
        return (topicId + " " + zScore);
    }
}