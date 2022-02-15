import java.util.Scanner;
import java.util.ArrayList;

public class Elevator {
		public static void main(String[] args) {
			Elevator e = new Elevator();
			e.askPassenger();
		}
		
		void start() {
			System.out.println("Welcome to elevator");
			System.out.println("This was programmed by Truc Nguyen '25");
			System.out.println("Let's get it started!!!");
			System.out.println("========================================================================");
			delay(1500);
		}
		// variable 
		
		final int maxF = 20;
		final int minF = 1;
		final int maxP = 20;
		final int minP = 1;
		int curF = 1;
		int desF = 0;
		int passF = 0;
		int numOfPass = 0;
		boolean isDoorOpen;
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> listOfFloor;
		int[] destination = new int[maxF];
	// method to go up	
	void up() {
		System.out.println( curF++ + "F" + " Going up...");
	}
	//method to go down
	void down() {
		System.out.println( curF-- + "F" + " Going down...");
	}
	//method to delay the running screen so it is easier for users to keep track
	void delay(int ms) {
	try {
		Thread.sleep(ms);}
	catch (Exception e) {};
	}
	// This method is the algorithm I used to prioritize the nearest destination. 
	int findShortest() {
		int shortest = Math.abs(curF - listOfFloor.get(0));
		int id = 0;
		for(int i = 1; i < listOfFloor.size(); i++) {
			if (shortest > Math.abs(curF - listOfFloor.get(i)))
			{
				shortest = Math.abs(curF - listOfFloor.get(i));
				id = i;
			}
		}
		shortest = listOfFloor.get(id);
		listOfFloor.set(id,100);
		return shortest;
	}
	// This is where every function of the elevator is called
	void startElevator() {
		for(int i = 0; i < listOfFloor.size(); i++) {
			int shortest = findShortest();
			System.out.println("Next destination: " + shortest+ "F " + "| " + destination[shortest-1] + " passenger");
			delay(1500);
		while(curF < shortest) {up();}
		while(curF > shortest) {down();}
		while(destination[shortest-1] > 0) {
			System.out.println("Unloading passenger " +destination[shortest-1]-- + " at " + curF + "F");
			delay(1500);
		}
	}
		askPassenger();
	}
	// this method ask for the number of passenger and check that value if it is available or not. 
	void askPassenger() {
		isDoorOpen = false;
		System.out.println("Door is opening...");
		delay(1500);
		isDoorOpen = true;
		System.out.println("How many passengers: ");
		numOfPass = sc.nextInt();
		if(numOfPass <= 0 || numOfPass > 20) {
			System.out.println("Please enter valid number!!");
			askPassenger();
		}
		else {
			listOfFloor = new ArrayList<>();
			for(int i = 0; i < numOfPass; i++) {
				int floor = askFloor(i);
				if(!listOfFloor.contains(floor)) listOfFloor.add(floor);
			}
		}
		System.out.println("Door is closing...");
		delay(1500);
		isDoorOpen = false;
		startElevator();
	}
	
	// This method asks each passenger with different id to put in the destination they want to go. And return the destination 
	int askFloor(int id) {
		boolean valid = false;
		int floor = 0;
		while(!valid) {
			System.out.println("Passenge #" + (id + 1) + " please enter your destination");
			floor = sc.nextInt();
			if(floor > 20 || floor <= 0)
			{
				System.out.println("Please enter the valid floor!!");
			}
			else if (floor == curF) {
				System.out.println("You are at your destination!!");		
			}
			else {
				destination[floor-1]++;
				valid = true;
			}
			}
		return floor;
	}
	
}
