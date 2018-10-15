/* Proj03_SimpleBST_student
 *
 * Author: TODO
 */

import java.io.*;

public class Proj03_SimpleBST_student<K extends Comparable<K>, V>
             implements Proj03_BST<K,V>
{
	public Proj03_SimpleBST_student(String debugStr)
	{
		// TODO: implement me
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
	}

	private Proj03_BSTNode<K,V> set_helper(Proj03_BSTNode<K,V> oldRoot,
	                                       K key, V value)
	{
		// TODO: implement me
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
		// TODO: open up a file (in the *CURRENT* directory!)

		// TODO: fill it out (probably with a recursive function?)

		// TODO: make sure to close the file, so that Java will flush
		//       out the contents
	}
}

