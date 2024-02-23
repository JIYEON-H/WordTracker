/**
 * Created on Dec 10, 2023
 * 
 * Project: Assignment03-Binary Search Trees and Serialization
 */
package treepackage;

import java.io.Serializable;

/**
 * BSTreeNode.java
 * 
 * @author Jiyeon Heo, Tze-chi Chan, and Parinthorn Songsana
 * @version 1.0
 * 
 * Class Description: This class represents a node in BSTree.
 * @param <E> The type of data stored in the node.
 */
public class BSTreeNode<E> implements Serializable{

	/**
	 * Serializable version ID to ensure compatibility during object serialization.
	 */
	private static final long serialVersionUID = -8400804895736602625L;
	
	//attributes
	private E data;
	private BSTreeNode<E> leftChild, rightChild;
	
	/**
	 * Creates a new BSTreeNode with no data.
	 */
	public BSTreeNode() {
		this(null);
	}
	
	/**
	 * Creates a new BSTreeNode with the specified data.
	 * 
	 * @param dataPortion The data to be stored in the node.
	 */
	public BSTreeNode(E dataPortion) {
		this(dataPortion, null, null);
	}
	
	/**
	 * Creates a new BSTreeNode with the specified data, left child, and right child.
	 * 
	 * @param dataPortion dataPortion The data to be stored in the node.
	 * @param newLeftChild The left child of the node.
	 * @param newRightChild The right child of the node.
	 */
	public BSTreeNode(E dataPortion, BSTreeNode<E> newLeftChild, BSTreeNode<E> newRightChild) {
		this.data = dataPortion;
		this.leftChild = newLeftChild;
		this.rightChild = newRightChild;
	}

	/**
	 * Gets the data stored in the node.
	 * 
	 * @return The data stored in the node.
	 */
	public E getData() {
		return data;
	}

	/**
	 * Sets the data stored in the node.
	 * 
	 * @param newData The new data to be stored in the node.
	 */
	public void setData(E newData) {
		this.data = newData;
	}

	/**
	 * Gets the left child of the node.
	 * 
	 * @return The left child of the node.
	 */
	public BSTreeNode<E> getLeftChild() {
		return leftChild;
	}

	/**
	 * Sets the left child of the node.
	 * 
	 * @param newLeftChild The new left child of the node.
	 */
	public void setLeftChild(BSTreeNode<E> newLeftChild) {
		this.leftChild = newLeftChild;
	}

	/**
     * Gets the right child of the node.
     *
     * @return The right child of the node.
     */
	public BSTreeNode<E> getRightChild() {
		return rightChild;
	}

	/**
	 * Sets the right child of the node.
	 * 
	 * @param newRightChild The new right child of the node.
	 */
	public void setRightChild(BSTreeNode<E> newRightChild) {
		this.rightChild = newRightChild;
	}

	/**
	 * Checks if the node has a left child.
	 * 
	 * @return True if the node has a left child, false otherwise.
	 */
	public boolean hasLeftChild() {
		return leftChild != null;
	}

	/**
     * Checks if the node has a right child.
     *
     * @return True if the node has a right child, false otherwise.
     */
	public boolean hasRightChild() {
		return rightChild != null;
	}

	/**
     * Checks if the node is a leaf node (has no children).
     *
     * @return True if the node is a leaf, false otherwise.
     */
	public boolean isLeaf() {
		return (leftChild == null) && (rightChild == null);
	}


	 /**
     * Gets the number of nodes in the subtree rooted at this node.
     *
     * @return The number of nodes in the subtree.
     */
	public int getNumberOfNodes() {
		int leftNumber = 0;
		int rightNumber = 0;
		
		if(leftChild != null) { leftNumber = leftChild.getNumberOfNodes(); }
		
		if(rightChild != null) { rightNumber = rightChild.getNumberOfNodes(); }
		
		return 1 + leftNumber + rightNumber;
	}

	/**
     * Gets the height of the subtree rooted at this node.
     *
     * @return The height of the subtree.
     */
	public int getHeight() {
		return getHeight(this);
	}
	
	 /**
     * Gets the height of the specified node.
     *
     * @param node The node to calculate the height for.
     * @return The height of the node.
     */
	private int getHeight(BSTreeNode<E> node) {
		int height = 0;
		
		if(node != null) {
			height = 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
		}
		return height;
	}
	
	/**
     * Creates a deep copy of the subtree rooted at this node.
     *
     * @return The root of the copied subtree.
     */
	public BSTreeNode<E> copy() {
		BSTreeNode<E> newRoot = new BSTreeNode<E>(data);
		
		if(leftChild != null) { newRoot.setLeftChild(leftChild.copy()); }
		if(rightChild != null) { newRoot.setRightChild(rightChild.copy());}
		
		return newRoot;
	}
	


}
