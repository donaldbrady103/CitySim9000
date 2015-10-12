import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;

public class CitySim9000Test {
	
	//Calling setCurrentLocation should update the current location of a car.
	//Create a car with an initial location and change that location to another location.
	//Returning the location should be the same location as the Location entered in setCurrentLocation.
	@Test
	public void testSetCarLocation(){
		Location loc = mock(Location.class);
		Location mockLocation = mock(Location.class);
		Car c = new Car("name", loc);
		c.setCurrentLocation(mockLocation);
		assertSame(c.getCurrentLocation(), mockLocation);
	}
	
	//The program should only continue if there is exactly 1 argument.
	//Pass a String array of size 1 into the function.
	//Assert that that this returns a true value.
	@Test
	public void testCheckArgs(){
		String [] args = new String[1];
		assertTrue(CitySim9000.checkNumArgs(args));
	}
	
	//The program should only continue if there is exactly 1 argument.
	//Pass a String array of a greater than 1 size into the function.
	//Assert that that this returns a false value.
	@Test
	public void testCheckArgsMore(){
		String [] args = new String[2];
		assertFalse(CitySim9000.checkNumArgs(args));
	}
	
	//The program should only continue if there is exactly 1 argument.
	//Pass a String array of size 0 into the function.
	//Assert that that this returns a false value.
	@Test
	public void testCheckArgsLess(){
		String [] args = new String[0];
		assertFalse(CitySim9000.checkNumArgs(args));
	}
	
	//The seed for the random number generator must be an integer.
	//Pass a String that contains an integer into the function.
	//Assert that true is returned for a normal integer.
	@Test
	public void testIsInteger() {
		String testString = "123";
		assertTrue(CitySim9000.isInteger(testString));
	}
	
	//The seed for the random number generator must be an integer.
	//Pass a String that contains a negative integer into the function.
	//Assert that true is returned for a normal integer.
	@Test
	public void testIsIntegerNeg() {
		String testString = "-123";
		assertTrue(CitySim9000.isInteger(testString));
	}
	
	//The seed for the random number generator must be an integer.
	//Pass a String that contains an integer greater than the int data structure's limits.
	//Assert that this test fails since Random can only accept data type int.
	@Test
	public void testIsIntegerBig() {
		String testString = "9999999999";
		assertFalse(CitySim9000.isInteger(testString));
	}
	
	//The seed for the random number generator must be an integer.
	//Pass a String that contains a negative integer greater than the int data structure's limits.
	//Assert that this test fails since Random can only accept data type int.
	@Test
	public void testIsIntegerBigNeg() {
		String testString = "-9999999999";
		assertFalse(CitySim9000.isInteger(testString));
	}
	
	//The seed for the random number generator must be an integer.
	//Pass a String that contains not only an integer
	//Assert that this test fails since an integer is desired.
	@Test
	public void testIsInteger2() {
		String testString = "-11Hello World!";
		assertFalse(CitySim9000.isInteger(testString));
	}
	
	//The name for each car should be able to be returned via a class.
	//Create a car with a name.
	//Assert that the returned name is equal to the name you used to create the car.
	@Test
	public void testGetName() {
		Location mockLocation = mock(Location.class);
		Car c = new Car("car1", mockLocation);
		assertEquals(c.getName(), "car1");
	}
	
	//A Car should have a returnable current location.
	//Create a car with an initial location.
	//Assert that returning the current location is equal to the initial location.
	@Test
	public void testGetCurrentLocation(){
		Location mockLocation = mock(Location.class);
		Car c = new Car("car1", mockLocation);
		assertSame(mockLocation, c.getCurrentLocation());
	}
	
	//A location should have a returnable name.
	//Create a new location with an initial name.
	//Assert that the name is equal to the initial name.
	@Test
	public void testLocationGetName(){
		Location loc = new Location("loc", "astreet", 1, "bstreet", 2);
		assertEquals("loc", loc.getName());
	}
	
	//A location should be able to be reached from another Location.
	//Create locations that point to each other.
	//Create a mock car that returns a value.
	//Assert that the location traveled to is the expected location.
	@Test
	public void testLocationGoToNextLocation(){
		Location[] _locs = new Location[5];
		_locs[0] = new Location("Mall", "Fourth Ave", 1, "Meow St", 3);
		_locs[1] = new Location ("Bookstore", "Fourth Ave", 4, "Chirp St", 2);
		_locs[2] = new Location ("University", "Fifth Ave", 3, "Chirp St", 1);
		_locs[3] = new Location ("Coffee", "Fifth Ave", 4, "Meow St", 0);
		_locs[4] = new Location ("Outside City", "Fourth Ave", 0, "Fifth Ave", 2);
		Car mockCar = mock(Car.class);
		when(mockCar.getName()).thenReturn("name");
		when(mockCar.getCurrentLocation()).thenReturn(_locs[0]);
		assertSame(_locs[1], _locs[0].goToNextLocation(mockCar, 2, _locs));
				
	}

	//A location should be able to be reached from another Location.
	//Create locations that point to each other.
	//Create a mock car that returns a value.
	//Assert that the location traveled to is the expected location.
	@Test
	public void testLocationGoToNextLocation2(){
		Location[] _locs = new Location[5];
		_locs[0] = new Location("Mall", "Fourth Ave", 1, "Meow St", 3);
		_locs[1] = new Location ("Bookstore", "Fourth Ave", 4, "Chirp St", 2);
		_locs[2] = new Location ("University", "Fifth Ave", 3, "Chirp St", 1);
		_locs[3] = new Location ("Coffee", "Fifth Ave", 4, "Meow St", 0);
		_locs[4] = new Location ("Outside City", "Fourth Ave", 0, "Fifth Ave", 2);
		Car mockCar = mock(Car.class);
		when(mockCar.getName()).thenReturn("name");
		when(mockCar.getCurrentLocation()).thenReturn(_locs[0]);
		assertSame(_locs[3], _locs[0].goToNextLocation(mockCar, 1, _locs));
				
	}
	
	//A location should be able to be reached from another Location.
	//Create locations that point to each other.
	//Create a mock car that returns a value.
	//Assert that the location traveled to is the expected location.
	@Test
	public void testLocationGoToNextLocationStartOutside(){
		Location[] _locs = new Location[5];
		_locs[0] = new Location("Mall", "Fourth Ave", 1, "Meow St", 3);
		_locs[1] = new Location ("Bookstore", "Fourth Ave", 4, "Chirp St", 2);
		_locs[2] = new Location ("University", "Fifth Ave", 3, "Chirp St", 1);
		_locs[3] = new Location ("Coffee", "Fifth Ave", 4, "Meow St", 0);
		_locs[4] = new Location ("Outside City", "Fourth Ave", 0, "Fifth Ave", 2);
		Car mockCar = mock(Car.class);
		when(mockCar.getName()).thenReturn("name");
		when(mockCar.getCurrentLocation()).thenReturn(_locs[4]);
		assertSame(_locs[0], _locs[4].goToNextLocation(mockCar, 2, _locs));
				
	}
	
	//The simulation should run properly given the appropriate argument.
	//Run the simulation passing a valid argument - an integer.
	//Assert that the simulation runs correctly.
	@Test
	public void testRunSim(){
		assertTrue(CitySim9000.runSim("13241"));
	}
	
	//The simulation should run properly given the appropriate argument.
	//Run the simulation passing a valid argument - a non integer value.
	//Assert that the simulation fails.
	@Test
	public void testRunSimFail(){
		assertFalse(CitySim9000.runSim("string"));
	}

}