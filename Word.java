public class Word {

	String  word;
	Integer frequency;

	// Constructor for a new word
	public Word(String newWord) {
		super();
		word = newWord;
		frequency = Integer.valueOf(1);
	}

	// Return the actual word held by this Word class
	public String getWord() {
		return word;
	}

	// Return the frequency of this word occurring
	public Integer getFrequency() {
		return frequency;
	}

	// Increment the word counter by 1
	public void incrementFrequency() {
		frequency = Integer.valueOf( frequency.intValue() + 1);
		;
	}


	// This method will be called anytime we try to print the Word object.  This returns the word, 
	// and the words occurs and the number of times/frequency.
	// So, if we put a word object in a System.out.println, then this method will be automatically called by java
	public String toString() {
		return word + " occurrs " +frequency +" time(s)";
	}
	
	public Word() {
		// Nothing to do in the empty argument constructor
	}
	


}
