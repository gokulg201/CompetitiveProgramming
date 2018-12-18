//$Id$
package GreedyAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://www.youtube.com/watch?annotation_id=annotation_2876488053&feature=iv&src_vid=oP2-8ysT3QQ&v=oP2-8ysT3QQ#t=5m58s
 * BinaryHeap + Map Data Structure
 * add O(logN)
 * extractMin O(logN)
 * contains O(1)
 * decrease O(logN)
 * @author gokul-4406
 * @param <T>
 */
public class BinaryHeap<T> {
	class Node<T>{
		int weight;
		T key;
	}
	List<Node<T>> allNodes = new ArrayList<BinaryHeap<T>.Node<T>>();
	HashMap<T,Integer> nodePosition = new HashMap<T, Integer>();
	
	public boolean containsData(T data){
		return nodePosition.containsKey(data);
	}
	public void add(int weight,T key){
		Node<T> node = new Node<T>();
		node.key = key;
		node.weight = weight;
		allNodes.add(node);
		int size = allNodes.size();
		int current = size - 1;
		int parentIndex = (current - 1) / 2;
		nodePosition.put(node.key, current);
		while(parentIndex >= 0 ){
			Node<T> parentNode = allNodes.get(parentIndex);
            Node<T> currentNode = allNodes.get(current);
            if (parentNode.weight > currentNode.weight) {
            	swap(parentNode, currentNode);
            	updateNodePosition(currentNode.key, parentNode.key, current,parentIndex);
            	current = parentIndex;
                parentIndex = (current - 1) / 2;
            }else{
            	break;
            }
		}
	}
	private void swap(Node<T> node1, Node<T> node2){
		int weight = node1.weight;
        T data = node1.key;
        
        node1.key = node2.key;
        node1.weight = node2.weight;
        
        node2.key = data;
        node2.weight = weight;
	}
	private void updateNodePosition(T data1, T data2, int pos1, int pos2){
		nodePosition.remove(data1);
        nodePosition.remove(data2);
        nodePosition.put(data1, pos1);
        nodePosition.put(data2, pos2);
	}
	/**
     * Get the heap min without extracting the key
     */
    public T min(){
        return allNodes.get(0).key;
    }

    /**
     * Checks with heap is empty or not
     */
    public boolean empty(){
        return allNodes.size() == 0;
    }

    /**
     * Decreases the weight of given key to newWeight
     */
    public void decrease(T data, int newWeight){
        Integer position = nodePosition.get(data);
        allNodes.get(position).weight = newWeight;
        int parent = (position -1 )/2;
        while(parent >= 0){
            if(allNodes.get(parent).weight > allNodes.get(position).weight){
                swap(allNodes.get(parent), allNodes.get(position));
                updateNodePosition(allNodes.get(parent).key,allNodes.get(position).key,parent,position);
                position = parent;
                parent = (parent-1)/2;
            }else{
                break;
            }
        }
    }
    /**
     * Get the weight of given key
     */
    public Integer getWeight(T key) {
        Integer position = nodePosition.get(key);
        if( position == null ) {
            return null;
        } else {
            return allNodes.get(position).weight;
        }
    }

    /**
     * Returns the min node of the heap
     */
    public Node extractMinNode() {
        int size = allNodes.size() -1;
        Node minNode = new Node();
        minNode.key = allNodes.get(0).key;
        minNode.weight = allNodes.get(0).weight;

        int lastNodeWeight = allNodes.get(size).weight;
        allNodes.get(0).weight = lastNodeWeight;
        allNodes.get(0).key = allNodes.get(size).key;
        nodePosition.remove(minNode.key);
        nodePosition.remove(allNodes.get(0));
        nodePosition.put(allNodes.get(0).key, 0);
        allNodes.remove(size);

        int currentIndex = 0;
        size--;
        while(true){
            int left = 2*currentIndex + 1;
            int right = 2*currentIndex + 2;
            if(left > size){
                break;
            }
            if(right > size){
                right = left;
            }
            int smallerIndex = allNodes.get(left).weight <= allNodes.get(right).weight ? left : right;
            if(allNodes.get(currentIndex).weight > allNodes.get(smallerIndex).weight){
                swap(allNodes.get(currentIndex), allNodes.get(smallerIndex));
                updateNodePosition(allNodes.get(currentIndex).key,allNodes.get(smallerIndex).key,currentIndex,smallerIndex);
                currentIndex = smallerIndex;
            }else{
                break;
            }
        }
        return minNode;
    }
    /**
     * Extract min value key from the heap
     */
    public T extractMin(){
    	Node<T> node = extractMinNode();
        return node.key;
    }

    private void printPositionMap(){
        System.out.println(nodePosition);
    }
    public void printHeap(){
        for(Node n : allNodes){
            System.out.println(n.weight + " " + n.key);
        }
    }
    public static void main(String args[]){
        BinaryHeap<String> heap = new BinaryHeap<String>();
        heap.add(3, "Tushar");
        heap.add(4, "Ani");
        heap.add(8, "Vijay");
        heap.add(10, "Pramila");
        heap.add(5, "Roy");
        heap.add(6, "NTF");
        heap.add(2,"AFR");
        heap.decrease("Pramila", 1);
        heap.printHeap();
        heap.printPositionMap();
    }
}
