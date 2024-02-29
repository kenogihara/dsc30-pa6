/*
 * Name: Ken Ogihara
 * PID:  A16969236
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Search Engine implementation.
 * 
 * @author Ken Ogihara
 * @since  ${2/23/24}
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @return false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                for (String person : cast) {
                    if (!movieTree.findKey(person.toLowerCase())) {
                        movieTree.insert(person.toLowerCase());
                    }
                    if (!movieTree.findDataList(person.toLowerCase()).contains(movie)) {
                        movieTree.insertData(person.toLowerCase(), movie);
                    }
                }

                for (String studio : studios) {
                    if (!studioTree.findKey(studio.toLowerCase())) {
                        studioTree.insert(studio.toLowerCase());
                    }
                    if (!studioTree.findDataList(studio.toLowerCase()).contains(movie)) {
                        studioTree.insertData(studio.toLowerCase(), movie);
                    }
                }

                for (String person : cast) {
                    if (!ratingTree.findKey(person.toLowerCase())) {
                        ratingTree.insert(person.toLowerCase());
                    }
                    if (!ratingTree.findDataList(person.toLowerCase()).contains(rating)) {
                        ratingTree.insertData(person.toLowerCase(), rating);
                    }
                }
                // populate three trees with the information you just read
                // hint: create a helper function and reuse it to build all three trees
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {

        // process query
        String[] keys = query.toLowerCase().split(" ");

        LinkedList<String> output = new LinkedList<>(searchTree.findDataList(keys[0]));
        for (int i = 1; i < keys.length; i++) {
            output.retainAll(searchTree.findDataList(keys[i]));
        }
        // search and output intersection results
        // hint: list's addAll() and retainAll() methods could be helpful

        // search and output individual results
        // hint: list's addAll() and removeAll() methods could be helpful

        print(query, output);

        LinkedList<String> history = new LinkedList<>(output);
        for (int i = 0; i < keys.length; i++) {
            LinkedList<String> list = searchTree.findDataList(keys[i]);
            list.removeAll(history);
            print(keys[i], list);
            history.addAll(list);
        }
    }


    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        BSTree<String> movieTree = new BSTree<>();
        BSTree<String> studioTree = new BSTree<>();
        BSTree<String> ratingTree = new BSTree<>();
        // initialize search trees

        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);

        String query = "";
        for (int i = 2; i < args.length; i++) {
            query += args[i] + " ";
        }

        // populate search trees
        populateSearchTrees(movieTree, studioTree, ratingTree, fileName);

        // choose the right tree to query
        switch (searchKind) {
            case 0:
                searchMyQuery(movieTree, query);
                break;
            case 1:
                searchMyQuery(studioTree, query);
                break;
            case 2:
                searchMyQuery(ratingTree, query);
                break;
        }
    }
}
