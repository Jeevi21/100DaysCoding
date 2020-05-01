package com.jv.graph;
/**
https://app.codesignal.com/arcade/graphs-arcade/kingdom-roads/nCMisf4ZKpDLdHevE

Tried a graph problem in new portal. 
Its a easy one.
Take aways
    1.Its easy with Adj Matrix.
    2. What if graph is undirected? - It works. 
    3. What if graph have self loops - Its works
    But both 2,3 are just useless points. which have not efect. so, you can remove that and consider only directed. 

*/

public class NewRoadSystem{
    boolean newRoadSystem(boolean[][] roadRegister) {

        //No of Incomming  == no of out going.. 

        for(int i=0;i<roadRegister.length;i++){ //i is current vertex

            //check the outgoing..
            int outGoing=0;
            for(int col =0 ;col <roadRegister[0].length;col++){
                if(roadRegister[i][col])
                    outGoing++;
            }

            int inComing=0;
            for(int row =0 ;row <roadRegister[0].length;row++){
                if(roadRegister[row][i])
                    inComing++;
            }

            if(outGoing!=inComing)
                return false;
        }

        return true;

    }


}