package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShipTest {
	@Test
	void testGetLength() {
		Ship ship = new Submarine();
		assertEquals(ship.getLength(), 1);
		ship = new Destroyer();
		assertEquals(ship.getLength(), 2);
		ship = new Cruiser();
		assertEquals(ship.getLength(), 3);
		ship = new Battleship();
		assertEquals(ship.getLength(), 4);
		ship = new EmptySea();
		assertEquals(ship.getLength(), 1);
	}

	@Test
	void testGetBowRow() {
		Ocean ocean = new Ocean();
		Ship ship = new Submarine();
		ship.placeShipAt(1, 3, true, ocean);
		assertEquals(ship.getBowRow(), 1);
		ship.placeShipAt(9, 7, false, ocean);
		assertEquals(ship.getBowRow(), 9);
		ship.placeShipAt(5, 2, true, ocean);
		assertEquals(ship.getBowRow(), 5);
	}

	@Test
	void testGetBowColumn() {
		Ocean ocean = new Ocean();
		Ship ship = new Submarine();
		ship.placeShipAt(3, 1, true, ocean);
		assertEquals(ship.getBowColumn(), 1);
		ship.placeShipAt(7, 9, false, ocean);
		assertEquals(ship.getBowColumn(), 9);
		ship.placeShipAt(2, 5, true, ocean);
		assertEquals(ship.getBowColumn(), 5);
	}

	@Test
	void testGetHit() {
		Ship ship = new Submarine();
		boolean[] hitSub = {false, true, true, true};
		assertArrayEquals(ship.getHit(), hitSub);
		ship = new Destroyer();
		boolean[] hitDes = {false, false, true, true};
		assertArrayEquals(ship.getHit(), hitDes);
		ship = new Cruiser();
		boolean[] hitCru = {false, false, false, true};
		assertArrayEquals(ship.getHit(), hitCru);
		ship = new Battleship();
		boolean[] hitBat = {false, false, false, false};
		assertArrayEquals(ship.getHit(), hitBat);
	}

	@Test
	void testIsHorizontal() {
		Ocean ocean = new Ocean();
		Ship ship = new Submarine();
		ship.placeShipAt(1, 3, true, ocean);
		assertEquals(ship.isHorizontal(), true);
		ship.placeShipAt(9, 7, false, ocean);
		assertEquals(ship.isHorizontal(), false);
		ship.placeShipAt(5, 2, true, ocean);
		assertEquals(ship.isHorizontal(), true);
	}

	@Test
	void testSetBowRow() {
		Ship ship = new Submarine();
		ship.setBowRow(1);
		assertEquals(ship.getBowRow(), 1);
		ship.setBowRow(5);
		assertEquals(ship.getBowRow(), 5);
		ship.setBowRow(9);
		assertEquals(ship.getBowRow(), 9);
	}

	@Test
	void testSetBowColumn() {
		Ship ship = new Submarine();
		ship.setBowColumn(1);
		assertEquals(ship.getBowColumn(), 1);
		ship.setBowColumn(5);
		assertEquals(ship.getBowColumn(), 5);
		ship.setBowColumn(9);
		assertEquals(ship.getBowColumn(), 9);
	}

	@Test
	void testSetHorizontal() {
		Ship ship = new Submarine();
		ship.setHorizontal(true);
		assertEquals(ship.isHorizontal(), true);
		ship.setHorizontal(false);
		assertEquals(ship.isHorizontal(), false);
		ship.setHorizontal(true);
		assertEquals(ship.isHorizontal(), true);
	}

	@Test
	void testGetShipType() {
		Ship ship = new Submarine();
		assertEquals(ship.getShipType(), "submarine");
		ship = new Destroyer();
		assertEquals(ship.getShipType(), "destroyer");
		ship = new Cruiser();
		assertEquals(ship.getShipType(), "cruiser");
		ship = new Battleship();
		assertEquals(ship.getShipType(), "battleship");
		ship = new EmptySea();
		assertEquals(ship.getShipType(), "empty");
	}

	@Test
	void testIsShipAdjacent() {
		Ocean ocean = new Ocean();
		Ship ship = new Submarine();
		ship.placeShipAt(5, 5, true, ocean);
		Ship[][] ships = ocean.getShipArray();
		assertEquals(ship.isShipAdjacent(5, 5, ships), true); // at same position as ship
		assertEquals(ship.isShipAdjacent(5, 4, ships), true); // on the left of ship
		assertEquals(ship.isShipAdjacent(4, 4, ships), true); // on the top left of ship
		assertEquals(ship.isShipAdjacent(4, 5, ships), true); // on the top of ship
		assertEquals(ship.isShipAdjacent(4, 6, ships), true); // on the top right of ship
		assertEquals(ship.isShipAdjacent(5, 6, ships), true); // on the right of ship
		assertEquals(ship.isShipAdjacent(6, 6, ships), true); // on the bottom right of ship
		assertEquals(ship.isShipAdjacent(6, 5, ships), true); // on the bottom of ship
		assertEquals(ship.isShipAdjacent(4, 6, ships), true); // on the bottom left of ship
		assertEquals(ship.isShipAdjacent(7, 5, ships), false); // away from ship
	}

	@Test
	void testOkToPlaceShipAt() {
		Ocean ocean = new Ocean();
		Ship ship = new Submarine();
		ship.placeShipAt(5, 5, true, ocean);
		assertEquals(ship.okToPlaceShipAt(5, 5, true, ocean), false); // at same position as ship
		assertEquals(ship.okToPlaceShipAt(5, 4, true, ocean), false); // on the left of ship
		assertEquals(ship.okToPlaceShipAt(4, 4, true, ocean), false); // on the top left of ship
		assertEquals(ship.okToPlaceShipAt(4, 5, true, ocean), false); // on the top of ship
		assertEquals(ship.okToPlaceShipAt(4, 6, true, ocean), false); // on the top right of ship
		assertEquals(ship.okToPlaceShipAt(5, 6, true, ocean), false); // on the right of ship
		assertEquals(ship.okToPlaceShipAt(6, 6, true, ocean), false); // on the bottom right of ship
		assertEquals(ship.okToPlaceShipAt(6, 5, true, ocean), false); // on the bottom of ship
		assertEquals(ship.okToPlaceShipAt(4, 6, true, ocean), false); // on the bottom left of ship
		assertEquals(ship.okToPlaceShipAt(7, 5, true, ocean), true); // away from ship
		ship = new Battleship(); // to test ship placement going past boundaries
		assertEquals(ship.okToPlaceShipAt(1, 1, true, ocean), false); // at boundary of ocean
	}

	@Test
	void testPlaceShipAt() {
		Ocean ocean = new Ocean();
		Ship ship = new Submarine();
		ship.placeShipAt(1, 3, true, ocean);
		assertEquals(ship.getBowRow(), 1);
		ship.placeShipAt(9, 7, false, ocean);
		assertEquals(ship.getBowRow(), 9);
		ship.placeShipAt(5, 2, true, ocean);
		assertEquals(ship.getBowRow(), 5);
		ship.placeShipAt(3, 1, true, ocean);
		assertEquals(ship.getBowColumn(), 1);
		ship.placeShipAt(7, 9, false, ocean);
		assertEquals(ship.getBowColumn(), 9);
		ship.placeShipAt(2, 5, true, ocean);
		assertEquals(ship.getBowColumn(), 5);
	}

	@Test
	void testShootAt() {
		Ocean ocean = new Ocean();
		Ship ship = new Battleship();
		ship.placeShipAt(5, 5, true, ocean);
		boolean shot = ship.shootAt(5, 5);
		assertEquals(shot, true); // shot on mark
		boolean[] hit = {true, false, false, false};
		assertArrayEquals(ship.getHit(), hit);
		ship.shootAt(5, 4);
		hit[1] = true;
		assertArrayEquals(ship.getHit(), hit);
		ship.shootAt(5, 3);
		hit[2] = true;
		assertArrayEquals(ship.getHit(), hit);
		ship.shootAt(5, 2);
		hit[3] = true;
		assertArrayEquals(ship.getHit(), hit);
		shot = ship.shootAt(9, 9);
		assertEquals(shot, false); // missed shot
		
		// Test EmptySea subclass
		EmptySea empty = new EmptySea();
		empty.placeShipAt(5, 5, true, ocean);
		shot = empty.shootAt(9, 9);
		assertEquals(shot, false);
		shot = empty.shootAt(5, 5);
		assertEquals(shot, false);
	}

	@Test
	void testIsSunk() {
		Ocean ocean = new Ocean();
		Ship ship = new Submarine();
		ship.placeShipAt(5, 5, true, ocean);
		assertEquals(ship.isSunk(), false);
		ship.shootAt(5, 5);
		assertEquals(ship.isSunk(), true);
		ship = new Battleship();
		ship.placeShipAt(5, 5, true, ocean);
		ship.shootAt(5, 5);
		ship.shootAt(5, 4);
		ship.shootAt(5, 3);
		assertEquals(ship.isSunk(), false);
		ship.shootAt(5, 2);
		assertEquals(ship.isSunk(), true);
		
		// Test EmptySea subclass
		EmptySea empty = new EmptySea();
		empty.placeShipAt(5, 5, true, ocean);
		assertEquals(empty.isSunk(), false);
		empty.shootAt(5, 5);
		assertEquals(empty.isSunk(), false);
	}

	@Test
	void testToString() {
		Ocean ocean = new Ocean();
		Ship ship = new Submarine();
		ship.placeShipAt(5, 5, true, ocean);
		assertEquals(ship.toString(), "x");
		ship.shootAt(5, 5);
		assertEquals(ship.toString(), "s");
		ship = new Battleship();
		ship.placeShipAt(5, 5, true, ocean);
		assertEquals(ship.toString(), "x");
		ship.shootAt(5, 5);
		assertEquals(ship.toString(), "x");
		ship.shootAt(5, 4);
		ship.shootAt(5, 3);
		ship.shootAt(5, 2);
		assertEquals(ship.toString(), "s");
		
		// Test EmptySea subclass
		EmptySea empty = new EmptySea();
		assertEquals(empty.toString(), "-");
		empty.placeShipAt(5, 5, true, ocean);
		empty.shootAt(5, 5);
		assertEquals(empty.toString(), "-");
	}
}
