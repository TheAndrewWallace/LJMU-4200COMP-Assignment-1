package coursework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SeatCalculator {

	public static void main(String[] args) throws FileNotFoundException {
		// Final constant as this will not change
		final double defaultDiscount = 20;
		
		// Declaring variables
		String option1 = "";
		String seatType;
		boolean validInput = false;
		int bookings;
		double option2 = 0;
		double seatPrice;
		double deductedMoney;
		double individualIncome;
		double totalDeducted = 0;
		double totalIncome = 0;
		
		// Section 1: Get input from user
		
		// Scan to get input from console from user
		Scanner scan = new Scanner(System.in);
		
		//Reads out title
		System.out.print("--Seat Discount System--\n\nSpecify Custom Discount Rate? [Y|N]: ");
		
		//Repeats loop until valid option entered by user. Accepts both upper and lower case.
		while(!(option1.equals("Y")) && !(option1.equals("N")))
		{
			option1 = scan.next();
			
			// Switch statement for menu. Will accept upper and lower case.
			switch (option1)
			{
			// Case for "Y" or "y". No break in "y" so will lead on to "Y".
			case "y":
				// Changes lower case to upper case so while statement will work.
				option1 = "Y";
			case "Y":
				System.out.print("Please enter custom discount rate: ");
				
				// While statement repeats until valid input is received.
				while(validInput == false)
				{
					// if statement will run if the input entered is a double.
					if (scan.hasNextDouble())
					{
						option2 = scan.nextDouble();
						// if statement checks if the option is between 0-100 inclusive.
						if(option2 >= 0 && option2 <= 100)
						{
							// If it is valid it changes boolean so the while loop will not repeat.
							validInput = true;
						}
						
						// if number entered was not between 0-100 it will print appropriate error message.
						else
						{
							System.out.println("Error: Number is out of 0-100 range. Please Enter valid number: ");
							
						}
								
					}
					
					// if input entered was not a number it will print a appropriate error message.
					else
					{
						System.out.print("Error: Invalid Input. Please enter valid number: ");
						// Moves the scan to the next line so user above hasNextDouble() will work correctly
						scan.nextLine();
						scan.nextLine();
						
					}
					
				}
				break;
				
			// case for "n" without break so it will lead on to case "N" to make menu not case sensitive.
			case "n":
				// Changes lower case to upper case so while statement will still work.
				option1 = "N";
			case "N":
				// Assumes discount rate is default and prints information to user.
				System.out.println("Assuming discount rate = 20.0%");
				option2 = defaultDiscount;
				break;
				
			//If other cases not valid is will output error message and repeat loop.
			default:
				System.out.print("Error: Invalid option selected. Please select [Y|N]: ");
				break;
				
			}
			
			//Divides before loop so it is not repeated needlessly
			option2 = (option2/100);
			
			//Prints empty line for clarity
			System.out.println();
		}
		
		//Section 2: Calculation and simultaneous output.
		
		
		// Reads and scans file
		FileReader file = new FileReader("seats.txt");
		Scanner fileScan = new Scanner(file);
		
		// While not at End of File it will continue.
		while (fileScan.hasNext())
		{
			seatType = fileScan.nextLine();
			seatPrice = fileScan.nextDouble();
			bookings = fileScan.nextInt();
			
			// Deals with empty lines between data if not at end of file
			if (fileScan.hasNext())
			{
				fileScan.nextLine();
				fileScan.nextLine();
			}
			
			// Calculation of discount and income.
			deductedMoney = option2 * seatPrice * bookings;
			individualIncome = (seatPrice * bookings) - deductedMoney;
			
			// Adds discount and income to total.
			totalDeducted += deductedMoney;
			totalIncome += individualIncome;
			
			//Prints individual statistics for each loop
			System.out.printf("Seat Type: %s, Seat Price: £%.2f, Bookings: %d, Discount: £%.2f, Income: £%.2f\n", seatType, seatPrice, bookings, deductedMoney, individualIncome);

		}
		
		//Prints total income and discount
		System.out.printf("\nTotal Income: £%.2f\nTotal Discount: £%.2f", totalIncome, totalDeducted);
		
		//Closes the scanners
		scan.close();
		fileScan.close();
		
	}

}