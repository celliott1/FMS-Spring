package com.fms.facility.maintenance;

public interface Employee {
	public String toString();
	public String getFirstName();
	public void setFirstName(String firstName);
	public String getLastName();
	public void setLastName(String lastName);
	public String getAssignedTask();
	public void setAssignedTask(String assignedTask);
	public String getPhoneNumber();
	public void setPhoneNumber(String phoneNumber);
	public double getSalary();
	public void setSalary(double salary);
	public int getTenure();
	public void setTenure(int tenure);
}
