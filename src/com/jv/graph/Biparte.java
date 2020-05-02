package com.jv.graph;

/**
  To check whether a Graph is Biparte or not !

Check : https://leetcode.com/problems/is-graph-bipartite/

Takeaways :
    1. Below code checks if the graph is 2 color colorable or not. 
    2. I figured it out that I am gonna use BFS for this. But visvializing BFS was ver easy when you think of it as a tree. So always try to simulate the Level-order traversal of the tree.
    3. ArrayQueue will not accept null. So for level order traversal use LinkedList Instead.
    4. Graphs with Zero (2 empty sets) or 1 Nodes are bipartite
    5. There can be disconnected componenets - be aware. 
    6. graph[0].length - may not work when there is uneven coloumns
    7. BFS - O(V+E) 
    8. Below Code Looks Better 
        if (!validColor(graph, colors, -color, next)) //Instead of saving it in variable and checking
                return false;
    9. The Solution with DFS looks more elegant
   
*/
import java.util.*;

enum Color{
    WHITE , BLACK;
}

class Vertex{
  private String label;
  private Color color;

  public Vertex(String label , Color color){
    this.label = label;
    this.color = color;
  }

  public Color getColor(){
      return color;
  }

  public void setColor(Color color){
      this.color = color;
  }

  public String getLabel(){
      return label;
  }

}

public class Biparte {

  public boolean isBiparte(Map<String,List<Vertex>> adjList,Vertex V){
    Queue<Vertex> queue = new LinkedList<>();
    V.setColor(Color.WHITE);
    queue.add(V);
    queue.add(null);//marker Node
    Color expectedColor = Color.BLACK;
    while(!queue.isEmpty()){
      Vertex cur = queue.poll();

      if(cur==null){ //End of level.. Time for color change and market node.
        expectedColor = (expectedColor==Color.WHITE)?Color.BLACK:Color.WHITE;
        if(!queue.isEmpty())
            queue.add(null);
      }
      else{
          for(Vertex u : adjList.get(V.getLabel())){
              if(u.getColor()!=null && u.getColor()!=expectedColor)
                return false;
              else if(u.getColor()==null){    //This says this node is not yet visited.
                    u.setColor(expectedColor);
                    queue.add(u);
              }
          }
      }
    }
    return true;
  }
}



//------------------------------------Leecode Solution------------------------------
class Solution {
    
    private boolean bipartiteUtil(int [][] graph , int [] color , int V){
    
    Queue<Integer> queue = new LinkedList<>();
    color[V]=1;
    queue.add(V);
    queue.add(null);//marker Node
    int expectedColor = 2;
    while(!queue.isEmpty()){
      Integer cur = queue.poll();

      if(cur==null){ //End of level.. Time for color change and market node.
        expectedColor = (expectedColor==1)?2:1; //flip color
        if(!queue.isEmpty())
            queue.add(null);
      }
      else{
          for(int u : graph[cur]){
              if(color[u]!=0 && color[u]!=expectedColor)
                return false;
              else if(color[u]==0){ 
                    color[u]=expectedColor;
                    queue.add(u);
              }
              
          }
      }
    }
    return true;
  }
    
    public boolean isBipartite(int[][] graph) {
        int [] color = new int[graph.length];
        
        for(int i =0;i<color.length;i++){
            if(color[i]==0){
                if(!bipartiteUtil(graph , color ,i))
                    return false;
            }
                
        }
        
        return true;
    }
}

//-------------------------