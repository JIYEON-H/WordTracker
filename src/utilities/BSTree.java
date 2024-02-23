package utilities;

import java.util.NoSuchElementException;
import java.util.Stack;

import exceptions.TreeException;
import treepackage.BSTreeNode;

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E>{

	/**
	 * Serializable version ID to ensure compatibility during object serialization.
	 */
	private static final long serialVersionUID = -5865709675229116862L;

	private BSTreeNode<E> root;
	
	public BSTree() {
		root = null;
	}
	
	public BSTree(E rootData) {
		root = new BSTreeNode<>(rootData);
	}
	
	
	@Override
	public BSTreeNode<E> getRoot() throws TreeException {
		if(isEmpty()) { throw new TreeException(); }
		
		return root;
	}

	@Override
	public int getHeight() {
		return root.getHeight();
	}

	@Override
	public int size() {
		if(root == null) {
			return 0;
		}else {
			return root.getNumberOfNodes();
		}
		
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public boolean contains(E entry) throws TreeException {
		if(isEmpty()) { throw new TreeException(); }
		
		return containsRecursive(root, entry);
	}
	
	private boolean containsRecursive(BSTreeNode<E> node, E entry) {
		if(node == null) { return false; }
		
		int compareResult = entry.compareTo(node.getData());
		
		if(compareResult == 0) {
			return true;
		}else if(compareResult < 0) {
			return containsRecursive(node.getLeftChild(), entry);
		}else {
			return containsRecursive(node.getRightChild(), entry);
		}
		
	}

	@Override
	public BSTreeNode<E> search(E entry) throws TreeException {
		if(isEmpty()) { throw new TreeException(); }
		
		return searchRecursive(root, entry);
	}
	
	private BSTreeNode<E> searchRecursive(BSTreeNode<E> node, E entry){
		if(node == null) { return null; }
		
		int compareResult = entry.compareTo(node.getData());
		
		if(compareResult == 0) {
			return node;
		}else if(compareResult < 0) {
			return searchRecursive(node.getLeftChild(), entry);
		}else {
			return searchRecursive(node.getRightChild(), entry);
		}
	}

	@Override
	public boolean add(E newEntry) throws NullPointerException {
		if(newEntry == null) { throw new NullPointerException();}
		
		root = addRecursive(root, newEntry);
		return true;
	}
	
	private BSTreeNode<E> addRecursive(BSTreeNode<E> node, E newEntry){
		if(node ==  null) { return new BSTreeNode<>(newEntry);}
		
		int compareResult = newEntry.compareTo(node.getData());
		if(compareResult < 0) {
			node.setLeftChild(addRecursive(node.getLeftChild(),newEntry));
		}else if(compareResult > 0) {
			node.setRightChild(addRecursive(node.getRightChild(),newEntry));
		}
		
		return node;
	}

	@Override
	public Iterator<E> inorderIterator() {
		return new InOrderIterator();
	}
	
	@Override
	public Iterator<E> preorderIterator() {
		return new PreOrderInterator();
	}

	@Override
	public Iterator<E> postorderIterator() {
		
		return new PostOrderIterator();
	}
	

	
	@SuppressWarnings("unused")
	private class InOrderIterator implements Iterator<E>{

		private Stack<BSTreeNode<E>> stack;
		
		public InOrderIterator() {
			stack = new Stack<>();
			pushLeftBranch(root);
		}
		
		
		public void pushLeftBranch(BSTreeNode<E> node) {
			while(node != null) {
				stack.push(node);
				node = node.getLeftChild();
			}
		}
		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext()) {throw new NoSuchElementException();}
			
			BSTreeNode<E> current = stack.pop();
			pushLeftBranch(current.getRightChild());
			
			return current.getData();
		}
	}
	
	
	@SuppressWarnings("unused")
	private class PreOrderInterator implements Iterator<E>{
		
		private Stack<BSTreeNode<E>> stack;
		
		public PreOrderInterator() {
			stack = new Stack<>();
			if (root != null) {
				stack.push(root);
			}
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext()) {throw new NoSuchElementException();}
			
			BSTreeNode<E> current = stack.pop();
			if(current.getRightChild() != null) {	
				stack.push(current.getRightChild());
			}
			if(current.getLeftChild() != null) {
				stack.push(current.getLeftChild());
			}
			
			return current.getData();
		}
	}
	
	@SuppressWarnings("unused")
	private class PostOrderIterator implements Iterator<E>{

		private Stack<BSTreeNode<E>> stack;
		private BSTreeNode<E> lastVisited;
		
		public PostOrderIterator() {
			stack = new Stack<>();
			lastVisited = null;
			pushPostOrder(root);
		}
		
		private void pushPostOrder(BSTreeNode<E> node) {
			while(node != null) {
				stack.push(node);
				node = node.getLeftChild();
			}
		}
		
		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext()) {throw new NoSuchElementException();}
			
			BSTreeNode<E> current = stack.pop();
			
			while(current.getRightChild() != null && current.getRightChild() != lastVisited) {
				stack.push(current);
				pushPostOrder(current.getRightChild());
				current = stack.pop();
			}
			
			lastVisited = current;
			return current.getData();
		}
		
	}
}
