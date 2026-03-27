import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main DictionaryShell class to take in command line inputs and use DictionaryBuilder
 */
public class DictionaryShell {

	/**
	 * The main entry point for the Dictionary Builder CLI application.
	 * Expects a filename as a command-line argument to initialize the dictionary.
	 * @param args Command-line arguments. args[0] should be the path to the input text file.
	 */
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Type in a filename");
		}
		else {
			try { 
			DictionaryBuilder user = new DictionaryBuilder(args[0]);
			System.out.println("Welcome to the Dictionary Builder CLI.\r\n"
					+ "Available commands: add <word>, delete <word>, search <word>, list, stats, exit");
			
			String command = "";
			Scanner input = new Scanner(System.in);
			
			/**
			 * Main command loop. Continues until the user enters "exit".
			 */
			while(!command.equals("exit")) {
				System.out.print("> ");
				command = input.nextLine();
				
				// Handle the "add" command
				if(command.contains("add")) {
					user.addWord(command.substring(command.indexOf(" ")+1));
					System.out.println("\""+ command.substring(command.indexOf(" ")+1)+"\" added.");
					System.out.println();
				}
				// Handle the "delete" command
				else if(command.contains("delete")) {
					try {
					user.removeWord(command.substring(command.indexOf(" ")+1));
					System.out.println("\""+ command.substring(command.indexOf(" ")+1)+"\" deleted.");
					System.out.println();
					}
					catch(DictionaryEntryNotFoundException d) {
						System.out.println(d.getMessage());
					}
				}
				// Handle the "search" command
				else if(command.contains("search")) {
					if(user.searchWord(command.substring(command.indexOf(" ")+1))){
						System.out.println("\""+ command.substring(command.indexOf(" ")+1)+"\" found "+user.getFrequency(command.substring(command.indexOf(" ")+1))+" times.");
						System.out.println();
					}
					else {
						System.out.println("\""+ command.substring(command.indexOf(" ")+1)+"\" not found.");
						System.out.println();
					}
						
				}
				// Handle the "list" command
				else if(command.contains("list")) {
					ArrayList<String> temp = user.getAllWords();
					for(int i = 0;i<temp.size();i++) {
						System.out.println(temp.get(i));
					}
					System.out.println();
				}
				// Handle the "stats" command
				else if(command.contains("stats")){
					System.out.println("Total words: "+user.getNumAllWords());
					System.out.println("Total unique words: "+user.getDiffWords());
					System.out.println("Estimated load factor: " + (double)user.getDiffWords()/user.getSize());
				}
				// Handle exit or unrecognized commands
				else if(command.equals("exit")) {
					// Do nothing, loop will terminate
				}
				else {
					System.out.println("Command not recognized");
				}
				
			}
			System.out.println("Quitting...");
			input.close();
			}
			catch(FileNotFoundException f) {
				System.out.println("That file doesn't exist");
			}
		}

	}

}