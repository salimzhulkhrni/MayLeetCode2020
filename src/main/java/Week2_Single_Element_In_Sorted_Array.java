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

You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Find this single element that appears only once.

Example 1:

Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: [3,3,7,7,10,11,11]
Output: 10
 

Note: Your solution should run in O(log n) time and O(1) space.


*/

public class Week2_Single_Element_In_Sorted_Array {
    
    
    public static int singleNonDuplicate(int[] nums){
    
        if(nums == null || nums.length == 0)
            return -1;
        
        // Approach 1: Single traversal - find which is not repeated. O(n)
        // Approach 2: Use XOR. O(n)
        // Approach 3: Use binary search. O(log n)
        
        return binarySearch(0, nums.length - 1, nums);
        
    }
    
    private static int binarySearch(int low, int high, int[] nums){
    
        if(low >= high)
            return nums[low]; // ended up with low >= high, then this is the single element. Handles single element case.
                    
        
        int mid = low + (high - low) / 2;
        
        // if nums[mid -1] & nums[mid] are not equal & nums[mid] & nums[mid + 1] are not equal - then we know mid is the single element that is not duplicated
        
        if(nums[mid - 1] !=  nums[mid] && nums[mid] != nums[mid + 1])
            return nums[mid];
        
        if(nums[mid] == nums[mid - 1]){
            
            // if mid & mid - 1 are equal, we check how many elements including mid are there in the left side of mid.
            // 1. If no: of elements is even Ex: 4- we know that mid & mid - 1 has same elements, so out of remaining 2 elements will definitely be the same. Therefore, left hand side contains all duplicated elements, so we need to recurse on the right hand side
            // 2. If no: of elements is odd Ex: 5 - we know mid & mid - 1 has same elements, so out of remaining 3, two elements will be same and one element will be a single element, so we must continue recursing on the left 
        
            int numOfElements = mid - low + 1;
            
            if(numOfElements % 2 == 0)  // even elements, recurse on the right
                return binarySearch(mid + 1, high, nums);
            else                        // odd elements continue recursing on the left
                return binarySearch(low, mid - 2, nums);  // wyhy mid - 2 ? we know nums[mid] & nums[mid - 1] are the same, so need to take this in the next recursion. If we take this, then it causes problems in calculating no: of odd/even elements in the next recursion.
        
        }
        
        if(nums[mid] == nums[mid + 1]){
        
            // same logic as above, but for the right side of the array
            
            int numOfElements = high - mid + 1;
            
            if(numOfElements % 2 == 0)  // even elements, recurse on the left
                return binarySearch(low, mid - 1, nums);
            else                        // odd elements continue recursing on the right
                return binarySearch(mid + 2, high, nums);
        }
        
        return -1;
    
    }
    
    public static void main(String[] args){
    
        int[] arr1 = new int[] { 1,1,2,3,3,4,4,8,8 };
        int[] arr2 = new int[] { 3,3,7,7,10,11,11 };
        
        System.out.println("arr1: " + (singleNonDuplicate(arr1) == 2) );
        System.out.println("arr2: " + (singleNonDuplicate(arr2) == 10));
        
    
    }
}
