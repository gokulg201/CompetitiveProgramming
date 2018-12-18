//$Id$
package hackerRank.dataStructures.trees;

import java.util.Random;
import java.util.Scanner;



/**
 * Max Heap implementation
 * https://www.hackerrank.com/challenges/array-and-simple-queries/problem
 * @author gokul-4406
 *
 */
public class Treap {
	static Random rand = new Random();
	private static class Node{
		int key,priority,pos;
		Node left,right;
		Node(int key){
			this.key = key;
			this.priority = rand.nextInt();
			this.left = null;
			this.right = null;
			this.pos = 1;
		}
		public String toString() {
        	return String.valueOf(key);
        }
	}
	private static class DNode {
        Node left;
        Node right;
        
        DNode() {
            left = null;
            right = null;
        }
        
        public String toString() {
            return "L:" + left + " R:" + right;
        }
    }
	private Node root;
	public void insert(int key){
		 Node n = new Node(key);
	     root = merge(root, n);
	}
	public int pos(Node node){
		if(node == null) return 0;
		return node.pos;
	}
	public void updatePos(Node node){
		if(node != null)
			node.pos = 1 + pos(node.left) + pos(node.right);
	}
	public DNode split(Node node,int key){
		DNode pair = new DNode();
		if(node == null) {
			return pair;
		}
		if(pos(node.left) >= key){
			pair = split(node.left, key);
			node.left = pair.right;
			updatePos(node);
			pair.right = node;
		}else{
			pair = split(node.right, key - pos(node.left) - 1);
			node.right = pair.left;
			updatePos(node);
			pair.left = node;
		}
		return pair;
	}
	public Node merge(Node leftTreap, Node rightTreap){
		if(leftTreap == null) return rightTreap;
		if(rightTreap == null) return leftTreap;
		if(leftTreap.priority > rightTreap.priority){
			leftTreap.right = merge(leftTreap.right, rightTreap);
			updatePos(leftTreap);
			return leftTreap;
		}else{
			rightTreap.left = merge(leftTreap, rightTreap.left);
			updatePos(rightTreap);
			return rightTreap;
		}
	}
	public void query1(int left, int right) {
    	DNode pair1 = split(root, right);
    	DNode pair2 = split(pair1.left, left-1);
    	
    	root = merge(pair2.left, pair1.right);
    	root = merge(pair2.right, root);
    }
	    
    public void query2(int left, int right) {
    	DNode pair1 = split(root, right);
    	DNode pair2 = split(pair1.left, left-1);
    	
    	root = merge(pair2.left, pair1.right);
    	root = merge(root, pair2.right);
    }
	    
    public void inorder() {
        inorder(root);
        System.out.println();
    }
    
    private void inorder(Node n) {
        if (n == null)
            return;
        inorder(n.left);
        System.out.print(n + " ");
        inorder(n.right);
    }
    public Node getFirst() {
    	if (root == null)
    		return null;
    	return getFirst(root);
    }
    
    private Node getFirst(Node root) {
    	if (root.left != null)
    		return getFirst(root.left);
    	return root;
    }
    
    public Node getLast() {
    	if (root == null)
    		return null;
    	return getLast(root);
    }
    
    private Node getLast(Node root) {
    	if (root.right != null)
    		return getLast(root.right);
    	return root;
    }
	public static void main(String[] args){
		Treap t = new Treap();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0;  i < n; i++) {
            int v = sc.nextInt();
            t.insert(v);
        }
        for (int i = 0; i < m; i++) {
        	int op = sc.nextInt();
            int low = sc.nextInt();
            int high = sc.nextInt();
            if (op == 1)
            	t.query1(low, high);
            else
            	t.query2(low, high);
        }
        System.out.println(Math.abs(t.getFirst().key - t.getLast().key));
        t.inorder();
        sc.close();
	}
}
