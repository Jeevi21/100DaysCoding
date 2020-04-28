/**
https://leetcode.com/problems/rotate-image

I know this question. 
1. Just swap the elements alone diagonal. Do not forget, j=i+1 , If u start from 0 , it ll reswap the matrix again.
2. Reverse it coloumn wise. 

It makes 2 passes.. Can you try in one pass , next time?


*/

class Solution {
    public void rotate(int[][] matrix) {
        
        //1. swap diagonal level elements
        
        for(int i=0;i<matrix.length;i++){
            for(int j=i+1;j<matrix[0].length;j++){
               int tmp =  matrix[i][j];
                matrix[i][j]  =matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        
        //reverse -> first col to last col 
        int left = 0 ,right = matrix[0].length-1;
        while(left<right){
            for(int i=0;i<matrix.length;i++){
                int tmp = matrix[i][right];
                matrix[i][right] =  matrix[i][left];
                matrix[i][left]= tmp;
            }
            
            right--;
            left++;
        }
        
    }
}