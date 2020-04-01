/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {


    private HangmanLexicon lex = new HangmanLexicon();
    private RandomGenerator rgen = new RandomGenerator();
    private String secretWord;
    private String guessUpdate;
    public int guessCtr = 8;

    private HangmanCanvas canvas;

    public void init(){
        canvas = new HangmanCanvas();
        add(canvas);
    }

    public void run()
    {
		/* You fill this in */
       setupGame();
       canvas.reset();
       canvas.displayWord(guessUpdate);
       playGame();
	}

    private void setupGame()
    {
        println("Welcome to Hangman!");
        secretWord = chosenWord();
        guessUpdate =  dashes();
        guessLeft();

    }

    private void playGame()
    {
        while(true)
        {
            // read input from user
            String guess = readLine("Your guess: ");
            guess = guess.toUpperCase();

            while (validateInput(guess) == false) {
                guess = readLine("Your guess: ");
                guess = guess.toUpperCase();
            }
            checkLetter(guess);
            //not at the end of the game
            if(decideWinLoss() == false)
            {
                wordUpdate();
                if(guessCtr != 1)
                {
                    guessLeft();
                }else if(guessCtr == 1)
                {
                    println("You have one guess left");
                }


            }
            //reached end of game
            else
            {
                break;
            }


        }


    }
    /** method to decide loss or win*/
    private boolean decideWinLoss()
    {
        if(guessCtr == 0)
        {
            println("You are completely hung");
            println("The word was " + secretWord);
            println("You lose.");
            return true;
        }
        else if(guessUpdate.equals(secretWord))
        {
            println("You guessed the word " + secretWord);
            println("You win.");
            return true;
        }
        return false;
    }

    /** method to get secret word from Hangman class*/
    private String chosenWord()
    {
        int index = rgen.nextInt(0, lex.getWordCount() -1);
        return lex.getWord(index);
    }

    /** method to validate user input
     * return true if input is valid
     * otherwise written false
     * */
    private boolean validateInput(String guess)
    {
        //return false if user entered a string
        if(guess.length() > 1)
        {
            return false;
        }
        //return false if user entered number
        else if(Character.isDigit(guess.charAt(0)))
        {
            return false;
        }
        return true;

    }

    /** method to check if user input exists in secret word and update guessUpdate*/
    private void checkLetter(String guess)
    {
        //user input not found in the string of secretWord
        if(!secretWord.contains(guess))
        {
            guessCtr--;
            println("There are no " + guess + "'s in the word.");
            canvas.noteIncorrectGuess(guess.charAt(0));

        }
        //user input found in secretWord
        else{
            //let user know the guess is correct
            println("Your guess is correct.");
            //check if the input(guess) exists anywhere in secretword
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == guess.charAt(0) && i != 0) {
                        /*//find index of guess character in secret word
                        int guessPos = secretWord.indexOf(guess);*/
                    //replace dash with guess and update result
                    guessUpdate = guessUpdate.substring(0, i) + guess + guessUpdate.substring(i + 1);
                }
                else if(secretWord.charAt(i) == guess.charAt(0) && i == 0)
                {
                    guessUpdate = guess + guessUpdate.substring(i + 1);
                }
            }
            canvas.displayWord(guessUpdate);


        }
    }

    private void wordUpdate(){
        println("The word now looks like this: " + guessUpdate);
    }

    private String dashes()
    {
        String result = "";
        for(int i=0; i< secretWord.length(); i++)
        {
            result =  result + "-";
        }
        println("The word looks like this: " + result);
        return result;
    }

    /** method to keep track of the number of guesses left*/
    private void guessLeft()
    {
        println("You have " + guessCtr + " guesses left");

    }

}
