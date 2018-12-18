//$Id$
package GreedyAlgorithms;

import java.util.HashMap;
/**
 * Disjoint Set implementation
 * https://www.youtube.com/watch?v=ID00PMy0-vE
 * O(m alpha(n))
 * m - no of operations
 * n - no of elements
 * @author gokul-4406
 *
 */
public class DisjointSet {
	class Node{
		long data;
		Node parent;
		int rank;
	}
	private HashMap<Long, Node> nodeMap = new HashMap<Long, DisjointSet.Node>();
	
	public void makeSet(long data){
		Node node = new Node();
		node.data =  data;
		node.rank = 0;
		node.parent = node;
		nodeMap.put(data, node);
	}
	public void union(long data1, long data2){
		Node node1 = nodeMap.get(data1);
		Node node2 = nodeMap.get(data2);
		
		Node parent1 = findSet(node1);
		Node parent2 = findSet(node2);
		
		if(parent1.data == parent2.data) return;     // do nothing
		if(parent1.rank >= parent2.rank){
			parent1.rank = parent1.rank == parent2.rank ? parent1.rank + 1 : parent1.rank; //increment rank only if both sets have same rank
			parent2.parent = parent1;
		}else{
			parent1.parent = parent2;
		}
			
	}
	private Node findSet(Node node){
		Node parent = node.parent;
		if(parent == node){
			return parent;
		}
		node.parent = findSet(node.parent);
		return node.parent;
	}
	public long findSet(long data){
		return nodeMap.get(data).data;
	}
}
