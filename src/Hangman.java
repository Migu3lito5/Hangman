
/*
    Name : Miguel Angel Francisco
    Date : 2/20/2022
    Description: A class called hangman in which it takes an input from a scanner
          and it outputs a blank version of the input onto the console.
          The user now has to guess the blank in a certain amount of lives or else it will end up in them
          losing the game.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String temp = "";
        int found = 0;
        int lives = 4;
        int tips = 3;
        String[] userWord;
        List<String> showWord = new ArrayList<String>();
        List<String> realWord = new ArrayList<String>();
        System.out.println("-------- Welcome to Hangman --------\n");

        userWord = goodWord().split(" ");



        /*
            The nested for loop below gets the length of userWord of each index and fills the
            array list.
         */

        for(int i = 0; i < userWord.length; i++){
            if(i > 0){
                showWord.add("#");
                realWord.add("#");
            }
            for(int j = 0; j < userWord[i].length(); j++){
                showWord.add("_");
                temp = userWord[i].substring(j,j+1);
                realWord.add(temp);
            }
        }


        // while loop keeps running until user runs out of lives
        while(lives != 0){

            //this just displays the most recent version of the word that is shown to the user
            displayShowWord(showWord);
            found = 0;
            temp = "";
            System.out.println("\nYou have " + lives + " incorrect guesses left. ");
            System.out.print("Enter either 1 to guess or 2 for a hint: ");
            temp = scanner.nextLine();

            // the if() statement handles all the things that include of guessing the word
            if(temp.equals("1")){
                temp = "";
                System.out.print("Enter a letter for a guess ");
                temp = scanner.nextLine();
                temp = temp.toLowerCase();

                for(int i = 0; i < realWord.size(); i++){
                    if(realWord.get(i).equals(temp)){
                        showWord.set(i,temp);
                        found++;
                    }
                }

                if(found < 1){
                    System.out.println("The letter " + temp + " is NOT in the phrase! ");
                    lives--;
                }
            // this handles the hint system that is supposed to be implemented
            } else if(temp.equals("2")){
                temp = "";
               for(int i = 0; i < showWord.size(); i++) {
                   if(showWord.get(i).equals("_")){
                       temp = realWord.get(i);
                       break;
                   }
               }

               for(int i = 0; i < showWord.size(); i++){
                   if(realWord.get(i).equals(temp)){
                       showWord.set(i,temp);
                   }
               }

                tips--;
               lives--;

               System.out.println("OK, the hint is: " + temp + " Since you used a hint you only have " + lives + " guesses");

            // this else just handles any input that isn't a 1 or 2
            } else{
                System.out.println("Not valid input. Please try again ");
            }
            // if the word that displays on the console equals the real word it stops the while loop
            // and outputs a winning message
            if(realWord.equals(showWord)){
                System.out.print("\nCongratulations you won. The word was ");
                displayRealWord(realWord);
                lives = 0;
            }


        }

        // this only runs if user never guessed the word while inside the while loop
        if(!realWord.equals(showWord)){
            System.out.print("You failed, the word was ");
            displayRealWord(realWord);
        }

    }

    /*
        A static method that checks that the word inputted is at least > 3.
        If the word isn't greater than 3 it will ask the user to resubmit another word.
     */

    public static String goodWord(){
        boolean endLoop = false;
        String word = "";
        Scanner scanner = new Scanner(System.in);

        while(!endLoop){
            System.out.println("Enter a word: ");

            word = scanner.nextLine();
            word = word.toLowerCase();

            if(word.length() >= 3){
                endLoop = true;
            } else {
                System.out.println("Word is too short, please make it atleast 4 characters long ");
            }

        }

        return word;

    }

    //displays the unknown word to the player
    public static void displayShowWord(List<String> str){

        System.out.print("\nSo far the word is: ");
        for (String s : str) {
            System.out.print(s);
        }
    }
// displays the word given by the user
    public static void displayRealWord(List<String> str){

        for (String s : str) {
            System.out.print(s);
        }
    }

}

