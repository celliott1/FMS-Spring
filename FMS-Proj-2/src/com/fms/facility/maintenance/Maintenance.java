package com.fms.facility.maintenance;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fms.facility.usage.PersonImpl;

public interface Maintenance {
	public String toString();
	public String getDescription();
	public void setDescription(String description);
	public int getRepairDays();
	public void setRepairDays(int repairDays);
	public PersonImpl getEmployee();
	public void setEmployee(PersonImpl employee);
	public String getComments();
	public void setComments(String comments);
	public double getCosts();
	public void setCosts(double costs);
	public int getId();
	public void setId(int id);
	public Date getRepairDate();
	public void setRepairDate(Date repairDate);
}


