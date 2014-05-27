/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2CarParks 
 * 21/04/2014
 * 
 */
package asgn2CarParks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import asgn2Exceptions.SimulationException;
import asgn2Exceptions.VehicleException;
import asgn2Simulators.Constants;
import asgn2Simulators.Simulator;
import asgn2Vehicles.Car;
import asgn2Vehicles.MotorCycle;
import asgn2Vehicles.Vehicle;

/**
 * The CarPark class provides a range of facilities for working with a car park in support 
 * of the simulator. In particular, it maintains a collection of currently parked vehicles, 
 * a queue of vehicles wishing to enter the car park, and an historical list of vehicles which 
 * have left or were never able to gain entry. 
 * 
 * The class maintains a wide variety of constraints on small cars, normal cars and motorcycles 
 * and their access to the car park. See the method javadoc for details. 
 * 
 * The class relies heavily on the asgn2.Vehicle hierarchy, and provides a series of reports 
 * used by the logger. 
 * 
 * @author hogan
 *
 */
public class CarPark {
	
	// CarPark variables.
	private String carParkStatus = "";
	private int totalSpaces;
	private int maxQueueSize;
	public int totalVehicleCount = 0;
	private ArrayList<Vehicle> satisfiedVehicles;
	private ArrayList<Vehicle> dissatifiedVehicles;
	private ArrayList<Vehicle> newVehicleArchive;
	// A list used to store all vehicle lists.
	private ArrayList<ArrayList<Vehicle>> allVehiclesParked;
	
	// Vehicle Variables
	private String idString;
	private int numVehicleIDs;
	private ArrayList<Vehicle> past;
	
	// Queue variables.
	private int numQueuedVehicles;
	private ArrayList<Vehicle> vehiclesInQueue;
	
	// Normal Car variables.
	private int maxCarSpaces;
	private int numCars;
	private ArrayList<Vehicle> carsParked;
	
	// Small car variables.
	private int maxSmallCarSpaces;
	private int numSmallCars;
	private ArrayList<Vehicle> smallCarsParked;
	
	// Motorcycle variables.
	private int maxMotorCycleSpaces;
	private int numMotorCycles;
	private ArrayList<Vehicle> motorCyclesParked;
	
	
	/**
	 * CarPark constructor sets the basic size parameters. 
	 * Uses default parameters
	 */
	public CarPark() {
		this(Constants.DEFAULT_MAX_CAR_SPACES,Constants.DEFAULT_MAX_SMALL_CAR_SPACES,
				Constants.DEFAULT_MAX_MOTORCYCLE_SPACES,Constants.DEFAULT_MAX_QUEUE_SIZE);
		
		// Initialise the parking spots avaliable.
		maxCarSpaces = Constants.DEFAULT_MAX_CAR_SPACES - Constants.DEFAULT_MAX_SMALL_CAR_SPACES;
		maxSmallCarSpaces = Constants.DEFAULT_MAX_SMALL_CAR_SPACES;
		maxMotorCycleSpaces = Constants.DEFAULT_MAX_MOTORCYCLE_SPACES;
		maxQueueSize = Constants.DEFAULT_MAX_QUEUE_SIZE;
		totalSpaces = maxCarSpaces + maxSmallCarSpaces + maxMotorCycleSpaces;
		
		
		// Arrays to hold vehicles past/present.
		vehiclesInQueue = new ArrayList<Vehicle>(maxQueueSize);
		satisfiedVehicles = new ArrayList<Vehicle>();
		dissatifiedVehicles = new ArrayList<Vehicle>();
		
		// Arrays to hold numbers of each type of vehicle + a list to hold the lists.
		motorCyclesParked = new ArrayList<Vehicle>();
		carsParked = new ArrayList<Vehicle>();
		smallCarsParked = new ArrayList<Vehicle>();
		allVehiclesParked = new ArrayList<ArrayList<Vehicle>>();
		
		allVehiclesParked.add(carsParked);
		allVehiclesParked.add(smallCarsParked);
		allVehiclesParked.add(motorCyclesParked);
		
	}
	
	/**
	 * CarPark constructor sets the basic size parameters. 
	 * @param maxCarSpaces maximum number of spaces allocated to cars in the car park 
	 * @param maxSmallCarSpaces maximum number of spaces (a component of maxCarSpaces) 
	 * 						 restricted to small cars
	 * @param maxMotorCycleSpaces maximum number of spaces allocated to MotorCycles
	 * @param maxQueueSize maximum number of vehicles allowed to queue
	 * @author Christopher Koren
	 */
	public CarPark(int maxCarSpaces,int maxSmallCarSpaces, int maxMotorCycleSpaces, int maxQueueSize) {
	
		this.maxCarSpaces = maxCarSpaces;
		this.maxSmallCarSpaces = maxSmallCarSpaces;
		this.maxMotorCycleSpaces = maxMotorCycleSpaces;
		this.maxQueueSize = maxQueueSize;
		totalSpaces = maxCarSpaces + maxSmallCarSpaces + maxMotorCycleSpaces;
		
		// Arrays to hold vehicles past/present.
		vehiclesInQueue = new ArrayList<Vehicle>(maxQueueSize);
		satisfiedVehicles = new ArrayList<Vehicle>();
		dissatifiedVehicles = new ArrayList<Vehicle>();
				
		// Arrays to hold numbers of each type of vehicle + a list to hold the lists.
		motorCyclesParked = new ArrayList<Vehicle>();
		carsParked = new ArrayList<Vehicle>();
		smallCarsParked = new ArrayList<Vehicle>();
		allVehiclesParked = new ArrayList<ArrayList<Vehicle>>();
		
		allVehiclesParked.add(carsParked);
		allVehiclesParked.add(smallCarsParked);
		allVehiclesParked.add(motorCyclesParked);
	}

	/**
	 * Archives vehicles exiting the car park after a successful stay. Includes transition via 
	 * Vehicle.exitParkedState(). 
	 * @param time int holding time at which vehicle leaves
	 * @param force boolean forcing departure to clear car park 
	 * @throws VehicleException if vehicle to be archived is not in the correct state 
	 * @throws SimulationException if one or more departing vehicles are not in the car park when operation applied
	 * @author Christopher Koren
	 */
	public void archiveDepartingVehicles(int time,boolean force) throws VehicleException, SimulationException {
		// Temp list used to store values grabbed from the list of Arrays.
		ArrayList<Vehicle> checkingForDepart = new ArrayList<Vehicle>();
		
		for(int i = 0; i < allVehiclesParked.size(); i++){
			for(Vehicle v : allVehiclesParked.get(i)){
				if(v.getDepartureTime() == time){
					checkingForDepart.add(v);
					satisfiedVehicles.add(v);
				}
				else{
					checkingForDepart.add(v);
				}
			}
		
			for(Vehicle v : checkingForDepart){
				unparkVehicle(v, time);

				if (v instanceof Car) {				
		            if (((Car)v).isSmall()) {
		            		if (v.getDepartureTime() == time){
		            			smallCarsParked.remove(v);
		            			numSmallCars--;
		            	 }
		            } 
		               // Else if the car is normal size.
		            else if(((Car)v).isSmall() == false){
		                if (v.getDepartureTime() == time){
		                	carsParked.remove(v);
		                	numCars--;
		                }
		               }
		           } 
		           else if (v instanceof MotorCycle){
		        	   if (v.getDepartureTime() == time){
		        		   motorCyclesParked.remove(v);
		        		   numMotorCycles--;
		        	   }
		           }
			}
			// Once the check has been complete, clear the list for future use.
			checkingForDepart.clear();
		}
	}
		
	/**
	 * Method to archive new vehicles that don't get parked or queued and are turned 
	 * away
	 * @param v Vehicle to be archived
	 * @throws SimulationException if vehicle is currently queued or parked
	 * @author Christopher Koren
	 */
	public void archiveNewVehicle(Vehicle v) throws SimulationException {
		if(v.isQueued() == true){
			throw new SimulationException("The vehicle is already in queue.");
		}
		else if(v.isParked() == true){
			throw new SimulationException("The vehicle is already parked.");
		}
		else{
			dissatifiedVehicles.add(v);
		}
	}
	
	/**
	 * Archive vehicles which have stayed in the queue too long 
	 * @param time int holding current simulation time 
	 * @throws VehicleException if one or more vehicles not in the correct state or if timing constraints are violated
	 * @author Christopher Koren
	 */
	public void archiveQueueFailures(int time) throws VehicleException {
		
		for(int i = 0; i < vehiclesInQueue.size(); i++){
            if((vehiclesInQueue.get(i).isQueued() != true)){
                    throw new VehicleException("The vehicle is not in the queue");
            }
            else if(time < 0 || time > Constants.CLOSING_TIME){
                    throw new VehicleException("The vehicle has been queued for too long");
            }
            else{
                    Vehicle v;
                    v = vehiclesInQueue.get(i);
                    /* If the vehicles been in queue for longer than the maximum queue time
                    then leave the queue and get archived. */
                    if((time - v.getArrivalTime()) > Constants.MAXIMUM_QUEUE_TIME){
                    		dissatifiedVehicles.add(v);
                            vehiclesInQueue.remove(v);
                            
                            if (v instanceof Car) {
                            	if (((Car)v).isSmall()) {
                            		carParkStatus += "|S:Q>A|";
                            	} 
                                // Else if the car is normal size.
                                else if(((Car)v).isSmall() == false){
                                    carParkStatus += "|C:Q>A|";
                                }
                            } 
                            else if (v instanceof MotorCycle){
                            	carParkStatus += "|M:Q>A|";
                            }
                            else{
                            	throw new VehicleException("Invalid vehicle types.");
                            }
                    }
           
            }
		}
	}
	
	/**
	 * Simple status showing whether carPark is empty
	 * @return true if car park empty, false otherwise
	 * @author Christopher Koren
	 */
	public boolean carParkEmpty() {
		if(getNumCars() == 0 && getNumMotorCycles() == 0)
		{
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Simple status showing whether carPark is full
	 * @return true if car park full, false otherwise
	 * @author Christopher Koren
	 */
	public boolean carParkFull() {
		if(getNumCars() >= totalSpaces)
		{
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Method to add vehicle successfully to the queue
	 * Precondition is a test that spaces are available
	 * Includes transition through Vehicle.enterQueuedState 
	 * @param v Vehicle to be added 
	 * @throws SimulationException if queue is full  
	 * @throws VehicleException if vehicle not in the correct state 
	 */
	public void enterQueue(Vehicle v) throws SimulationException, VehicleException {
	
		if(vehiclesInQueue.size() >= maxQueueSize){
			throw new VehicleException("The queue is full.");
		}
		else if(v.vehicleState == "queued"){
			throw new VehicleException("The vehicle is already queued.");
		}
		else if(v.vehicleState == "parked"){
			throw new VehicleException("The vehicle is already parked.");
		}
		else{
			vehiclesInQueue.add(v);
			v.vehicleState = "queued";
		}
	}
	
	
	/**
	 * Method to remove vehicle from the queue after which it will be parked or 
	 * archived. Includes transition through Vehicle.exitQueuedState.  
	 * @param v Vehicle to be removed from the queue 
	 * @param exitTime int time at which vehicle exits queue
	 * @throws SimulationException if vehicle is not in queue 
	 * @throws VehicleException if the vehicle is in an incorrect state or timing 
	 * constraints are violated
	 * @author Christopher Koren
	 */
	public void exitQueue(Vehicle v,int exitTime) throws SimulationException, VehicleException {
		if(v.isQueued() == false){
			throw new VehicleException("Vehicle is not in queue.");
		}
		else if(exitTime < 0){
			throw new VehicleException("Vehicle cannot exit at a negative time.");
		}
		else if(exitTime> Constants.CLOSING_TIME){
			throw new VehicleException("Car Park is closed.");
		}
		v.exitQueuedState(exitTime);
		vehiclesInQueue.remove(v);
	}
	
	/**
	 * State dump intended for use in logging the final state of the carpark
	 * All spaces and queue positions should be empty and so we dump the archive
	 * @return String containing dump of final carpark state 
	 * @author Matthew Wheeler
	 */
	public String finalState() {
		String str = "Vehicles Processed: count:" + 
				this.totalVehicleCount + ", logged: " + this.dissatifiedVehicles.size() 
				+ "\nVehicle Record: \n";
		for (Vehicle v : this.dissatifiedVehicles) {
			str += v.toString() + "\n\n";
		}
		return str + "\n";
	}
	
	/**
	 * Simple getter for number of cars in the car park 
	 * @return number of cars in car park, including small cars
	 * @author Christopher Koren
	 */
	public int getNumCars() {
		return (numCars + numSmallCars);		
	}
	
	/**
	 * Simple getter for number of motorcycles in the car park 
	 * @return number of MotorCycles in car park, including those occupying 
	 * 			a small car space
	 * @author Christopher Koren
	 */
	public int getNumMotorCycles() {
		return numMotorCycles;
	}
	
	/**
	 * Simple getter for number of small cars in the car park 
	 * @return number of small cars in car park, including those 
	 * 		   not occupying a small car space. 
	 * @author Christopher Koren
	 */
	public int getNumSmallCars() {
		return numSmallCars;
	}
	
	/**
	 * Method used to provide the current status of the car park. 
	 * Uses private status String set whenever a transition occurs. 
	 * Example follows (using high probability for car creation). At time 262, 
	 * we have 276 vehicles existing, 91 spaces in car park (P), 84 cars in car park (C), 
	 * of which 14 are small (S), 7 MotorCycles in car park (M), 48 dissatisfied (D),
	 * 176 archived (A), queue of size 9 (CCCCCCCCC), and on this iteration we have 
	 * seen: car C go from Parked (P) to Archived (A), C go from queued (Q) to Parked (P),
	 * and small car S arrive (new N) and go straight into the car park<br>
	 * 262::276::P:91::C:84::S:14::M:7::D:48::A:176::Q:9CCCCCCCCC|C:P>A||C:Q>P||S:N>P|
	 * @return String containing current state 
	 * @author Matthew Wheeler
	 */
	public String getStatus(int time) {
		String str = time +"::"
		+ totalVehicleCount + "::" 
		+ "P:" + totalSpaces + "::"
		+ "C:" + getNumCars() + "::S:" + getNumSmallCars() 
		+ "::M:" + getNumMotorCycles() 
		+ "::D:" + dissatifiedVehicles.size()
		+ "::A:" + satisfiedVehicles.size()  
		+ "::Q:" + vehiclesInQueue.size(); 
		for (Vehicle v : this.vehiclesInQueue) {
			if (v instanceof Car) {
				if (((Car)v).isSmall()) {
					str += "S";
				} else {
					str += "C";
				}
			} else {
				str += "M";
			}
		}
		
		str += this.setVehicleMsg(null, str, str);
		return str+"\n";
	}
	

	/**
	 * State dump intended for use in logging the initial state of the carpark.
	 * Mainly concerned with parameters. 
	 * @return String containing dump of initial carpark state 
	 */
	public String initialState() {
		return "CarPark [maxCarSpaces: " + this.maxCarSpaces
				+ " maxSmallCarSpaces: " + this.maxSmallCarSpaces 
				+ " maxMotorCycleSpaces: " + this.maxMotorCycleSpaces 
				+ " maxQueueSize: " + this.maxQueueSize + "]";
	}

	/**
	 * Simple status showing number of vehicles in the queue 
	 * @return number of vehicles in the queue
	 * @author Christopher Koren
	 */
	public int numVehiclesInQueue() {
		return vehiclesInQueue.size();
	}
	
	/**
	 * Method to add vehicle successfully to the car park store. 
	 * Precondition is a test that spaces are available. 
	 * Includes transition via Vehicle.enterParkedState.
	 * @param v Vehicle to be added 
	 * @param time int holding current simulation time
	 * @param intendedDuration int holding intended duration of stay 
	 * @throws SimulationException if no suitable spaces are available for parking 
	 * @throws VehicleException if vehicle not in the correct state or timing constraints are violated
	 * @author Christopher Koren
	 */
	public void parkVehicle(Vehicle v, int time, int intendedDuration) throws SimulationException, VehicleException {
		if((getNumCars() + getNumMotorCycles()) >= totalSpaces){
			throw new VehicleException("No parks available.");
		} 
		else if(v.isParked() || v.wasParked()){
			throw new VehicleException("Vehicle cannot park twice.");
		}
		else if(v.getArrivalTime() > Constants.CLOSING_TIME){
			throw new VehicleException("CarPark is closed.");
		}
		
		if(v instanceof Car){
			if(((Car) v).isSmall()){
				numSmallCars++;
				
				/* If there are any small parks available, fill those first.
				 * Else: Park in a normal park if available.
				 */
				if(smallCarsParked.size() < maxSmallCarSpaces){
					smallCarsParked.add(v);
					v.enterParkedState(time, intendedDuration);
				}
				else if(carsParked.size() < maxCarSpaces){
					carsParked.add(v);
					v.enterParkedState(time, intendedDuration);
				}
			}
			else{
				numCars++;
				if(carsParked.size() < maxCarSpaces){
					carsParked.add(v);
					v.enterParkedState(time, intendedDuration);
				}
			}
		}
		
		else if(v instanceof MotorCycle){
			numMotorCycles++;
			if(motorCyclesParked.size() < maxMotorCycleSpaces){
				motorCyclesParked.add(v);
				v.enterParkedState(time, intendedDuration);
			}
			else if(smallCarsParked.size() < maxSmallCarSpaces){
				smallCarsParked.add(v);
				v.enterParkedState(time, intendedDuration);
			}
		}
	}

	/**
	 * Silently process elements in the queue, whether empty or not. If possible, add them to the car park. 
	 * Includes transition via exitQueuedState where appropriate
	 * Block when we reach the first element that can't be parked. 
	 * @param time int holding current simulation time 
	 * @throws SimulationException if no suitable spaces available when parking attempted
	 * @throws VehicleException if state is incorrect, or timing constraints are violated
	 * @author Christopher Koren
	 */
	public void processQueue(int time, Simulator sim) throws VehicleException, SimulationException {
		archiveQueueFailures(time);
        
        Vehicle v;
        for (int i = 0; i < vehiclesInQueue.size(); i++){

                v = vehiclesInQueue.get(0);
                if (!v.isQueued()){
                        throw new VehicleException("The vehicle is not in queue.");
                }
                else if (time < 0){
                        throw new VehicleException("Time cannot be negative.");
                }
                else if(v.getArrivalTime() > Constants.CLOSING_TIME){
                	throw new VehicleException("Carpark is closed.");
                }
                
                // If there is an available space, park the vehicle.
                else if (spacesAvailable(v)){  
                        exitQueue(v, time);
                        parkVehicle(v, time, sim.setDuration());
                       
                        if (v instanceof Car){
                                if (((Car)v).isSmall()){
                                        carParkStatus += "|S:Q>P|";
                                }
                                else{
                                	carParkStatus += "|C:Q>P|";
                                }
                        }
                        else{
                        	carParkStatus += "|M:Q>P|";
                        }
                }
                else if(!spacesAvailable(v)){
                	throw new SimulationException("There are no suitable spaces to park the first in queue");
                }
        }
	}

	/**
	 * Simple status showing whether queue is empty
	 * @return true if queue empty, false otherwise
	 * @author Christopher Koren
	 */
	public boolean queueEmpty() {
		if(vehiclesInQueue.size() == 0){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * Simple status showing whether queue is full
	 * @return true if queue full, false otherwise
	 * @author Christopher Koren
	 */
	public boolean queueFull() {
		if(vehiclesInQueue.size() > maxQueueSize){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Method determines, given a vehicle of a particular type, whether there are spaces available for that 
	 * type in the car park under the parking policy in the class header.  
	 * @param v Vehicle to be stored. 
	 * @return true if space available for v, false otherwise 
	 * @author Christopher Koren
	 */
	public boolean spacesAvailable(Vehicle v) {
		// Test for Cars.
		if(v instanceof Car){
			// Test for small cars.
			if(((Car)v).isSmall()){
				if(smallCarsParked.size() < maxSmallCarSpaces){
					return true;
				}
				else if(carsParked.size() < maxCarSpaces){
					return true;
				}
			}
			
			// Test for normal cars.
			else if(((Car)v).isSmall() == false){
				if(carsParked.size() < maxCarSpaces){
					return true;
				}
			}
		}
		
		// Test for MotorCycles.
		else if(v instanceof MotorCycle){
			if(motorCyclesParked.size() < maxMotorCycleSpaces){
				return true;
			}
			else if(smallCarsParked.size() < maxSmallCarSpaces){
				return true;
			}
		}
		// Else return false. I.e. No available parks.
		return false;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CarPark [count: " + totalVehicleCount
                + " numCars: " + getNumCars()
                + " numSmallCars: " + getNumSmallCars()
                + " numMotorCycles: " + getNumMotorCycles()
                + " queue: " + numVehiclesInQueue()
                + " numDissatisfied: " + dissatifiedVehicles.size()
                + " past: " + past.size() + "]";
	}

	/**
	 * Method to try to create new vehicles (one trial per vehicle type per time point) 
	 * and to then try to park or queue (or archive) any vehicles that are created 
	 * @param sim Simulation object controlling vehicle creation 
	 * @throws SimulationException if no suitable spaces available when operation attempted 
	 * @throws VehicleException if vehicle creation violates constraints 
	 */
	public void tryProcessNewVehicles(int time,Simulator sim) throws VehicleException, SimulationException {
		if((smallCarsParked.size() + carsParked.size() + motorCyclesParked.size()) >= totalSpaces){
			throw new SimulationException("The car park is full.");
		}
		
		if(sim.newCarTrial()){
			totalVehicleCount++;
			if(sim.newCarTrial())
				if(sim.smallCarTrial()){
					idString = "SC" + numVehicleIDs;
					Vehicle newSmallCar = new Car(idString, time, true);
					
					numVehicleIDs++;
					totalVehicleCount++;
					
					if(spacesAvailable(newSmallCar)){
						parkVehicle(newSmallCar, time, newSmallCar.getParkingTime());
						numSmallCars++;
						carParkStatus += "|S:N>P|";
					}
					else if(!spacesAvailable(newSmallCar) && !queueFull()){
						enterQueue(newSmallCar);
						carParkStatus += "|S:N>Q|";
					}
					else{
						archiveNewVehicle(newSmallCar);
						carParkStatus += "|S:N>A|";
					}
				}
				else{
					idString = "C" + numVehicleIDs;
					
					Vehicle newCar = new Car(idString, time, false);
					numVehicleIDs++;
					totalVehicleCount++;
					
					if(spacesAvailable(newCar)){
						parkVehicle(newCar, time, newCar.getParkingTime());
						carParkStatus += "|S:N>P|";
						
					}
					else if(!spacesAvailable(newCar) && !queueFull()){
						enterQueue(newCar);
						carParkStatus += "|S:N>Q|";
					}
					else{
						archiveNewVehicle(newCar);
						carParkStatus += "|S:N>A|";
					}
				}
			}
		else if(sim.motorCycleTrial()){
			idString = "MC" + numVehicleIDs;
			Vehicle newMotorCycle = new MotorCycle(idString, time);
			
			numVehicleIDs++;
			totalVehicleCount++;
			
			if(spacesAvailable(newMotorCycle)){
				numVehicleIDs++;
				
				parkVehicle(newMotorCycle, time, newMotorCycle.getParkingTime());
				numMotorCycles++;
			}
			else if(!queueFull()){
				enterQueue(newMotorCycle);
				carParkStatus += "|M:N>Q|";
			}
			else{
				archiveNewVehicle(newMotorCycle);
				carParkStatus += "|M:N>A|";
			}
		}
	}

	/**
	 * Method to remove vehicle from the carpark. 
	 * For symmetry with parkVehicle, include transition via Vehicle.exitParkedState.  
	 * So vehicle should be in parked state prior to entry to this method. 
	 * @param v Vehicle to be removed from the car park 
	 * @throws VehicleException if Vehicle is not parked, is in a queue, or violates timing constraints 
	 * @throws SimulationException if vehicle is not in car park
	 * @author Christopher Koren 
	 */
	public void unparkVehicle(Vehicle v,int departureTime) throws VehicleException, SimulationException {
		if(v.isParked() == false){
			throw new VehicleException("Vehicle is not parked!");
		}
		else if(v.isQueued()){
			throw new VehicleException("Vehicle is currently in Queue!");
		}
		else if(v.getDepartureTime() < 0){
			throw new VehicleException("Invalid deperture time!");
		}
		else if(v.isQueued() == false && v.isParked() == false){
			throw new SimulationException("Vehicle doesn't exist.");
		}
		
		if(v instanceof Car){
			if(((Car) v).isSmall()){
				if(smallCarsParked.contains(v)){
					smallCarsParked.remove(v);
					carParkStatus += "|S:P>A|";
				}
				else if(carsParked.contains(v)){
					carsParked.remove(v);
					carParkStatus += "|S:P>A|";
				}
				else{
					throw new SimulationException("Cannot find vehicle.");
				}
			}
			else{
				carsParked.remove(v);
				carParkStatus += "|C:P>A|";
			}
		}
		else if(v instanceof MotorCycle){
			if(smallCarsParked.contains(v)){
				smallCarsParked.remove(v);
				carParkStatus += "|M:P>A|";
			}
			else if(motorCyclesParked.contains(v)){
				motorCyclesParked.remove(v);
				carParkStatus += "|M:P>A|";
			}
		}
	}
	
	/**
	 * Helper to set vehicle message for transitions 
	 * @param v Vehicle making a transition (uses S,C,M)
	 * @param source String holding starting state of vehicle (N,Q,P) 
     * @param target String holding finishing state of vehicle (Q,P,A) 
     * @return String containing transition in the form: |(S|C|M):(N|Q|P)>(Q|P|A)|
	 */
	private String setVehicleMsg(Vehicle v,String source, String target) {
		String str="";
		if (v instanceof Car) {
			if (((Car)v).isSmall()) {
				str += "S";
			} else {
				str += "C";
			}
		} else {
			str += "M";
		}
		return "|" +str + ":" + source + ">" + target + "|";
	}
}
