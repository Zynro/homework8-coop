package battleship;

public class Cruiser extends Ship {
	private int length;
	
	public Cruiser(int length) {
		super(length);
		this.length = length;
	}

	@Override
	public String getShipType() {
		return "cruiser";
	}

}
