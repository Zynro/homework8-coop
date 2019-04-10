package battleship;

public class Submarine extends Ship {
	private int length;
	
	public Submarine(int length) {
		super(length);
		this.length = length;
	}

	@Override
	public String getShipType() {
		return "submarine";
	}

}
