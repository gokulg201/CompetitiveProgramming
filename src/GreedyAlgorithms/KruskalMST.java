//$Id$
package GreedyAlgorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import GreedyAlgorithms.Graph.Edge;
import GreedyAlgorithms.Graph.Vertex;

/**
 * Disjoint Set implementation of Minimum Spanning Tree
 * 
 * https://www.youtube.com/watch?v=fAuF0EuZVCk&t=1s
 * Space Complexity O(E + V)
 * Time Complexity O(ElogE + E)
 * @author gokul-4406
 *
 */
public class KruskalMST {
	public class EdgeComparator implements Comparator<Edge<Integer>> {
        @Override
        public int compare(Edge<Integer> edge1, Edge<Integer> edge2) {
            if (edge1.getWeight() <= edge2.getWeight()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
	public List<Edge<Integer>> getMST(Graph<Integer> graph){
		List<Edge<Integer>> mst = new ArrayList<Graph.Edge<Integer>>();
		List<Edge<Integer>> edges = graph.getAllEdges();
		Collections.sort(edges, new EdgeComparator());
		
		DisjointSet set = new DisjointSet();
		Collection<Vertex<Integer>> vertices  = graph.getAllVertex();
		for(Vertex<Integer> v : vertices){
			set.makeSet(v.getId());
		}
		for(Edge<Integer> e : edges){
			long root1 = set.findSet(e.getVertex1().getId());
			long root2 = set.findSet(e.getVertex2().getId());
			if(root1 == root2){
				continue;
			}
			set.union(root1, root2);
			mst.add(e);
		}
		return mst;
	}
	//@Test
	public static void main(String args[]) {
        Graph<Integer> graph = new Graph<Integer>(false);
        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 5, 1);
        graph.addEdge(2, 6, 3);
        graph.addEdge(2, 4, 2);
        graph.addEdge(6, 5, 2);
        graph.addEdge(6, 4, 3);
        graph.addEdge(4, 7, 2);
        graph.addEdge(3, 4, 5);
        graph.addEdge(3, 7, 8);
        KruskalMST mst = new KruskalMST();
        List<Edge<Integer>> result = mst.getMST(graph);
        for (Edge<Integer> edge : result) {
            System.out.println(edge.getVertex1() + " " + edge.getVertex2());
        }
    }
}
