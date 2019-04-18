package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean oceanTest = new Ocean();
	Ship shipTest;


	@Test
	void testOcean() {
		shipTest = new EmptySea();
		for(int i=0;i<10;i++) {
			for (int b = 0;b<10;b++) {
				assertNotNull(oceanTest.getShipArray()[i][b]);
				assertEquals("empty",oceanTest.getShipArray()[i][b].getShipType());
			}
		}
	}

	@Test
	void testPlaceAllShipsRandomly() {
		oceanTest.placeAllShipsRandomly();
		ArrayList<String> shipList = new ArrayList<String>();
		for(int row = 0; row<10;row++) {
			for(int column = 0; column<10;column++) {
				Ship ship = oceanTest.getShipArray()[row][column];
				shipList.add(ship.getShipType());
				if(ship.getShipType() != "empty") {
					int bowColumn = ship.getBowColumn();
					int bowRow = ship.getBowRow();
					boolean horizontal = ship.isHorizontal();
					int currRow = row;
					int currColumn = column;
					if (horizontal) { // if ship is aligned horizontally
						for (int i = 0; i < ship.getLength(); i++) {
							currColumn -= i; // decrease column to get full length of ship
							if (currColumn < 0) { // return false if outside ocean boundaries
								continue;
							}
							assertTrue(ship.isShipAdjacent(bowRow, bowColumn, oceanTest.getShipArray()));
						}
					}
					else { // if ship is aligned vertically
						for (int i = 0; i < ship.getLength(); i++) {
							currRow -= i; // decrease row to get full length of ship
							if (currRow < 0) { // return false if outside ocean boundaries
								continue;
							}
							assertTrue(ship.isShipAdjacent(bowRow, bowColumn, oceanTest.getShipArray()));
							}
						}
					}	
				}
			}
		int battleships = 0;
		int cruisers = 0;
		int destroyers = 0;
		int submarines = 0;
		for(String s: shipList) {
			if(s == "battleship"){
				battleships++;
			}else if (s == "cruiser") {
				cruisers++;
			}else if (s == "destroyer") {
				destroyers++;
			}else if (s == "submarine"){
				submarines++;
			}
		}
		assertEquals(4, battleships);
		assertEquals(6, cruisers);
		assertEquals(6, destroyers);
		assertEquals(4, submarines);
	}

	@Test
	void testIsOccupied() {
		oceanTest = new Ocean();
		shipTest = new Battleship();
		shipTest.placeShipAt(5, 5, true, oceanTest);
		assertTrue(oceanTest.isOccupied(5,5));
		assertFalse(oceanTest.isOccupied(0, 0));
	}

	@Test
	void testShootAt() {
		Ship battleship = new Battleship();
		battleship.placeShipAt(5, 5, true, oceanTest);
		assertEquals(0, oceanTest.getShotsFired());
		assertEquals(0, oceanTest.getHitCount());
		oceanTest.shootAt(5, 5);
		assertEquals(1, oceanTest.getShotsFired());
		assertEquals(1, oceanTest.getHitCount());
		oceanTest.shootAt(0, 0);
		assertEquals(1, oceanTest.getHitCount());
	}

	@Test
	void testGetShotsFired() {
		Ship battleship = new Battleship();
		battleship.placeShipAt(5, 5, true, oceanTest);
		assertEquals(0, oceanTest.getShotsFired());
		oceanTest.shootAt(5, 5);
		assertEquals(1, oceanTest.getShotsFired());
	}

	@Test
	void testGetHitCount() {
		Ship battleship = new Battleship();
		battleship.placeShipAt(5, 5, true, oceanTest);
		assertEquals(0, oceanTest.getHitCount());
		oceanTest.shootAt(5, 5);
		assertEquals(1, oceanTest.getHitCount());
		oceanTest.shootAt(0, 0);
		assertEquals(1, oceanTest.getHitCount());
	}

	@Test
	void testGetShipsSunk() {
		assertEquals(0, oceanTest.getShipsSunk());
		Ship ship = new Submarine();
		ship.placeShipAt(5, 5, true, oceanTest);
		assertEquals(0, oceanTest.getShipsSunk());
		ship.shootAt(5, 5);
		if (ship.isSunk()) {
        	oceanTest.addShipSunk();
		}
		assertEquals(1, oceanTest.getShipsSunk());
		ship = new Battleship();
		ship.placeShipAt(5, 5, true, oceanTest);
		ship.shootAt(5, 5);
		ship.shootAt(5, 4);
		ship.shootAt(5, 3);
		if (ship.isSunk()) {
        	oceanTest.addShipSunk();
		}
		assertEquals(1, oceanTest.getShipsSunk());
		ship.shootAt(5, 2);
		if (ship.isSunk()) {
        	oceanTest.addShipSunk();
		}
		assertEquals(2, oceanTest.getShipsSunk());
	}

	@Test
	void testAddShipSunk() {
		assertEquals(0,oceanTest.getShipsSunk());
		oceanTest.addShipSunk();
		assertEquals(1,oceanTest.getShipsSunk());
		oceanTest.addShipSunk();
		oceanTest.addShipSunk();
		oceanTest.addShipSunk();
		assertEquals(4,oceanTest.getShipsSunk());
	}

	@Test
	void testIsGameOver() {
		assertFalse(oceanTest.isGameOver());
		for(int i=0;i<10;i++) {
			oceanTest.addShipSunk();
		}
		assertTrue(oceanTest.isGameOver());
	}

	@Test
	void testGetShipArray() {
		assertEquals(10, oceanTest.getShipArray().length);
		for(int i = 0; i<oceanTest.getShipArray().length; i++) {
			assertEquals(10, oceanTest.getShipArray()[i].length);
		}
		Ship battleship =  new Battleship();
		battleship.placeShipAt(5, 5, true, oceanTest);
		assertEquals("battleship", oceanTest.getShipArray()[5][5].getShipType());
	}

}
