//Word class
public class Word{
    //Initiates variables necessary throughout the code
    String name;
    int number;
    //Constructs the word class and takes in the name and number
    public Word(String word, int value){
	name = word;
	number = value;
    }
    //Finds name
    public String getName(){
	return name;
    }
    //Finds number or array value
    public int getNumber(){
	return number;
    }
    //Converts word to character array using String method
    public char[] toCharArray(){
	char[] characters;
	characters = name.toCharArray();
	return characters;
    }
    //Compares word to another word using String method
    public int compareTo(Word other){
	if(other == null)
	    throw new IllegalArgumentException();
	int result = 0;
	if(this.name.compareTo(other.getName()) <= 0)
	    result = -1;
	else if(this.name.compareTo(other.getName()) > 0)
	    result = 1;
	return result;
    }
}