/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author salim
 */

/*

Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

It's guaranteed that for the given test cases there is always possible to find a binary search tree with the given requirements.

Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

    8
   / \
  5  10
 / \   \ 
1  7   12

Constraints:

1 <= preorder.length <= 100
1 <= preorder[i] <= 10^8
The values of preorder are distinct.


*/

import java.util.*;

/*
class TreeNode{
    
    int val;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(int val){
        
        this.val = val;
        left = null;
        right = null;
    }

}
*/





public class Week4_Construct_BST_Preorder {
    
    
    // Approach 1 & 2: Iterative & Recursive
    // Time: O(n)
    // Space: O(n) -> build TreeNode for each value in preorder
    
    public static TreeNode bstFromPreorder_recursive(int[] preorder){
    
        if(preorder == null || preorder.length == 0)
            return null;
        
        return buildBST(preorder, 0, preorder.length - 1);
    
    }

    private static TreeNode buildBST(int[] preorder, int left, int right){
    
        if(left > right)
            return null;
        
        TreeNode root = new TreeNode(preorder[left]);  // build a root node straight away since as per pre-order, first node is parent, then left, then right
        
        int index = left + 1;    // we need to separate left and right nodes of the parent. 'left' denotes the parent. From index = left + 1, we need to find those nodes < parent(left)
        
        while(index <= right && preorder[index] < preorder[left])
            index++;                                                // find those nodes whole child < parent(left). keep moving the index
                                                                   // why index <= right? since there can also be a case where till 'right' all nods belong to left branch of parent node and there are no right branch nodes.
                                                                    
        root.left = buildBST(preorder, left + 1, index - 1);     // finally index would have point to the node that is greater than parent forming the right branch nodes. so to recurse on the left branch of nodes, do 'index - 1', this will give those left branch nodes of parent & also so do 'left + 1' since , we already formed a node with parent (left)
        root.right = buildBST(preorder, index, right);          // index points to right branch nodes of parent(left) node. so recurse with index & right
        
        return root;
        
    }
    
    public static TreeNode bstFromPreorder_iterative(int[] preorder){
    
        if(preorder == null || preorder.length == 0)
            return null;
        
        if(preorder.length == 1)
            return new TreeNode(preorder[0]);
        
        Stack<TreeNode> st = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);    // since in preorder first root is always the parent
        st.push(root);
        
        for(int i=1; i<preorder.length; i++){
            
            TreeNode parent = st.peek();                    // parent points to top of stack
            TreeNode child = new TreeNode(preorder[i]);     // make child node
            
            if(child.val < parent.val){
                      
               parent.left = child;                        // if child.val < parent.val, we know that this child comes to the left of parent
               st.push(child);                             // push the child node in the stack. so that node may becomes the parent of the next incoming left child
            }
            
            else{
                
                TreeNode last_parent = null;              // child.val > parent.val, then we need to find out to which parent, this child must be attached as a right node.
                
                while(!st.isEmpty() && st.peek().val < child.val){
                    
                    last_parent = st.pop();              // so we keep popping the parent's present at the top of the stack(whose parent at top of stack's val < child.val) until we find a parent at the top of stack whose parent.val > child's value. We also keep track of the last parent that was popped from the top of the stack
                }
                
               if(last_parent != null)
                   last_parent.right = child;           // once we get the last parent, we place the child to the right child of the this last parent node.
                                                        // we know that the top of stack can either be empty or contains a parent that is greater than child value (since we popped all parents whose parent's val < child's val)
               
               st.push(child);                         // push the child back into stack. Since this child will act as parent of forthcoming children
                
     
            }
        }
        
        return root;
        
    }
    
    public static List<TreeNode> levelOrderTraversal(TreeNode root){
        
        if(root == null)
            return new ArrayList<>();
        
        List<TreeNode> list = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()){
            
            int size = q.size();
            for(int i=0; i<size; i++){
                
                TreeNode node = q.poll();
                
                if(node == null){
                    
                    list.add(node);
                    continue;
                    
                }
                
                list.add(new TreeNode(node.val));
                
                if(node.left != null)
                    q.add(node.left);
                
                if( (node.left == null && node.right != null) || (node.right == null && node.left != null) )
                    q.add(null);
                
                if(node.right != null)
                    q.add(node.right);
                
                
            }
        
        }
        
        return list;
        
    
    }
    
    public static String generateStringFromList(List<TreeNode> list){
    
    
        if(list == null)
            return new String();
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<list.size(); i++){
        
            TreeNode node = list.get(i);
            
            if(node != null)
                sb.append(node.val);
            else
                sb.append("null" );
            
            sb.append("#");
        }
        
        return sb.toString();
            
    
    }
    
    public static void main(String[] args){
        
        int[] preorder = new int[] { 8,5,1,7,10,12 };
        
        TreeNode root1 = bstFromPreorder_recursive(preorder);
        TreeNode root2 = bstFromPreorder_iterative(preorder);
                
        List<TreeNode> list1 = levelOrderTraversal(root1);
        List<TreeNode> list2 = levelOrderTraversal(root2);
        
        String list1_str = generateStringFromList(list1);
        String list2_str = generateStringFromList(list2);
        
        // output: [8,5,10,1,7,null,12]
        
        List<TreeNode> output = new ArrayList<>();
        output.add(new TreeNode(8));
        output.add(new TreeNode(5));
        output.add(new TreeNode(10));
        output.add(new TreeNode(1));
        output.add(new TreeNode(7));
        output.add(null);
        output.add(new TreeNode(12));
        
        String output_str = generateStringFromList(output);

        System.out.println("output_str: " + output_str);
        
        System.out.println("list1_str: " + list1_str);
        
        System.out.println("list1: " + list1_str.equals(output_str));

        System.out.println("list2: " + list2_str.equals(output_str));
    
    }

}
