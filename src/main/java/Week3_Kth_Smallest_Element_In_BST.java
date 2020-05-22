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

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

 

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3




*/

import java.util.*;

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

// Complexity: All Approaches
// TIme: O(h) -> h is height of the tree
// Space: Approach 1, 3 => O(1), Approach 2: O(h)


public class Week3_Kth_Smallest_Element_In_BST {
    
    // Approach 1: Using global variables & recursive
    
    static int count; 
    static int res;
    
    public static  int kthSmallest(TreeNode root, int k){
        
        if(root == null || k < 0)
            return -1;
        
        count = 0;
        res = -1;
        
        findSmallest(root, k);
        
        return res;
    
    }
    
    
    private  static void findSmallest(TreeNode root, int k){
        
        // do inorder traversal
        
        if(root == null)
            return;
        
        findSmallest(root.left, k);
        
        count++;
        
        if(count == k){
            
            res = root.val;
            return;
        }
        
        findSmallest(root.right, k);
    }
    
    
    //Approach 2: Iteration - Using Stack
    
    public static int kthSmallestUsingStack(TreeNode root, int k){
    
        if(k < 0)
            return -1;
        
        Stack<TreeNode> st = new Stack<>();
        
        while(true){
        
            while(root != null){
                
                st.push(root);
                root = root.left; // keep pushing the "left" node
            }
            
            // now the left node will be null; no more left subtree available to push & we reached the smallest node at the top of stack
            // since inorder - left + curr + right => left is supposed to be smallest now its emoty, next smallest is in the curr node which is present on the top of stack
            
            TreeNode node = st.pop();  //next smallest is in the "curr" node which is present on the top of stack
            k--;                      // this popped node is the next smallest one, so we decrement k value to track the smallest nodes found so far
            
            if(k == 0)
                return node.val;
            
            root = node.right; // now push the "right" node and try recursing it
            
        
        }
        
    }

    
    
    
    //Approach 3: Using left node count 
    
    public static int kthSmallestUsingCount(TreeNode root, int k){
    
        if(k < 0)
            return -1;
        
        return findSmallestUsingCount(root, k);
        
    }
    
    private static int findSmallestUsingCount(TreeNode root, int k){
    
        if(root == null)
            return -1;     // this case would not happen, since we calculate the left count before-hand and then decide which subtree we want to go
        
        int count = getCount(root.left);  // get count of left subtree
        
        if(count + 1 == k)        //  k = 3, left Node count = 2, then leftNodecount(2) + 1 (current node's val )  == k; then current node is the kth element
            return root.val;
        
        else if(count + 1 < k)
            return findSmallestUsingCount(root.right, k - count - 1);     // k = 5; total nodes (left node count(2) + 1(current node)) = 3 < 5 means that left side had less number of nodes than wha we require k, then it means kth node will lie on the right subtree.
                                                                          // so we ignore left nodes + curr node since they already contributed to the array, now k'th will be in the right subtree which is of 'k - leftCount - 1'
         else
            return findSmallestUsingCount(root.left, k);     // k = 3; total nodes (left node count(5) + 1(current node)) = 6 > 3, we know that kth element will appear on the left subtree; so recurse on the left 
                                                                                      
    }
    
    public static int getCount(TreeNode root){
    
        
        if(root == null)
            return 0;
        
        return getCount(root.left) + getCount(root.right) + 1;
    }
    
    public static void main(String[] args){
        
        /*
        
          3
         / \
        1   4
         \
          2
        
        */
        
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        TreeNode one = root1.left;
        one.right = new TreeNode(2);
        root1.right = new TreeNode(4);
        
        int k1= 1;
        System.out.println("kth smallest element: " + (kthSmallest(root1, k1) == 1));
        System.out.println("kth smallest element: " + (kthSmallestUsingCount(root1, k1) == 1));
        System.out.println("kth smallest element: " + (kthSmallestUsingStack(root1, k1) == 1));
        
        /*
        
             5
            / \
           3   6
          / \
         2   4
        /
        1
        
        */
        
        TreeNode root2 = new TreeNode(5);
        root2.right = new TreeNode(6);
        root2.left = new TreeNode(3);
        TreeNode three = root2.left;
        three.right = new TreeNode(4);
        three.left = new TreeNode(2);
        TreeNode two = three.left;
        two.left = new TreeNode(1);
        
        int k2 = 3;
        
        System.out.println("kth smallest element: " + (kthSmallest(root2, k2) == 3));
        System.out.println("kth smallest element: " + (kthSmallestUsingCount(root2, k2) == 3));
        System.out.println("kth smallest element: " + (kthSmallestUsingStack(root2, k2) == 3));
    }
    
}
