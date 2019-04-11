package battleship;

import java.util.Random;

public class Ocean {

	private Ship[][] ships = new Ship[10][10];
	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	
	public Ocean() {
		for(int i = 0; i<10;i++) {
			for(int b = 0; b<10;b++) {
				ships[i][b] = new EmptySea();
			}
		}
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
	}
	
	private int determineDirection() {
		Random random = new Random();
		int direction = random.nextInt(1);
		return direction;
	}
	
	void placeAllShipsRandomly() {
		
		
	}
	
	boolean isOccupied(int column, int row) {
		if(ships[column][row].getClass().getSimpleName() != "EmptySea") {
			return true;
		}else {
			return false;
		}
	}
	
	boolean shootAt(int column, int row) {
		shotsFired++;
		if((ships[column][row].getClass().getSimpleName() != "EmptySea") && (ships[column][row].isSunk() == false)) {
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
		
	}
	
}