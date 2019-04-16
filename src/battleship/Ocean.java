package battleship;

import java.util.Random;

public class Ocean {

	private Ship[][] ships = new Ship[10][10];
	private boolean[][] hitArray = new boolean[10][10];
	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	
	
	public Ocean() {
		for(int i = 0; i<10;i++) {
			for(int b = 0; b<10;b++) {
				ships[i][b] = new EmptySea();
				hitArray[i][b] = false;
			}
		}
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
	}
	/**
	 * 
	 */
	void placeAllShipsRandomly() {
		Random random = new Random();
		boolean placed = false;
		boolean horizontal;
		
		Ship battleship = new Battleship();
		Ship[] cruisers = new Cruiser[2]; 
		Ship[] destroyers = new Destroyer[3];
		Ship[] submarines = new Submarine[4];
		
		while(placed == false) {
			int column = random.nextInt(10);
			int row = random.nextInt(10);
			horizontal = random.nextBoolean();
			if (battleship.okToPlaceShipAt(row, column, horizontal, this) == true) {
				battleship.placeShipAt(row, column, horizontal, this);
				placed = true;
			}else {
				continue;
			}
		}	
		
		for(int i = 0; i<cruisers.length; i++) {
			placed = false;
			cruisers[i] = new Cruiser();
			while(placed == false) {
				int column = random.nextInt(10);
				int row = random.nextInt(10);
				horizontal = random.nextBoolean();
				if (cruisers[i].okToPlaceShipAt(row, column, horizontal, this) == true) {
					cruisers[i].placeShipAt(row, column, horizontal, this);
					placed = true;
				}else {
					continue;
				}
			}
		}
		for(int i = 0; i<destroyers.length; i++) {
			placed = false;
			destroyers[i] = new Destroyer();
			while(placed == false) {
				int column = random.nextInt(10);
				int row = random.nextInt(10);
				horizontal = random.nextBoolean();
				if (destroyers[i].okToPlaceShipAt(row, column, horizontal, this) == true) {
					destroyers[i].placeShipAt(row, column, horizontal, this);
					placed = true;
				}else {
					continue;
				}
			}
		}
		for(int i = 0; i<submarines.length; i++) {
			placed = false;
			submarines[i] = new Submarine();
			while(placed == false) {
				int column = random.nextInt(10);
				int row = random.nextInt(10);
				horizontal = random.nextBoolean();
				if (submarines[i].okToPlaceShipAt(row, column, horizontal, this) == true) {
					submarines[i].placeShipAt(row, column, horizontal, this);
					placed = true;
				}else {
					continue;
				}
			}
		}
		
	}
	
	boolean isOccupied(int row, int column) {
		if(ships[row][column].getShipType() != "empty") {
			return true;
		}else {
			return false;
		}
	}
	
	boolean shootAt(int row, int column) {
		shotsFired++;
		if((ships[row][column].getShipType() != "empty") && (ships[column][row].isSunk() == false)) {
			hitArray[row][column] = true;
			hitCount++;
			return true;
		}else {
			return false;
		}
	}
	
	int getShotsFired() {
		return this.shotsFired;
	}
	
	int getHitCount() {
		return this.hitCount;
	}
	
	int getShipsSunk() {
		return this.shipsSunk;
	}
	
	boolean isGameOver() {
		if (this.shipsSunk == 10){
			return true;
		}else {
			return false;
		}
	}
	
	Ship[][] getShipArray() {
		return this.ships;
	}
	
	void print() {
		String output = "";
		String[] printArray = new String[11];
		for(int i=0;i<printArray.length;i++) {
			if(i==0) {
				printArray[i] = "  0 1 2 3 4 5 6 7 8 9";
				continue;
			}else {
				try {
					printArray[i] = Integer.toString(i-1);
					for (int column=0;column<ships[i-1].length;column++) {
						if (hitArray[i-1][column] == false){
							printArray[i] = printArray[i] + " " + ".";
						}else {
							printArray[i] = printArray[i] + " " + ships[i-1][column].toString();
						}
					}
				}
				catch (Exception e) {}
			}
		}
		output = String.join("\n", printArray);
		System.out.println(output);
	}
	void printHidden() {
		String output = "";
		String[] printArray = new String[11];
		for(int i=0;i<printArray.length;i++) {
			if(i==0) {
				printArray[i] = "  0 1 2 3 4 5 6 7 8 9";
				continue;
			}else {
				try {
					printArray[i] = Integer.toString(i-1);
					for (int column=0;column<ships[i-1].length;column++) {
						printArray[i] = printArray[i] + " " + ships[i-1][column].toString();
					}
				}
				catch (Exception e) {}
			}
		}
		output = String.join("\n", printArray);
		System.out.println(output);
	}
	
}