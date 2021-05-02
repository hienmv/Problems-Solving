// https://leetcode.com/problems/min-stack/
// #stack #design
/*
    use two min-heap:
        one to store added items from stack.
        one to store removed items from stack.
    O(nlogn)
*/
/*
class MinStack {

    private PriorityQueue<Integer> pq;
    private Deque<Integer> stack;
    private PriorityQueue<Integer> deletedPq;
    public MinStack() {
        pq = new PriorityQueue<>(); 
        deletedPq = new PriorityQueue<>();
        stack = new LinkedList<>();
    }
    
    public void push(int x) {
        stack.addFirst(x);
        pq.add(x);
    }
    
    public void pop() {      
        deletedPq.add(stack.pollFirst());
    }
    
    public int top() {
        return stack.peekFirst();
    }
    
    public int getMin() {
        while (!deletedPq.isEmpty()) {
            int v = deletedPq.peek();
            int q = pq.peek();
            if (v == q) {
                deletedPq.poll();
                pq.poll();
            }
            else {
                break;
            }
        }
        return pq.peek();
    }
}
*/
    
/*
Better way: 0(n)
 
20 15 30 18 22 10 15 10 3 5 2 9 10 11 

Stack: 20 15 30 18 22 10 15
=>
min:   20 15 15 15 15 10 10
=> 
*/
class MinStack {

    private Deque<Integer> stack;
    private Deque<Integer> minStack;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList<>();        
        minStack = new LinkedList<>();
    }
    
    public void push(int x) {
        stack.addFirst(x);
        int min = stack.peekFirst();
        if (!minStack.isEmpty()) {
            min = Math.min(min, minStack.peekFirst());
        }
        minStack.addFirst(min);
    }
    
    public void pop() {      
        stack.pollFirst();
        minStack.pollFirst();
    }
    
    public int top() {
        return stack.peekFirst();
    }
    
    public int getMin() {
        return minStack.peekFirst();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */