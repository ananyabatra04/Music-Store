/**
 * BST.java
 * 
 * @author Mehul Jaiswal 
 * CIS 22C, course project Team 1
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

@SuppressWarnings("hiding")
public class BST<Product> {

	private class Node {
		private Product data;
		private Node left;
		private Node right;

		public Node(Product data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	private Node root;
	private ArrayList<Product> list;

	/*** CONSTRUCTORS ***/

	/**
	 * Default constructor for BST sets root to null
	 */
	public BST() {
		root = null;
		list = new ArrayList<Product>();
	}

	/**
	 * Copy constructor for BSProduct
	 * 
	 * @param bst the BSProduct to make a copy of
	 */
	public BST(BST<Product> bst) {
		if (bst.root == null) {
			root = null;
			list = new ArrayList<Product>();
		} else {
			copyHelper(bst.root);
			this.list = bst.list;
		}
	}

	/**
	 * Helper method for copy constructor
	 * 
	 * @param node the node containing data to copy
	 */
	private void copyHelper(Node node) {
		if (node != null) {
			// insert(node.data, new CompareByName());
			copyHelper(node.left);
			copyHelper(node.right);
		}
	}

	/*** ACCESSORS ***/

	/**
	 * Returns the data stored in the root
	 * 
	 * @precondition !isEmpty()
	 * @return the data stored in the root
	 * @throws NoSuchElementException when preconditon is violated
	 */
	public Product getRoot() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("getRoot(): Tree is empty");
		else
			return root.data;
	}

	/**
	 * Determines whether the tree is empty
	 * 
	 * @return whether the tree is empty
	 */
	public boolean isEmpty() {
		if (root == null)
			return true;
		else
			return false;
	}

	/**
	 * Returns the current size of the tree (number of nodes)
	 * 
	 * @return the size of the tree
	 */
	public int getSize() {
		return getSize(root);
	}

	/**
	 * Helper method for the getSize method
	 * 
	 * @param node the current node to count
	 * @return the size of the tree
	 */
	private int getSize(Node node) {
		if (node == null)
			return 0;
		else {
			return 1 + getSize(node.left) + getSize(node.right);
		}
	}

	/**
	 * Returns the height of tree by counting edges.
	 * 
	 * @return the height of the tree
	 */
	public int getHeight() {
		if (isEmpty())
			return -1;
		else {
			return getHeight(root);
		}
	}

	/**
	 * Helper method for getHeight method
	 * 
	 * @param node the current node whose height to count
	 * @return the height of the tree
	 */
	private int getHeight(Node node) {
		if (node == null)
			return -1;
		int lHeight = getHeight(node.left);
		int rHeight = getHeight(node.right);
		if (lHeight > rHeight)
			return lHeight + 1;
		else
			return rHeight + 1;
	}

	/**
	 * Returns the smallest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the smallest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public Product findMin() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("findMin(): Tree is empty");
		else
			return findMin(root);
	}

	/**
	 * Helper method to findMin method
	 * 
	 * @param node the current node to check if it is the smallest
	 * @return the smallest value in the tree
	 */
	private Product findMin(Node node) {
		if (node.left != null)
			return findMin(node.left);
		else
			return node.data;
	}

	/**
	 * Returns the largest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the largest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public Product findMax() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("findMax(): Tree is empty");
		else
			return findMax(root);
	}

	/**
	 * Helper method to findMax method
	 * 
	 * @param node the current node to check if it is the largest
	 * @return the largest value in the tree
	 */
	private Product findMax(Node node) {
		if (node.right != null)
			return findMax(node.right);
		else
			return node.data;
	}

	/**
	 * Searches for a specified value in the tree
	 * 
	 * @param input_name the value to search for
	 * @return whether the value is stored in the tree
	 */
	public Product searchByName(Product input_name, Comparator<Product> c) {
		if (isEmpty())
			return null;
		else
			return searchByName(input_name, root, c);
	}

	/**
	 * Helper method for the search method
	 * 
	 * @param data the data to search for
	 * @param node the current node to check
	 * @return whether the data is stored in the tree
	 */

	private Product searchByName(Product data, Node node, Comparator<Product> c) {
		if (c.compare((Product) data, (Product) node.data) == 0)
			return node.data;
		if (c.compare((Product) data, (Product) node.data) < 0) {
			if (node.left == null)
				return null;
			else
				return searchByName(data, node.left, c);
		} else {
			if (node.right == null)
				return null;
			else
				return searchByName(data, node.right, c);
		}
	}

	/**
	 * Searches for a specified value in the tree
	 * 
	 * @param data the value to search for
	 * @return whether the value is stored in the tree
	 */
	public Product searchByID(Product data, Comparator<Product> c) {
		if (isEmpty())
			return null;
		else
			return searchByID(data, root, c);
	}

	/**
	 * Helper method for the search method
	 * 
	 * @param data the data to search for
	 * @param node the current node to check
	 * @return whether the data is stored in the tree
	 */
	private Product searchByID(Product data, Node node, Comparator<Product> c) {
		if (c.compare((Product) data, (Product) node.data) == 0)
			return node.data;
		if (c.compare((Product) data, (Product) node.data) < 0) {
			if (node.left == null)
				return null;
			else
				return searchByID(data, node.left, c);
		} else {
			if (node.right == null)
				return null;
			else
				return searchByID(data, node.right, c);
		}
	}

	/**
	 * Determines whether two trees store identical data in the same structural
	 * position in the tree
	 * 
	 * @param o another Object
	 * @return whether the two trees are equal
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		return equals(root, ((BST<Product>) o).root);
	}

	/**
	 * Helper method for the equals method
	 * 
	 * @param node1 the node of the first bst
	 * @param node2 the node of the second bst
	 * @return whether the two trees contain identical data stored in the same
	 *         structural position inside the trees
	 */
	private boolean equals(Node node1, Node node2) {
		if (node1 == null && node2 == null)
			return true;

		if (node1 != null && node2 != null) {
			return ((node1.data == node2.data) && equals(node1.left, node2.left) && equals(node1.right, node2.right));
		}

		return false;
	}

	/*** MUTATORS ***/

	/**
	 * Inserts a new node in the tree
	 * 
	 * @param data the data to insert
	 */
	public void insert(Product data, Comparator<Product> c) {
		if (root == null) {
			root = new Node((Product) data);
		} else {
			insert(data, root, c);
		}
	}

	/**
	 * Helper method to insert Inserts a new value in the tree
	 * 
	 * @param data the data to insert
	 * @param node the current node in the search for the correct location in which
	 *             to insert
	 */
	private void insert(Product data, Node node, Comparator<Product> c) {
		// Comparator c = new Comparator<Product>;
		if ((c.compare(data, node.data) <= 0)) {
			if (node.left == null)
				node.left = new Node(data);
			else
				insert(data, node.left, c);
		} else {
			if (node.right == null)
				node.right = new Node(data);
			else
				insert(data, node.right, c);
		}
	}

	/**
	 * Removes a value from the BST
	 * 
	 * @param data the value to remove
	 * @precondition !isEmpty()
	 * @precondition the data is located in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public void remove(Product data, Comparator<Product> c) throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("remove(): Element is not found");
		else
			root = remove(data, root, c);
	}

	/**
	 * Helper method to the remove method
	 * 
	 * @param data the data to remove
	 * @param node the current node
	 * @return an updated reference variable
	 */
	public Node remove(Product data, Node node, Comparator<Product> c) {
		if (node == null) {
			return node;
		} else if (c.compare(data, node.data) < 0) {
			node.left = remove(data, node.left, c);
		} else if (c.compare(data, node.data) > 0) {
			node.right = remove(data, node.right, c);
		} else if (node.left == null && node.right == null) {
			node = null;
		} else if (node.left != null && node.right == null) {
			node = node.left;
		} else if (node.left == null && node.right != null) {
			node = node.right;
		} else {
			node.data = findMin(node.right);
			node.right = remove(node.data, node.right, c);
		}
		return node;
	}

	/*** ADDITIONAL OPERATIONS ***/

	public void sortByPrimary(Comparator<Product> c) {
		list.clear();
		inOrderString();
		bubblesortByPrimary(c);
	}

	private void bubblesortByPrimary(Comparator<Product> c) {
		int i, j;
		Product temp;
		int n = list.size();
		boolean swapped;
		for (i = 0; i < n - 1; i++) {
			swapped = false;
			for (j = 0; j < n - i - 1; j++) {
				if (c.compare(list.get(j), list.get(j + 1)) > 0) {
					// swap arr[j] and arr[j+1]
					temp = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, temp);
					swapped = true;
				}
			}

			// IF no two elements were
			// swapped by inner loop, then break
			if (swapped == false)
				break;
		}
	}

	public void sortBySecondary(Comparator<Product> c) {
		list.clear();
		inOrderString();
		bubblesortBySecondary(c);
	}

	private void bubblesortBySecondary(Comparator<Product> c) {
		int i, j;
		Product temp;
		int n = list.size();
		boolean swapped;
		for (i = 0; i < n - 1; i++) {
			swapped = false;
			for (j = 0; j < n - i - 1; j++) {
				if (c.compare(list.get(j), list.get(j + 1)) > 0) {
					// swap arr[j] and arr[j+1]
					temp = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, temp);
					swapped = true;
				}
			}

			// IF no two elements were
			// swapped by inner loop, then break
			if (swapped == false)
				break;
		}
	}

	/**
	 * Returns a String containing the data in post order
	 * 
	 * @return a String of data in post order
	 */
	public String preOrderString() {
		StringBuilder str = new StringBuilder();
		preOrderString(root, str);
		return str + "\n";
	}

	/**
	 * Helper method to preOrderString Inserts the data in pre order into a String
	 * 
	 * @param node     the current Node
	 * @param preOrder a String containing the data
	 */
	private void preOrderString(Node node, StringBuilder preOrder) {
		if (node == null) {
			return;
		}
		preOrder.append(node.data + " ");
		preOrderString(node.left, preOrder);
		preOrderString(node.right, preOrder);
	}

	/**
	 * Returns a String containing the data in order
	 * 
	 * @return a String of data in order
	 */
	public String inOrderString() {
		StringBuilder str = new StringBuilder();
		inOrderString(root, str);
		return str.append("").toString();
	}

	/**
	 * Helper method to inOrderString Inserts the data in order into a String
	 * 
	 * @param node    the current Node
	 * @param inOrder a String containing the data
	 */
	private void inOrderString(Node node, StringBuilder inOrder) {
		if (node == null) {
			return;
		}
		inOrderString(node.left, inOrder);
		inOrder.append(node.data + "");
		list.add(node.data);
		inOrderString(node.right, inOrder);
	}

	/**
	 * Returns a String containing the data in post order
	 * 
	 * @return a String of data in post order
	 */
	public String postOrderString() {
		StringBuilder str = new StringBuilder();
		postOrderString(root, str);
		return str + "\n";
	}

	/**
	 * Helper method to postOrderString Inserts the data in post order into a String
	 * 
	 * @param node      the current Node
	 * @param postOrder a String containing the data
	 */
	private void postOrderString(Node node, StringBuilder postOrder) {
		if (node == null) {
			return;
		}
		postOrderString(node.left, postOrder);
		postOrderString(node.right, postOrder);
		postOrder.append(node.data + " ");
	}

	public ArrayList<Product> getProducts() {
		return list;
	}

}