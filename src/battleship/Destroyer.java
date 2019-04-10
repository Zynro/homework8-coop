package battleship;

public class Destroyer extends Ship {
	private int length;
	
	public Destroyer(int length) {
		super(length);
		this.length = length;
	}

	@Override
	public String getShipType() {
		return "destroyer";
	}

}
