package battleship;

import java.util.Scanner;

public class BattleshipGame {

	public static void main(String[] args) {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		ocean.printHidden();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter row:");
		int row = sc.nextInt();
		System.out.println("Enter column:");
		int column = sc.nextInt();
		
	}

}
