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

Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.


*/

import java.util.*;

public class Week2_Remove_k_digits {
    
    public static String removeKdigits(String num, int k) {
    
    
        // Approach : Use Stack
        // Check if top of stack contains a number >= than the incoming number
        // If yes, it means, we can pop the top contents of the stack and add this new incoming number since adding this small new incoming number will ensure the number formed in the stack will be smallest
        //         - there is also a chance, even after popping top contents of stack, still the second number in the stack which now becomes the top of stack is again > than incoming number.
        //         - In that case, again pop top of stack. This needs to be done until we have popped(or removed 'k' digits ) or until top of stack < incoming number. Because, when top of stack < incoming number, it means we are continuing to form a smaller number
        // If no, it means the stack already contains smaller number till now, and adding new incoming number will continue to generate a smaller number
        
        
        if(k < 0)
            return "";
        
        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        
        int i = 0;
        
        while(i < num.length()){
            
            char ch = num.charAt(i);
            
            if(st.isEmpty()) // nothing to remove or pop, so just push the incoming number
                st.push(ch);
            
            else{
                
                while(!st.isEmpty() && st.peek() > ch && k > 0){     // until we remove 'k' digits, keep checking if top of stack > incoming digit. It must be strictly greater.  
                                                    // If we have top of stack >= incoming digit:- See example:1432219. stack will contain 1, 2( 2 is top of stack), another 2 comes, we have to pop the top of stack.
                                                    // Reason: For top of stack >= incoming nmuber => We dont pop out, it will be 1 2 2 and for top of stack > = incoming number => We pop out it will be 1 2. So in this case 1 2 < 1 2 2. 
                                                    // But it would not work for "112", k = 1, we would have 1(top of stack), another 1 comes, top of stack is popped. We would end up getting "12" as the final answer which is wrong.
                                                    // However, it we put top of stack > incoming digit, 1 would be top of stack, we add another incoming 1 and 2. We would get "11" as our final answer which is even smaller than "12" that we got above.
                                                    // That is why the condition will be "top of stack > incoming number"
                    st.pop();
                    k--;                            // track number of digits to remove
                }
                
                st.push(ch);                        // once all the digits are popped from stack as long as < k or when 'k' digits are removed, just keep pushing the incoming new digits
            
            }
            
            i++;
        }
        
        // by this point, we would have parsed all chars from the string and added to the stack
        
        // Case 1: When 'k' digits are not removed, then we need remove remaning remaining 'k' digits mandatorily
        
        for(i=0; i<k; i++)
            st.pop();     // Ex. Stack contains 1 2 3 4 5 6 7 , k = 4, and only 2 digits are removed, we can remove remaining 2 digits from top of stack. This will ensure the no:of digits are reduced, leading to further smalller number
        
        
        while(!st.isEmpty())
            sb.append(st.pop());   // create string inside String builder
        
        sb.reverse();              // reverse since stack stores in LIFO order
        
        // Case 2: When there are trailing 0's. Example: in the string builder if it contains 0 2 0 0, then we need to remove the leading zero's also since they are not meaningful
        //      - Leading 0's appear at [0] position, so we need to ensure that string builder is of length > 1. 
        //      - Why length > 1? 
        //          -  If string builder contains just 0, no need to remove it, just have it retained as such
        //          -  If string builder contains length >  1. Example: 0 2, we can remove leading zero at [0], resulting in just 2.
        //          -  If string builder is empty, it means all k digits were from the stack resulting in empty string builder. Then just return "0". Example: num = "10", k = 2, stack & string builder is empty, just return "0"
        
        while(sb.length() > 1 && sb.charAt(0) == '0' )
            sb.deleteCharAt(0);
        
        return sb.length() == 0 ? "0" : sb.toString();  // -  If string builder is empty, it means all k digits were from the stack resulting in empty string builder. Then just return "0". Example: num = "10", k = 2, stack & string builder is empty, just return "0"
    
    
        // Time: O(n), Space: O(n)
    }
    
    public static void main(String[] args){
    
        String num1 = "1432219"; int k1 = 3;
        String num2 = "10200";   int k2 = 1;
        String num3 = "10";      int k3 = 2;
        String num4 = "112";     int k4 = 1; 
        
        System.out.println("num1: " + (removeKdigits(num1, k1).equals("1219")));
        System.out.println("num2: " + (removeKdigits(num2, k2).equals("200")));
        System.out.println("num3: " + (removeKdigits(num3, k3).equals("0")));
        System.out.println("num4: " + (removeKdigits(num4, k4).equals("11")));
    }
    
}
