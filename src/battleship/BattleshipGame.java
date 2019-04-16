package battleship;

public class BattleshipGame {

	public static void main(String[] args) {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		ocean.printHidden();
	}

}
