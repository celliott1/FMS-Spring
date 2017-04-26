package com.fms.facility.usage;

public class PersonImpl implements Person {
	private String firstName;
	private String lastName;
	private boolean primaryOccupant;
	
	public PersonImpl(String fn, String ln){
		this.firstName = fn;
		this.lastName = ln;
		this.primaryOccupant = false;
	}
	public String toString(){
		return lastName + "." + firstName + (primaryOccupant ? ".(primary_occupant)" : "");
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isPrimaryOccupant() {
		return primaryOccupant;
	}
	public void setPrimaryOccupant(boolean primaryOccupant) {
		this.primaryOccupant = primaryOccupant;
	}
}
