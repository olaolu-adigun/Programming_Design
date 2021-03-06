// Name: ADIGUN OLAOLUWA 
// USC loginid: adigun
// CSCI455 PA2
// Spring 2016

/*
   class SolitaireBoard
   The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
   by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
   for CARD_TOTAL that result in a game that terminates.
   (See comments below next to named constant declarations for more details on this.)
 */

import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class SolitaireBoard {
   
    public static final int NUM_FINAL_PILES = 9;
    // number of piles in a final configuration
    // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
    public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
    // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
    // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
    // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES
   
    /**
      Representation Invariant:
      --- The numofPiles can only assume values from 1 to CARD_TOTAL.
      --- The value of cardPiles[0] to cardPiles[numOfPiles - 1] can only assume a value from 1 to CARD_TOTAL. 
      --- The sum of values in cardPiles[0] to cardPiles[numOfPiles - 1] must always sum to CARD_TOTAL.
      --- The value of numOfPiles is the length of partially filled array cardPiles.
      --- The maximum possible number of partially filled positions in cardPiles array is CARD_TOTAL
    */
     
    private int[] cardPiles;
    private int numOfCardPiles;
    
    /**
     Creates a solitaire board with the given configuration from the user.
     @param numberString is an input of type String
     PRE: numString contains integers only
     PRE: The elements in numString are space-seprated
     PRE: The numberString contains integers in the range of 1 to CARD_TOTAL
     PRE: The sum of integers in numberString must be equal to CARD_TOTAL
     PRE: The number of integers in numberString cannot exceed CARD_TOTAL
   */
    public SolitaireBoard(String numberString) {
   
       assert isValidConfigString(numberString); // assert that the numberString input is a valid configuration
       this.cardPiles = new int [CARD_TOTAL];
       
       //Read the numbers in numberString into the cardPiles array	
       Scanner in = new Scanner(numberString);
       int i = 0;
       while (in.hasNextInt())
       {
	 this.cardPiles[i] = in.nextInt();
	 i++;
       }

       this.numOfCardPiles = i;
       assert isValidSolitaireBoard();  // assert that the state of the board is valid 
    }
 
    /**
      Creates a solitaire board with random initial configuration. The default constructor randomly generates initial configuration for Solitaire board.
    */
    public SolitaireBoard() {
 
      int value  = 0;
      int total  = 0;
      int i      = 0;
      Random gen = new Random();
      
      this. cardPiles = new int [CARD_TOTAL];
      while( total < CARD_TOTAL) 
      {
	 value = gen.nextInt(CARD_TOTAL) + 1; // Generate random number between from the range 1 to CARD_TOTAL
	
	 // Add the generated value to cardPiles if its addition to the total does not exceed CARD_TOTAL
	 if ((total + value) < (CARD_TOTAL + 1))
	 {
	    this.cardPiles[i] = value;
	    total = total + value;
	    i++;
	 }   
      }
      this.numOfCardPiles = i;
      assert isValidSolitaireBoard(); // assert that the state of the board is valid 
    }
  
    /**
      Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
      of Bulgarian solitaire. Takes one card from each pile, and puts them all together in a new pile.
      The old piles that are left will be in the same relative order as before, 
      and the new pile will be at the end.
    */
    public void playRound() {

      int newPile = numOfCardPiles;
      int j = 0;

      // Remove a card from each pile   	
      for (int i = 0; i < numOfCardPiles ; i++)
      {
	cardPiles[j] = cardPiles[i] - 1; // Update the pile
	if ((cardPiles[j] == 0))
	{
          j--;	
	}
	j++;
      }
      cardPiles[j] = newPile;
      
      // Remove the empty pile if any pile is 0 now
      for( int i = j+1; i < numOfCardPiles; i++)
      {
	cardPiles[i] = 0;
      }	
      numOfCardPiles = j+1;
      assert isValidSolitaireBoard();// assert that the state of the board is valid 
   }
   
   /**
      Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
      piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
      @return a boolean value that indicates if the current board contains all values 1,2,3,....., NUM_FINAL_PILES
    */
   public boolean isDone() {

      if ( numOfCardPiles != NUM_FINAL_PILES)
      {
	return false;
      }
      
      // Use the unfilled part of cardPiles to test for any duplicate if there is any duplicate when numOfCards == NUM_FINAL_PILES
      // No duplicate means done.
      boolean duplicate = false;
      for (int i = 0; i < NUM_FINAL_PILES ; i++)
      {
	if (cardPiles[i] <= NUM_FINAL_PILES)
	{	
	  cardPiles[cardPiles[i] + NUM_FINAL_PILES - 1]++; 
        }
      }
   
      // Clear the part of cardPiles used for checking
      for (int i = 0; i < NUM_FINAL_PILES ; i++)
      {
	if (cardPiles[i + NUM_FINAL_PILES] != 1)
	{
	  duplicate = true;
	}
	cardPiles[i + NUM_FINAL_PILES] = 0;
      }
      assert isValidSolitaireBoard(); //assert that the state of the board is valid 
      return !duplicate;
   }

   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
      @return boardString of type String. It contains the size of each piles on the current board configuration
    */
   public String configString() {
      
      //Convert array of current configuration to String
      String boardString = convertToString(cardPiles, numOfCardPiles); // Use helper function for conversion
      assert isValidSolitaireBoard();
      return boardString;   
   }   

   /**
      Returns true iff configString is a space-separated list of numbers that
      is a valid Bulgarian solitaire board with card total SolitaireBoard.CARD_TOTAL
      @param configString of type String. Contains values for each pile
      @return a boolean that indicates if configString is a valid board configuration 
   */
   public static boolean isValidConfigString(String configString) {
      
      boolean status = isValidConfiguration(configString); // Use helper function to test for validity
      return status;  
   }

   /**
      This method returns true iff the solitaire board data is in a valid state
      (See representation invariant comment for more details.)
      @return a boolean that indicates if the current board configuration is valid  
    */
   private boolean isValidSolitaireBoard() {
   
      //Convert the partially filled array to a String 		
      String string = convertToString(cardPiles, numOfCardPiles); // Use helper function for conversion
      return isValidConfiguration(string);// Use helper function to test for validity
   }
   
   // <add any additional private methods here>
   /* Checks whether the string is a valid input.
      @param theString of type String
      @return a boolean that indicates if theString is a valid board configuration.
   */
   private static boolean isValidConfiguration (String theString){
	
       theString = theString.trim(); // Remove white spaces before and after the String
       int i = 0;
       while (i < theString.length())
       {
	  // Check that it contains only integers and white spaces separating numbers
	  if (!(Character.isDigit(theString.charAt(i))) && !(Character.isWhitespace(theString.charAt(i))))
	  {
	    return false;
	  }
	  i++;	   
       }
       return isValidContent(theString);
    }

   /*
      Checks if the integers in theString are valid and if the integers sum up to CARD_TOTAL
      @param theString of type String. It contains number
      @return true of type boolean if the content of the theString is a valid board confguration  
   */
    private static boolean isValidContent (String theString){
	 
       Scanner in = new Scanner(theString);
       int total = 0;
       int size  = 0;
       int value = 0;
         
       // Check that theString has valid integers and valid size of integers 
       while(in.hasNextInt())
       {
	  value = in.nextInt();
	  total = total + value;
	  size++;
	  if ((value < 1)|| (size > CARD_TOTAL) || (value > CARD_TOTAL))
	  {
	    return false;
	  }
       }
       // Check the total number of cards	
       if (total != CARD_TOTAL)
       {
	 return false;
       }
       return true;
    }

   /*
     Converts an array into a String with the elements separated by space
     @param arrOfNum of type int array
     @return an output String containing the elements in arrOfNum
   */
   private static String convertToString(int[] arrOfNum, int numOfElements){
   
      String output = "";
      for (int i = 0 ; i < numOfElements ; i++)
      {
	output = output + arrOfNum[i] + " ";
      }
      return output;
   }
}
