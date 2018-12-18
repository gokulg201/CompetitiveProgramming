//$Id$
package GreedyAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GreedyAlgorithms.Graph.Edge;
import GreedyAlgorithms.Graph.Vertex;

/**
 * Find the minimum Spanning tree using Prim's Algorithm
 * Algo:
 * This is a greedy algorithm
 * 1) Maintain a minheap/priority queue to get the vertex with minimum edge weight at a given time 
 * 2) Select the edge with minimu weight and for a tree
 * Complexity:
 * Space complexity - O(E + V)
 * Time complexity - O(ElogV)
 * 
 * Reference:
 * https://www.youtube.com/watch?annotation_id=annotation_2876488053&feature=iv&src_vid=oP2-8ysT3QQ&v=oP2-8ysT3QQ#t=5m58s
 * https://www.geeksforgeeks.org/?p=27455
 * https://www.geeksforgeeks.org/prims-mst-for-adjacency-list-representation-greedy-algo-6/
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/PrimMST.java
 * @author gokul-4406
 *
 */
public class PrimMST {
	public List<Edge<Integer>> getMST(Graph<Integer> graph){
		//heap + map containing edge wieghts
		BinaryHeap<Vertex<Integer>> minHeap = new BinaryHeap<Graph.Vertex<Integer>>();
		//A vertex edge map that will contain the edge with minimum weight for that vertex
		Map<Vertex<Integer>, Edge<Integer>> vertexToEdge = new HashMap<Graph.Vertex<Integer>, Graph.Edge<Integer>>();
		//Final Spanning tree
		List<Edge<Integer>> spanningTree = new ArrayList<Graph.Edge<Integer>>();
		
		//Initialising the minHeap distances to Infinity
		for(Vertex<Integer> vertex : graph.getAllVertex()){
			minHeap.add(Integer.MAX_VALUE, vertex);
		}
		//start from any random vertex
        Vertex<Integer> startVertex = graph.getAllVertex().iterator().next();

		minHeap.decrease(startVertex, 0);
		
		while(!minHeap.empty()){
			BinaryHeap<Vertex<Integer>>.Node<Vertex<Integer>> heapNode = minHeap.extractMinNode();
			Vertex<Integer> current = heapNode.key;
			//Getting the edge with minimum weight for this vertex
			Edge<Integer> stEdge = vertexToEdge.get(current);
			//Adding this edge to the minimum spanning tree
			if(stEdge != null){//This will be null for Start vertex
				spanningTree.add(stEdge);
			}
			if(!minHeap.containsData(current)){
				continue;
			}
			//iterate through all the adjacent vertices
			for(Edge<Integer> edge : current.getEdges()){
				Vertex<Integer> adjacent = getVertexForEdge(current, edge);
				//check if adjacent vertex exist in heap + map and weight attached with this vertex is greater than this edge weight
				if(minHeap.getWeight(adjacent) > edge.getWeight()){
					//decrease the value of adjacent vertex to this edge weight.
					minHeap.decrease(adjacent, edge.getWeight());
					//add vertex->edge mapping in the graph.
					vertexToEdge.put(adjacent, edge);
				}
			}
		}
		return spanningTree;
	}
	private Vertex<Integer> getVertexForEdge(Vertex<Integer> v, Edge<Integer> e){
        return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
    }
}
