// System.out.println("      ________      ");
// System.out.println("     |        |");
// System.out.println("     |        o");
// System.out.println("     |       /|\'");
// System.out.println("     |        |");
// System.out.println("     |       / \");
// System.out.println("    _|_");
// System.out.println("   |   |______________ ");
// System.out.println("   |                  |");
// System.out.println("   |                  |");
// System.out.println("   |__________________|");

import java.util.Scanner;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Fonctions {
    public Fonctions(){  // create object type Fonctions
        System.out.println("Fonctions imported");
    }

    public void aesthetic(){  // just for aesthetic
        System.out.println();
        System.out.println();
    }

    public void draw_Hangman(int wrong_attempt){   // draw the hangman based on the wrong_attempt  
        System.out.println("      ________      ");
        if(wrong_attempt >= 1){
            System.out.println("     |        |");
        }
        else{
            System.out.println("     |");
        }
        if(wrong_attempt >= 2){
            System.out.println("     |        o");
        }
        else{
            System.out.println("     |");
        }
        switch(wrong_attempt){
            case 3:
                System.out.println("     |       /");
                break;
            case 4:
                System.out.println("     |       /|");
                break;
            case 5:
                System.out.println("     |       /|\\");
                break;
            case 6:
                System.out.println("     |       /|\\");
                break;
            case 7:
                System.out.println("     |       /|\\");
                break;
            case 8:
                System.out.println("     |       /|\\");
                break;
            default:
                System.out.println("     |");
        }
        if(wrong_attempt >= 6){
            System.out.println("     |        |");
        }
        else{
            System.out.println("     |");
        }
        switch(wrong_attempt){
            case 7:
                System.out.println("     |       /");
                break;
            case 8:
                System.out.println("     |       / \\");
                break;
            default:
                System.out.println("     |");
        }
        System.out.println("    _|_");
        System.out.println("   |   |______________ ");
        System.out.println("   |                  |");
        System.out.println("   |                  |");
        System.out.println("   |__________________|");
        
    }
    
    public Character guess(Scanner myObj){  // let the player guess a letter
        System.out.print("Your guess: ");
        String line = myObj.nextLine();
        while(line.isEmpty()){
            System.out.print("Please enter a letter: ");
            line = myObj.nextLine();
        }
        return line.charAt(0);
    }

    public Boolean check_letter_inside_word(Character letter, String word){  // check if the word contains the letter
        for(int i = 0; i < word.length(); i++){
            if (word.charAt(i) == letter){
                return true;
            }
        }
        return false;
    }

    public Vector<Integer> letter_index(Character letter, String word){  // return all of the index where the letter appear
        Vector<Integer> index = new Vector<>();
        for(int i = 0; i < word.length(); i++){
            if (word.charAt(i) == letter){
                index.add(i);
            }
        }
        return index;
    }

    public void draw_hidden_word(Character[] hidden_word){  // for example the word: music --> the hidden word:  _ _ _ _ _
        for(int i = 0; i < hidden_word.length; i++){
            System.out.print(hidden_word[i] + " ");
        } System.out.println();
    }

    public void modify_hidden_word(Character[] hidden_word, Vector<Integer> index, Character letter){  // to change "_" into a letter if the player guess a letter inside the word
        for(int i = 0; i < index.size(); i++){
            hidden_word[index.get(i)] = letter;
        }
        
    }
    
    public Boolean guessed(Character[] hidden_word){  // check if the player guessed the word
        for(int i = 0; i < hidden_word.length; i++){
            if(hidden_word[i] == '_'){  // if 1 letter is missing, they didn't guess the word
                return false;
            }
        }
        return true;  // else they guessed the word
    }

    public Vector<String> create_mot_list(String fileName){   // Create the word list from Words.txt
        Vector<String> create_mot_list = new Vector<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine()) != null){
                String regex = "\\s+";
                String[] mots = line.split(regex);
                for(int i = 0; i < mots.length; i++){
                    create_mot_list.add(mots[i]);               // words in 1 line seperate by space is added to create_mot_list each iteration
                }
            }
            System.out.println("Liste de mots created");
        } catch (IOException e){
            System.err.println("Error when reading the file: " + e.getMessage());
        }
        return create_mot_list;
    }
}

