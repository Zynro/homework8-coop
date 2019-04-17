package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

class OceanTest {

	Ocean oceanTest;
	Ship shipTest;


	@Test
	void testOcean() {
		oceanTest = new Ocean();
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
		fail("nope");
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
		fail("Not yet implemented");
	}

	@Test
	void testGetShotsFired() {
		fail("Not yet implemented");
	}

	@Test
	void testGetHitCount() {
		fail("Not yet implemented");
	}

	@Test
	void testGetShipsSunk() {
		fail("Not yet implemented");
	}

	@Test
	void testAddShipSunk() {
		fail("Not yet implemented");
	}

	@Test
	void testIsGameOver() {
		fail("Not yet implemented");
	}

	@Test
	void testGetShipArray() {
		fail("Not yet implemented");
	}

}
