import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileWordCounter
{
	
    public static void main(String[] args) throws Exception
    {

    	Map<String, Word> wordMap = new HashMap<>();
    	
    	// Ask the keyboard operator for the name of the input file
    	Scanner keyboardInput = new Scanner( System.in );
    	String  inputFileName;
    	System.out.println("Please enter the input file name to process: ");
    	inputFileName = keyboardInput.nextLine().trim();
    	keyboardInput.close();
    	
    	// Read the entire file into memory, into this List object
    	List<String> allLines= Files.readAllLines(Paths.get(inputFileName), StandardCharsets.UTF_8);

    	
        // Now iterate thru the list and convert everything to lowercase so word comparisons are case insensitive -- Also, get rid of mutiple embedded spaces using replaceAll with regex expression
    	for(int i = 0; i < allLines.size(); i++) {
    		allLines.set(i, allLines.get(i).toLowerCase().trim().replaceAll("( )+", " "));
    	}
    	
    	// Now, iterate (using for-each) to count words in each line
    	for(String line : allLines) {
    		StringTokenizer words = new StringTokenizer(line, " .,;?!");
    		
    		while(words.hasMoreElements())
    		{
    			String word = words.nextToken();
    			if(wordMap.containsKey(word)) {
    				Word wordFromHashMap = wordMap.get(word);
    				wordFromHashMap.incrementFrequency();
    				wordMap.put(word, wordFromHashMap);
    			}else {
    				Word newFoundWord = new Word(word);
    				wordMap.put(word, newFoundWord);
    			}
    		}
    	}
    	
    	// Now sort the words we found in descending order; meaning, highest occurrences to lowest occurrences
    	// HashMaps are not sortable, so let's convert it into a List
    	List<Word> listOfWordObjects = new ArrayList<Word>();
    	
    	for(Map.Entry<String, Word> entry : wordMap.entrySet()) {
    	    Word value = entry.getValue();
    	    listOfWordObjects.add(value);
    	}
    	
        // Like Steve Harvey said on Family fued -- "Collections.sort"
        Collections.sort(listOfWordObjects, new SortByWordCountDescendingOrder());
        
        // OK, let's print the order to see how it's going, and create the output file
        Path outputPath = Paths.get(inputFileName +"." +Long.toString(System.currentTimeMillis()));
        BufferedWriter writer = Files.newBufferedWriter(outputPath, Charset.forName("UTF-8"));
        for(Word word: listOfWordObjects) {
        	//System.out.println(word.getWord() +" --> " +word.getFrequency());
        	System.out.println(word);
        	writer.write(word.toString());
        	writer.newLine();
        }
        // Close the output file, else it won't actually contain the data written
        writer.close();

    }
}