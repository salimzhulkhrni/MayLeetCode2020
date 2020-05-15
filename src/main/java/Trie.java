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

Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true
Note:

You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.

*/


public class Trie {
    
    // Define Node structure
    
    class Node{
    
        char ch;             // to store current character in the node
        Node[] children;     // array of 26 Node(from a-z) that would represent the children
        boolean isEnd;       // indicate if its end of the word
        
        public Node(char ch){
            
            this.ch = ch;
            this.children = new Node[26]; // we need to size of 26 since chars are from a-z
            this.isEnd = false;           // initialize to false i.e not the end of the word        
        }
    
    }
    
    Node root;
    
    public Trie(){
    
        root = new Node('\0');  // root at the top of the whole tree
    }
    
    public void insert(String word){
        
        Node curr = root;      // point current node to the root i.e top of the tree
        
        for(int i=0; i<word.length(); i++){
            
            char ch = word.charAt(i);
            Node[] children = curr.children;    // get children of the current node
            Node temp = children[ch - 'a'];     // check if the children contain the character that we want to insert as a Trie node
                                                // If not present, create a new Node holding the required char
                                                // Make the newly created child / existing child node as the current node and repeat the process
            if(temp == null){
            
                temp = new Node(ch);
                children[ch - 'a'] = temp;      // add this new child node to its children list nodes'
            }
                
            
            curr = temp;
            
        }
        
        // reached end of word. curr child node will marked as end of the word
        
        curr.isEnd = true;
    
    }
    
    public boolean search(String word){
    
        Node curr = root;
        
        for(int i=0; i<word.length(); i++){
            
            char ch = word.charAt(i);
            Node[] children = curr.children;
            Node temp = children[ch - 'a'];
            
            if(temp == null)
                return false;       // got a character in between that does not exist in any of the current node's childre, return false since that word to be searched for will not exist at all
            
            curr = temp;
        }
        
        return curr.isEnd;      // search is succesfull only if the word ends with the character in the last node
    }
    
    public boolean startsWith(String prefix){
        
        Node curr = root;
        
        for(int i=0; i<prefix.length(); i++){
        
            char ch = prefix.charAt(i);
            Node[] children = curr.children;
            Node temp = children[ch - 'a'];
            
            if(temp == null)
                return false;      // got a character in between that does not exist in any of the current node's childre, return false since that word to be searched for will not exist at all
            
            curr = temp;
            
        }
        
        return true;     // all the characters in the word were found till now in the trie, not necessary this word has to end since this word is a prefix
        
    }
    
    
    public static void main(String[] args){
    
        Trie trie = new Trie();
        
        trie.insert("apple");
        System.out.println("trie.search(apple)" + (trie.search("apple") == true));   // returns true
        System.out.println("trie.search(app)" + (trie.search("app") == false));     // returns false
        System.out.println("trie.startsWith(app)" + (trie.startsWith("app") == true)); // returns true
        trie.insert("app");   
        System.out.println("trie.search(app)" + (trie.search("app") == true));     // returns true
        
    }
}
