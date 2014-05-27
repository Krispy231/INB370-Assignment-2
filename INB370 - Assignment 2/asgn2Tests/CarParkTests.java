/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Tests 
 * 29/04/2014
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import asgn2CarParks.CarPark;
import asgn2Exceptions.SimulationException;
import asgn2Exceptions.VehicleException;
import asgn2Simulators.Constants;
import asgn2Vehicles.Car;
import asgn2Vehicles.MotorCycle;
import asgn2Vehicles.Vehicle;

/**
 * @author hogan
 * 
 */

public class CarParkTests {

	public Car testCar;
	public Car testSmallCar;
	public MotorCycle testMotorCycle;

	public CarPark carParkTest;
	int maxCarSpaces = 100;
	int maxSmallCarSpaces = 30;
	int maxMotorCycleSpaces = 10;
	int maxQueueSize = 5;

	String vehicleStatus;

	ArrayList<Vehicle> vehiclesInCarPark = new ArrayList<Vehicle>();
	ArrayList<Vehicle> vehiclesInQueue = new ArrayList<Vehicle>();
	ArrayList<Vehicle> departArchive = new ArrayList<Vehicle>();
	ArrayList<Vehicle> newVehicleArchive = new ArrayList<Vehicle>();
	ArrayList<Vehicle> failedQueueArchive = new ArrayList<Vehicle>();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		carParkTest = new CarPark(maxCarSpaces, maxSmallCarSpaces,
				maxMotorCycleSpaces, maxQueueSize);
		String vehID = "01", veh2ID = "02", veh3ID = "03";
		int arrivalTime = 10, arrivalTime2 = 15, arrivalTime3 = 20;
		boolean small = false, small2 = true;

		testCar = new Car(vehID, arrivalTime, small);
		testSmallCar = new Car(veh2ID, arrivalTime2, small2);
		testMotorCycle = new MotorCycle(veh3ID, arrivalTime3);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 */
	@Test
	public void testArchiveDepartingVehiclesIsParked() {
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#archiveNewVehicle(asgn2Vehicles.Vehicle)}.
	 */
	@Test
	public void testArchiveNewVehicle() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * 
	 * @author Matthew Wheeler
	 * @throws VehicleException
	 */
	@Test(expected = VehicleException.class)
	public void testArchiveQueueFailuresBeforeZero() throws VehicleException {
		int time = -1;
		carParkTest.archiveQueueFailures(time);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * 
	 * @author Matthew Wheeler
	 * @throws VehicleException
	 */
	@Test
	public void testArchiveQueueFailuresAtZero() throws VehicleException {
		int time = 0;
		carParkTest.archiveQueueFailures(time);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * 
	 * @author Matthew Wheeler
	 * @throws VehicleException
	 */
	@Test
	public void testArchiveQueueFailuresAfterZero() throws VehicleException {
		int time = 1;
		carParkTest.archiveQueueFailures(time);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * 
	 * @author Matthew Wheeler
	 * @throws VehicleException
	 */
	@Test
	public void testArchiveQueueFailuresBeforeClosing() throws VehicleException {
		int time = Constants.CLOSING_TIME - 1;
		carParkTest.archiveQueueFailures(time);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * 
	 * @author Matthew Wheeler
	 * @throws VehicleException
	 */
	@Test(expected = VehicleException.class)
	public void testArchiveQueueFailuresAtClosing() throws VehicleException {
		int time = Constants.CLOSING_TIME;
		carParkTest.archiveQueueFailures(time);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * 
	 * @author Matthew Wheeler
	 * @throws VehicleException
	 */
	@Test(expected = VehicleException.class)
	public void testArchiveQueueFailuresAfterClosing() throws VehicleException {
		int time = Constants.CLOSING_TIME + 1;
		carParkTest.archiveQueueFailures(time);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkEmpty()}.
	 */
	@Test
	public void testCarParkEmpty() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkFull()}.
	 */
	@Test
	public void testCarParkFull() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#enterQueue(asgn2Vehicles.Vehicle)}.
	 */
	@Test
	public void testEnterQueue() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#exitQueue(asgn2Vehicles.Vehicle, int)}.
	 */
	@Test
	public void testExitQueue() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#finalState()}.
	 */
	@Test
	public void testFinalState() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumCars()}.
	 */
	@Test
	public void testGetNumCars() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumMotorCycles()}.
	 */
	@Test
	public void testGetNumMotorCycles() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumSmallCars()}.
	 */
	@Test
	public void testGetNumSmallCars() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getStatus(int)}.
	 */
	@Test
	public void testGetStatus() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#initialState()}.
	 */
	@Test
	public void testInitialState() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#numVehiclesInQueue()}.
	 */
	@Test
	public void testNumVehiclesInQueue() {
		// assertEquals(CarPark.numVehiclesInQueue())
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#parkVehicle(asgn2Vehicles.Vehicle, int, int)}
	 * .
	 */
	@Test
	public void testParkVehicle() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#processQueue(int, asgn2Simulators.Simulator)}
	 * .
	 */
	@Test
	public void testProcessQueue() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#queueEmpty()}.
	 */
	@Test
	public void testQueueEmpty() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#queueFull()}.
	 */
	@Test
	public void testQueueFull() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 */
	@Test
	public void testSpacesAvailable() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#tryProcessNewVehicles(int, asgn2Simulators.Simulator)}
	 * .
	 */
	@Test
	public void testTryProcessNewVehicles() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#unparkVehicle(asgn2Vehicles.Vehicle, int)}.
	 */
	@Test
	public void testUnparkVehicle() {
		fail("Not yet implemented"); // TODO
	}

}
