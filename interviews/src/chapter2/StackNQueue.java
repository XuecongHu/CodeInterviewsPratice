package chapter2;

import java.util.Stack;

/**
 * 使用两个栈实现队列
 * Created by frank on 15-12-16.
 */
public class StackNQueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(!stack2.isEmpty())
            return stack2.pop();
        else{
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
    }

    static void testStack(){
        StackNQueue queue = new StackNQueue();
        queue.push(1);queue.push(2);queue.push(3);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }

    public static void main(String[] args) {
        testStack();
    }
}
