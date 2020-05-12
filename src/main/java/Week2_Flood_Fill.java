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

An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

At the end, return the modified image.

Example 1:
Input: 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: 
From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 
by a path of the same color as the starting pixel are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected
to the starting pixel.

Note:

The length of image and image[0] will be in the range [1, 50].
The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
The value of each color in image[i][j] and newColor will be an integer in [0, 65535].


*/
public class Week2_Flood_Fill {
    
    
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor){
    
        if(image == null )
            return new int[0][0];       // Note: image[sr][sc] == newColor - condition is required. ie when the new color to be filled is same as that of the color. If this condition is not put, then it goes it inifinite loop
        
                                       // See for the same example, pass the newColor value as '1' instead of '2' after removing this condition, this will go to inifinite loop.
        
        if(image[sr][sc] == newColor)
                return image;
                                       
        int originalColor = image[sr][sc];
        
        dfs(image, sr, sc, originalColor, newColor);
        
        return image;
        
    }
    
    private static void dfs(int[][] image, int i, int j, int originalColor, int newColor){
        
        // 1. check for rows, cols out of bounds case
        // 2. we will color with the new color only if the current row, col has the original color
        
        if(i < 0 || i >= image.length || j < 0 || j >= image[i].length || image[i][j] != originalColor)
            return;
        
        // at this point we are at row, col whose current color is the original color and that will be replaced with the new color
        
        image[i][j] = newColor;
        
        dfs(image, i - 1, j, originalColor, newColor);
        dfs(image, i + 1, j, originalColor, newColor);
        dfs(image, i, j + 1, originalColor, newColor);
        dfs(image, i, j - 1, originalColor, newColor);
    }
    
    public static void main(String[] args){
        
        int[][] arr = new int[][] {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        
        
        int newColor = 2;
        
        int[][] res = floodFill(arr, 1, 1, newColor);
        
        for(int i=0; i<res.length; i++){
        
            for(int j=0; j<res[i].length; j++){
                
                System.out.print("" + res[i][j] + " ");
            }
            
            System.out.println("");
        
        }
            
        
    
    }
    
}
