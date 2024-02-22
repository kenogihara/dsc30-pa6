/*
 * Name: Ken Ogihara
 * PID:  A16969236
 */

import java.util.*;

import static java.util.Collections.max;

/**
 * Binary search tree implementation.
 * 
 * @author Ken Ogihara
 * @since  ${2/19/24}
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.key = key;
            this.dataList = dataList;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            dataList = new LinkedList<>();
            this.left = left;
            this.right = right;
            this.key = key;
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setLeft(BSTNode newleft) {
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setRight(BSTNode newright) {
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            if (this.dataList.contains(data)) {
                this.dataList.remove(data);
                return true;
            }
            return false;
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        nelems = 0;
        root = null;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        if (root == null) {
            return 0;
        }
        return nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key
     * @return true if insertion is successful and false otherwise
     * @throws NullPointerException if key is null
     */
    public boolean insert(T key) throws NullPointerException {
        if (findKey(key)) {
            return false;
        }
        root = insertH(root, key);
        return root != null;
    }


    /**
     * Helper method that allows us to take hold of root.
     *
     * @param root of the BST
     * @param key that we are seeking to insert
     * @return true if insertion is successful and false otherwise
     * @throws NullPointerException if key is null
     */
    private BSTNode insertH(BSTNode root, T key) {
        if (key == null) {
            throw new NullPointerException("key is null");
        }
        if (root == null || nelems == 0) {
            root = new BSTNode(null, null, new LinkedList<>(), key);
            nelems++;
        }
        if (key.compareTo(root.getKey()) < 0) {
            root.setLeft(insertH(root.getLeft(), key));
        }
        if (key.compareTo(root.getKey()) > 0) {
            root.setRight(insertH(root.getRight(), key));
        }
        return root;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) throws NullPointerException {
        return findKeyH(root, key);
    }

    /**
     * Helper method that allows us to take hold of root.
     *
     * @param key To be searched
     * @param root Node of the BST
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    private boolean findKeyH(BSTNode root, T key) {
        if (key == null) {
            throw new NullPointerException("key is null");
        }
        if (root == null) {
            return false;
        }
        if (key.compareTo(root.getKey()) < 0) {
            return findKeyH(root.getLeft(), key);
        }
        if (key.compareTo(root.getKey()) > 0) {
            return findKeyH(root.getRight(), key);
        }
        else {
            return true;
        }
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException If either key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if (key == null || data == null) {
            throw new NullPointerException("key is null");
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException("key is not found in BST");
        }
        else {
            insertDataH(root, key, data);
        }

    }

    private BSTNode insertDataH(BSTNode root, T key, T data) {
        if (root == null) {
            return new BSTNode(null, null, new LinkedList<>(), key);
        }
        if (key.compareTo(root.getKey()) < 0) {
            return insertDataH(root.getLeft(), key, data);
        }
        if (key.compareTo(root.getKey()) > 0) {
            return insertDataH(root.getRight(), key, data);
        }
        else {
            root.addNewInfo(data);
        }
        return root;
    }



    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if (key == null) {
            throw new NullPointerException("key is null");
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException("key is not found in BST");
        } else {
            return root.getDataList();
        }
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        return this.findHeightHelper(root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if (root == null) {
            return -1;
        }
        int leftHeight = findHeightHelper(root.getLeft());
        int rightHeight = findHeightHelper(root.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {
        private Stack<BSTNode> stack;
        public BSTree_Iterator() {
            stack = new Stack<>();
        }

        private void leftMostPath(BSTNode root) {
            while (root != null) {
               stack.push(root);
               root = root.getLeft();
            }
        }
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("no next item");
            }
            BSTNode currentNode = stack.pop();
            leftMostPath(currentNode.getRight());
            return currentNode.getKey();
        }
    }

    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        /* TODO */
        return null;
    }

    public T levelMax(int level) {
        /* TODO */
        return null;
    }
}
