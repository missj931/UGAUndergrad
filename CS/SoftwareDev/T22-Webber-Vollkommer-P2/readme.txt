Matthew Volkommer and Jake Webber
Hangman Project 2
CSCI 1302 (Spring 2014 UGA)


Commands for running Hangman.java properly
1) edit the makefile commands for running Hangman.java
	- 'computer' must be specified before the 'player'.
	- 'player' can be 'human', 'random', 'naive', 'cutthroat', or 'run_faster'. 
	- -L specifies to redirect all printing to an .output file.
		- Doing so will remove printing to the console, 
		so do not use -L when running human player
2) run the makefile with run
	"make run"
3) clean the .class files made when compiling the program
	"make clean"
4) to use our AI, enter the command run_faster
	"make run_faster"
...




EXTRA CREDIT (NOT YET COMPLETED, FINAL VERSION WILL BE SUBMITTED THIS WEEKEND): 
Specifying the player 'run_faster' will set the game player as our PlayFasterComputerPlayer AI.
How it works:
	- The PlayFasterComputerPlayer(int wordLength) constructor will import the dictionary.data file into a dictionary ArrayList.
		> Based on the length of the word it is trying to guess, the AI adds all words that length in dictionary into a new ArrayList called possibleWords.
		
	- char generateMove(string in_word) is a AI method that will generate a guess based on what letters have already been correctly guessed.
		> If generateMove has ran at least one time: in_word is compared to the last instance of in_word.
			If the string in_word is the same as the last time generateMove ran, then the last char generateMove returned was an incorrect letter.
				All words containing that incorrect letter are removed from possibleWords.
			If the string in_word is different as the last time generateMove ran, then the last char generateMove returned was a correct letter (and therefore added to in_word)
				All words NOT containing the correctly guessed letters of in_word in the right positions are removed from possibleWords.
		> A String[][] possibleLetters double array is used to store each letter of the alphabet and their frequencies.
			For every letter in every word in possibleWords arrayList, the frequency of that letter is incremented in possibleLetters.
			possibleLetters is then sorted from letters of highest to lowest frequency. 
		> The char guess that generateMove will make is the first letter in possibleLetters (with the highest frequency)
			If that guess has already been made, the next most frequent letter is guessed.

 Extra Credit JUnit
we teted our cases by creating testing methods which took in parameters
and return information. We made sure that the information being returned was
expected for the provided information. We ran each case seperately as we developed
the program. After completing the program, we ran all the test cases from a class
that allowed us to run all tests. the screenshot of the results from test all test cases
is included. All tests run succesfully. Our work for JUnit is included in the testing directory.
Please note that some versions of our files in the testing directory still include statements that
print out information we were looking at to see what was occuring as each test was running.

