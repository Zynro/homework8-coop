package battleship;

class Battleship extends Ship {
	private int length;
	
	public Battleship(int length) {
		super(length);
		this.length = length;
	}
	
	@Override
	public String getShipType() {
		return "battleship";
	}
	
}
