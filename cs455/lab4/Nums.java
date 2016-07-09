
/**
   Stores a sequence of integer data values and supports some computations
   with it.

   CS 455 Lab 4.
*/
import java.util.ArrayList;
  
public class Nums {
    /**
       Create an empty sequence of nums.
     */
    private ArrayList<Integer> nums; 
    public Nums () {
	nums = new ArrayList<Integer>();
    }

    /**
       Add a value to the end of the sequence.
     */
    public void add(int value) {
	nums.add(value);
    }


    /**
       Return the minimum value in the sequence.
       If the sequence is empty, returns Integer.MAX_VALUE
     */
    public int minVal() {
	if (nums.size() > 0)
	{
	  int minValue = nums.get(0);
	  for (int i = 1; i < nums.size(); i++)
	  {
	     if (minValue > nums.get(i))
	     {
	        minValue = nums.get(i);
             }
	  }
	  return minValue;
	}
	else
	{
	  return Integer.MAX_VALUE;   // stub code to get it to compile
	}
    }

    /**
       Prints out the sequence of values as a space-separated list 
       on one line surrounded by parentheses.
       Does not print a newline.
       E.g., "(3 7 4 10 2 7)", for empty sequence: "()"
    */
    public void printVals() {
	if (nums.size() > 0)
	{	
	  System.out.print("(");
	  for (int j = 0; j < nums.size()-1; j++)
	  {
	    System.out.print(nums.get(j) + " ");
	  }
	  System.out.print(nums.get(nums.size()-1) + ")");
	}
	else
	{
	  System.out.print("()");
	}
    }

    /**
       Returns a new Nums object with all the values from this Nums
       object that are above the given threshold.  The values in the
       new object are in the same order as in this one.
       E.g.: call to myNums.valuesGT(10) where myNums = (3 7 19 4 21 19 10)
             returns      (19 21 19)
             myNums after call:  (3 7 19 4 21 19 10)
       This method does not modify the object the method is called on.
     */
    public Nums valuesGT(int threshold) {
    
	Nums myNums = new Nums();
	for (int i = 0; i < nums.size(); i++)
	{
	  if (nums.get(i) > threshold)
	  {
	     myNums.add(nums.get(i));
	  } 
	}

 	return myNums;  // stub code to get it to compile

    }   
}
