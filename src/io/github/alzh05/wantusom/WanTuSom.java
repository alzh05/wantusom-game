package io.github.alzh05.wantusom;

import java.util.*;

public class WanTuSom {
	private static Scanner input = new Scanner(System.in);
	private static Random rand = new Random();
	private static final String[] MOVES = {"Bird", "Stone", "Water", "Fire"};
	private static int rounds = 4;
	private static int playerScore = 0;
	private static int computerScore = 0;
	private static int player1Score = 0;
	private static int player2Score = 0;

	// function to pause the execution of the current thread for a given number of seconds
	public static void pause(int seconds) {
		if (seconds < 0) {
			throw new IllegalArgumentException("Seconds cannot be negative");
		}
		
		try {
			long milliseconds = (long) seconds * 1000;  
			Thread.sleep(milliseconds);
		} 
		catch (InterruptedException e) {
			Thread.currentThread().interrupt(); // restore the interrupted status
			throw new RuntimeException("Pause was interrupted", e);
		}
	}
	
	// function to display the home menu, which prompts user to select either play game, view game rules, or quit game
	public static void displayHomeMenu() {
		System.out.println("\nW a n T u S o m!");
		System.out.println("1. Play");
		System.out.println("2. Rules");
		System.out.println("3. Quit Game");
		System.out.print("Select an option: ");
		
		int choice = 0;
		
		do {
			choice = input.nextInt();
			input.nextLine();
			
			if (choice == 1) {
				displayGameModeMenu();
				break;
			}
			else if (choice == 2) {
				displayRules();
				break;
			}
			else if (choice == 3) {
				System.out.println("You have quit the game.");
				System.exit(0);
				break;
			}
			else {
				System.out.print("Invalid input. Please enter a valid number: ");
			}
		} while (choice < 1 || choice > 3);
	}
	
	// function to display the game rules
	public static void displayRules() {
		System.out.println("\n{GAME RULES}");
		System.out.println("1. Win conditions:");
		System.out.println("   - [Bird] beats [Water]");
		System.out.println("   - [Stone] beats [Bird]");
		System.out.println("   - [Water] beats [Stone]");
		System.out.println("   - [Fire] defeats all");
		System.out.println("2. Match structure:");
		System.out.println("   - One match consists of 4 rounds.");
		System.out.println("   - If scores are tied after 4 rounds, sudden death round begins.");
		System.out.println("3. Restrictions:");
		System.out.println("   - Each player can use [Fire] only twice per match.");
		System.out.println("   - [Fire] is banned in sudden death round.");
		System.out.print("\nEnter 0 to return to home menu: ");
		
		int back = 0;
		
		do {
			back = input.nextInt();
			input.nextLine();
			
			if (back == 0) {
				displayHomeMenu();
				break;
			} 
			else {
				System.out.print("Invalid input. Please enter 0 to return to home menu: ");
			}
		} while (back != 0);
	}
	
	// function to display the game mode selection menu, which prompts user to select either PvC or PvP mode
	public static void displayGameModeMenu() {
		System.out.println("\n{GAME MODES}");
		System.out.println("1. Player vs Computer (PvC)");
		System.out.println("2. Player vs Player (PvP)");
		System.out.println("<Enter 0 to return to home menu>");				
		System.out.print("Select a mode: ");
		
		int mode = 0;
		
		do {
			mode = input.nextInt();
			input.nextLine();
			
			if (mode == 0) {
				displayHomeMenu();
				break;
			} 
			else if (mode == 1) {
				System.out.println("Loading...");
				pause(1);
				System.out.println("Match found.");
				pause(1);
				System.out.println("Match starts in:");
				System.out.println("       3");
				pause(1);
				System.out.println("       2");
				pause(1);
				System.out.println("       1");		
				displayMatchPvC();
				break;
			}	
			else if (mode == 2) {
				System.out.println("Loading...");
				pause(1);
				System.out.println("Match found.");
				pause(1);
				System.out.println("Match starts in:");
				System.out.println("       3");
				pause(1);
				System.out.println("       2");
				pause(1);
				System.out.println("       1");	
				displayMatchPvP();
				break;
			}	
			else {
				System.out.print("Invalid input. Please enter a valid number: ");
			}
		} while (mode < 0 || mode > 2);
	}
	
	// function to display a PvC match (4 rounds)
	public static void displayMatchPvC() {
		rounds = 4;
		playerScore = 0;
		computerScore = 0;
		WanTuSom p = new WanTuSom();
		int playerMove = 0;		
		int computerMove = 0;		
		int playerFireCount = 2;
		int computerFireCount = 2;
		
		while (rounds > 0) {
			pause(1);
			System.out.println("\n{ROUND #" + (5 - rounds) + "}");
			
			// player's turn
			pause(1);
			System.out.println("Player's Turn:");
			p.displayMovesList();	
			System.out.println("(Available uses: " + playerFireCount + ")");	
			System.out.println("<Enter 0 to return to gamemode menu>");
			System.out.print("Select a move: ");            
			
			do {
				do {
					playerMove = input.nextInt();
					input.nextLine();
					
					if (playerMove == 0) {
						System.out.println("Returning to gamemode menu...");
						pause(1);
						displayGameModeMenu();
						break;
					}
					else if (playerMove < 0 || playerMove > 4) {
						System.out.print("Invalid input. Please enter a valid number: ");
					}
				} while (playerMove < 0 || playerMove > 4);
				
				if (playerMove > 0 && playerMove < 4) {
					System.out.println("Player chose " + displayMove(playerMove) + ".");
					break;
				}
				else if (playerMove == 4 && playerFireCount > 0) {
					playerFireCount--;
					System.out.println("Player chose [Fire]. (Remaining uses: " + playerFireCount + ")");
					break;
				}
				else if (playerMove == 4 && playerFireCount == 0) {
					System.out.print("0 remaining uses for [Fire]. Please select another move: ");
				}
			} while (playerMove == 4 && playerFireCount == 0);
				  
			// computer's turn
			pause(1);
			System.out.println("\nComputer's Turn:");
			
			if (computerFireCount > 0) {
				computerMove = rand.nextInt(4) + 1; // bird, stone, water, or fire
			} 
			else if (computerFireCount == 0) {
				computerMove = rand.nextInt(3) + 1; // bird, stone, or water
			}
			
			if (computerMove > 0 && computerMove < 4) {
				pause(1);
				System.out.println("Computer chose " + displayMove(computerMove) + ".");
			}		
			else if (computerMove == 4) {
				pause(1);
				System.out.println("Computer chose [Fire].");
				computerFireCount--;
			}
			
			pause(1);
			announceResultPvC(playerMove, computerMove);
			pause(1);
			displayScoreCounterPvC();
			
			rounds--;
		}
		
		if (playerScore == computerScore) {
			pause(1);
			System.out.println("\nIt's a tie after 4 rounds.");
			pause(1);
			System.out.println("Initiating sudden death round...");
			displaySuddenDeathRoundPvC();
		}
		else {
			pause(1);
			announceWinnerPvC();              
		}
	}
		
	// function to display a PvP match (4 rounds)
	public static void displayMatchPvP() {
		rounds = 4;
		player1Score = 0;
		player2Score = 0;
		WanTuSom p1 = new WanTuSom();
		WanTuSom p2 = new WanTuSom();
		int player1Move = 0;
		int player2Move = 0;
		int player1FireCount = 2;
		int player2FireCount = 2;
		
		while (rounds > 0) {
			pause(1);
			System.out.println("\n{ROUND #" + (5 - rounds) + "}");
			
			// player1's turn
			pause(1);
			System.out.println("Player1's Turn:");
			p1.displayMovesList();	
			System.out.println("(Available uses: " + player1FireCount + ")");	
			System.out.println("<Enter 0 to return to gamemode menu>");
			System.out.print("Select a move: ");
			
			do {
				do {
					player1Move = input.nextInt();
					input.nextLine();
					
					if (player1Move == 0) {
						System.out.println("Returning to gamemode menu...");
						pause(1);
						displayGameModeMenu();
						break;
					}
					else if (player1Move < 0 || player1Move > 4) {
						System.out.print("Invalid input. Please enter a valid number: ");
					}
				} while (player1Move < 0 || player1Move > 4);
				
				if (player1Move > 0 && player1Move < 4) {
					System.out.println("Player1 chose " + displayMove(player1Move) + ".");
					break;
				}
				else if (player1Move == 4 && player1FireCount > 0) {
					player1FireCount--;
					System.out.println("Player1 chose [Fire]. (Remaining uses: " + player1FireCount + ")");
					break;
				}
				else if (player1Move == 4 && player1FireCount == 0) {
					System.out.print("0 remaining uses for [Fire]. Please select another move: ");
				}
			} while (player1Move == 4 && player1FireCount == 0);
			
			// player2's turn
			pause(1);
			System.out.println("\nPlayer2's Turn:");
			p2.displayMovesList();		
			System.out.println("(Available uses: " + player2FireCount + ")");		
			System.out.println("<Enter 0 to return to gamemode menu>");
			System.out.print("Select a move: ");
			
			do {
				do {
					player2Move = input.nextInt();
					input.nextLine();
					
					if (player2Move == 0) {
						System.out.println("Returning to gamemode menu...");
						pause(1);
						displayGameModeMenu();
						break;
					}
					else if (player2Move < 0 || player2Move > 4) {
						System.out.print("Invalid input. Please enter a valid number: ");
					}
				} while (player2Move < 0 || player2Move > 4);
				
				if (player2Move > 0 && player2Move < 4) {
					System.out.println("Player2 chose " + displayMove(player2Move) + ".");
					break;
				}	
				else if (player2Move == 4 && player2FireCount > 0) {
					player2FireCount--;
					System.out.println("Player2 chose [Fire]. (Remaining uses: " + player2FireCount + ")");
					break;
				}		
				else if (player2Move == 4 && player2FireCount == 0) {
					System.out.print("0 remaining uses for [Fire]. Please select another move: ");
				}
			} while (player2Move == 4 && player2FireCount == 0);
			
			pause(1);
			announceResultPvP(player1Move, player2Move);
			pause(1);
			displayScoreCounterPvP();
			
			rounds--;
		}
		
		if (player1Score == player2Score) {
			pause(1);
			System.out.println("\nIt's a tie after 4 rounds.");
			pause(1);
			System.out.println("Initiating sudden death round...");	
			displaySuddenDeathRoundPvP();
		}
		else {
			pause(1);
			announceWinnerPvP();
		}
	}
		
	// function to display the sudden death round in the PvC match
	public static void displaySuddenDeathRoundPvC() {
		WanTuSom p = new WanTuSom();
		int playerMove = 0;
		int computerMove = 0;
		
		do {		
			pause(1);
			System.out.println("\n{SUDDEN DEATH ROUND}");
	
			// player's turn
			pause(1);
			System.out.println("Player's Turn:");
			p.displayMovesList();		
			System.out.println("(Banned)");		
			System.out.println("<Enter 0 to return to gamemode menu>");
			System.out.print("Select a move: ");
			
			do {
				do {
					playerMove = input.nextInt();
					input.nextLine();
					
					if (playerMove == 0) {
						System.out.println("Returning to gamemode menu...");
						pause(1);
						displayGameModeMenu();
						break;
					}
					else if (playerMove < 0 || playerMove > 4) {
						System.out.print("Invalid input. Please enter a valid number: ");
					}
				} while (playerMove < 0 || playerMove > 4);
				
				if (playerMove > 0 && playerMove < 4) {
					System.out.println("Player chose " + displayMove(playerMove) + ".");
					break;
				}			
				else if (playerMove == 4) {
					System.out.print("[Fire] is banned. Please select another move: ");
				}
			} while (playerMove == 4);
			
			// computer's turn
			pause(1);
			System.out.println("\nComputer's Turn:");
			
			computerMove = rand.nextInt(3) + 1; // bird, stone, or water
			
			if (computerMove > 0 && computerMove < 4) {
				pause(1);
				System.out.println("Computer chose " + displayMove(computerMove) + ".");
			}
			
			pause(1);	
			announceResultPvC(playerMove, computerMove);
			pause(1);
			displayScoreCounterPvC();
			
		} while (playerScore == computerScore);
		
		pause(1);
		announceWinnerPvC();
	}
	
	// function to display the sudden death round in the PvP match
	public static void displaySuddenDeathRoundPvP() {
		WanTuSom p1 = new WanTuSom();
		WanTuSom p2 = new WanTuSom();
		int player1Move = 0;		
		int player2Move = 0;
		
		do {	
			pause(1);
			System.out.println("\n{SUDDEN DEATH ROUND}");

			// player1's turn
			pause(1);
			System.out.println("Player1's Turn:");			
			p1.displayMovesList();		
			System.out.println("(Banned)");		
			System.out.println("<Enter 0 to return to gamemode menu>");
			System.out.print("Select a move: ");            
			
			do {
				do {
					player1Move = input.nextInt();
					input.nextLine();
					
					if (player1Move == 0) {
						System.out.println("Returning to gamemode menu...");
						pause(1);
						displayGameModeMenu();
						break;
					}				
					else if (player1Move < 0 || player1Move > 4) {
						System.out.print("Invalid input. Please enter a valid number: ");
					}
				} while (player1Move < 0 || player1Move > 4);
				
				if (player1Move > 0 && player1Move < 4) {
					System.out.println("Player1 chose " + displayMove(player1Move) + ".");
					break;
				}			
				else if (player1Move == 4) {
					System.out.print("[Fire] is banned. Please select another move: ");
				}
			} while (player1Move == 4);
			
			// player2's turn
			pause(1);
			System.out.println("\nPlayer2's Turn:");
			p2.displayMovesList();		
			System.out.println("(Banned)");		
			System.out.println("<Enter 0 to return to gamemode menu>");
			System.out.print("Select a move: ");
			
			do {
				do {
					player2Move = input.nextInt();
					input.nextLine();
					
					if (player2Move == 0) {
						System.out.println("Returning to gamemode menu...");
						pause(1);
						displayGameModeMenu();
						break;
					}				
					else if (player2Move < 0 || player2Move > 4) {
						System.out.print("Invalid input. Please enter a valid number: ");
					}
				} while (player2Move < 0 || player2Move > 4);
				
				if (player2Move > 0 && player2Move < 4) {
					System.out.println("Player2 chose " + displayMove(player2Move) + ".");
					break;
				}			
				else if (player2Move == 4) {
					System.out.print("[Fire] is banned. Please select another move: ");
				}
			} while (player2Move == 4);
			
			pause(1);	
			announceResultPvP(player1Move, player2Move);
			pause(1);
			displayScoreCounterPvP();
			
		} while (player1Score == player2Score);
		
		pause(1);
		announceWinnerPvP();
	}
	
	// function to display the moves list
	public void displayMovesList() {
		System.out.println("1. Bird");
		System.out.println("2. Stone");
		System.out.println("3. Water");
		System.out.print("4. Fire ");
	}
	
	// function to display the move chosen by player or computer
	public static String displayMove(int choice) {
		String move = "";
		
		switch (choice) {
			case 1:
				move = "[" + MOVES[0] + "]"; // [Bird]
				break;		    
			case 2:
				move = "[" + MOVES[1] + "]"; // [Stone]
				break;		    
			case 3:
				move = "[" + MOVES[2] + "]"; // [Water]
				break;
		}
		return move;
	}
		
	// function to announce the result of each round in the PvC match
	public static void announceResultPvC(int playerMove, int computerMove) {
		if (playerMove == 1 && computerMove == 3 || playerMove == 2 && computerMove == 1 || playerMove == 3 && computerMove == 2 ) {
			System.out.println("\nPlayer wins this round!");
			playerScore++;
		}	
		else if (playerMove == 3 && computerMove == 1 || playerMove == 1 && computerMove == 2 || playerMove == 2 && computerMove == 3 ) {
			System.out.println("\nComputer wins this round!");
			computerScore++;
		}	
		else if (playerMove == computerMove) {
			System.out.println("\nIt's a tie!");
			playerScore++;
			computerScore++;
		}	
		else if (playerMove == 4) {
			System.out.println("\nPlayer wins this round!");
			playerScore++;
		}	
		else if (computerMove == 4) {
			System.out.println("\nComputer wins this round!");
			computerScore++;
		}
	}
	
	// function to announce the result of each round in the PvP match
	public static void announceResultPvP(int player1Move, int player2Move) {
		if (player1Move == 1 && player2Move == 3 || player1Move == 2 && player2Move == 1 || player1Move == 3 && player2Move == 2) {
			System.out.println("\nPlayer1 wins this round!");
			player1Score++;
		}	
		else if (player1Move == 3 && player2Move == 1 || player1Move == 1 && player2Move == 2 || player1Move == 2 && player2Move == 3) {
			System.out.println("\nPlayer2 wins this round!");
			player2Score++;
		}	
		else if (player1Move == player2Move) {
			System.out.println("\nIt's a tie!");
			player1Score++;
			player2Score++;
		}	
		else if (player1Move == 4) {
			System.out.println("\nPlayer1 wins this round!");
			player1Score++;
		}	
		else if (player2Move == 4) {
			System.out.println("\nPlayer2 wins this round!");
			player2Score++;
		}
	}
	
	// function to display the score counter of the PvC match
	public static void displayScoreCounterPvC() {
		System.out.println("\n-----------------------------------------");	
		System.out.println("{SCORE COUNTER}");
		System.out.println("Player: " +  playerScore);
		System.out.println("Computer: " + computerScore);
		System.out.println("-----------------------------------------");
	}
	
	// function to display the score counter of the PvP match
	public static void displayScoreCounterPvP() {
		System.out.println("\n-----------------------------------------");	
		System.out.println("{SCORE COUNTER}");
		System.out.println("Player1: " +  player1Score);
		System.out.println("Player2: " + player2Score);
		System.out.println("-----------------------------------------");
	}
	
	// function to announce the winner of the PvC match
	public static void announceWinnerPvC() {
		System.out.println("\n{GAME OVER}");
		System.out.println("Final score of Player: " + playerScore);
		System.out.println("Final score of Computer: " + computerScore);
		
		if (playerScore > computerScore) {
			System.out.println("WINNER - Player");
		} 
		else {
			System.out.println("WINNER - Computer");
		}
		
		confirmPlayAgainPvC();
	}
	
	// function to announce the winner of the PvP match
	public static void announceWinnerPvP() {
		System.out.println("\n{GAME OVER}");
		System.out.println("Final score of Player1: " + player1Score);
		System.out.println("Final score of Player2: " + player2Score);
		
		if (player1Score > player2Score) {
			System.out.println("WINNER - Player1");
		} 
		else {
			System.out.println("WINNER - Player2");
		}
		
		confirmPlayAgainPvP();
	}
	
	// function to prompt player to confirm if they want to play again in PvC mode
	public static void confirmPlayAgainPvC() {
		System.out.println("\nPlay Again?");
		System.out.print("Enter 'Y' for yes or 'N' for no: ");
		
		String isPlayAgain = "";
		
		do {
			isPlayAgain = input.nextLine();
			
			if (isPlayAgain.equalsIgnoreCase("Y")) {
				pause(1);
				System.out.println("Match starts in:");
				System.out.println("       3");
				pause(1);
				System.out.println("       2");
				pause(1);
				System.out.println("       1");		
				displayMatchPvC();
				break;
			}	
			else if (isPlayAgain.equalsIgnoreCase("N")) {
				System.out.println("Returning to gamemode menu...");
				pause(1);
				displayGameModeMenu();
				break;
			}	
			else if (!isPlayAgain.equalsIgnoreCase("Y") || !isPlayAgain.equalsIgnoreCase("N")) {
				System.out.print("Invalid input. Please enter 'Y' or 'N': ");
			}
		} while (!isPlayAgain.equalsIgnoreCase("Y") || !isPlayAgain.equalsIgnoreCase("N"));	
	}
	
	// function to prompt player to confirm if they want to play again in PvP mode
	public static void confirmPlayAgainPvP() {
		System.out.println("\nPlay Again?");
		System.out.print("Enter 'Y' for yes or 'N' for no: ");
		
		String isPlayAgain = "";
		
		do {
			isPlayAgain = input.nextLine();
			
			if (isPlayAgain.equalsIgnoreCase("Y")) {
				pause(1);
				System.out.println("Match starts in:");
				System.out.println("       3");
				pause(1);
				System.out.println("       2");
				pause(1);
				System.out.println("       1");			
				displayMatchPvP();
				break;
			}	
			else if (isPlayAgain.equalsIgnoreCase("N")) {
				System.out.println("Returning to gamemode menu...");
				pause(1);
				displayGameModeMenu();
				break;
			}	
			else if (!isPlayAgain.equalsIgnoreCase("Y") || !isPlayAgain.equalsIgnoreCase("N")) {
				System.out.print("Invalid input. Please enter 'Y' or 'N': ");
			}
		} while (!isPlayAgain.equalsIgnoreCase("Y") || !isPlayAgain.equalsIgnoreCase("N"));
	}
}
