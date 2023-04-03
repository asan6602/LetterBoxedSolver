# Notes
* Program according to the assignment is located at commit [9fd28e105dd5ee6f551bd3c836efa26fa4dc5911](https://github.com/asan6602/LetterBoxedSolver/tree/9fd28e105dd5ee6f551bd3c836efa26fa4dc5911).  Subsequent commits are readme / improvements.
* I don’t like that swear words are included in the solution.
Found a .txt file of bad words and used that to write a new file that does not include these words.
Copied a [program](https://www.geeksforgeeks.org/java-program-delete-certain-text-file/) to do this.  Working txt file is cleanWords.txt
  * The Letterboxed game uses the [Oxford Dictionary of English](https://twitter.com/thegridkid/status/1151191845222998016?lang=en) and filters offensive words.
* Added a zipfile of my implementation, but in Kotlin.  Created using Intellij. solverKotlin.zip
* There is a bug that causes the program to break if I try to access a list from a hashmap that has not been created because there were no valid words.  Can fix if I do null checking, in class atm.


# Working Program
Main located in: src\main\java\letterboxedsolver\game.java
* running main will prompt you to enter the problem in the ”xxx,xxx,xxx,xxx” format.
* after entering an input, your solutions will be printed along with the number of solutions and the time it took to compute the solutions.
* Output for: “ing,aht,oyc,pru”

![Merged_document](https://user-images.githubusercontent.com/110870409/229323903-3de50666-302d-416f-971b-d627ed5a9c60.png)


# Letter Boxed Summary
![Screenshot 2023-03-31 225013](https://user-images.githubusercontent.com/110870409/229262308-23d33853-8af0-41c3-b478-9f75fd6d1ec7.png)
Letter Boxed is a daily word game you can play using any web browser.  Each puzzle has 12 unique letters arranged around a box.  The goal is to connect those letters together to create words.  
Rules:
* letters only
* Words must be at least 3 words long
* Consecutive letters cannot be from the same side
* Last letter of a word becomes the first letter of the next word
* Letters can be reused


# Program Goal
To write a program that finds all valid two-word solutions, given a puzzle definition and a word list file. 


# Plan of Attack / Initial Design
* Create a method that takes in a .txt file (1 word for each line)  and returns a Hashmap<Character, ArrayList<String>> while dropping words that are 2 letters or less, containing non-letter characters, and words containing the same letter twice in a row
  * How to read through a .txt file?
    * Use BufferedReader to reader the .txt.  
      * BufferedReader contains the readLine() method which fits this use-case since the .txt file separates words by new line.
  * How to check if a string contains non-ascii letter characters?
    * Regex: String.matches("[a-zA-Z]+")
  * How to check if a word contains the same letter twice in a row?
    * Iterate through the string and check if current char equals next char
  * How to add to existing ArrayList<String>>?
    * computeIfAbsent, returns the new or old value associated with the specified key
  * Why use a Hashmap?
    * I want to sort words by first letter.  The key is the character of the first letter, while the value is an Arraylist of words beginning with the key letter.

* Create a method that takes in a string in the format of “XXX,XXX,XXX,XXX” where X are unique letters.  Converts them into a 2d ArrayList<Character> with the arraylists being a list of characters that make up a row.  Verifies that all characters are unique.
  * Why use ArrayList?
    * Arraylist has the .contains method, which I can use to easily check if a character is present in a row.
  * How to read the format?
    * Split by comma, then iterate through characters of split strings.
  * How to check that all characters are unique.
    * Use a set and add characters to the set, compare the sizes of the set to the string.

* Create a method to retrieve puzzle words that takes in a string in the format of “XXX,XXX,XXX,XXX” where X are unique letters; and returns a Hashmap<Character, ArrayList<String>> of the valid puzzle words.
  * How to check to make sure that words on the same line aren’t consecutive.
    * Iterate through the string while checking if the next character is any of the other two characters that share the row of the current character.
  * How to check which row to check against?
    * Iterate through all the rows to see which row contains the character.
  * How to check if characters of a string are present in the problem
    * Put all problem letters into an ArrayList
    * Iterate through string and use ArrayList.contains()

* Create a method to check the puzzle words to see which two word combination uses all the letters of the game.
   * Using a hashmap to organize words by first letter helps in that I can check the last letter of every word and search only through the arraylist at the corresponding character.
   * How to check that all letters are used?
   
   
# Implementation
Step 1)
Had to change maven version to 1.8 from 1.7 because I used a lambda expression.

Step 2) 
Split into two methods, one to convert into a 2d array and one to check if the input is valid.
Moved methods into their own class, creating the validator class.

Step 3)
I created two helper methods.
To deal with words that adhere to containing letters contained in the problem.
To deal with words that do not have consecutive letters belonging to the same row.

Step 4) 
In order to check if the two word solution contains all the characters of the problem, I concatenated them and then converted that string into an arraylist so I can use the .contains method.


# Key Code Snippets
* Adding to an existing or non-existing ArrayList tied to a key in a HashMap. (readText.java line 52)

![Screenshot 2023-04-02 105115](https://user-images.githubusercontent.com/110870409/229360597-88cf143d-87a2-4cd0-ab2b-faa9ae4a409a.png)

* Validating that an input contains no duplicates. (validator.java line 31-43)

![Screenshot 2023-04-02 105441](https://user-images.githubusercontent.com/110870409/229360818-70269bd9-ae3c-4710-947a-04bebd245c3a.png)

* Checking that words do not contain consecutive characters from the same row. (game.java 102-121)

![Screenshot 2023-04-02 105714](https://user-images.githubusercontent.com/110870409/229360980-63ff04f8-6fa3-4fa9-b76e-40c33239b586.png)

* Checking that a two word combination contains all the letters of the problem. (game.java 143-165)

![Screenshot 2023-04-02 105930](https://user-images.githubusercontent.com/110870409/229361173-ceb6e5b8-a349-41da-b5b2-5601206381b2.png)


# Improvements
- [X] Continue to clean words
- [X] Convert to Kotlin using intellij
- [ ] Option to solve in more words than 2
 * Another for loop
 * Most important problem here would be to check that two word solution does not solve the problem already.
- [ ] Find a txt file using the Oxford Dictionary of English and read from that one instead
 * Found a [txt](https://raw.githubusercontent.com/sujithps/Dictionary/master/Oxford%20English%20Dictionary.txt) file.
   * The format of this file is word and definition on same line.  Can use readLine, but would have to split by " " and just use the first value
 * Found an [api](https://developer.oxforddictionaries.com/), but it seems to be paywalled.
- [ ] [a*search](https://www.youtube.com/watch?v=zSlgT6j0kQU)?
- [ ] [stream/filter](https://www.geeksforgeeks.org/stream-filter-java-examples/)?
