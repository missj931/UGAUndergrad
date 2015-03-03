import java.util.*;

public class P5_Detective {



	public static void main(String[] args) {

		//check the number of arguments
		//are there too few arguments?
		if(args.length < 3){
			System.err.println("Not enough input arguments");
			System.exit(1);
		}

		//are there not enough arguments
		if(args.length > 3){
			System.err.println("Too many input arguments");
			System.exit(1);
		}


		//initialize local variables
		String theDictionary = args[0];
		String wordOne =  args[1];
		String wordTwo =  args[2];
		Scanner scan = new Scanner(System.in);

		//read the dictionary file
		Reader theReader = new Reader();
		ArrayList <String> theWords = theReader.readTheFile(theDictionary);



		//initialize array and string place holder
		String[] words = theReader.listToArray(theWords);


		//clear the arraylist
		theWords.clear();


		for(int counter = 0; counter < words.length; counter++){

			words[counter] = theReader.sortString(words[counter]);

		}
		
		//for debugging System.out.println(words.length);

		theWords = theReader.arrayToUniqueList(words);



		//write the arraylist to the output file
		theReader.writeTheFile(theWords,"sorted_output.txt");


		//compare the two words in canoncal form
		if(theReader.compare(theReader.sortString(wordOne), theReader.sortString(wordTwo)) != 0){
			System.out.println();
			System.out.println(args[1] + " and " + args[2] + " are not anagrams of each other");
		}else{
			System.out.println();
			System.out.println(args[1] + " and " + args[2] + " are anagrams of each other");
		}

		//initialize booleans
		boolean wordOneFound = false;
		boolean wordTwoFound = false;

		//clear the word array

		//check the arraylist of words, looking for the word
		//for word one

		if(theWords.contains(theReader.sortString(wordOne)) == true){
			wordOneFound = true;
		}


		//for wordtwo
		if(theWords.contains(theReader.sortString(wordTwo)) == true){
			wordTwoFound = true;
		}


		//print out error if both words were not found in the dictionary
		if(wordOneFound == false && wordTwoFound == false){
			System.out.println();
			System.out.println(args[1] + " and " + args[2] + " were not found in the dictionary");
			
		}else if(wordOneFound == true && wordTwoFound == false){
			System.out.println();
			System.out.println(args[2] + " was not found in the dictionary");

		}else if(wordOneFound == false && wordTwoFound == true){
			System.out.println();
			System.out.println(args[1] + " was not found in the dictionary");
		}


		//if the word was not found prompt the user to add to the dictionary
		if(wordOneFound == false){
			System.out.println();
			System.out.println("Would you like to enter "+ args[1] + " canonical form to the sorted_output.txt?");
			System.out.println("Enter y for yes. all other input accepted as a no.");

			if (scan.next().equalsIgnoreCase("y")){



				//yes, add the word
				theWords.add(theReader.sortString(wordOne));
				words = theReader.listToArray(theWords);
				Arrays.sort(words);
				theWords = theReader.arrayToUniqueList(words);
				theReader.writeTheFile(theWords, "sorted_output.txt");
			}

		}

		if(wordTwoFound == false && (theReader.compare(theReader.sortString(wordOne), theReader.sortString(wordTwo)) != 0)){
			System.out.println();
			System.out.println("Would you like to enter "+ args[2] + " canonical form to the sorted_output.txt??");
			System.out.println("Enter y for yes. all other input accepted as a no.");


			if (scan.next().equalsIgnoreCase("y")){

				//yes, add the word
				theWords.add(theReader.sortString(wordTwo));
				words = theReader.listToArray(theWords);
				Arrays.sort(words);
				theWords = theReader.arrayToUniqueList(words);
				theReader.writeTheFile(theWords, "sorted_output.txt");
			}
		}


		boolean repeat = true;

		//todo repeat asking for more input

		//ask for more input
		do{

			//prompt user for input
			System.out.println("Enter two more words to continue. Enter -1 for each word to exit");
			System.out.println();
			System.out.println("Word 1: ");
			wordOne = scan.next();
			System.out.println();
			System.out.println("Word 2: ");
			wordTwo = scan.next();
			
			if(wordOne.trim().equals("-1") && wordTwo.trim().equals("-1")){
				System.out.println("goodnight sweet prince");
				System.exit(0);
			}

			//compare the two words in canoncal form
			if(theReader.compare(theReader.sortString(wordOne), theReader.sortString(wordTwo)) != 0){
				System.out.println(wordOne + " and " + wordTwo + " are not anagrams of each other");
			}else{
				System.out.println(wordOne + " and " + wordTwo + " are anagrams of each other");
			}

			//initialize booleans
			wordOneFound = false;
			wordTwoFound = false;

			//clear the word array

			//check the arraylist of words, looking for the word
			//for word one

			if(theWords.contains(theReader.sortString(wordOne)) == true){
				wordOneFound = true;
			}


			//for wordtwo
			if(theWords.contains(theReader.sortString(wordTwo)) == true){
				wordTwoFound = true;
			}


			//print out error if both words were not found in the dictionary
			if(wordOneFound == false && wordTwoFound == false){
				System.out.println(wordOne + " and " + wordTwo + " were not found in the dictionary");
				//
			}else if(wordOneFound == true && wordTwoFound == false){
				System.out.println(wordTwo + " was not found in the dictionary");

			}else if(wordOneFound == false && wordTwoFound == true){
				System.out.println(wordOne + " was not found in the dictionary");
			}


			//if the word was not found prompt the user to add to the dictionary
			if(wordOneFound == false){
				System.out.println("Would you like to enter "+ wordOne + " canonical form to the sorted_output.txt?");
				System.out.println("Enter y for yes. all other input accepted as a no.");

				if (scan.next().equalsIgnoreCase("y")){



					//yes, add the word
					theWords.add(theReader.sortString(wordOne));
					words = theReader.listToArray(theWords);
					Arrays.sort(words);
					theWords = theReader.arrayToUniqueList(words);
					theReader.writeTheFile(theWords, "sorted_output.txt");
				}

			}

			if(wordTwoFound == false && (theReader.compare(theReader.sortString(wordOne), theReader.sortString(wordTwo)) != 0)){
				System.out.println("Would you like to enter "+ wordTwo + " canonical form to the sorted_output.txt?");
				System.out.println("Enter y for yes. all other input accepted as a no.");


				if (scan.next().equalsIgnoreCase("y")){

					//yes, add the word
					theWords.add(theReader.sortString(wordTwo));
					words = theReader.listToArray(theWords);
					Arrays.sort(words);
					theWords = theReader.arrayToUniqueList(words);
					theReader.writeTheFile(theWords, "sorted_output.txt");
				}
			}



		}while(repeat == true);//repeat until no longer true;
	}

}




