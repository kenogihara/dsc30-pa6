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
        assertEquals(2, dictionary.getSize());
    }

    @org.junit.jupiter.api.Test
    void insert() {
    }

    @org.junit.jupiter.api.Test
    void findKey() {
    }

    @org.junit.jupiter.api.Test
    void insertData() {
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