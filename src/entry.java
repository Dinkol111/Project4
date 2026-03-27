/**
 *Entry class to represent a word and how many times it has been repeated
 */
public class entry implements Comparable<entry>{
	private String word;
	private int repeated;

	/**
	 * Constructs a new entry with the specified word.
	 * initializes the repeat count to 1 and converts the word to lowercase.
	 * @param word The string to be stored in this entry.
	 */
	public entry(String word) {
		this.word = word.toLowerCase();
		this.repeated = 1;
	}

	/**
	 * Gets the word stored in this entry.
	 * @return The word string.
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Sets or updates the word stored in this entry.
	 * @param word The new word string.
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * Gets the number of times this word has been added 
	 * @return The repeat count.
	 */
	public int getRepeated() {
		return repeated;
	}

	/**
	 * Increments the repeat count for this word by one.
	 */
	public void repeated() {
        this.repeated++;
    }

	/**
	 *Compares both words alphebetically
	 * @param other The entry to be compared.
	 * @return A negative integer, zero, or a positive integer to show if the word comes before, is the same, or comes after the other word
	 */
	@Override
    public int compareTo(entry other) {
        return word.compareTo(other.word);
    }
	
	/**
	 * Indicates whether some other object is "equal to" this one.
	 * Two entries are considered equal if they contain the same word string.
	 * @param o The reference object with which to compare.
	 * @return true if this object is the same as the obj argument or 
	 * contains the same word; false otherwise.
	 */
	@Override
    public boolean equals(Object o) {
        if(o == null ||o.getClass()!=this.getClass())
        	return false;
        entry e = (entry) o;
        if(word.equals(e.word))
        	return true;
        return false;
    }
	
	/**
	 * Returns a string representation of the entry.
	 * @return A string describing the word and its repeat count.
	 */
	@Override
	public String toString() {
		return word + " has been repeated "+ repeated+" times.";
	}
}