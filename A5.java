import java.util.Scanner;
import java.util.TreeMap;
import java.util.HashMap;


/**
 * COMP 2503 Fall 2019 Assignment 5
 * 
 * This program reads a text file and compiles a list of the words together with
 * how many times each word appears.
 * 
 * Words are converted to all lowercase. Punctuation and numbers are skipped.
 *
 * It uses a HashMap to store the Word objects, and TreeMaps to store the sorted
 * Keywords and Longword lists.
 * 
 * Last updated by @author Maryam Elahi
 * 
 * @date Fall 2019
 * 
 */

public class A5 // a lot of help from google and stack overflow
{
	/*
	 * Create and initialize the following variables: HashMap<String, Word> words:
	 * hash map of unique words TreeMap<Word, Word> keywordsByFreqDesc: TreeMap of
	 * keywords, ordered by the keyword comparator TreeMap<Word, Word>
	 * longwordsByLengthDesc: TreeMap of longwords, ordered by the longword
	 * comparator
	 */

	// TODO: Initialize the following variables with the proper constructor.
	private HashMap<String, Word> words = new HashMap<String, Word>();
	private TreeMap<Word, Word> keywordsByFreqDesc = new TreeMap<Word, Word>(Word.CompFreqDesc);
	private TreeMap<Word, Word> longwordsByLengthDesc = new TreeMap<Word, Word>(Word.CompLengthDesc);

	private Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		A5 a5 = new A5();
		a5.run();
	}

	/**
	 * Print the list of keywords in the required order. Use the built-in iterator
	 * for the key set or the values collection in the TreeMap
	 */
	private void printKeywords() {
		for (Word w : keywordsByFreqDesc.values()) {
			System.out.println(w.toString());
		}

	}

	/**
	 * Print the list of longwords in the required order. Use the built-in iterator
	 * for the key set or the values collection in the TreeMap
	 */
	private void printLongwords() {

		for (Word w : longwordsByLengthDesc.values()) {
			System.out.println(w.toString());
		}
	}

	private void printResults() {
		System.out.println("Number of Unique Words: " + words.size());
		System.out.println("Number of Keywords: " + keywordsByFreqDesc.size());
		printKeywords();
		System.out.println("Number of Longwords: " + longwordsByLengthDesc.size());
		printLongwords();
	}

	/*
	 * Read the file and add words to the tree.
	 */
	private void readFile() {
		while (input.hasNext()) {

			String word = input.next().toLowerCase().trim().replaceAll("[^a-z]", "");

			if (word.length() > 0) {
				Word w1 = new Word(word);
				if (words.containsKey(word)) {
					words.get(word).incrCount();
				} else {
					words.put(word, w1);
				}
			}
		}
	}

	/* Create the keywords and longwords trees. */
	private void createSortedTrees() {  // got help from Fahim, put longword first and it was throwing
										//my keywords off, so he suggested that i move keywords up and it fixed it
		for (Word w1 : words.values()) {

			if (w1.getCount() > 1 && w1.getWord().length() >= 4) {
				keywordsByFreqDesc.put(w1, w1);
			}

			else if (w1.getWord().length() >= 8) {
				longwordsByLengthDesc.put(w1, w1);
			}

		}
	}

	/* Run the program. */
	private void run() {
		readFile();
		createSortedTrees();
		printResults();
	}
}
