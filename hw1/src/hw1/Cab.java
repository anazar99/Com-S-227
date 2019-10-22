package hw1;
/**
 * COM S 227 - Assignment 1 
 * code which simulates a simple cab and a few operations
 * @author Ahmad M. Nazar
 *
 */
public class Cab 
{
	/**
	 * fare amount
	 */
	private double fare;
	
	/**
	 * mileage rate for cab
	 */
	private double rate;
	
	/**
	 * value for miles driven
	 */
	private double milesDriven;
	
	/**
	 * total amount of cash 
	 */
	private double totalCash;
	
	/**
	 * value of the meter 
	 */
	private double meter;
	
	/**
	 * value for the current rate
	 */
	private double currentRate;
	
	/**
	 * value for the average income per mile
	 */
	private double avgIncomePerMile;
	
	
	/**
	 * creates a new Cab
	 * @param baseFare 
	 * @param mileRate
	 * new cab with base fare and mile rate entered
	 */
	public Cab (double baseFare, double mileRate)
	{
		milesDriven = 0;
		rate = mileRate;
		fare = baseFare;
		currentRate = 0;
		totalCash = 0;
	}
	/**
	 * gives the value of the miles driven
	 * @return
	 * returns the total number of miles
	 */
	public double getTotalMiles()
	{
		return milesDriven;
	}
	
	/**
	 * value of the meter
	 * @return
	 * returns the value of the meter depending on the current rate
	 */
	public double getMeter()
	{
		return meter;
	}
	
	/**
	 * method that gives the value of the current rate
	 * @return
	 * returns the value of the current rate depending on if there is a passenger or not
	 */
	public double getCurrentRate()
	{
		return currentRate;
	}
	
	/**
	 * gives the value of total cash earned
	 * @return
	 * returns the value of total cash based on miles driven and passenger fare
	 */
	public double getTotalCash()
	{
		return totalCash;
	}
	
	/**
	 * simulates the cab to drive
	 * @param miles
	 * updates the miles driven to the given miles and updates the meter based on the rate
	 */
	public void drive(double miles)
	{
		milesDriven = milesDriven + miles;
		meter = meter + currentRate * miles;
	}
	
	/**
	 * simulates a pick up of passenger and makes the rate = current rate and the meter = fare
	 */
	public void pickUp() 
	{
		currentRate = rate; 
		meter = fare; 
	}
	
	/**
	 * simulates the drop off of a passenger by updating total cash and reseting the meter and current rate
	 */
	public void dropOff()
	{
		totalCash = totalCash + meter;
		meter = 0;
		currentRate = 0;
	}
	
	/**
	 * takes the total cash and divides it by the total miles driven
	 * @return
	 * returns the average income per mile
	 */
	public double getAverageIncomePerMile()
	{
		avgIncomePerMile = totalCash / milesDriven;
		return avgIncomePerMile;
	}
	
	/**
	 * boolean that checks if the cab has a passenger or not
	 * @return
	 * returns true or false based on whether the current rate is greater than 0 or not
	 */
	public boolean hasPassenger()
	{
		return currentRate > 0;
	}
	
}
