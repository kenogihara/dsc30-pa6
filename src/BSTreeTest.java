import static org.junit.jupiter.api.Assertions.*;

class BSTreeTest {

    BSTree<String> dictionary;
    BSTree<Integer> numbers;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        dictionary = new BSTree<>();
        numbers = new BSTree<>();
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
        numbers.insertData(2, 2);
        numbers.insertData(3, 3);


    }

    @org.junit.jupiter.api.Test
    void findDataList() {
    }

    @org.junit.jupiter.api.Test
    void findHeight() {
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