My code is an example of good code. 
It notifies the user that the system is completing
a task when reading in the dictionary file. This
can be rather large and can take quite some time.
Most people would give up on a program that fails
to respond for 3 seconds. I made sure to print out
a dot every 2000 words(about three seconds on my computer)
this can be seen as a primitive loading bar. Yay for
HCI.

My code is also clear and easy to read. The main method
is in the P5_Detective class. The methods used to 
read, write and sort are in the reader class.

The program starts by reading in the args from the
command line. The first argument must be the
dictionary file. If the dictionary file cannot 
be found, the user is prompted and the program
ends. A properly formated dictionary file
will have one word (String) per line. If 
the file is delimited by tabs, spaces,
commas, etc. it is not properly formatted
and the user is notified. The program then ends.

The two words are then compared to the dictionary
file stored as an array list.  If a word already
exists in the array list, it is not added.
This is faster than comparing the words to the dictionary file
directly.  Only words that are do not have anagrams 
in the array are added.  
The words are sorted into their canonical form before being
added to the array list.

After the words all the words
are added, the array is sorted in canonical order.
After that, the array list is written to the sorted_output.txt
file.

After comparing the second and third argument, the user
is prompted as specified in the instructions.

The user is then given the opportunity to add the words
to the output file if it does not already exist in the 
arraylist that was just written. If the word does not
exist in the output file, the user then adds it in its
canonical form to the list. The list is then sorted
and then overwrites the previous sorted_output.txt file.

If the two words are anagrams, the user is only prompted
to add the first word to the output(they are the same,
so giving the user the option to add both would be redundant)

After the initial run through, the user is asked to 
add two more words. if each word is a -1, the program ends
and exits. If not, the program coninues and loops through, comparing
the two words to eachother and then to the words in the dictionary file.
