import org.junit.jupiter.api.Test;
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

    @Test
    void getRoot() {
        assertNull(dictionary.getRoot());
        dictionary.insert("science");
        dictionary.insert("people");
        assertEquals(dictionary.getRoot().getKey(), "science");
        assertEquals(dictionary.getRoot().getLeft().getKey(), "people");
    }

    @Test
    void getSize() {
        dictionary.insert("science");
        dictionary.insert("people");
        dictionary.insert("coolio");
        assertEquals(3, dictionary.getSize());
    }

    @Test
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

    @Test
    void findKey() {
        dictionary.insert("random");
        dictionary.insert("data science");
        dictionary.insert("heaps");
        dictionary.insert("binary");
        dictionary.insert("sorting");
        assertTrue(dictionary.findKey("random"));
        assertFalse(dictionary.findKey("Heaps"));
        assertThrows(NullPointerException.class, () -> dictionary.insert(null));
        assertTrue(dictionary.findKey("binary"));
    }

    @Test
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

    @Test
    void findDataList() {
        assertThrows(NullPointerException.class, () -> friends.findDataList(null));
        assertThrows(IllegalArgumentException.class, () -> friends.findDataList("Andrew"));

        friends.insert("Andrew");
        friends.insert("Aria");
        friends.insert("Ricky");
        friends.insertData("Andrew", "Korean");
        friends.insertData("Aria", "Japanese");
        friends.insertData("Ricky", "Korean");
        friends.insertData("Ricky", "Studying");
        friends.getRoot().getRight().getRight().addNewInfo("5'7");

        //assertEquals("Korean, Studying, 5'7", friends.findDataList("Ricky"));
        assertEquals("Japanese", friends.findDataList("Aria").getFirst());
        assertThrows(IllegalArgumentException.class, () -> friends.findDataList("aria"));

    }

    @Test
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

    @Test
    void iterator() {
        numbers.insert(92);
        numbers.insert(44);
        numbers.insert(100);
        numbers.insert(10);
        numbers.insert(11);
        numbers.insert(50);
        numbers.insert(3);

    }

    @Test
    void intersection() {
    }

    @Test
    void levelMax() {
    }
}