import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class Proj03_Test {
	@Test
	void testSet() {
		Proj03_SimpleBST_student<Integer, String> tree = new Proj03_SimpleBST_student<Integer, String>("hi");
		tree.set(1, "one");
		tree.set(2, "two");
		tree.set(3, "three");
		tree.set(4, "four");

		assertEquals(tree.getSize(), 4);
		
		
	}
	
	@Test
	void testGet() {
		Proj03_SimpleBST_student<Integer, String> tree = new Proj03_SimpleBST_student<Integer, String>("hi");
		tree.set(1, "one");
		tree.set(2, "two");
		tree.set(3, "three");
		tree.set(4, "four");
		String val = tree.get(1);
		assertEquals("one", val);
		
		
	}
	
	@Test
	void testInOrderTraversal() {
		Proj03_SimpleBST_student<Integer, String> tree = new Proj03_SimpleBST_student<Integer, String>("hi");
		tree.set(1, "one");
		tree.set(2, "two");
		tree.set(3, "three");
		tree.set(4, "four");
		
		String[] values = new String[4];
		Integer[] keys = new Integer[4];
		int[] counts = new int[4];
		
		tree.inOrder(keys, values, counts);
		
		for(int i = 0; i < 4; i++) {
			System.out.println("key " + keys[i] +" val " + values[i] + " counts " + counts[i] );
		}
	}
	
	@Test
	void testSearch() {
		Proj03_SimpleBST_student<Integer, String> tree = new Proj03_SimpleBST_student<Integer, String>("hi");
		tree.set(1, "one");
		tree.set(2, "two");
		tree.set(3, "three");
		tree.set(4, "four");

		Proj03_BSTNode<Integer, String> node = tree.search(tree.root, new Integer(3));

		assertEquals("three",node.value);
		 

	}
	@Test
	void testRemove() {
		Proj03_SimpleBST_student<Integer, String> tree = new Proj03_SimpleBST_student<Integer, String>("hi");
		tree.set(1, "one");
		tree.set(2, "two");
		tree.set(3, "three");
		tree.set(4, "four");
		tree.remove(1);
		assertEquals(3, tree.getSize());
		
		
	}
}
