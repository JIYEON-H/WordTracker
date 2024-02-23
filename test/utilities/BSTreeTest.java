package utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.TreeException;
import treepackage.BSTreeNode;

class BSTreeTest {
	
	BSTree<String> tree1;

	@BeforeEach
	void setUp() throws Exception {
		tree1 = new BSTree<>();
	}

	
	@Test
	void testGetRoot() {
		tree1.add("C");
		tree1.add("E");
		tree1.add("D");
		tree1.add("B");
		tree1.add("A");
		
		BSTreeNode<String> root = tree1.getRoot();
		
		assertNotNull(root);
		assertEquals("C", tree1.getRoot().getData());
	}
	
	@Test
	void testGetRootTreeException() {
		assertThrows(TreeException.class, () -> {
			tree1.getRoot();
		});
	}

	@Test
	void testGetHeight() {
		tree1.add("E");
		tree1.add("C");
		tree1.add("G");
		tree1.add("B");
		tree1.add("D");
		tree1.add("F");
		tree1.add("H");
		
		assertEquals(3, tree1.getHeight());
	}
	
	@Test
	void testGetHeightInOrder() {
		tree1.add("A");
		tree1.add("B");
		tree1.add("C");
		tree1.add("D");
		tree1.add("E");
		
		assertEquals(5, tree1.getHeight());
	}

	@Test
	void testSize() {
		tree1.add("E");
		tree1.add("C");
		tree1.add("G");
		tree1.add("B");
		tree1.add("D");
		tree1.add("F");
		tree1.add("H");
		
		assertEquals(7, tree1.size());
		
	}

	@Test
	void testIsEmpty() {
		assertTrue(tree1.isEmpty());
	}
	
	@Test
	void testIsNotEmpty() {
		tree1.add("A");
		tree1.add("B");
		tree1.add("C");
		
		assertFalse(tree1.isEmpty());
	}

	@Test
	void testClear() {
		tree1.add("A");
		tree1.add("B");
		tree1.add("C");
		
		assertFalse(tree1.isEmpty());
		
		tree1.clear();
		
		assertTrue(tree1.isEmpty());
		assertEquals(0, tree1.size());
	}

	@Test
	void testContains() {
		tree1.add("E");
        tree1.add("C");
        tree1.add("G");
        tree1.add("B");
        tree1.add("D");
        tree1.add("F");
        tree1.add("H");
        
        assertTrue(tree1.contains("E"));
        assertTrue(tree1.contains("B"));
        assertTrue(tree1.contains("G"));
	}
	
	@Test
	void testNotContains() {
		tree1.add("E");
        tree1.add("C");
        tree1.add("G");
        
        assertFalse(tree1.contains("A"));
        assertFalse(tree1.contains("D"));
	}
	
	@Test
	void testContainsTreeException() {
		assertThrows(TreeException.class, () -> {
			tree1.contains("A");
		});
	}

	@Test
	void testSearch() {
		tree1.add("E");
        tree1.add("C");
        tree1.add("G");
        tree1.add("B");
        
        BSTreeNode<String> resultNode = tree1.search("E");
        assertNotNull(resultNode);
        assertEquals("E", resultNode.getData());
	}
	
	@Test
	void testSearchTreeException() {
		assertThrows(TreeException.class, () -> {
			tree1.search("E");
		});
	}

	@Test
	void testAdd() {
		tree1.add("E");
        tree1.add("C");
        tree1.add("G");
        tree1.add("B");
        
        assertEquals(4, tree1.size());
        
	}
	
	void testAddNullPointerException() {
		assertThrows(NullPointerException.class, () -> {
			tree1.add(null);
		});
        
	}

	@Test
	void testInorderIterator() {
		tree1.add("E");
        tree1.add("C");
        tree1.add("G");
        tree1.add("B");
        tree1.add("D");
        tree1.add("F");
        tree1.add("H");
        
        Iterator<String> iterator = tree1.inorderIterator();
        String[] expectedOrder = {"B","C","D","E","F","G","H"};
        
        for(int i=0; iterator.hasNext(); i++) {
        	assertEquals(expectedOrder[i], iterator.next());
        }
        
        assertFalse(iterator.hasNext());
        
	}

	@Test
	void testPreorderIterator() {
		tree1.add("E");
        tree1.add("C");
        tree1.add("G");
        tree1.add("B");
        tree1.add("D");
        tree1.add("F");
        tree1.add("H");
        
        Iterator<String> iterator = tree1.preorderIterator();
        String[] expectedOrder = {"E","C","B","D","G","F","H"};
        
        for(int i=0; iterator.hasNext(); i++) {
        	assertEquals(expectedOrder[i], iterator.next());
        }
        
        assertFalse(iterator.hasNext());
	}

	@Test
	void testPostorderIterator() {
		tree1.add("E");
        tree1.add("C");
        tree1.add("G");
        tree1.add("B");
        tree1.add("D");
        tree1.add("F");
        tree1.add("H");
        
        Iterator<String> iterator = tree1.postorderIterator();
        String[] expectedOrder = {"B", "D", "C", "F", "H", "G", "E"};

        for (int i = 0; iterator.hasNext(); i++) {
            assertEquals(expectedOrder[i], iterator.next());
        }

        assertFalse(iterator.hasNext());
        
	}

}
