//$Id$
package hackerRank.dataStructures.trees;

public class AVLTree {
	static class Node{
		Node left;
		Node right;
		int val;
		int ht;
		Node(){
			
		}
	}
	static int height(Node n){
		if(n == null) return -1;
		return n.ht;
	}
	static int max(int a, int b) {
        return Math.max(a, b);
    }
	static Node rotateRight(Node x){
		Node y = x.left;
		Node T2 = y.right;
		x.left = T2;
		y.right = x;
		x.ht = max(height(x.left) ,height(x.right)) + 1;
		y.ht = max(height(y.left) , height(y.right))+ 1;
		return y;
	}
	static Node rotateLeft(Node x){
		Node y = x.right;
		Node T2 = y.left;
		x.right = T2;
		y.left = x;
		x.ht = max(height(x.left), height(x.right)) + 1;
	    y.ht = max(height(y.left), height(y.right)) + 1;
	    return y;
	}
	static int getBalance(Node n){
		if(n == null) return 0;
		return height(n.left) - height(n.right);
	}
	static Node insert(Node root,int val) {
	    Node newNode = new Node(); 
	    newNode.val = val;
	    newNode.ht = 0;
	    newNode.left = null;
	    newNode.right = null;
	    
	    if (root==null) {
	        root = newNode;
	    } else {
	        root = avlinsert(root, val);
	    }
	    
	    return root;
	} 
	static Node avlinsert(Node root, int value){
		if(root == null){
			Node node = new Node();
			node.val = value;
			node.ht = 0;
			return node;
		}
		if(value < root.val) root.left = insert(root.left, value);
		else if(value > root.val) root.right = insert(root.right, value);
		else return root;
		root.ht = 1 + max(height(root.right), height(root.left));
		
		int balance = getBalance(root);
		
		if(balance > 1){
			if(value < root.left.val){
				return rotateRight(root);
			}
			if(value > root.left.val){
				root.left = rotateLeft(root.left);
				return rotateRight(root);
			}
		}else if(balance < -1){
			if(value > root.right.val){
				return rotateLeft(root);
			}
			if(value < root.right.val){
				root.right = rotateRight(root.right);
				return rotateLeft(root);
			}
		}
		return root;
	}
	void preOrder(Node node) {
        if (node != null) {
            preOrder(node.left);
            System.out.println(node.val + "(BF="+getBalance(node)+")");
            preOrder(node.right);
        }
    }
	public static void main(String[] args){
		AVLTree tree = new AVLTree();
		/*
		tree.root = tree.insert(14);
		tree.root = tree.insert(25);
		tree.root = tree.insert(21);
		tree.root = tree.insert(10);
		tree.root = tree.insert(23);
		tree.root = tree.insert(7);
		tree.root = tree.insert(26);
		tree.root = tree.insert(12);
		tree.root = tree.insert(30);
		tree.root = tree.insert(16);
//		tree.preOrder(tree.root);
		tree.root = tree.insert(19);
		tree.preOrder(tree.root);
		*/
		Node root = null; 
		root = insert(root,3);
		root = insert(root,2);
		root = insert(root,4);
		root = insert(root,5);
		root = insert(root,6);
		tree.preOrder(root);
	}
}
