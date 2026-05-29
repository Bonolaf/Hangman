import java.util.Scanner;
import java.util.Vector;
import java.util.Random;

public class App {
    public static void main(String[] args){
        // init
        Database d = new Database();    // to use database functions in Database.java
        Fonctions f = new Fonctions();  // to use functions in Fonctions.java
        int wrong_attempt =  0, score = 0;
        String word_list_file = "D:\\ESAIP\\ING2 25 - 26\\Projet de developpement\\Hangman (VScode)\\Hangman (Project)\\src\\main\\resources\\Words.txt";
        Vector<String> mot_list = f.create_mot_list(word_list_file);   // create mot_list
        Random r = new Random();
        int random_int = r.nextInt(mot_list.size());   // choose a random word
        Vector<Character> words_already_guessed = new Vector<>();
        Scanner myObj = new Scanner(System.in);
        
        // Demander le Nom du joueur
        System.out.println("Enter username"); 
        String username = myObj.nextLine();

        // Choisit le mot
        String word = mot_list.get(random_int);
        // System.out.println("The word: " + word);

        // Create and print hidden word
        Character[] hidden_word = new Character[word.length()]; 
        for(int i = 0; i < word.length(); i++){
            hidden_word[i] = '_';   // for example: the word: music --> the hidden word: _ _ _ _ _ 
        } f.aesthetic();
        
        // Play
        while(true){
            // always print username, wrong_attempt, the hidden word and hangman at the start of every wrong_attempt
            System.out.println("Username: " + username);
            System.out.println("Wrong attempt(s): " + wrong_attempt);
            f.draw_hidden_word(hidden_word);
            f.draw_Hangman(wrong_attempt);

            // Lose condition: wrong_attempt == 8
            if(wrong_attempt == 8){
                f.aesthetic();  // just to make the UI more beautiful
                System.out.println("Defaite");
                break;
            }

            System.out.println("Letters already guessed: " + words_already_guessed);  // so the player knows what letter they guessed

            // let the player guess a letter
            Character letter = f.guess(myObj);
            words_already_guessed.add(letter);
            Vector<Integer> index = f.letter_index(letter, word);

            // check if a letter is inside the word
            if(f.check_letter_inside_word(letter, word)){  // if yes, replace the "_" with the "letter" inside the hidden word
                f.modify_hidden_word(hidden_word, index, letter);  
                f.draw_hidden_word(hidden_word);
            }
            else{  // if no, wrong_attempt += 1
                wrong_attempt += 1;
                f.draw_hidden_word(hidden_word); 
            } f.aesthetic();

            // Win condition: guessed the word
            if(f.guessed(hidden_word)){
                System.out.println("Victoire !");
                break;
            }
        } f.aesthetic();
        score = Math.toIntExact(Math.round(((8.0 - wrong_attempt) / 8.0) * 100));
        System.out.println(username + ": " + score);

        d.saveScore(username, score);  // save scores to database
        d.printScores();  // print top 10 from database
    }
}
