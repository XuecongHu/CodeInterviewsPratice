package chapter2;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 使用两个队列实现栈
 * Created by frank on 15-12-17.
 */
public class StackImplQueue {
    Queue<Integer> queue1 = new ArrayDeque<Integer>();
    Queue<Integer> queue2 = new ArrayDeque<Integer>();

    public void push(int node) throws Exception{
        if(queue1.isEmpty()&&queue2.isEmpty())
            queue1.add(node);
        else if(!queue1.isEmpty()&&queue2.isEmpty())
            queue1.add(node);
        else if(!queue2.isEmpty()&&queue1.isEmpty())
            queue2.add(node);
        else
            throw new Exception("队列都不为空，有bug");
    }

    public Integer pop(){
        if(!queue1.isEmpty()){
            while(queue1.size()>1){
                queue2.add(queue1.poll());
            }
            return queue1.poll();
        } else if(!queue2.isEmpty()){
            while(queue2.size()>1){
                queue1.add(queue2.poll());
            }
            return queue2.poll();
        } else{
            return null;
        }
    }

    public static void main(String[] args) throws Exception{
        StackImplQueue stack = new StackImplQueue();
        stack.push(1);stack.push(2);stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(4);
        stack.push(5);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
