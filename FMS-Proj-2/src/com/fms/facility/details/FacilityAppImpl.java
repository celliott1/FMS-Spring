package com.fms.facility.details;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.fms.facility.usage.UnitImpl;
import com.fms.facility.maintenance.*;
import com.fms.facility.usage.*;
import com.fms.facility.view.*;
import com.fms.facility.*;

public class FacilityAppImpl implements FacilityApp{
	//arraylist use for storage
	private ArrayList<FacilityImpl> storage;
	public boolean PRINT = true;
	public FacilityAppImpl(){
		storage = new ArrayList<FacilityImpl>();
	}
	// listFacilities: list facilities 
	public ArrayList<FacilityImpl> listFacilities(){
		if(PRINT){
			if(storage.size() == 0) System.out.println("There are no facilities at the moment");
			for(FacilityImpl f : storage){
				System.out.println(f.getName().toString());
			}
		}
		System.out.println();
		return storage;
	}
	// getFacilityInformation: basic details of facility (2 floors, 4000 sq ft)
	public String getFacilityInformation(FacilityImpl f) {
		if(PRINT){
			System.out.println(f.toString());
		}
		System.out.println();
		return f.toString();
	}
	// requestAvailableCapacity: How many units are there in the building
	public int requestAvailableCapacity(FacilityImpl f) {
		if(PRINT){
			System.out.println("Facility contains " + f.getUnits().size() + " units");
		}
		System.out.println();
		return f.getUnits().size();
	}
	// addNewFacility: adds a new facility
	public void addNewFacility(FacilityImpl f){
		this.storage.add(f);
	}
	public void addFacilityDetail(FacilityImpl f, String facilityDescription){
		f.setDescription(facilityDescription);
	}
	// returns true if removed successful, false otherwise
	// (false indicates the input facility id does not match any facility in our storage
	// removes the facility with the input facility id
	public boolean removeFacility(int id) {
		for(int i = 0; i < storage.size(); i += 1) {
			FacilityImpl f = storage.get(i);
			if(f.getId() == id){
				storage.remove(i);
				if(PRINT) {
					System.out.println("Removed facility with id " + id);
				}
				System.out.println();
				return true;
			}
		}
		if(PRINT) {
			System.out.println("Facility with id " + id + " is not stored in the storage");
		}
		System.out.println();
		return false;
	}
	
	// returns true if the any of the units in the facility is currently being used
	// false if the facility is *completely* vacant
	public boolean isInUseDuringInterval(FacilityImpl f) {
		for(UnitImpl u : f.getUnits()){
			if(u.isOccupied()) return true;
		}
		return false;
	}
	// assignFacilityToUse: set usage of facility to 
	// something like: apartment building currently renting units
	public void assignFacilityToUse(FacilityImpl f, String usage){
		f.setCurUsage(usage);
	}
	// vacateFacility: clear all units of tenants
	public void vacateFacility(FacilityImpl f){
		for(UnitImpl u : f.getUnits()){
			u.setOccupied(false);
			u.getOccupants().clear();
		}
	}
	// listInspections: What inspections have been done at the facility in the past? 
	// Damage inspection for example.
	public ArrayList<InspectionImpl> listInspections(FacilityImpl f){
		if(PRINT) {
			System.out.print("Listing inspection for facility with id " + f.getId() + ": ");
			if(f.getInspections().size() == 0){
				System.out.print("There are no inspections.");
			}
			for(Inspection i : f.getInspections()){
				System.out.print(i.toString() + ".");
			}
		}
		System.out.println();
		return f.getInspections();
	}
	
	public ArrayList<InspectionImpl> listEmployees(FacilityImpl f){
		if(PRINT) {
			System.out.print("Listing inspection for facility with id " + f.getId() + ":");
			if(f.getInspections().size() == 0){
				System.out.print("There are no inspections");
			}
			for(InspectionImpl i : f.getInspections()){
				System.out.print(i.toString() + ",");
			}
		}
		System.out.println();
		return f.getInspections();
	}
	// listActualUsage: how many units are currently occupied
	public int listActualUsage(FacilityImpl f){
		int rtn = 0;
		for(UnitImpl u : f.getUnits()){
			if(u.isOccupied()) rtn += 1;
		}
		if(PRINT) {
			System.out.println("Facility with id " + f.getId() + " has " + rtn + " occupied units.");
		}
		System.out.println();
		return rtn;
	}
	// calcUsageRate: what is the percentage of occupied units vs empty
	public double calcUsageRate(FacilityImpl f) {
		int occupied = 0;
		for(UnitImpl u : f.getUnits()){
			if(u.isOccupied()) occupied += 1;
		}
		double rtn = occupied == 0 ? 0.0 : (double) occupied / f.getUnits().size();
		if(PRINT) {
			System.out.println("Facility with id " + f.getId() + " has a usage rate of " + rtn + ".");
		}
		System.out.println();
		return rtn;
	}
	// makeFacilityMaintRequest: start new maintenance request, unit 201 has a leaky faucet
	public void makeFacilityMaintRequest(UnitImpl u, MaintenanceImpl maintenance) {
		u.addOutstandingMaintenance(maintenance);
	}
	// scheduleMaintenance: what date will maintenance be occurring on
	public void scheduleMaintenance(MaintenanceImpl maintenance, Date date){
		maintenance.setRepairDate(date);
	}
	// how much will it cost to fix faucet, just make an example number: $20 parts, $20 labor
	public double calcMaintenanceCostForFacility(FacilityImpl f) {
		double rtn = 0;
		for(UnitImpl u : f.getUnits()){
			for(MaintenanceImpl m : u.getOutstandingMaintenance()){
				rtn += m.getCosts();
			}
		}
		if(PRINT) {
			System.out.println("It will cost " + rtn + " dollars to finish all outstanding maintenance for facility " + f.getId());
		}
		System.out.println();
		return rtn;
	}
	// calcProblemRateForFacility: how many units have issues vs those that don't
	public double calcProblemRateForFacility(FacilityImpl f) {
		int problemUnits = 0;
		for(UnitImpl u : f.getUnits()){
			if(u.getOutstandingMaintenance().size() > 0) problemUnits += 1;
		}
		double rtn = problemUnits == 0 ? 0.0 : (double) problemUnits / f.getUnits().size();
		if(PRINT) {
			System.out.println("Facility with id " + f.getId() + " has a problem rate of " + rtn + ".");
		}
		System.out.println();
		return rtn;
	}
	// calcDownTimeForFacility: how many days until problem is fixed for a Unit
	public double calcDownTimeForFacility(UnitImpl u){
		double downTime = 0;
		for(MaintenanceImpl m : u.getOutstandingMaintenance()){
			long diff = m.getRepairDate().getTime() - new Date().getTime();
			double days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			if(diff > 0) days += 1;
			days += m.getRepairDays();
			if(days > downTime) downTime = days; 
		}
		if(PRINT) {
			System.out.println("The input unit need " + downTime + " day to fix all problems.");
		}
		System.out.println();
		return downTime;
	}
	// listMaintRequests: how many active maintenance requests are there
	public ArrayList<MaintenanceImpl> listMaintRequests(UnitImpl u){
		if(PRINT){
			if(u.getOutstandingMaintenance().size() == 0){
				System.out.println("The input unit has NO outstanding maintenance requests");
			} else {
				System.out.println("The input unit has the following outstanding maintenance requests:" + u.getOutstandingMaintenance());
			}
		}
		System.out.println();
		return u.getOutstandingMaintenance();
	}
	// listMaintenance: what past maintenance events have been done
	public ArrayList<MaintenanceImpl> listMaintenance(UnitImpl u) {
		if(PRINT){
			if(u.getPreviousMaintenance().size() == 0){
				System.out.println("The input unit has NO previous maintenance requests");
			} else {
				System.out.println("The input unit has the following finished maintenance requests:" + u.getPreviousMaintenance());
			}
		}
		System.out.println();
		return u.getPreviousMaintenance();
	}
	// list all outstanding maintenance requests within a facility
	public ArrayList<MaintenanceImpl> listFacilityProblems(FacilityImpl f){
		ArrayList<MaintenanceImpl> rtn = new ArrayList<MaintenanceImpl>();
		for(UnitImpl u : f.getUnits()){
			rtn.addAll(u.getOutstandingMaintenance());
		}
		if(PRINT){
			if(rtn.size() == 0)
				System.out.println("The input facility has NO outstanding maintenance requests.");
			else
				System.out.println("The input facility has the following outstanding maintenance requests:" + rtn);
		}
		System.out.println();
		return rtn;
	}
}
