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
import asgn2Vehicles.MotorCycle;
import asgn2Vehicles.Vehicle;

/**
 * @author hogan
 *
 */
public class MotorCycleTests {
	
	public MotorCycle testMotorCycle;
	public Vehicle testVehicle;
	String vehID = "test01";
	int arrivalTime = 1;
	
	String vehicleState;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testMotorCycle = new MotorCycle(vehID, arrivalTime);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link asgn2Vehicles.MotorCycle#MotorCycle(java.lang.String, int)}.
	 * @throws VehicleException 
	 */
	@Test
	public void testMotorCycle() throws VehicleException {
		vehID = "test02";
		arrivalTime = 100;
		
		testMotorCycle = new MotorCycle(vehID, arrivalTime);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#Vehicle(java.lang.String, int)}.
	 * @throws VehicleException 
	 */
	@Test
	public void testVehicle() throws VehicleException {
		testVehicle = new MotorCycle(vehID, arrivalTime);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getVehID()}.
	 */
	@Test
	public void testGetVehID() {
		String expectedResult = "test01";
		assertEquals(expectedResult, Vehicle.getVehID());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getArrivalTime()}.
	 */
	@Test
	public void testGetArrivalTime() {
		int expectedResult = 1;
		assertEquals(expectedResult, Vehicle.getArrivalTime());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterQueuedState()}.
	 */
	@Test
	public void testEnterQueuedState() {
		String expectedResult = "queued";
		
		Vehicle.enterQueuedState();
		assertEquals(expectedResult, vehicleState);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}.
	 */
	@Test
	public void testExitQueuedState() {
		String expectedResult = "unqueued";
		int exitTime = 40;
		
		Vehicle.exitQueuedState(exitTime);
		assertEquals(expectedResult, vehicleState);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 */
	@Test
	public void testEnterParkedState() {
		String expectedResult = "parked";
		int parkingTime = 10;
		int intendedDuration = 25;
		
		Vehicle.enterParkedState(parkingTime, intendedDuration)
		assertEquals(expectedResult, vehicleState);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState(int)}.
	 */
	@Test
	public void testExitParkedStateInt() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	 */
	@Test
	public void testExitParkedState() {
		String expectedResult = "unparked";
		int departureTime = 30;
		Vehicle.exitParkedState(departureTime);
		
		assertEquals(expectedResult, vehicleState);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isParked()}.
	 */
	@Test
	public void testIsParked() {
		vehicleState = "parked";
		assertTrue(Vehicle.isParked());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isQueued()}.
	 */
	@Test
	public void testIsQueued() {
		vehicleState = "queued";
		assertTrue(Vehicle.isQueued());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getParkingTime()}.
	 */
	@Test
	public void testGetParkingTime() {
		int expectedResult = 10;
		int parkingTime = 10;
		int intendedDuration = 20;
		
		Vehicle.enterParkedState(parkingTime, intendedDuration);
		assertEquals(expectedResult, Vehicle.getParkingTime());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getDepartureTime()}.
	 */
	@Test
	public void testGetDepartureTime() {
		int expectedResult = 10;
		int departureTime = 10;
		
		Vehicle.exitParkedState(departureTime);
		assertEquals(expectedResult, Vehicle.getDepartureTime());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasQueued()}.
	 */
	@Test
	public void testWasQueued() {
		boolean wasQueued = true;
		assertTrue(Vehicle.wasQueued());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasParked()}.
	 */
	@Test
	public void testWasParked() {
		boolean wasParked = true;
		assertTrue(Vehicle.wasParked());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}.
	 */
	@Test
	public void testIsSatisfied() {
		boolean wasQueued = true;
		assertTrue(Vehicle.isSatisfied());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented"); // TODO
	}

}
