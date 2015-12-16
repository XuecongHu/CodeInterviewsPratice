package chapter2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * 输入一个链表，从尾到头打印链表每个节点的值。
 * Created by frank on 15-12-16.
 */
public class List {

    /**
     * 链表结点
     */
    static class ListNode{
        int val;
        ListNode next = null;

        ListNode(int val){
            this.val = val;
        }
    }


    /**
     * 从头到尾遍历，但是输出却是从尾到头，是一个栈的结构
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        if(listNode != null){
            Deque<Integer> stack = new ArrayDeque<Integer>();
            ListNode p = listNode;
            while(p != null){
                stack.addFirst(p.val);
                p = p.next;
            }

            if(stack.size() > 0){
                while(!stack.isEmpty()){
                    arrayList.add(stack.pollFirst());
                }
            }
        }

        return arrayList;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2; node2.next = node3; node3.next = node4;
        List list = new List();
        System.out.println(list.printListFromTailToHead(node1));
    }
}
