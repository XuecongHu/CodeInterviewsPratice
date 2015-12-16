package chapter2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * 树结点
 */
class TreeNode {
    int val;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(int val) {
        this.val = val;
    }

    public void setChildren(TreeNode left, TreeNode right){
        this.left = left;
        this.right = right;
    }
}

/**二叉树操作
 * Created by frank on 15-12-16.
 */
public class MyTree {
    ArrayList<Integer> result = new ArrayList<Integer>();

    /**
     * 前序遍历递归实现
     * @param node
     */
    public void preorderTraversalRecursive(TreeNode node){
        if(node != null){
            result.add(node.val);
            preorderTraversalRecursive(node.left);
            preorderTraversalRecursive(node.right);
        }
    }

    /**
     * 前序遍历非递归实现
     * @param node
     */
    public void preorderTraversalLoop(TreeNode node){
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode p = node;
        while(!stack.isEmpty()||p != null){
            if (p != null){
                result.add(p.val);
                stack.push(p);
                p = p.left;
            }else {
                p = stack.poll();
                p = p.right;
            }
        }
    }

    /**
     * 中序遍历递归实现
     * @param node
     */
    public void inorderTraversalRecursive(TreeNode node){
        if(node != null){
            inorderTraversalRecursive(node.left);
            result.add(node.val);
            inorderTraversalRecursive(node.right);
        }
    }

    /**
     * 中序遍历非递归实现
     * @param node
     */
    public void inorderTraversalLoop(TreeNode node){
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode p = node;
        while(!stack.isEmpty()||p != null){
            if(p != null){
                stack.push(p);
                p = p.left;
            }else {
                p = stack.poll();
                result.add(p.val);
                p = p.right;
            }
        }
    }

    public void postorderTraversalRecursive(TreeNode node){
        if(node != null){
            postorderTraversalRecursive(node.left);
            postorderTraversalRecursive(node.right);
            result.add(node.val);
        }
    }

    public void postorderTraversalLoopVisited(TreeNode node){
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode p = node;
        TreeNode visited = null;
        TreeNode peek;
        while(!stack.isEmpty()||p != null){
            if(p != null){
                stack.push(p);
                p = p.left;
            }else{
                peek = stack.peek();
                if(peek.right!=null && visited!=peek.right)
                    p = peek.right;
                else{
                    result.add(peek.val);
                    visited = stack.poll();
                }
            }
        }
    }

    public void postorderTraversalDoubleStack(TreeNode node){
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        Deque<TreeNode> output = new ArrayDeque<TreeNode>();
        stack.push(node);
        while(!stack.isEmpty()){
            TreeNode p = stack.poll();
            output.push(p);

            if(p.left!=null)
                stack.push(p.left);
            if(p.right!=null)
                stack.push(p.right);
        }
        while(!output.isEmpty())
            result.add(output.poll().val);
    }

    public TreeNode constructBinaryTree(int[] preorder, int []inorder){
        if(preorder==null||inorder==null||
                preorder.length==0||inorder.length==0||
                preorder.length!=inorder.length)
            return null;
        return reConstructBinaryTree(preorder, inorder);
    }

    public TreeNode reConstructBinaryTree(int[] preorder, int[] inorder){
        int root = preorder[0];
        int root_index = -1;
        for(int i=0;i<inorder.length;i++){
            if(inorder[i] == root){
                root_index = i;
                break;
            }
        }

        TreeNode rootNode = new TreeNode(root);

        if(root_index>0){
            int[] newLeftInorder = new int[root_index];
            for(int i=0;i<root_index;i++)
                newLeftInorder[i] = inorder[i];
            int[] newLeftPreorder = new int[root_index];
            for(int i=0;i<root_index;i++)
                newLeftPreorder[i] = preorder[i+1];

            rootNode.left = reConstructBinaryTree(newLeftPreorder, newLeftInorder);
        }

        if(root_index!=preorder.length-1){
            int rightLen = inorder.length-root_index-1;
            int[] newRightInorder = new int[rightLen];
            for(int i=0;i<rightLen;i++)
                newRightInorder[i] = inorder[i+1+root_index];
            int[] newRightPreorder = new int[rightLen];
            for(int i=0;i<rightLen;i++)
                newRightPreorder[i] = preorder[i+1+root_index];

            rootNode.right = reConstructBinaryTree(newRightPreorder, newRightInorder);
        }
        return rootNode;
    }

    static void testTraversal(){
        TreeNode[] tns = {new TreeNode(10),
                new TreeNode(6), new TreeNode(14),
                new TreeNode(4), new TreeNode(8),
                new TreeNode(12), new TreeNode(16)};
        tns[0].setChildren(tns[1], tns[2]);
        tns[1].setChildren(tns[3], tns[4]);
        tns[2].setChildren(tns[5], tns[6]);

        MyTree tree = new MyTree();
        TreeNode root = tns[0];
        System.out.println("-----前序遍历-------");
        tree.preorderTraversalLoop(root);
        System.out.println(tree.result);

        tree.result.clear();
        tree.preorderTraversalRecursive(root);
        System.out.println(tree.result);
        System.out.println("-----中序遍历-------");
        tree.result.clear();
        tree.inorderTraversalRecursive(root);
        System.out.println(tree.result);

        tree.result.clear();
        tree.inorderTraversalLoop(root);
        System.out.println(tree.result);

        System.out.println("-----后序遍历-------");
        tree.result.clear();
        tree.postorderTraversalLoopVisited(root);
        System.out.println(tree.result);

        tree.result.clear();
        tree.postorderTraversalDoubleStack(root);
        System.out.println(tree.result);
    }


    public static void testConstruct(){
        int[] preorder = {1,2,4,7,3,5,6,8};
        int[] inorder = {4,7,2,1,5,3,8,6};
        MyTree tree = new MyTree();
        TreeNode root = tree.constructBinaryTree(preorder,inorder);
        tree.preorderTraversalLoop(root);
        System.out.println(tree.result);
        tree.result.clear();
        tree.inorderTraversalLoop(root);
        System.out.println(tree.result);
    }
    public static void main(String[] args) {
//        testTraversal();
        testConstruct();
    }
}
