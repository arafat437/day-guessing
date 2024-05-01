package randomSelector;
import java.util.Random;
import java.util.Vector;





public class randomSelector {
	//The class pass any vector of the events: title OR date OR day
	//and generate the random integer based on the size of the vector
	public int random(Vector<String> vector) {
        Random rand = new Random();
		int selector = rand.nextInt(vector.size());
		return selector;
	}
	
	//define the answer where Monday is = 1, Tuesday = 2 ... Sunday = 7
	public int score(Vector<String> vector, int selector) {
	    int answer;
	    String day = vector.get(selector);
	    if (day.equals(" Monday")) {
	        answer = 1;
	    } else if (day.equals(" Tuesday")) {
	        answer = 2;
	    } else if (day.equals(" Wednesday")) {
	        answer = 3;
	    } else if (day.equals(" Thursday")) {
	        answer = 4;
	    } else if (day.equals(" Friday")) {
	        answer = 5;
	    } else if (day.equals(" Saturday")) {
	        answer = 6;
	    } else {
	        answer = 7;
	    }
	    return answer;
	}

}
