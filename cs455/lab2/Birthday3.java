import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Scanner;

public class Birthday3{
    public static void main(String [] args){

	// Create Scanner object for input
	Scanner in = new Scanner(System.in);

	System.out.print("Enter your birth month [1..12]: ");
	int MONTH = in.nextInt();

	System.out.print("Enter your birth day of month: ");
	int DAY_OF_MONTH = in.nextInt();

	System.out.print("Enter your birth year [4-digit year]: ");
	int YEAR = in.nextInt();
	
	// Create the birthday object
	Calendar birthday = new GregorianCalendar(YEAR,MONTH-1,DAY_OF_MONTH);
        int day_birthday = birthday.get(Calendar.DAY_OF_YEAR);
	
	// Create the object for today's date
	Calendar today = new GregorianCalendar();
         int day_today = today.get(Calendar.DAY_OF_YEAR);

	// Create the birthday this year
	Calendar thisYear = new GregorianCalendar(today.get(Calendar.YEAR),MONTH-1,DAY_OF_MONTH);
	int birthday_thisYear = thisYear.get(Calendar.DAY_OF_YEAR);
	//System.out.println(birthday_thisYear);
	//System.out.println(day_today);

	// Compute the age
	int age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
	
	// Print out the output statement
	
	if(day_today < birthday_thisYear)
	    {
		age = age - 1; 
		if (age > 0)
		    {
			System.out.println("You're " + age  + " years old.");
		    }
		else
		    {
			System.out.println("You're less than a year old");
		    }
	    }

	else
	    {
		System.out.println("You're " + age + " years old.");
	    }

    }
}
