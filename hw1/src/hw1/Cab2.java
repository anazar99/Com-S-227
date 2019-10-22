package hw1;

public class Cab2 
{
	private double fare;
	
	private double rate;
	
	private double milesDriven;
	
	private double currentRate;
	
	private double totalCash;
	
	private double meter;
	
	private double averageIncomePerMile;
	
	public Cab2(double baseFare, double baseRate)
	{
		milesDriven = 0;
		rate = baseRate;
		fare = baseFare;
		currentRate = 0;
		totalCash = 0;
	}
	
	public void drive(double miles) 
	{
		milesDriven = miles + milesDriven;
		meter = meter + currentRate * miles;
	}
	
	public void pickUp()
	{
		meter = fare;
		currentRate = rate;
	}
	
	public void dropOff()
	{
		totalCash = meter + totalCash;
		meter = 0;
		currentRate = 0;
	}
	
	public double getMeter()
	{
		return meter;
	}
	
	public double getCurrentRate()
	{
		return currentRate;
	}
	
	public double getTotalCash()
	{
		return totalCash;
	}
	
	public double getTotalMiles()
	{
		return milesDriven;
	}
	
	public double getAverageIncomePerMile()
	{
		averageIncomePerMile = totalCash / milesDriven;
		return averageIncomePerMile;
	}
	public boolean hasPassenger()
	{
		return currentRate  < 0;
	}
}
