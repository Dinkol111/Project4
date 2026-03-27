import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to create a generic linked list based Dictionary ADT
 */
public class DictionaryBuilder {
	private GenericLinkedList<entry>[] table; 
	private int size;
	private int diffWords;
	private int allWords;

	/**
	 * Default constructor for DictionaryBuilder.
	 */
	public DictionaryBuilder() {}
	
	/**
	 * Constructs a DictionaryBuilder with a table size optimized for the 
	 * estimated number of entries using a 0.6 load factor and a 4k+3 prime number.
	 * @param estimatedEntries The expected number of unique words to be stored.
	 */
	 public DictionaryBuilder(int estimatedEntries){
		 int estimate = (int)(estimatedEntries/.6);
		 size = find4k3PrimeNum(estimate);
		 diffWords = 0;
		 allWords = 0;
		 table = new GenericLinkedList[size];
		 for(int i = 0;i<table.length;i++) {
			 table[i] = new GenericLinkedList<entry>();
			 }
		 
	 }
	 
	 /**
	  * Constructs a DictionaryBuilder by reading words from a specified file.
	  * @param filename the name of the file to be read
	  * @throws FileNotFoundException If the specified file does not exist.
	  */
	 public DictionaryBuilder(String filename) throws FileNotFoundException {
		 File f = new File(filename);
		 Scanner input = new Scanner(f);
		 diffWords = 0;
		 allWords = 0;
		 ArrayList<String> allInput = new ArrayList<>();
		 while(input.hasNext()) {
			 String oneWord = input.next();
			 if(!oneWord.isEmpty()) {
				 allInput.add(oneWord);
			 }
			 
		 }
		 input.close();
		 
		 int estimate = (int)(allInput.size()/.6);
		 size = find4k3PrimeNum(estimate);
		 table = new GenericLinkedList[size];
		 for(int i = 0;i<table.length;i++) {
			 table[i] = new GenericLinkedList<entry>();
			 }
		 for(String s : allInput) {
		        this.addWord(s); 
		    }
		 
	 }

	 /**
	  * Finds a prime number that works with 4k3
	  * @param num The starting integer to check.
	  * @return An integer that is prime and fits the 4k+3 criteria.
	  */
	 private static int find4k3PrimeNum(int num) {
		 int n = num;
		 boolean found = true;
		 while(found) {
			 if(isPrime(n)&&(n%4==3)) {
				 return n;
			 }
			 n++;
		 }
		 return n;
	 }

	 /**
	  * Checks if a number is prime.
	  * @param n The integer to check.
	  * @return true if the number is prime, false otherwise.
	  */
	 private static boolean isPrime(int n){
	        if (n <= 1)
	            return false;
	        for (int i = 2; i < n; i++)
	            if (n % i == 0)
	                return false;
	        return true;
	    }

	 /**
	  * Adds a word to the dictionary. Increases counter if it's a repeat word.
	  * @param word The word to be added.
	  */
	 public void addWord(String word) {
		 String oneWord = word;
		 oneWord= oneWord.toLowerCase();
		 String filteredWord= "";
		 char character;
		 for(int i = 0;i<oneWord.length();i++) {
			 character = oneWord.charAt(i);
			 if(character >= 'a' && character <= 'z') {
				 filteredWord+= character;
			 }
		 } 
		 int index = Math.abs(filteredWord.hashCode()) % size;
		 GenericLinkedList<entry> temp = table[index];
		 entry exists= null;
		 for(int i = 0;i<temp.size();i++) {
			 if(temp.get(i).getWord().equals(filteredWord)) {
				 exists = temp.get(i);
				 break;
			 }
		 }
		 if(exists!= null) {
			 exists.repeated();
			 allWords++;
		 }else {
		        temp.addFirst(new entry(filteredWord));
		        diffWords++; 
		        allWords++;
		    }
	 }

	 /**
	  * Searches for a word in the hash table.
	  * @param word The word to search for.
	  * @return true if the word exists in the dictionary, false otherwise.
	  */
	 public boolean searchWord(String word) {
		    int index = Math.abs(word.hashCode()) % size;
		    GenericLinkedList<entry> temp = table[index];
		    for(int i = 0;i<temp.size();i++) {
		    	if(word.equals(temp.get(i).getWord()))
		    		return true;
		    }
		    return false;
	 }
	 
	 /**
	  * Returns the number of repeats of a word
	  * @param word The word to check.
	  * @return The number of times the word has been added.
	  */
	 public int getFrequency(String word) {
		 String oneWord = word;
		 oneWord= oneWord.toLowerCase();
		 String filteredWord= "";
		 char character;
		 for(int i = 0;i<oneWord.length();i++) {
			 character = oneWord.charAt(i);
			 if(character >= 'a' && character <= 'z') {
				 filteredWord+= character;
			 }
		 } 
		    int index = Math.abs(filteredWord.hashCode()) % size;
		    GenericLinkedList<entry> temp = table[index];
		    for(int i = 0;i<temp.size();i++) {
		    	if(filteredWord.equals(temp.get(i).getWord()))
		    		return temp.get(i).getRepeated();
		    }
		    return 0;
	 }

	 /**
	  * Removes a word entry from the dictionary 
	  * @param word The word to be removed.
	  * @throws DictionaryEntryNotFoundException If the word is not found in the table.
	  */
	 public void removeWord(String word) throws DictionaryEntryNotFoundException {
		 String oneWord = word;
		 oneWord= oneWord.toLowerCase();
		 String filteredWord= "";
		 char character;
		 for(int i = 0;i<oneWord.length();i++) {
			 character = oneWord.charAt(i);
			 if(character >= 'a' && character <= 'z') {
				 filteredWord+= character;
			 }
		 } 
		    int index = Math.abs(filteredWord.hashCode()) % size;
		    boolean found = false;
		    GenericLinkedList<entry> temp = table[index];
		    for(int i = 0;i<temp.size();i++) {
		    	if(filteredWord.equals(temp.get(i).getWord())) {
		    		diffWords--;
		    		allWords -= temp.get(i).getRepeated();
		    		temp.remove(i);
		    		found = true;
		    	}
		    }
		    if(!found)
		    throw new DictionaryEntryNotFoundException("Entry not found");
	 }
	 
	 /**
	  * Returns an alphabetically sorted list of all the different words 
	  * @return An ArrayList containing all unique words in alphabetical order.
	  */
	 public ArrayList<String> getAllWords(){
		 ArrayList<String> all = new ArrayList<>();
		 for(int i = 0;i<table.length;i++) {
			 for(int j = 0;j<table[i].size();j++) {
				 all.add(table[i].get(j).getWord());
			 }
		 }
		 for(int i = 0;i<all.size()-1;i++) {
			 for(int j = 0;j<all.size()-i-1;j++) {
				 if (all.get(j).compareTo(all.get(j + 1)) > 0) {
					 String temp = all.get(j);
					 all.set(j, all.get(j+1));
					 all.set(j+1,temp);
				 }
			 }
			 
		 }
		 return all;
	 }

	/**
	 * Gets the count of unique words in the dictionary.
	 * @return The number of distinct entries.
	 */
	public int getDiffWords() {
		return diffWords;
	}

	/**
	 * Gets the total count of words processed, including duplicates.
	 * @return The sum of all word frequencies.
	 */
	public int getNumAllWords() {
		return allWords;
	}
	
	/**
	 * Gets the current size of the hash table.
	 * @return The number of buckets in the table.
	 */
	public int getSize() {
		return size;
	}
	
}