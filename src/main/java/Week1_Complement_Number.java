/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author salim
 */
public class Week1_Complement_Number {
    
    public static int getComplement(int num){
        
        // Solution: Use XOR
        // Truth Table for XOR 
        // 0 ^ 1 = 1 (see that 0 is changed to 1)
        // 1 ^ 1 = 0 (see that 1 is changed to 0)
        // 0 ^ 0 = 0
        // 0 ^ 1 = 1
        // 1. So use the XOR for rach of the bit. So XOR for how many bits needs to be done?
        // If we use XOR for each bit, then ex: for 5 it will be represented as 0.....(29 bits) 1 0 1 ..; doing XOR for each bit becomes 1 1 1 1 ...... 0 1 0 (and this binary respresentation is of some other number which is not 2 (which is supposed to be complement of 5) )
        // 2. How many times we do it? So we do it for the number of bits used(significant bits). How do we find that?  using right shift >>
        // Example: for 5 - 000.. 1 0 1 .. doing right shift for 3 times makes it reach 0. Therefore, the number of bits used is 3.
        // 3. Each time we need to XOR with 1, each digit of the number (equivalent to the number of bits used), and then XOR with 1 with the next digit of num and so on..
        // To acheive this, we can do left shift of 1 << i( if i = 0), 1 << 0 = 001( XOR with the last digit of num), 1 << 2(i ==2) = 010 (XOR with the secnd last digit of num
        // Combine the (1), (2), (3) knowledge from above.
        // Time: O(1)
        // Space: O(1)
        
        int numOfBitsToConsider = 0;
        int temp = num;
        
        // find how many times we have to do XOR based on the number of bits
        while(temp != 0){
            
            temp>>= 1;
            numOfBitsToConsider++;
        }
        
        
        // do XOR for each bit of num based on the number of bits.
        for(int i=0; i<numOfBitsToConsider; i++){
            
            num = num ^ (1 << i);
            
        }
        
        return num;
    
    }
    
    public static void main(String[] args){
    
        Week1_Complement_Number obj = new Week1_Complement_Number();
        int num1 = 5, num2 = 1;
        
        System.out.println("complement of 5 is: " + (obj.getComplement(num1) == 2) + " complement of 1 is: " + (obj.getComplement(num2) == 0));
        
    }
    
}
