//$Id$
package crackingTheCodingInterview;

public class CheckBST {
	public boolean checkBST(Node<Integer> root){
		if (root == null) return true;
		if(root.left != null){
			System.out.println(root.data.compareTo(root.left.data));
			if (root.data.compareTo(root.left.data) <=0){
				return false;
			}
		}
		if(root.right != null){
			System.out.println(root.data.compareTo(root.right.data));
			if (root.data.compareTo(root.right.data)>=0){
				return false;
			}
		}
		if(!(checkBST(root.left) && checkBST(root.right))){
			return false;
		}
		return true;
	}
	public static void main(String[] args){
			Node<Integer> tree = new Node<Integer>(6);
	        tree.left = new Node<Integer>(4);
	        tree.right = new Node<Integer>(8);
	        tree.left.left = new Node<Integer>(1);
	        tree.left.right = new Node<Integer>(3);
	        tree.right.right = new Node<Integer>(5);
	        tree.right.left = new Node<Integer>(9);
	        CheckBST check= new CheckBST();
	        System.out.println(check.checkBST(tree));
	}
}
class Node<T extends Comparable<T>>{
	Node<T> left;
	Node<T> right;
	T data;
	
	public Node(T data){
		this.left=null;
		this.right=null;
		this.data=data;
	}
	public void insert(T value){
		if(value.compareTo(this.data) <=0){
			if(left == null){
				left=new Node<T>(value);
			}else {
				left.insert(value);
			}
		}else{
			if(right == null){
				right = new Node<T>(value);
			}else{
				right.insert(value);
			}
		}
	}
	public boolean contains(T value){
		if(value.compareTo(data) == 0) return true;
		else if (value.compareTo(data) < 0){
			return left == null ? false :left.contains(value);
		}else if (value.compareTo(data) > 0){
			return right == null ? true : right.contains(value);
		}else{
			return false;
		}
	}
	public void inOrder(){
		if(left != null){
			left.inOrder();
		}
		System.out.println(this.data);
		if(right != null){
			right.inOrder();
		}
	}
	public void preOrder(){
		System.out.println(this.data);
		if(left != null){
			left.inOrder();
		}
		if(right != null){
			right.inOrder();
		}
	}
	public void postOrder(){
		if(left != null){
			left.inOrder();
		}
		if(right != null){
			right.inOrder();
		}
		System.out.println(this.data);
	}
}
