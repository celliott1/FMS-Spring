package com.fms.facility.details;

import java.util.ArrayList;

import com.fms.facility.maintenance.InspectionImpl;
import com.fms.facility.usage.PersonImpl;
import com.fms.facility.usage.Unit;
import com.fms.facility.usage.UnitImpl;
import com.fms.facility.maintenance.*;
import com.fms.facility.details.*;

import java.lang.StringBuilder;

public class FacilityImpl implements Facility {
	private int id;
	private ArrayList<UnitImpl> units;
	private String name;
	private String description;
	private String address;
	// default to empty string "", empty string means there is no usage message.
	private String curUsage;
	private ArrayList<InspectionImpl> inspections;
	private ArrayList<EmployeeImpl> employees;
	private FinancesImpl finances;
	private improvementProjectsImpl improvement;
	private officeRosterImpl roster;
	private serviceContractsImpl service;
	
	public FacilityImpl(){
		units = new ArrayList<UnitImpl>();
		inspections = new ArrayList<InspectionImpl>();
		this.employees = new ArrayList<EmployeeImpl>();
		this.curUsage = "";
	}
	public void addUnit(UnitImpl unit){
		units.add(unit);
	}
	public void printUnits(){
		for(UnitImpl u : units){
			System.out.println(u.toString());
		}
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Facility: " + name);
		sb.append("; Address: " + address);
		sb.append("; Description: " + description);
		sb.append("; Units: ");
		for(Unit u : units){
			sb.append("{" + u.toString() + "}");
		}
		return sb.toString();
	}
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ArrayList<UnitImpl> getUnits() {
		return units;
	}
	public String getCurUsage() {
		return curUsage;
	}
	public void setCurUsage(String curUsage) {
		this.curUsage = curUsage;
	}
	public ArrayList<InspectionImpl> getInspections() {
		return inspections;
	}
	public void addInspection(InspectionImpl inspection) {
		this.inspections.add(inspection);
	}
	public ArrayList<EmployeeImpl> getEmployees() {
		return employees;
	}
	public void addEmployees(EmployeeImpl employee) {
		this.employees.add(employee);
	}
	public ArrayList<EmployeeImpl> listEmployees(FacilityImpl f){
		if(true) {
			System.out.print("Listing employees for facility: ");
			if(f.getEmployees().size() == 0){
				System.out.print("There are no employees");
			}
			for(EmployeeImpl e : f.getEmployees()){
				System.out.print(e.toString() + ",");
			}
		}
		System.out.println();
		return f.getEmployees();
	}
	public FinancesImpl getFinances() {
		return finances;
	}
	public void setFinances(FinancesImpl finances) {
		this.finances = finances;
	}
	public improvementProjectsImpl getImprovementProjects(){
		return improvement;
	}
	public void setImprovementProjects(improvementProjectsImpl improvement){
		this.improvement = improvement;
	}
	public  officeRosterImpl getOfficeRoster(){
		return roster;
	}
	public void setOfficeRoster(officeRosterImpl roster){
		this.roster = roster;
	}
	public serviceContractsImpl getServiceContracts(){
		return service;
	}
	public void setServiceContracts(serviceContractsImpl service){
		this.service = service;
	}
}
