Instruction
How to run
# NGUYEN Minh An
# IR2 2025-2026
# ------ HANGMAN ------ #

# How to set up
You just need to unzip all of the files.

For the Words.txt, you can add your own words. Just remember to seperate each word with 1 or more space (I prefer to make 1 word per line so it is easier to read).

For the Database.java, remember to change:
-- localhost to your local host (String url).
-- user to your user (String user).
-- password to your password (String password).

*** Remark**: If you don't have MySQL on your computer, you can comment the last 2 line in App.java. The code still works. You just don't have a top 10 leaderboard.

# How the code works
I created 3 files in \Hangman (Project)\src\main\java
-- App.java is where the main things happen.
-- Fonctions.java defines all functions to run the game.
-- Database.java defines all functions related to the database.

In App.java:
-- First, I initialised my variables (score, wrong_attempts, mot_list, etc.). Also I initialise 2 objects: database and fonctions to use their functions. Then, the programme will choose a random word in Words.txt and creates a hidden word which has the same length as the word. (for example: music --> _ _ _ _ _ ).
-- After the player types in their username, the game begins.
-- Each turn, the programme will print the username, the number of wrong attempts, the hidden word, the hangman and the letters have already been guessed and the player's guess.
-- If you guess a letter, the number of wrong attempts and the hangman stay the same. The letter will appear in the word instead of "_".
-- If you guess wrong, the number of wrong attempts adds 1, the hangman updates and the hidden word stay the same.
-- Finally, it will check if you had win the game. You will lose if you guess wrong 8 times.

In Database.java and Fonctions.java, I commented on each function to explain what each function does.

# How to play the game
Run the App.java file. You will see the program print stuffs on your console.

Click on the console and follow the game.

Guess 1 letter each turn. You can guess wrong 7 times. At the 8th time, you will lose.
