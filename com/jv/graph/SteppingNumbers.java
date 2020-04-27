/**
    https://leetcode.com/problems/stepping-numbers/
    Interesting problem. Very hard for me to come up with solution. Even after knowing this is something to do with Graphs.
    Actually it is DFS. So the idea is 
        1. start from single digit number .If they are part of range add them. Else ,
        2. You have 2 choice.. next number can be > or < the current(which is lastDigit). we always do this for the lastDigit.
        3. If the lastDigit==0 , dont take prev(neg)
        4. If the lastDigit==9 , dont take next(pos)
        5. Pay attention to base condition.. cur == 0 || cur>high -> this eliminates 0 to be part of result.So later you can have explicit check on it and add.
        6. Remember its not cur < low.. bcoz something which is less now.. can produce the number in range while decrementing.. 
        7. Have to user Long for cur -> because int can overflow
        8. Integer is 2*pow(10,9) - max size (10 digits)
     
*/

public class SteppingNumbers {
    
    
    public void countUtil( int low , int high ,long cur , List<Integer> choosen){
        
        if(cur==0 || cur>high)
            return;
        if(cur>=low &&cur<=high)
            choosen.add((int)cur);
        
        //I have 2 options now.. from the last digit of cur.. append lastDigit+1 , lastdigit-1
        long lastDigit = cur%10;
        long nextDigit = (cur*10)+lastDigit+1;
        long prevDigit = (cur*10)+lastDigit-1;
        
        if(lastDigit==0)
            countUtil(low,high,nextDigit,choosen);
        else if(lastDigit==9)
            countUtil(low,high,prevDigit,choosen);
        else{
             countUtil(low,high,nextDigit,choosen);
             countUtil(low,high,prevDigit,choosen);
        }
    
    }
    
    public List<Integer> countSteppingNumbers(int low, int high) {
        
        List<Integer> list = new ArrayList<>();
        
        for(long i=0;i<=9;i++){
            countUtil(low,high,i,list);
        }
       
        if(low==0)
            list.add(0);
        
        Collections.sort(list);
        
        return list;
        
    }
    
 
}