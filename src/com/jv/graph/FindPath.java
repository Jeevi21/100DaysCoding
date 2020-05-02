package com.jv.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Exploring all the PATHS between nodes - UnWeighted Graph
 * @author jeevi
 *
 *	1. find if path exists - DFS
 *  2. Print All possible paths - DFS
 *
 */
public class FindPath {
	
	
	
	/**
	 * visited is required ?? - Yes , there can be a loop which involves your src,  but not target. So.. your DFS might struck forever in the loop.
	 */
	public static boolean doesPathExistsUtil(Map<Integer,List<Integer>> graph , int V , int dest , Set<Integer> visited) {
		
		visited.add(V);  //It is always good to add it to visited first. Bcoz , even inside if you may want to add it once the base case is reached.

		
		if(V==dest)
			return true;
		
				
		for(int u : graph.get(V)) {
			if(!visited.contains(u) && doesPathExistsUtil(graph, u, dest, visited))
					return true;
		}
		
		return false;
		
	}
	
	public static boolean doesPathExists(Map<Integer,List<Integer>> graph , int src , int dest) {
		
		Set<Integer> visited = new HashSet<>();
		
		if(doesPathExistsUtil(graph, src, dest, visited))
			return true;
		
		/*
		//This is stupid. you want find a path from only source. So above one is enough. 
		for(Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
			
				if(!visited.contains(entry.getKey()) && doesPathExistsUtil(graph, entry.getKey(), dest, visited))
						return true;
							
		}
		*/
		return false;
	}
	
	
	
	private static void getAllPathUtil(Map<Integer,List<Integer>> graph , int v , int dest,Set<Integer> visited , List<Integer> choosen ,List<String> paths) {
		
		visited.add(v);
		choosen.add(v);
		
		if(v==dest) 
			paths.add(choosen.stream().map(e-> String.valueOf(e)).collect(Collectors.joining("/")));
		else {
			
			for(int u : graph.get(v)) {
				if(!visited.contains(u)) 
					getAllPathUtil(graph, u, dest, visited, choosen, paths);
			}
		}
		
		
		choosen.remove(choosen.size()-1);  
		visited.remove(v); //This is important.. so that this will be visited next time.
	
	}
	
	
	public static List<String> getAllPath(Map<Integer,List<Integer>> graph , int src , int dest) {
		
		List<String> paths = new ArrayList<>();
		Set<Integer> visited = new HashSet<>();
		List<Integer> choosen = new ArrayList<>();
		
		
		getAllPathUtil(graph, src, dest, visited, choosen, paths);
		
		return paths;
	}
	
	
	public static String getshortestPath(Map<Integer,List<Integer>> graph , int src , int dest) {
		
		Set<Integer> visited = new HashSet<>();
		
		
		Queue<List<Integer>> queue = new LinkedList<>(); //ArrayDequeue will not accept null.
		
		List<Integer> list = new ArrayList<>();
		list.add(src);
	//	list.add(null);
		
		queue.add(list);
		
		while(!queue.isEmpty()) {
			
			List<Integer> curList= queue.poll();
			int cur = curList.get(curList.size()-1);
			visited.add(cur);
		
			if(cur==dest)
				return curList.stream().map(e -> String.valueOf(e)).collect(Collectors.joining("/"));
			
			
			else {
				for(int u : graph.get(cur)) {
					if(!visited.contains(u)) {
						List<Integer> newList = new ArrayList<>();
						newList.addAll(curList);
						newList.add(u);
						queue.add(newList);
					}
				}
			}
		}
		
		return "";
		
	}
	
	
	public static void main(String[] args) {
	
		
		//This is undirected graph
		Map<Integer,List<Integer>> graph = new HashMap<>(); //Not to have set.. Bcoz i dont want to restrict having more than one edge to same node.
		graph.put(1,Arrays.asList(2,3,5));
		graph.put(2, Arrays.asList(1,3));
		graph.put(3,Arrays.asList(2,1,4));
		graph.put(4,Arrays.asList(3,5));
		graph.put(5,Arrays.asList(4,1)); //Remember this.
		
		
		//1.Does path exists
		System.out.println("Does Path exists : " +  doesPathExists(graph, 1, 5));
		
		
		//2.Print All possible paths
		System.out.println("Paths : " + getAllPath(graph, 1	,89));
		
		//3.Get shortest Path
		System.out.println("Paths : " + getshortestPath(graph, 1, 4));
		
		
		
		
	}
	
	
	

}
