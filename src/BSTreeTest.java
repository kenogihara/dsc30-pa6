import static org.junit.jupiter.api.Assertions.*;

class BSTreeTest {

    BSTree<String> dictionary;
    BSTree<Integer> numbers;
    BSTree<String> friends;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        dictionary = new BSTree<>();
        numbers = new BSTree<>();
        friends = new BSTree<>();
    }

    @org.junit.jupiter.api.Test
    void getRoot() {
        assertNull(dictionary.getRoot());
        dictionary.insert("science");
        dictionary.insert("people");
        assertEquals(dictionary.getRoot().getKey(), "science");
        assertEquals(dictionary.getRoot().getLeft().getKey(), "people");
    }

    @org.junit.jupiter.api.Test
    void getSize() {
        dictionary.insert("science");
        dictionary.insert("people");
        dictionary.insert("coolio");
        assertEquals(3, dictionary.getSize());
    }

    @org.junit.jupiter.api.Test
    void insert() {
        assertNull(numbers.getRoot());
        numbers.insert(92);
        numbers.insert(44);
        assertEquals(44, numbers.getRoot().getLeft().getKey());
        numbers.insert(100);
        numbers.insert(10);
        assertEquals(92, numbers.getRoot().getKey());
        assertThrows(NullPointerException.class, () -> numbers.insert(null));
        assertFalse(numbers.insert(100));
        assertFalse(numbers.insert(10));
        assertTrue(numbers.insert(7));
    }

    @org.junit.jupiter.api.Test
    void findKey() {
        dictionary.insert("random");
        dictionary.insert("data science");
        dictionary.insert("heaps");
        dictionary.insert("binary");
        dictionary.insert("sorting");
        assertTrue(dictionary.findKey("random"));
        assertFalse(dictionary.findKey("heap"));
        assertThrows(NullPointerException.class, () -> dictionary.insert(null));
        assertTrue(dictionary.findKey("binary"));
    }

    @org.junit.jupiter.api.Test
    void insertData() {
        numbers.insert(2);
        numbers.insert(3);
        assertThrows(NullPointerException.class, () -> numbers.insertData(null, 1));
        assertThrows(NullPointerException.class, () -> numbers.insertData(1, null));
        assertThrows(IllegalArgumentException.class, () -> numbers.insertData(1, 1));

        friends.insert("Andrew");
        friends.insert("Aria");
        friends.insert("Ricky");
        friends.insertData("Andrew", "Korean");
        friends.insertData("Aria", "Japanese");
        friends.insertData("Ricky", "Korean");
        assertEquals("Korean", friends.findDataList("Andrew").getFirst());
        friends.getRoot().addNewInfo("Weightlifting");
        friends.getRoot().getRight().addNewInfo("Dancing");
        assertEquals("Dancing", friends.findDataList("Aria").get(1));
    }

    @org.junit.jupiter.api.Test
    void findDataList() {
    }

    @org.junit.jupiter.api.Test
    void findHeight() {
        numbers.insert(10);
        numbers.insert(9);
        numbers.insert(8);
        numbers.insert(7);
        numbers.insert(6);
        numbers.insert(11);
        numbers.insert(12);
        numbers.insert(13);
        assertEquals(4, numbers.findHeight());
        assertEquals(-1, dictionary.findHeight());
        dictionary.insert("word!");
        assertEquals(0, dictionary.findHeight());
        dictionary.insert("xylophone");
        dictionary.insert("victory");
        assertEquals(1, dictionary.findHeight());
        dictionary.insert("zebra");
        assertEquals(2, dictionary.findHeight());
    }

    @org.junit.jupiter.api.Test
    void iterator() {
    }

    @org.junit.jupiter.api.Test
    void intersection() {
    }

    @org.junit.jupiter.api.Test
    void levelMax() {
    }
}