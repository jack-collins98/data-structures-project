import java.util.*;
import java.lang.Exception;
/**
* @author Jack Collins
* @version November 19th, 2018
* Class contains: Remove methods, Insert methods and contains methods.
*/

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{ 

/***********************************
*
*		Constructors 
*
************************************/
	public BinarySearchTree(){ super(); } // calls superclass constructor

	public BinarySearchTree(T[] seq){ super(seq); }

	public BinarySearchTree(T[] seq, T nullSymbol){ super(seq, nullSymbol);}
/***********************************
*
*		makeEmpty/isEmpty Methods
*
************************************/
	public void makeEmpty(){ root = null; }

	public boolean isEmpty(){ return root == null; }
/***********************************
*
*		contains Methods
*
************************************/
	public boolean contains(T value){ return contains(value, this.root); }

	/**
	* Helper Method to see if a node contains data
	* @param value The value we are trying to find.
	* @param t This nodes data is being compare to Value using the compareTo method. 
	*/
	private boolean contains(T value, BinaryNode<T> t){
		if(t == null){ return false; } 
	else{ 
			if(value.compareTo(t.getData()) < 0){ 	//if value is less than the data of t 
			return this.contains(value, t.getLeftNode()); 
		}
		else if(value.compareTo(t.getData()) > 0){ 	//if value is greater than the data of t
			return this.contains(value, t.getRightNode());
		}
		else {
			return true; 
		}
	}	
}
/***********************************
*
*		findMin Methods
*
************************************/
public T findMin(){
	if (isEmpty()) throw new RuntimeException();
		return findMin(root).getData();
} // End findMin method

	/**
	* Helper method for findMin.
	* @param t the left node of the tree
	* @return left node
	*/
	private BinaryNode<T> findMin(BinaryNode<T> t){
		if(t == null){
			return null;
		}
		else if (t.getLeftNode() == null){ //if left node is null
			return t;
		}
		return findMin(t.getLeftNode());
	} // End findMin Method
/***********************************
*
*		findMax Methods
*
************************************/
public T findMax(){
	if (isEmpty()) throw new RuntimeException();
		return findMax(root).getData();
}


/**
* Helper method for findMax
* @param t 	The right node of the tree.
* @return the right node of the tree.
*/
private BinaryNode<T> findMax(BinaryNode<T> t){
		if(t != null){
			while(t.getRightNode() != null){
				t = t.getRightNode();
			}
		}
		return t;
	}
/***********************************
*
*		Insert Method
*
************************************/
	public void insert(T value){ 
		if (this.root == null){ this.root = new BinaryNode<T>(value); }
		else{ this.insert(value, this.root); } 
	}

	/**
	* Helper method for Insert.
	* @param value the value we are inserting.
	* @param t the node that we are comparing to value.
	*/
	private void insert(T value, BinaryNode<T> t){
		if(value.compareTo(t.getData()) < 0){	// if the new value we are comparing is less than t
			if(t.getLeftNode() == null){		// if the left node is empty
				t.setLeftNode(new BinaryNode<T>(value)); //create new node in the left child.
			}
			else {
				insert(value, t.getLeftNode());	//recursive call to insert new node into a null space.
			}
		}
		else if(value.compareTo(t.getData()) > 0){	// if the new value we are comparing is greater than t
			if(t.getRightNode() == null){		// if the right child is empty
				t.setRightNode(new BinaryNode<T>(value)); // create new node and insert it into right child.
			}
			else{
				insert(value, t.getRightNode());	//recursive call to insert new node into a null space.
			}
		}
	} // end insert method
/***********************************
*
*		Remove Methods
*
************************************/
	public void remove(T value){ this.remove(value, this.root, null); } // end remove method

	
/**
* Helper method for remove
* @param value 	the value we are removing from the tree
* @param t 		the node that we are comparing to value
* @param p 		the parent node of t
*/
	private void remove(T value, BinaryNode<T> t, BinaryNode<T> p){
		
		if(t == null){ return; } //if there is no value to remove, do nothing and return.
		else {
		if(value.compareTo(t.getData()) < 0){	//if the value being removed is less than t
			this.remove(value,t.getLeftNode(), t); //check the left child
			}
		else if(value.compareTo(t.getData()) > 0){ 	//if the value being removed is less than t
			this.remove(value, t.getRightNode(), t); //check the right child
		}
		else{
			removeHelper(t,p); // call removeHelper method.
			}
		} 
	}// end remove method


/**
* A helper method to the remove helper method that handles which case of remove we 
* are dealing with.  
* @param n 		the node that is being removed.
* @param p 		the parent node of the node that is being removed.
*/
	private void removeHelper(BinaryNode<T> n, BinaryNode<T> p){
		if(n == this.root){ 	// the node we are removing is this.root.
			removeRoot();
		}
		else if ((n.getLeftNode() != null) && (n.getRightNode()!= null)){ // if a child node has children
			removeChildren(n);
		}
		else{
			removeChild(n,p); // if a child node that we are removing has a child or no child.
		}
	} // end method

/**
* Helper method for removeHelper. Handles the case that if a a child that we are removing
* has two children.
* @param n 	the node being removed.
*/
	private void removeChildren(BinaryNode<T> n){
		BinaryNode<T>  leftChild = findMax(n.getLeftNode());
		BinaryNode<T>  leftChildParent = getParent(leftChild);

		n.setData(leftChild.getData());
		remove(leftChild.getData(), leftChild, leftChildParent);
	}// end method

/**
* Method that removes the root of the enitre tree.
*/
	private void removeRoot(){
		if ((this.root.getLeftNode() != null) && (this.root.getRightNode() != null)) { // if the root has children
			removeChildren(this.root); 
		}
		else if (this.root.getLeftNode() != null) { // if the root has a left child
			this.root = this.root.getLeftNode(); 
		}
		else if (this.root.getRightNode() != null) { // if the root has a right child
			this.root = this.root.getRightNode(); 
		}
		else {
			this.root = null; //if the has no left or right child.
		} 
	}//end method


/**
* Method that removes a child node
* @param n 		the node that is being removed.
* @param p  	the parent of the node that is being removed.
*
*/
	private void removeChild(BinaryNode<T> n, BinaryNode<T> p) {
		if (n == p.getLeftNode()) { 	// if n is the left child of p
			if (n.getLeftNode() != null) { 	//if n had a left child to move.
				p.setLeftNode(n.getLeftNode()); 
			}
			else { 
				p.setLeftNode(n.getRightNode()); // n had a right child to move.
			} 
		}
		else if (n == p.getRightNode()) { // if n is the right child of p
			if (n.getLeftNode() != null) {	 // if n had a left child to move.				
				p.setRightNode(n.getLeftNode()); 
			}
			else {
				p.setRightNode(n.getRightNode()); // if n had a right child to move.
			} 
		}
		else {
			if (n == p.getLeftNode()) { //if node is a leaf set reference to null.
				p.setLeftNode(null); 
			}
			else {
				p.setRightNode(null); 
			}
		}
	} // end method removeChild
	
/***********************************
*
*		getParentNode Method
*
************************************/
/**
* Method that get the parent of a node.
* @param n the node that is a child of the parent we are findnig.
* @return the parent of the node.
*/	
	private BinaryNode<T> getParent(BinaryNode<T> n) {
		BinaryNode<T> currentNode = this.root; 
		BinaryNode<T> parentNode = this.root; 
		boolean nodeFound = false; 
		
		if (n != this.root) { //if the root node has no parent, check the other non root nodes.
			while (!nodeFound) {
				if (n.getData().compareTo(currentNode.getData()) < 0) { // if the node is in the left subtree
					parentNode = currentNode; 
					currentNode = currentNode.getLeftNode(); 
				}
				else if (n.getData().compareTo(currentNode.getData()) > 0) { //if the node is in the right subtree
					parentNode = currentNode; 
					currentNode = currentNode.getRightNode(); 
				}
				else {
					nodeFound = true;
				}
			}  
		} 
		return parentNode; 
	} // end getParent method
} // end class