/* Proj03_SimpleBST_student
 *
 * Author: TODO
 */

import java.io.*;
import java.util.Random;

public class Proj03_SimpleBST_student<K extends Comparable<K>, V>
             implements Proj03_BST<K,V>
{	
	public Proj03_BSTNode<K,V> root;
	private int count = 0;
	private String debugStr;
	public Proj03_SimpleBST_student(String debugStr)
	{
		this.debugStr = debugStr;
		root = null;
		
	}


	/* void set(K,V)
	 *
	 * Inserts a given (key,value) pair into the tree.
	 *
	 * This call may assume that both the key and value are non-null.
	 *
	 * If the key already exists, then update the stored value to the new
	 * value; do *NOT* store multiple values for the same key!
	 */
	public void set(K key, V value)
	{
		/* we use a recursive implementation, in the x=change(x)
		 * style, for most methods in this class.
		 */
		root = set_helper(root, key,value);
		genDebugDot();
	}

	private Proj03_BSTNode<K,V> set_helper(Proj03_BSTNode<K,V> oldRoot,
	                                       K key, V value)
	{
		if(oldRoot == null) {
			return new Proj03_BSTNode<K, V>(key, value);
		}
		if(oldRoot.key == key) {
			oldRoot.value = value;
			return oldRoot;
		}
		if(oldRoot.key.compareTo(key) > 0) {
			oldRoot.left = set_helper(oldRoot.left,key, value);
		}
		else if(oldRoot.key.compareTo(key) < 0){
			oldRoot.right = set_helper(oldRoot.right,key, value);

		}
		oldRoot.count = (1 + getCount(oldRoot.left) + getCount(oldRoot.right));
		return oldRoot;
	}


	/* int getSize()
	 *
	 * Returns the number of keys stored in the tree.  This *MUST* run in
	 * O(1) time.
	 */
	public int getSize()
	{
		return getCount(root);
	}
	private int getCount(Proj03_BSTNode<K,V> node)
	{
		if (node == null)
			return 0;
		return node.count;
	}


	/* void genDebugDot()
	 *
	 * Generates a \texttt{.dot} file which represents the tree; if this
	 * is called multiple times, then they must all have different
	 * filenames.
	 *
	 * The generated files must be placed in the *current* directory, and
	 * must not include any whitespace in the name.
	 */
	public void genDebugDot()
	{
		
		File file = new File(debugStr + count++ + ".dot");
		try {
			FileWriter writer = new FileWriter(file);
			writer.write("digraph{\n");
			//all nodes 
			debugAllNodes(root, writer);
			//all node attachments 
			debugAttachments(root, writer);

			writer.write("}\n");
			writer.close();
		} catch (IOException e) {
			System.err.println("IOException");
			e.printStackTrace();
		}
		
	}
		// TODO: open up a file (in the *CURRENT* directory!)
	private void debugAttachments(Proj03_BSTNode<K,V> root, FileWriter writer) {
		try {
		if(root == null) {
			return;
		}
		if(root.left != null) {
			writer.write("\t" + root.toString() + "->" + root.left.toString() + ";\n");
			debugAttachments(root.left, writer);
		}
		else if(root.right != null) {
			writer.write("\t" + root.toString() + "->" + root.right.toString() + ";\n");
			debugAttachments(root.right, writer);
		}
		else {
			return;
		}
		}
		catch(IOException io) {
			System.err.println("IOException");
			io.printStackTrace();
		}

	}
	private void debugAllNodes(Proj03_BSTNode<K,V> root, FileWriter writer)  {
		if(root == null) {
			return;
		}
		try {
		writer.write("\t" + root.toString() + ";\n");
		}		
		catch(IOException io) {
			System.err.println("IOException");
			io.printStackTrace();
		}
		debugAllNodes(root.left, writer);
		debugAllNodes(root.right, writer);

	}
	
	@Override
	public V get(K key) {
		 Proj03_BSTNode<K,V> node = search(root, key);
		if(node == null) {
			return null;
		}
		else {
			return node.value; 
		}

	}


	@Override
	public void remove(K key) {
		 Proj03_BSTNode<K,V> node = search(root, key);
		 //CASE 1: leaf 
		 if(node.right == null && node.left == null) {
			 node = null;
		 }
		 //CASE 2: one child 
		 else if(node.right != null || node.left != null) {
			 if(node.right != null) {
				 node = node.right;
			 }
			 else {
				 node = node.left;
			 }
		 }
		 //CASE 3: two children
		 else if(node.right != null && node.left != null) {
			 
			 //swap with successor, then delete the successor
			 Proj03_BSTNode<K,V> successor = minValue(node.right);
			 //swap
			 Proj03_BSTNode<K,V> temp = successor;
			 successor = node;
			 node = successor;
			 //delete
			 successor = null;
			 
		 }
			genDebugDot();
		 
		
	}
	
	private Proj03_BSTNode<K,V>  minValue(Proj03_BSTNode<K,V> root) {
        while (root.left != null) 
        { 
        	root = root.left; 
        } 
        return root; 
		
	}
	
	public Proj03_BSTNode<K,V> search(Proj03_BSTNode<K,V> root, K key){
		if(root == null || root.key == key) {
			return root;
		}
		else if(root.key.compareTo(key) < 0){
			 return search(root.right, key);
		}
		else{
			 return search(root.left, key);
		}

	}
	
	@Override
	public void rotateLeft(K at) {
		 Proj03_BSTNode<K,V> node = search(root, at);
		 Proj03_BSTNode<K,V> C = node;
		 Proj03_BSTNode<K,V> E = C.right;
		 Proj03_BSTNode<K,V> A = C.left;
		 Proj03_BSTNode<K,V> D = E.left;
		 Proj03_BSTNode<K,V> F = E.right;
		 
		 
		 E.left = C;
		 E.right = F;
		 C.left = A;
		 C.right = D;
		
	}


	@Override
	public void rotateRight(K at) {
		 Proj03_BSTNode<K,V> node = search(root, at);
		 Proj03_BSTNode<K,V> E = node;
		 Proj03_BSTNode<K,V> C = E.left;
		 Proj03_BSTNode<K,V> F = E.right;

		 Proj03_BSTNode<K,V> A = C.left;
		 Proj03_BSTNode<K,V> D = C.right;
		 
		 C.left = A;
		 C.right = E;
		 E.left = D;
		 E.right = F;

	}


	@Override
	public void inOrder(K[] keysOut, V[] valuesOut, int[] countsOut) {
		inOrderHelper(root, 0, keysOut, valuesOut, countsOut);
	}
	
	private void inOrderHelper(Proj03_BSTNode<K,V> root, int index, K[] keysOut, V[] valuesOut, int[] countsOut) {
		if(root == null) {
			return;
		}
		inOrderHelper(root.left, index, keysOut, valuesOut, countsOut);
		if(keysOut != null)
			keysOut[index] = root.key;
		if(valuesOut != null)
			valuesOut[index] = root.value;
		if(countsOut != null)
			countsOut[index] = root.count;
		index++;
		inOrderHelper(root.right, index, keysOut, valuesOut, countsOut);

	}

	@Override
	public void postOrder(K[] keysOut, V[] valuesOut, int[] countsOut) {
		postOrderHelper(root, 0, keysOut, valuesOut, countsOut);
		
	}
	private void postOrderHelper(Proj03_BSTNode<K,V> root, int index, K[] keysOut, V[] valuesOut, int[] countsOut) {
		if(root == null) {
			return;
		}
		inOrderHelper(root.left, index, keysOut, valuesOut, countsOut);
		inOrderHelper(root.right, index, keysOut, valuesOut, countsOut);
		if(keysOut != null)
			keysOut[index] = root.key;
		if(valuesOut != null)
			valuesOut[index] = root.value;
		if(countsOut != null)
			countsOut[index] = root.count;
		index++;

	}

}

