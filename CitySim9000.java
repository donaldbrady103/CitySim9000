import java.util.*;

/**
 * CitySim9000 class is used to generate the city have explore it.
 * @author Donald Brady		djb88@pitt.edu
 */
public class CitySim9000 {
	/**
	 * The main of the program. This sets up shop and runs the program
	 * @param args
	 */
	public static void main(String[] args){
		
		
		
		//check the number of arguments
		if(!checkNumArgs(args)){
			System.exit(1);
		}
			
		
		//make sure the argument is an integer
		if(!isInteger(args[0])){
			System.exit(1);
		}
		
		
		
		
		//main guts of the whole program
		runSim(args[0]);
	}
	
	/**
	 * Returns void, but exits the program if there are not the required
	 * number of arguments entered on the command line
	 * @param args This is the arguments array from the command line
	 */
	public static boolean checkNumArgs(String[] args){
		if(args.length <=0 || args.length > 1){
			System.out.println("Invalid arguments, please try again using a single integer as an argument.");
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Parses the command line arguments and to determine if it is an integer or not.
	 * If there is no integer, it returns false
	 * @param arg The command line arguments
	 * @return boolean
	 */
	public static boolean isInteger(String arg){
		
		try{
			Integer.parseInt(arg);
			return true;
		}
		catch(Exception e){
			System.out.println("Invalid arguments, please try again using a single integer as an argument");
			return false;
		}
		
	}
	
	public static boolean runSim(String args){
		try{
			//Defined number of cars for the program
			final int NUM_CARS = 5;
			//generate the city in the form of Locations Array
			Location[] _locs = new Location[5];
			_locs[0] = new Location("Mall", "Fourth Ave", 1, "Meow St", 3);
			_locs[1] = new Location ("Bookstore", "Fourth Ave", 4, "Chirp St", 2);
			_locs[2] = new Location ("University", "Fifth Ave", 3, "Chirp St", 1);
			_locs[3] = new Location ("Coffee", "Fifth Ave", 4, "Meow St", 0);
			_locs[4] = new Location ("Outside City", "Fourth Ave", 0, "Fifth Ave", 2);
			Random rand = new Random(Integer.parseInt(args));
			for (int i = 0; i < NUM_CARS; i++){
				
				//Initialize each new car
				Car c = new Car ("Driver " + i, _locs[Math.abs(rand.nextInt() % _locs.length)]);
								
				//traverse the city
				do{
					//move the car from one location to the next
					c.setCurrentLocation(c.getCurrentLocation().goToNextLocation(c, rand.nextInt(), _locs));
				}while(!c.getCurrentLocation().equals(_locs[4])); //_locs[4] = outside city
				System.out.println(c.getName()+ " has left the city!");
				System.out.println("-----");
			}
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
}

/**
 * Simple car class that holds the location and car name
 *
 */
class Car{
	private String name;
	private Location currentLocation;
	
	/**
	 * Constructs the car giving it a name
	 * @param n String to name the car
	 */
	public Car(String n, Location loc){
		name = n;
		currentLocation = loc;
	}
	
	/**
	 * Sets the current location of the car
	 * @param loc The location to set as the current location
	 */
	public boolean setCurrentLocation(Location loc){
		currentLocation = loc;
		return true;
	}
	
	/**
	 * Returns the current location of the car
	 * @return currentLocation
	 */
	public Location getCurrentLocation(){
		return currentLocation;
	}
	
	/**
	 * Returns the name of the car
	 * @return name, the car's name
	 */
	public String getName(){
		return name;
	}
}

/**
 * Locations are objects that are used to create a city when used in a Location array
 *
 */
class Location{
	private String locationName;
	private String aStreet;
	private int aLocation;
	private String bStreet;
	private int bLocation;
	
	/**
	 * Constructs the location
	 * @param locName Name of the location
	 * @param aSt Name of one of the two streets
	 * @param aLoc index of the next location along aSt
	 * @param bSt Name of the second street
	 * @param bLoc index of the location of the second location
	 */
	public Location(String locName, String aSt, int aLoc, String bSt, int bLoc){
		locationName = locName;
		aStreet = aSt;
		aLocation = aLoc;
		bStreet = bSt;
		bLocation = bLoc;
	
	}
	
	/**
	 * Returns the name of the location
	 * @return locationName, name of the location
	 */
	public String getName(){
		return locationName;
	}
	
	/**
	 * Returns the location that is to be traveled to by car c next
	 * @param c The car that is traveling
	 * @param rand Randomly generated number to decide where to go
	 * @param _locs Array of locations
	 * @return Location that should be traveled to next
	 */
	public Location goToNextLocation(Car c, int rand, Location[] _locs){
		Location nextLocation;
		String viaStreet;
		
		//choose which street to use via modular division
		if(rand % 2 == 0){
			nextLocation = _locs[aLocation];
			viaStreet = aStreet;
		}
		else{
			nextLocation = _locs[bLocation];
			viaStreet = bStreet;
		}
		System.out.println(c.getName() + " heading from " + c.getCurrentLocation().getName() + " to " + nextLocation.getName() + " via " + viaStreet + ".");
		
		return nextLocation;
	}
	
}