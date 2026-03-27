import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DictionaryShellTestStudent {

	private DictionaryBuilder dictionary;

    @BeforeEach
    void setUp() {
    	dictionary = new DictionaryBuilder(10);
    }

    @Test
    void testFrequencyForSameWord() {
    	dictionary.addWord("APple#21!");
    	dictionary.addWord("apple!");
        
        assertEquals(1, dictionary.getDiffWords(), "Apple is the only word provided, this should be one");
        assertEquals(2, dictionary.getNumAllWords());
        assertEquals(2, dictionary.getFrequency("apple"));
    }
    
    @Test
    void testRepeated() {
    	dictionary.addWord("monkey");
    	dictionary.addWord("lion");
    	dictionary.addWord("cat");
    	dictionary.addWord("hippo");
    	dictionary.addWord("monkey");

        assertEquals(2, dictionary.getFrequency("monkey"));
        assertEquals(1, dictionary.getFrequency("cat"));
        assertEquals(0, dictionary.getFrequency("tiger"), "Word never provided");
    }
    
    @Test
    void testRemovingWords() throws DictionaryEntryNotFoundException {
    	dictionary.addWord("spiderman");
    	dictionary.addWord("spiderman");
        assertTrue(dictionary.searchWord("spiderman"));
        
        dictionary.removeWord("spiderman");
        assertFalse(dictionary.searchWord("spiderman"));
        assertEquals(0, dictionary.getDiffWords());
        assertEquals(0, dictionary.getNumAllWords());
    }
    
    @Test
    void checkDuplicates() {
    	dictionary.addWord("grape");
    	dictionary.addWord("grape");
        assertEquals(1, dictionary.getDiffWords());
        assertEquals(2, dictionary.getNumAllWords(),"There were two instances of grapes so it should be 2 for total words");

    }
    
    @Test
    void deleteEmpty() throws DictionaryEntryNotFoundException{
    	dictionary.addWord("spiderman");
    	assertThrows(DictionaryEntryNotFoundException.class, () -> dictionary.removeWord("batman"));
    }

}
