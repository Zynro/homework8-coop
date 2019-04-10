package battleship;

public abstract class Ship {
	private int bowRow;
	private int bowColumn;
	private int length;
	private boolean horizontal;
	private boolean[] hit;
	
	public Ship(int length) {
		
	}
	
	public int getLength() {
		return this.length;
	}
	
	public int getBowRow() {
		return this.bowRow;
	}
	
	public int getBowColumn() {
		return this.bowColumn;
	}
	
	public boolean[] getHit() {
		return this.hit;
	}
	
	public boolean isHorizontal() {
		return this.horizontal;
	}
	
	public void setBowRow(int row) {
		this.bowRow = row;
	}
	
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}
	
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	public abstract String getShipType();
	
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		return false;
	}
	
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		
	}
	
	boolean shootAt(int row, int column) {
		return false;
	}
	
	boolean isSunk() {
		return false;
	}
	
	@Override
	public String toString() {
		return "";
	}
}