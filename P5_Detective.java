import java.io.*;

//Main class
public class P5_Detective{
    public static void main(String[] args){
	//Returns error message if the user enters an incorrect amount of arguments
	if(args.length != 3){
	    System.err.println("This program requires three arguments: the file name of the dictionary and two separate words to compare.");
	    System.exit(0);
	}
	
	//Assigns String values to each argument
	String dictionary_name = args[0];
	String wordOne = args[1];
	String wordTwo = args[2];

	//Returns error message if the dictionary file is not the correct file type
	if(dictionary_name.contains(".txt")){
	}
	else{
	    System.err.println("Please make sure the dictionary file is a text file (\".txt\").");
	    System.exit(0);
	}

	//Initiates a Sorter class with the dictionary file
	Sorter sort = new Sorter(dictionary_name);
	//Sorts the words canonically and stores them in a new file
	sort.makeSorted();
	//Compares the two words entered to be anagrams
	sort.comparator(wordOne, wordTwo);
    }
}