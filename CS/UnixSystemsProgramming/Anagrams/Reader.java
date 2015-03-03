
import java.util.*;  
import java.io.*;  


public class Reader implements Comparator
{  




	public String [] listToArray(ArrayList <String> pWords){

		String [] returnWords = new String [pWords.size()];

		for(int counter = 0; counter < pWords.size(); counter++){

			returnWords[counter] = pWords.get(counter);
		}
		Arrays.sort(returnWords);

		return returnWords;

	}

	public ArrayList <String> arrayToUniqueList(String [] pWords){

		ArrayList<String> theWords = new ArrayList<String>();

		Arrays.sort(pWords);

		System.out.println();


		//add the words from the array back to the arraylist only if they do not already
		//exist in the array list
		for(int counter = 0; counter < pWords.length; counter++){
			// for debugging System.out.println(counter);

			//let the user know that it is still running
			if(counter%5000 == 0){
				System.out.print(".");
			}

			//only add words that do not already exist in the list
			if(theWords.contains(pWords[counter]) == false){
				theWords.add(pWords[counter]);
			}
		}

		System.out.println();
		return theWords;



	}

	//reads strings in from a file
	public ArrayList<String> readTheFile(String fileName){

		ArrayList<String> theWords = new ArrayList<String>();

		Scanner scanner = null;

		try{
			File file = new File(fileName);
			scanner = new Scanner(file);

		}catch(FileNotFoundException filenotfound){
			//print error
			System.err.println("Where's the dictionary file, yo?");

			//end the program
			System.exit(1);

		}catch(Exception e){

			//print the error
			System.err.println("An error occured when looking for the dictionary file");

			//end the program
			System.exit(1);
		}

		//continue loop when the scanner has more to wriet
		while(scanner.hasNext() == true){
			//add eachline to the array
			String tempRead ="";
			tempRead = scanner.nextLine().trim();

			//only one word per line or print errors. make sure in proper input format
			if(tempRead.contains(" ") == true || tempRead.contains(",") == true || tempRead.contains("	") == true)
			{

				System.err.println("improper file delimitation");
				System.exit(1);
			}

			theWords.add(tempRead);
			
		}//exit the loop when there is no more to be read

		//close the stream
		scanner.close();

		return theWords;

	}



	//writes an arraylist to a file
	public void writeTheFile(ArrayList <String> sortedArray, String fileName){


		try {

			//output string
			String writeString = "";

			//output file
			File file = new File(fileName);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			//initialize proper objects
			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			//loop through the array list, adding each word to its own line
			for(int counter = 0; counter < sortedArray.size(); counter++){

				bufferedWriter.write(sortedArray.get(counter) + "\n");
			}

			//close the streams
			bufferedWriter.close();
			fileWriter.close();


			//catch any errors
		} catch (Exception e) {

			//Could not write to file
			System.err.println("error outputting file");
			System.exit(1);


		}
	}


	//comparator
	//compares to objects
	//returns < 0 if arg0 < arg1
	//returns > 0 if arg0 > arg1
	//returns 0 if arg0 == arg1
	@Override

	public int compare(Object arg0, Object arg1) {
		//initialize local variable
		int theReturn = 42;

		// for debugging System.out.println(arg0 + " " + arg1);


		//are the two objects equal?
		//yes
		if(arg0.toString().equals(arg1.toString())){
			theReturn = 0;

			//no
		}else{
			theReturn = arg0.toString().hashCode() - arg1.toString().hashCode();

		}


		return theReturn;
	}


	//etruns the string, with letters in alphabetical order
	public String sortString (String pWord){

		pWord = pWord.toLowerCase();

		//convert the strinng to a char array
		char charArray [] = new char [pWord.length()];

		for(int counter = 0; counter < pWord.length(); counter++){

			charArray[counter] = pWord.charAt(counter);
		}

		//sort the array
		Arrays.sort(charArray);

		//convert back to a string
		String tempWord = "";
		//make the array of chars back into a string
		for(int index = 0; index < charArray.length; index++){

			tempWord= tempWord + charArray[index];

		}

		//return the string
		return tempWord;

	}


}