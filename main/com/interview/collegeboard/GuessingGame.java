package com.interview.collegeboard;

import java.util.Scanner;

public class GuessingGame {

	public  int lowerValue = 0;
	//initial upperValue.
	public  int upperValue = 100;
	// a threshold value for number of times
	//user can type higher. if user types 'higher'
	//three consecutive time, upperValue variable will be doubled
	public  int higherThreshold = 3;		
	public  boolean isGameEnd = false;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GuessingGame guessingGame= new GuessingGame();
		guessingGame.play();
	}


	/*
	 * this play method takes input from user
	 */
	public void play() {
		
		//take input from user
		Scanner scanner = new Scanner(System.in);
		//initializing computerGuess variable
		int computerGuess = 0;
		//game sarted flag
		//this variable will prevent user input like 'lower' or 'higher'
		//before even the game started
		boolean isGameStarted = false;
		
		//printing basic information
		printInitialInfo();

		//this while loop will keep running, unless user hits 'yes' or 'end'
		while (!isGameEnd) {

			String input = scanner.next();
			switch (input.trim().toLowerCase()) {

			case "yes":
				isGameEnd = true;
				print("Goodbye!");
				break;
				
			case "end":
				isGameEnd = true;
				print("Goodbye!");
				break;

			case "ready" :
				isGameStarted = true;
				// Compuer Generates a number
				computerGuess = getComputerGuess();
				print("is the number " + computerGuess + "?");
				break;

			case "higher":
				//only execute code if the game has already begun
				if (isGameStarted) {
					// print("higher");non
					//setting new upper and lower value based on user input
					setRange("higher", computerGuess);
					//get the new computer guessed number
					computerGuess = getComputerGuess();
					print("is the number " + computerGuess+ "?");
				} else {
					printInitialInfo();
				}
				break;			
			case "lower":
				if (isGameStarted) {
					// print("lower");
					//setting new upper and lower value based on user input
					setRange("lower", computerGuess);
					//get the new computer guessed number
					computerGuess = getComputerGuess();
					print("is the number " + computerGuess+ "?");
				} else {
					printInitialInfo();
				}
				break;
			default:
				if (isGameStarted) {
					wrongUserInput();
				}else
				{
					printInitialInfo();
				}
				
				break;
			}
		}

		scanner.close();

	}

	/*
	 * Print initial information. 
	 */
	public void printInitialInfo() {
		print("Type 'Ready' to Begin The Game, and Guess a Non-Negative number!");
	}

	/*
	 * printing wrong user input
	 */
	public  void wrongUserInput() {
		print("please enter 'higher' or 'lower' or 'end' or 'yes'");
	}
	
	/*
	 * setting up new upper and lower value based on user input
	 * if our current range is [0,100], computer guess is 50. If user says 'higher'; we set the new range [50,100]
	 * if our current range is [50,100], computer guess is 75. If user says 'lower'; we set the new range [50,75]
	 */
	public  void setRange(String userAnswer, int computerGuess) {
		
		if ("higher".equals(userAnswer.toLowerCase().trim())) {

			lowerValue = computerGuess;

			higherThreshold--;
			//after three consecutive higher input, we assume User's guess is above our upper limit;
			//in this scenario we double our upperLimitl;
			if (higherThreshold == 0) {
				higherThreshold = 3;
				upperValue = upperValue * 2;
			}
		}

		if ("lower".equals(userAnswer.toLowerCase().trim())) {
			//on selection of 'lower', we reset higherThreshold value.
			higherThreshold = 3;
			upperValue = computerGuess;
		}
		//print("lower value:  upper value  " + lowerValue + " "  + upperValue);

	}

	/*
	 * Generating computer guess value. 
	 */
	public  int getComputerGuess() {
		return (upperValue + lowerValue) / 2;
	}

	public  void print(String string) {
		System.out.println(string);
	}

}
