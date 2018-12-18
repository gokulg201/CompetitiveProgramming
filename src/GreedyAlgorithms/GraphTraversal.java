//$Id$
package GreedyAlgorithms;

import java.util.HashSet;
import java.util.LinkedList;

import GreedyAlgorithms.Graph.Vertex;


public class GraphTraversal {
	Graph<Integer> graph;
	private boolean DFS(Vertex<Integer> source,Vertex<Integer> destination,HashSet<Vertex<Integer>> visited){
		if(visited.contains(source.id)){
			return false;
		}
		visited.add(source);
		if(source.equals(destination))
			return true;
		for(Vertex<Integer> adjacent : source.getAdjacentVertices()){
			if(DFS(adjacent, destination, visited)){
				return true;
			}
		}
		return false;
	}
	private boolean BFS(Vertex<Integer> source,Vertex<Integer> destination){
		HashSet<Vertex<Integer>> visited = new HashSet<Vertex<Integer>>();
		LinkedList<Vertex<Integer>> nextToVisit = new LinkedList<Graph.Vertex<Integer>>();
		nextToVisit.add(source);
		while(!nextToVisit.isEmpty()){
			Vertex<Integer> v = nextToVisit.remove();
			if(v == destination)
				return true;
			if(visited.contains(v))
				continue;
			visited.add(v);
			for(Vertex<Integer> child : v.getAdjacentVertices()){
				nextToVisit.add(child);
			}
		}
		return false;
	}
}
