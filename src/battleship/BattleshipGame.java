package battleship;

import java.util.Scanner;

public class BattleshipGame {

	public static void main(String[] args) {
		// Set up ocean, place all ships, and print the display
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		ocean.printHidden();
		
		Scanner sc = new Scanner(System.in); // variable for scanner
		boolean keepPlaying = true; // variable to determine if user wants to keep playing
		
		// While user wants to keep playing, keep looping
		while (keepPlaying) {
			// While game is not over, keep looping
		    while (!ocean.isGameOver()) {
		    	// Get row and column to shoot
		        System.out.println("Enter row:");
		        int row = sc.nextInt();
		        System.out.println("Enter column:");
		        int column = sc.nextInt();
		        
		        // Shoot at row and column
		        ocean.shootAt(row, column);
		        Ship ship = ocean.getShipArray()[row][column];
		        ship.shootAt(row, column);
		        
		        // Check if ship was sunk, if so increase ocean's shipsSunk counter by 1
		        if (ship.isSunk()) {
		        	ocean.addShipSunk();
		        }
		        
		        // Update display
		        ocean.print();
		    }
		    
		    // Check if user wants to play again
	        System.out.println("Type 1 to play again or 2 to end the game:");
	        if (sc.nextInt() != 1) {
	        	keepPlaying = false;
	        }
		}
		sc.close();
	}
}
