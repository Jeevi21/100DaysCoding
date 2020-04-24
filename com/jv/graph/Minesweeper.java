/**
https://leetcode.com/problems/minesweeper/
1. Modified is Safe - Added the condition (board[x][y]!='E' && board[x][y]!='M')  - which made it working. 

2.Its basically dfs where i missed isVisited Array

3.Whenever it is moving in all directions(8 or even 4) , i.e when it have posibilities of visited already visited cell. then isVisited is mandatory.

4. Here we change the existing matrix values, so that it will not be considered again (isSafe is a crutial part )

5.
*/

public class Minesweeper {
    
    int[] dx = {-1, 0, 1, -1, 1, 0, 1, -1};
    int[] dy = {-1, 1, 1, 0, -1, -1, 0, 1};
    
    private int getAdjacentMines(char [][] board , int x , int y){
       
        int count=0;
        for(int i=0;i<dx.length;i++){
        	
            if(x+dx[i] < 0 || x+dx[i] >= board.length || y+dy[i]<0 || y+dy[i] >= board[0].length)
                 continue;
            
            if(board[x+dx[i]][y+dy[i]]=='M')
                count++;
        }
         // System.out.println("Adjacent : "+ x +" - "+ y +"- "+ count);
        return count;
    }
    
 
    private boolean isSafe(char [][] board , int x , int y){
        if(x<0 || x >= board.length || y<0 || y >= board[0].length || (board[x][y]!='E' && board[x][y]!='M') )
            return false;
        
        return true;
    }
    
       /*
        Irrespective of who clicks.. 
        if cell == 'M' , mark cell as X .. Done.
        if cell == 'E' && getAdjacentMines(cell) == 0  , mark cell B and recurse in all 8 directions
        if cell == 'E' && getAdjacentMines(cell) > 0  , set cell = getAdjacentMines() 
    */
    private void play(char[][] board , int x , int y){
        
        if(isSafe(board , x, y)){ //If it is safe to move.            

            //isVisited is not required, since we are marking cell as B after revealing

            if(board[x][y] =='M'){
                board[x][y]='X'; //Game over
            }
            else{
                int minesAround = getAdjacentMines(board,x,y);
                if(minesAround == 0 ){
                    board[x][y]='B';
                    
                    //Now recurse in all 8 directions
                    for(int i=0;i<dx.length;i++){ //in all direction
                        play(board,x+dx[i],y+dy[i]);                    
                    }
                }
                else if (minesAround>0)
                    board[x][y]= (char)(minesAround+48); // or minesAround+'0'
                
            }
        }
    }
    
    public char[][] updateBoard(char[][] board, int[] click) {
        
        play(board, click[0],click[1]);
        
        return board;
        
    }
    
   
}