package cashregister;

import java.security.SecureRandom;
import java.util.Scanner;

public class JamesPotratz_HW01 {

	// CONSTANT VARIABLES
	public static double SWITCH = 0;
	public static double TAX = 0.04225;

	// DECLARED VARIABLES
	public static double priceTotal, couponTotal, overallTotal, inputValue, amountPaid, changeDue;
	public static int counter;
	public static boolean check = false;
	// Create Scanner
	public static Scanner input = new Scanner(System.in);
	public static SecureRandom randomNumbers;
	public static int randomInteger;

	// Generate Random number and complete a lottery
	public static void lottery() {
		randomNumbers = new SecureRandom();
		randomInteger = 1 + randomNumbers.nextInt(20);
		int lotteryCount = 1;
		int guess = 0;
		System.out.println("Welcome to the lottery! Please enter a number between one and twenty!");
		while (lotteryCount <= 3) {
			// Check if lottery is on last chance
			if (lotteryCount == 3) {
				System.out.println("Last Chance!");
			}
			// Try catch loop to ensure no funny business
			try {
				guess = Integer.parseInt(input.next());
				if (guess > 20) {
					System.out.println("Disqualified! You entered a number greater than 20! Naughty!");
					break;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Disqualified! You didn't enter a number! Naughty!");
				break;
			}
			// If they won, let them know
			if (guess == randomInteger) {
				System.out.println("You win at $25 Gift card!");
				break;
			}
			// If they are on the last chance and lose
			if (lotteryCount == 3 && (randomInteger > guess || randomInteger < guess)) {
				System.out.println("Better luck next time!");
				break;
			}
			// If they guessed too low
			if (randomInteger > guess) {
				System.out.println("Try again, go higher!");
				lotteryCount++;
			}
			// If they guess too high
			if (randomInteger < guess) {
				System.out.println("Try again, go lower!");
				lotteryCount++;
			}
		}
		System.out.println("Lottery Over.");
	}

	// Function to calculate the totals
	public static double parseInput() {
		inputValue = 1;
		double total = 0;
		while (inputValue != SWITCH) {
			try {
				inputValue = Double.parseDouble(input.next());
				total += inputValue;
			} catch (NumberFormatException nfe) {
				System.out.println("Please enter only #'s.");
			}
		}
		return total;
	}

	// Function to calculate and display the receipt
	public static void receipt() {
		overallTotal = (priceTotal - couponTotal) * (1 + TAX);
		System.out.printf("Item Total: $%.2f%n", priceTotal);
		System.out.printf("Coupon Total: $%.2f%n", couponTotal);
		System.out.printf("Subtotal: $%.2f%n", priceTotal - couponTotal);
		System.out.printf("Tax Percentage: %.2f%%%n", TAX * 100);
		System.out.printf("Tax Amount: $%.2f%n", overallTotal - (priceTotal - couponTotal));
		System.out.printf("Total: $%.2f%n", overallTotal);
	}

	// Function to calculate and print change
	public static void calculateChange(double changeIncrement) {
		counter = 0;
		while (changeDue >= changeIncrement || (changeDue > 0 && changeIncrement == 0.01)) {
			changeDue -= changeIncrement;
			counter += 1;
		}
	}

	// Function that prints the change
	public static void printChange() {
		System.out.println("Please give the customer the following bills/change.");
		calculateChange(20);
		System.out.println("$20.00" + " - " + String.format("%d", counter));
		calculateChange(10);
		System.out.println("$10.00" + " - " + String.format("%d", counter));
		calculateChange(5);
		System.out.println("$5.00" + " - " + String.format("%d", counter));
		calculateChange(1);
		System.out.println("$1.00" + " - " + String.format("%d", counter));
		calculateChange(0.25);
		System.out.println("$0.25" + " - " + String.format("%d", counter));
		calculateChange(0.10);
		System.out.println("$0.10" + " - " + String.format("%d", counter));
		calculateChange(0.05);
		System.out.println("$0.05" + " - " + String.format("%d", counter));
		calculateChange(0.01);
		System.out.println("$0.01" + " - " + String.format("%d", counter));
	}

	// Main function to process logic of cash register
	public static void main(String[] args) {
		// Calculate total price
		System.out.println("Enter price values, when done enter 0.");
		priceTotal = parseInput();
		// Calculate total coupon discount
		System.out.println("Enter coupon values, when done enter 0.");
		couponTotal = parseInput();
		// Display Receipt
		receipt();
		// Cash back, calculate and print
		System.out.println("Please enter the amount the customer has paid.");
		while (!check)
			try {
				amountPaid = Double.parseDouble(input.next());
				check = true;
			} catch (NumberFormatException nfe) {
				System.out.println("Please enter only #'s.");
			}
		changeDue = amountPaid - overallTotal;
		// Reduce changeDue to two decimals places, otherwise calculateChange breaks
		changeDue = Math.floor(changeDue * 100) / 100;
		System.out.printf("Change Due: $%.2f%n", changeDue);
		printChange();
		// Run the lottery
		lottery();
		// End Program
		System.out.println("-----------------------");
		System.out.println("*** END OF PROGRAM ***");
	}

}
