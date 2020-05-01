package com.jv.graph;

/**
    https://leetcode.com/problems/number-of-islands/
    It is a flood-fill algorithm

    1.Remeber the directions matrix
    2.DFS -> isVisited we have in the same grid.(By making it as 0)
    3.No of disconnected Components basically.


*/
public class NoOfIsland {
    
    int [] directions ={1,0,-1,0,1};
    
    
    private boolean isSafe(char[][] grid,int x , int y){
        if(x<0 || x>=grid.length || y<0 || y>= grid[0].length || grid[x][y]!='1')
            return false;
        
        return true;
    }
    
    private void findIsland(char[][] grid, int x,int y){
        if(isSafe(grid,x,y)){
            
            grid[x][y] = '0';
            
            for(int d= 0;d<directions.length-1;d++){
                findIsland(grid,x+directions[d],y+directions[d+1]);
            }
            
        }    
    }
    
    public int numIslands(char[][] grid) {
        
        int count=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    findIsland(grid,i,j);
                    count++;
                }
                    
            }
        }
        
        return count;
    }
}