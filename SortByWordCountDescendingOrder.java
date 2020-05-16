import java.util.Comparator;

public class SortByWordCountDescendingOrder implements Comparator<Word>{

	public SortByWordCountDescendingOrder() {
		// TODO Auto-generated constructor stub
	}

	public int compare(Word o1, Word o2) {
		return o2.getFrequency() - o1.getFrequency();
	}

}
