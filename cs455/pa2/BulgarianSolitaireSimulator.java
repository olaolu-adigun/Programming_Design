// Name: ADIGUN OLAOLUWA
// USC loginid: adigun
// CSCI455 PA2
// Spring 2016


/**
  BulgarianSolitaireSimulator    
    Console-based program for simulating Bulgarian Solitaire game.
    The user can decide to manually enter a valid input or opt for 
    randomly generated input.
 */

import java.util.Scanner;

public class BulgarianSolitaireSimulator {
     /**  
	Main method for Bulgarian solitaire game
	@param args of type String array. If args contain '-u' prompt the user for
             inital configuration, else Solitaire board generates a random configuration. 
	     If args contain '-s' the game stops between every round of the game
	     and prompts the user game to type enter for the game to continue.
     */

   public static void main(String[] args) {
	
      // Read the input from the args array	     
      boolean singleStep = false;
      boolean userConfig = false;

      for (int i = 0; i < args.length; i++) {
         if (args[i].equals("-u")) {
            userConfig = true;
         }
         else if (args[i].equals("-s")) {
            singleStep = true;
         }
      }

      SolitaireBoard myGame = new SolitaireBoard();// Create the new game
      if (userConfig == true)
      {      
         String input = userChoice(); // User the helper function to get user configuration if needed
	 myGame = new SolitaireBoard(input);
      }
      System.out.println("Initial Configuration: " + myGame.configString());
      stepChoice(singleStep, myGame); 
   }

  /*
     This helper function (method) initializes the SolitaireBoard game by requesting that
     user should input configuration. 
     @param theUserConfig of type boolean gives the input choice status 
     @param newGame of type SolitaireBoard 
     @return SolitaireBoard with the desired initial configuration specified by the user
  */
  private static String userChoice ()
   {	
       // Read input from the user
       boolean done = false;
       Scanner input  = new Scanner(System.in);
       System.out.println("Number of total cards is "+ SolitaireBoard.CARD_TOTAL);
       System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile)");
        
       String in = "";
       while(!done)
       {
	  System.out.println("Please enter a space-separated list of positive integers followed by newline: ");
	  in = input.nextLine();
	  done = SolitaireBoard.isValidConfigString(in);
	  if (done == false)
	  {
	    System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be " + SolitaireBoard.CARD_TOTAL);
  	  }
      }      
      return in;
   }

/*
  This is the helper function (method) that runs the Solitaire game depending on the users.
  It either requests for the user to press enter after each step or  not.
  @param theSingleStep of type boolean gives the choice status message display 
  @param newGame of type SolitaireBoard      
*/
  private static void stepChoice (boolean theSingleStep, SolitaireBoard theGame){
     int i = 1;
     boolean done = false;

     if (theSingleStep == false)
     {
	while (!done)
	{
	  theGame.playRound();
	  System.out.println("[" + i + "] Current configuration: " + theGame.configString());
	  i++;
	  done = theGame.isDone();
 	}
     }
     else
     {
	Scanner pass = new Scanner(System.in);	
	while (!done)
	{
	  theGame.playRound();
	  System.out.println("[" + i + "] Current configuration: " + theGame.configString());
	  System.out.print("<Type return to continue>");
	  pass.nextLine();
	  i++;
	  done = theGame.isDone();
 	}
     }
     System.out.println("Done!");
   } 
}
