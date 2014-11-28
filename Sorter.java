import java.io.*;
import java.util.*;

//Sorter class
public class Sorter{
    //Initializes all of the necessary variables needed throughout the code
    String dictionary_name;
    String line;
    char[] can_array;
    int count;
    int lines;
    String sorted_output;
    Word[] words;
    Word[] sorted_words;
    File sorted_file = new File("sorted-output.txt");

    //Constructs the Sorter class and takes in the dictionary file as a String
    public Sorter(String file_name){
	dictionary_name = file_name;
	//Initializes the writer for the sorted-output file and erases contents if there are any
	try{
	    PrintWriter erase = new PrintWriter(sorted_file);
	    erase.print("");
	    erase.close();
	}
	catch(Exception e){
	    e.printStackTrace();
	}
    }

    //Sorts the words canonically
    public String makeSorted(){
	//Sets values to nothing
	sorted_output = "";
	line = "";
	count = 0;
	lines = 0;
	try{
	    //Counts the number of lines
	    Scanner scan = new Scanner(new File(dictionary_name));
	    while(scan.hasNextLine()){
		lines++;
		scan.nextLine();
	    }
	    //Initializes the readers to read the dictionary
	    FileReader reader = new FileReader(dictionary_name);
	    BufferedReader bReader = new BufferedReader(reader);
	    //Initializes an original array and a sorted array of the word class; length is number of lines
	    sorted_words = new Word[lines];
	    words = new Word[lines];
	    //Reads each line
	    while((line = bReader.readLine()) != null){
		//Returns error message if there is more than one word on a single line
		if(line.contains(" ")){
		    System.err.println("The dictionary file is not in proper format. Please make sure there is only one word per line.");
		    System.exit(0);
		}
		//Assigns a new Word variable to an array positon
		words[count] = new Word(line, count);
		Word temp = words[count];
		//Converts String to array of characters to arrange it canonically
		can_array = temp.toCharArray();
		//Sorts the array
		Arrays.sort(can_array);
		//Converts sorted character array back to a String and stores it in a Word object to keep track of which word it is
		temp = new Word(new String(can_array), count);
		//Stores the word into the sorted array
		sorted_words[count] = temp;
		//Moves to the next position in the array
		count++;
	    }
	    reader.close();
	    //Uses the quickSort method to sort the words in the array
	    quickSort(sorted_words, 0, (sorted_words.length-1));
	    //Adds original word to the sorted output
	    for(int i = 0; i < sorted_words.length; i ++){
		int tempNum = sorted_words[i].getNumber();
		String tempString = words[tempNum].getName();
		sorted_output += (tempString + "\n");
	    }
	}
	catch(Exception e){
	    e.printStackTrace();
	}
        try{
	    //Writes sorted output to the sorted-file
            PrintStream stream = new PrintStream(new BufferedOutputStream(new FileOutputStream(sorted_file, true)));
            stream.println(sorted_output);
            stream.close();
            System.out.println("The words have been sorted in canonical order and saved to the file \"sorted-output.txt\"");
        }
        catch(Exception e){
            e.printStackTrace();
        }
	return sorted_output;
    }

    //Compares the two words to be anagrams
    public void comparator(String word_one, String word_two){
	//Quits if user enters -1 and -1
	if((word_one.equals("-1"))&&(word_two.equals("-1"))){
	    System.exit(0);
	}
	//Returns error message if both words aren't in dictionary
	if((!sorted_output.contains(word_one)) && (!sorted_output.contains(word_two))){
	    System.out.println("Word 1 and word 2 were not found in the dictionary. Please try entering two more words, or enter -1 and -1 to exit.");
	    Scanner scan = new Scanner(System.in);
	    try{
		String one_temp = scan.next();
		String two_temp = scan.next();
		comparator(one_temp, two_temp);
	    }
	    catch(Exception e){
		System.err.println("The words you entered were not valid. Please try again.");
	    }
	}
	//Returns error message if word two isn't in dictionary
	if(!sorted_output.contains(word_two)){
	    System.out.println("Word 2 was not found in the dictionary. Please try entering two more words, or enter -1 and -1 to exit.");
	    Scanner scan = new Scanner(System.in);
	    try{
		String one_temp = scan.next();
		String two_temp = scan.next();
		comparator(one_temp, two_temp);
	    }
	    catch(Exception e){
		System.err.println("The words you entered were not valid. Please try again.");
	    }
	}
	//Returns error message if word one isn't in dictionary
	if(!sorted_output.contains(word_one)){
	    System.out.println("Word 1 was not found in the dictionary. Please try entering two more words, or enter -1 and -1 to exit.");
	    Scanner scan = new Scanner(System.in);
	    try{
		String one_temp = scan.next();
		String two_temp = scan.next();
		comparator(one_temp, two_temp);
	    }
	    catch(Exception e){
		System.err.println("The words you entered were not valid. Please try again.");
	    }
	}

	//Converts both words to character arrays and sorts them canonically
	char[] temp_can_array = word_one.toCharArray();
	Arrays.sort(temp_can_array);
	String one_word = new String(temp_can_array);
	char[] temp_can_array_two = word_two.toCharArray();
	Arrays.sort(temp_can_array_two);
	String two_word = new String(temp_can_array_two);

	//Checks for anagrams and returns message
	if(one_word.equals(two_word)){
	    System.out.println("Word 1 and word 2 are anagrams of each other. Please try entering two more words, or enter -1 and -1 to exit.");
	    Scanner scan = new Scanner(System.in);
	    try{
		String one_temp = scan.next();
		String two_temp = scan.next();
		comparator(one_temp, two_temp);
	    }
	    catch(Exception e){
		System.err.println("The words you entered were not valid. Please try again.");
	    }
	}
	//Not anagrams
	else{
	    System.out.println("Word 1 and word 2 are not anagrams of each other. Please try entering two more words, or enter -1 and -1 to exit.");
	    Scanner scan = new Scanner(System.in);
	    try{
		String one_temp = scan.next();
		String two_temp = scan.next();
		comparator(one_temp, two_temp);
	    }
	    catch(Exception e){
		System.err.println("The words you entered were not valid. Please try again.");
	    }
	}
    }
    
    //quickSort method
    public static void quickSort(Word[] data, int min, int max){
	//Initiates pivot value
	int pivot;

	if(min < max){
	    //Makes partitions
	    pivot = partition(data, min, max);
	    //Sorts left partition
	    quickSort(data, min, pivot-1);
	    //Sorts right partition
	    quickSort(data, pivot+1, max);
	}
    }

    public static int partition(Word[] data, int min, int max){
	//Uses first element as the partition value
	Word partitionValue = data[min];
	
	int left = min;
	int right = max;

	while(left < right){
	    //Searches for an element that is > the partition
	    while(data[left].compareTo(partitionValue) <= 0 && left < right)
		left++;
	    //Searches for an element that is < the partition
	    while(data[right].compareTo(partitionValue) > 0)
		right--;
	    //Swaps the two values
	    if(left < right)
		swap(data, left, right);
	}
	//Moves the partition element to its final position
	swap(data, min, right);
	return right;
    }

    //Swaps the two elements
    public static void swap (Word[] data, int index1, int index2){
	Word temp = data[index1];
	data[index1] = data[index2];
	data[index2] = temp;
    }
}