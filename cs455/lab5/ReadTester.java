import java.util.ArrayList;
import java.util.Scanner;
public class ReadTester {

    public static void main (String [] args){
    
     int sentinel = 1;
     
      Scanner in = new Scanner(System.in);

      while (sentinel < 2)
	  {
	      ArrayList<Integer> numArrList = new ArrayList<Integer>();
	      
	      // Request for Input as a string
	      System.out.print("Enter a space separated list of numbers: ");
	      String input = in.nextLine();
	      
	      Scanner num = new Scanner(input);
	      
	      // Read the nmbers into ArrayList
	      while(num.hasNextInt())
		  {
		      numArrList.add(num.nextInt());
		  }   
	      System.out.println("The numebers were: " + numArrList);
	  } 
     }

}
