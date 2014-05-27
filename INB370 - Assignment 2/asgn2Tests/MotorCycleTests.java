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
	 * @author Christopher Koren
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
	 * @author Christopher Koren
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
	 * @author Christopher Koren
	 */
	@Test
	public void testVehicle() throws VehicleException {
		testVehicle = new MotorCycle(vehID, arrivalTime);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getVehID()}.
	 * @author Christopher Koren
	 */
	@Test
	public void testGetVehID() {
		String expectedResult = "test01";
		assertEquals(expectedResult, testMotorCycle.getVehID());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getArrivalTime()}.
	 * @author Christopher Koren
	 */
	@Test
	public void testGetArrivalTime() {
		int expectedResult = 1;
		assertEquals(expectedResult, testMotorCycle.getArrivalTime());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterQueuedState()}.
	 * @throws VehicleException 
	 * @author Christopher Koren
	 */
	@Test
	public void testEnterQueuedState() throws VehicleException {
		String expectedResult = "queued";
		
		testMotorCycle.enterQueuedState();
		assertEquals(expectedResult, vehicleState);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}.
	 * @throws VehicleException 
	 * @author Christopher Koren
	 */
	@Test
	public void testExitQueuedState() throws VehicleException {
		String expectedResult = "unqueued";
		int exitTime = 40;
		
		testMotorCycle.exitQueuedState(exitTime);
		assertEquals(expectedResult, vehicleState);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * @throws VehicleException 
	 * @author Christopher Koren
	 */
	@Test
	public void testEnterParkedState() throws VehicleException {
		String expectedResult = "parked";
		int parkingTime = 10;
		int intendedDuration = 25;
		
		testMotorCycle.enterParkedState(parkingTime, intendedDuration);
		assertEquals(expectedResult, vehicleState);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState(int)}.
	 * @author Christopher Koren
	 */
	@Test
	public void testExitParkedStateInt() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	 * @throws VehicleException 
	 * @author Christopher Koren
	 */
	@Test
	public void testExitParkedState() throws VehicleException {
		String expectedResult = "unparked";
		int departureTime = 30;
		testMotorCycle.exitParkedState(departureTime);
		
		assertEquals(expectedResult, vehicleState);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isParked()}.
	 * @author Christopher Koren
	 */
	@Test
	public void testIsParked() {
		vehicleState = "parked";
		assertTrue(testMotorCycle.isParked());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isQueued()}.
	 * @author Christopher Koren
	 */
	@Test
	public void testIsQueued() {
		vehicleState = "queued";
		assertTrue(testMotorCycle.isQueued());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getParkingTime()}.
	 * @throws VehicleException 
	 * @author Christopher Koren
	 */
	@Test
	public void testGetParkingTime() throws VehicleException {
		int expectedResult = 10;
		int parkingTime = 10;
		int intendedDuration = 20;
		
		testMotorCycle.enterParkedState(parkingTime, intendedDuration);
		assertEquals(expectedResult, testMotorCycle.getParkingTime());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getDepartureTime()}.
	 * @throws VehicleException 
	 * @author Christopher Koren
	 */
	@Test
	public void testGetDepartureTime() throws VehicleException {
		int expectedResult = 10;
		int departureTime = 10;
		
		testMotorCycle.exitParkedState(departureTime);
		assertEquals(expectedResult, testMotorCycle.getDepartureTime());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasQueued()}.
	 * @author Christopher Koren
	 */
	@Test
	public void testWasQueued() {
		boolean wasQueued = true;
		assertTrue(testMotorCycle.wasQueued());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasParked()}.
	 * @author Christopher Koren
	 */
	@Test
	public void testWasParked() {
		boolean wasParked = true;
		assertTrue(testMotorCycle.wasParked());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}.
	 * @author Christopher Koren
	 */
	@Test
	public void testIsSatisfied() {
		boolean wasQueued = true;
		assertTrue(testMotorCycle.isSatisfied());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#toString()}.
	 * @author Christopher Koren
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented"); // TODO
	}

}
