
public class DictionaryEntryNotFoundException extends Exception {
	public DictionaryEntryNotFoundException(String message) {
		super(message);
	}
	
	public DictionaryEntryNotFoundException() {
		super("Entry not found");
	}

}
