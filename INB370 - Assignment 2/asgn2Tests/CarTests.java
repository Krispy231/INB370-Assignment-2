/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Tests 
 * 22/04/2014
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.VehicleException;
import asgn2Vehicles.Car;

/**
 * @author hogan
 * 
 */
public class CarTests {

	public Car testCar;

	/**
	 * @throws java.lang.Exception
	 * @author Christopher Koren
	 */
	@Before
	public void setUp() throws Exception {
		String vehID = "test1";
		int arrivalTime = 11;
		boolean small = true;
		testCar = new Car(vehID, arrivalTime, small);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link asgn2Vehicles.Car#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link asgn2Vehicles.Car#Car(java.lang.String, int, boolean)}.
	 * 
	 * @throws VehicleException
	 * @author Matthew Wheeler
	 */
	@Test(expected = VehicleException.class)
	public void testArrivalTimeBefore() throws VehicleException {
		String vehID = "test1";
		int arrivalTime = -1;
		boolean small = true;
		testCar = new Car(vehID, arrivalTime, small);
	}

	/**
	 * Test method for
	 * {@link asgn2Vehicles.Car#Car(java.lang.String, int, boolean)}.
	 * 
	 * @throws VehicleException
	 * @author Matthew Wheeler
	 */
	@Test
	public void testArrivalTimeAtZero() throws VehicleException {
		String vehID = "test1";
		int arrivalTime = 0;
		boolean small = true;
		testCar = new Car(vehID, arrivalTime, small);
	}

	/**
	 * Test method for
	 * {@link asgn2Vehicles.Car#Car(java.lang.String, int, boolean)}.
	 * 
	 * @throws VehicleException
	 * @author Christopher Koren
	 */
	@Test
	public void testArrivalTimeAfter() throws VehicleException {
		String vehID = "test1";
		int arrivalTime = 1;
		boolean small = true;
		testCar = new Car(vehID, arrivalTime, small);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Car#isSmall()}.
	 * 
	 * @throws VehicleException
	 * @author Christopher Koren
	 */
	@Test
	public void testIsSmallTrue() throws VehicleException {
		String vehID = "testSmallTrue";
		int arrivalTime = 11;
		boolean small = true;
		testCar = new Car("vehID", arrivalTime, small);
		assertTrue(testCar.isSmall());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Car#isSmall()}.
	 * 
	 * @throws VehicleException
	 * @author Matthew Wheeler
	 */
	@Test
	public void testIsSmallFalse() throws VehicleException {
		String vehID = "testSmallFalse";
		int arrivalTime = 11;
		boolean small = false;
		testCar = new Car("vehID", arrivalTime, small);
		assertFalse(testCar.isSmall());
	}

}
