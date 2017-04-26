package com.fms.facility.usage;

public interface Person {
	public String toString();
	public String getFirstName();
	public void setFirstName(String firstName);
	public String getLastName();
	public void setLastName(String lastName);
	public boolean isPrimaryOccupant();
	public void setPrimaryOccupant(boolean primaryOccupant);
}
