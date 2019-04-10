package battleship;

public class Ocean {

	private Ship[][] ships = new Ship[10][10];
	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	
	public Ocean() {
		
	}
	
	void placeAllShipsRandomly() {
		
	}
	
	boolean isOccupied(int row, int column) {
		return false;
	}
	
	boolean shootAt(int row, int column) {
		return false;
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
		return false;
	}
	
	Ship[][] getShipArray() {
		return this.ships;
	}
	
	void print() {
		
	}
	
}