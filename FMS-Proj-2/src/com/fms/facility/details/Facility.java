package com.fms.facility.details;

import java.util.ArrayList;

import com.fms.facility.maintenance.EmployeeImpl;
import com.fms.facility.maintenance.InspectionImpl;
import com.fms.facility.usage.UnitImpl;
import com.fms.facility.details.*;

public interface Facility {
	public void addUnit(UnitImpl unit);
	public void printUnits();
	public String toString();
	public int getId();
	public void setId(int id);
	public String getName();
	public void setName(String name);
	public String getDescription();
	public void setDescription(String description);
	public String getAddress();
	public void setAddress(String address);
	public ArrayList<UnitImpl> getUnits();
	public String getCurUsage();
	public void setCurUsage(String curUsage);
	public ArrayList<InspectionImpl> getInspections();
	public void addInspection(InspectionImpl inspection);
	public ArrayList<EmployeeImpl> getEmployees();
	public void addEmployees(EmployeeImpl employee);
	public ArrayList<EmployeeImpl> listEmployees(FacilityImpl f);
	public FinancesImpl getFinances();
	public void setFinances(FinancesImpl finances);
	public improvementProjectsImpl getImprovementProjects();
	public void setImprovementProjects(improvementProjectsImpl improvement);
	public  officeRosterImpl getOfficeRoster();
	public void setOfficeRoster(officeRosterImpl roster);
	public serviceContractsImpl getServiceContracts();
	public void setServiceContracts(serviceContractsImpl service);
	
}
