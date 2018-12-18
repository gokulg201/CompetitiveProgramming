//$Id$
package GreedyAlgorithms;

import java.util.HashMap;
import java.util.Map;

import GreedyAlgorithms.Graph.Edge;
import GreedyAlgorithms.Graph.Vertex;

/**
 * Find the shortest path from a start point to the end point in a graph
 * Algo:
 * It is a greedy algorithm wherin it will select a vertex with minimum edge on the priority queue
 * 1) Create a binary min heap(heap + map) that will give the edge with minimum distance
 * 2) Initially set the edges to infinity in heap
 * 3) Select the root vertex(start) and update the edge to zero
 * 4) Update the edges to adjacent vertices only if the edge is less than that in the heap
 * 5) Extract the vertex with minumum edge and continue the step 4 for the extracted vertex
 * 6) Maintain a parent map to keep track of the path
 * 7) Repeat steps 4 and 5 utill we reach the destination vertex
 * 
 * Complexity Analysis:
 * Space complexity - O(E + V) 
 * Time complexity - O(ElogV)
 * 
 * References:
 * https://www.geeksforgeeks.org/?p=27697
 * https://www.youtube.com/watch?v=lAXZGERcDf4 - Tushar Roy
 * https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/DijkstraShortestPath.java
 * @author gokul-4406
 *
 */
public class DijkstraShortestpath {
	public static final int INF = Integer.MAX_VALUE;
	
	/**
	 * Shortest path from the given source vertex to all the vertices in the graph
	 * @param graph
	 * @param sourceVertex
	 * @return
	 */
	 @SuppressWarnings("unchecked")
	public Map<Vertex<Integer>,Integer> shortestPath(Graph<Integer> graph, Vertex<Integer> sourceVertex){
		//heap + map data structure
        BinaryHeap<Vertex<Integer>> minHeap = new BinaryHeap<>();

        //stores shortest distance from root to every vertex
        Map<Vertex<Integer>,Integer> distance = new HashMap<>();

        //stores parent of every vertex in shortest distance
        Map<Vertex<Integer>, Vertex<Integer>> parent = new HashMap<>();
        
        //initialize all vertex with infinite distance from source vertex
        for(Vertex<Integer> vertex: graph.getAllVertex()){
        	minHeap.add(INF, vertex);
        }
        //update the distnace from source vertex to itself to 0
        minHeap.decrease(sourceVertex, 0);
        
        //Update distance path for source vertex
        distance.put(sourceVertex, 0);
        
        //source vertex parent is null
        parent.put(sourceVertex, null);
        
        while(!minHeap.empty()){
        	BinaryHeap<Vertex<Integer>>.Node<Vertex<Integer>> heapNode = minHeap.extractMinNode();
        	Vertex<Integer> current = heapNode.key;
        	
        	//update shortest distance of current vertex from source vertex
            distance.put(current, heapNode.weight);
            
            //update the distances of the adjacent vertices
            for(Edge<Integer> edge:current.getEdges()){
            	Vertex<Integer> adjacentVertex = getVertexForEdge(current, edge);
            	//if heap does not contain adjacent vertex means adjacent vertex already has shortest distance from source vertex
                if(!minHeap.containsData(adjacentVertex)){
                    continue;
                }
                
                //add distance of current vertex to edge weight to get distance of adjacent vertex from source vertex
                //when it goes through current vertex
                
                int newWeight = distance.get(current) + edge.getWeight();
                //If this distance is minimum then update the distance
            	if(minHeap.getWeight(adjacentVertex) > newWeight){
            		minHeap.decrease(adjacentVertex, newWeight);
            		parent.put(adjacentVertex, current);
            	}
            }
        }
        return distance;
	 }
	 public int shortestPath(Graph<Integer> graph, Vertex<Integer> sourceVertex, Vertex<Integer> destinationVertex){
		//heap + map data structure
	        BinaryHeap<Vertex<Integer>> minHeap = new BinaryHeap<>();

	        //stores shortest distance from root to every vertex
	        Map<Vertex<Integer>,Integer> distance = new HashMap<>();

	        //stores parent of every vertex in shortest distance
	        Map<Vertex<Integer>, Vertex<Integer>> parent = new HashMap<>();
	        
	        //initialize all vertex with infinite distance from source vertex
	        for(Vertex<Integer> vertex: graph.getAllVertex()){
	        	minHeap.add(INF, vertex);
	        }
	        //update the distnace from source vertex to itself to 0
	        minHeap.decrease(sourceVertex, 0);
	        
	        //Update distance path for source vertex
	        distance.put(sourceVertex, 0);
	        
	        //source vertex parent is null
	        parent.put(sourceVertex, null);
	        
	        while(!minHeap.empty()){
	        	BinaryHeap<Vertex<Integer>>.Node<Vertex<Integer>> heapNode = minHeap.extractMinNode();
	        	Vertex<Integer> current = minHeap.extractMin();
	        	
	        	//update shortest distance of current vertex from source vertex
	            distance.put(current, heapNode.weight);
	            if(current.equals(destinationVertex)){
	            	return heapNode.weight;
	            }
	            //update the distances of the adjacent vertices
	            for(Edge<Integer> edge:current.getEdges()){
	            	Vertex<Integer> adjacentVertex = getVertexForEdge(current, edge);
	            	//if heap does not contain adjacent vertex means adjacent vertex already has shortest distance from source vertex
	                if(!minHeap.containsData(adjacentVertex)){
	                    continue;
	                }
	                
	                //add distance of current vertex to edge weight to get distance of adjacent vertex from source vertex
	                //when it goes through current vertex
	                
	                int newWeight = distance.get(current) + edge.getWeight();
	                //If this distance is minimum then update the distance
	            	if(minHeap.getWeight(adjacentVertex) > newWeight){
	            		minHeap.decrease(adjacentVertex, newWeight);
	            		parent.put(adjacentVertex, current);
	            	}
	            }
	        }
	        return -1;
	 }
	 private Vertex<Integer> getVertexForEdge(Vertex<Integer> v, Edge<Integer> e){
	        return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
	 }
	 public static void main(String[] args){
		Graph<Integer> graph = new Graph<>(false);
		graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 2);
        graph.addEdge(1, 4, 9);
        graph.addEdge(1, 5, 3);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 4, 2);
        graph.addEdge(3, 4, 3);
        DijkstraShortestpath dsp = new DijkstraShortestpath();
        Vertex<Integer> sourceVertex = graph.getVertex(1);
        Map<Vertex<Integer>,Integer> distance = dsp.shortestPath(graph, sourceVertex);
        System.out.print(distance);
	 }
}
