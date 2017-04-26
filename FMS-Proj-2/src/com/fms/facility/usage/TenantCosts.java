package com.fms.facility.usage;

public interface TenantCosts {
	public double getRentCost();
	public void setRentCost(double rentCost);
	public double getPetCost();
	public void setPetCost(double petCost);
	public double getParkingCost();
	public void setParkingCost(double parkingCost);
	public double getLockoutCost();
	public void setLockoutCost(double lockoutCost);
}
