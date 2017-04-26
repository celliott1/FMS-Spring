package com.fms.facility.usage;

import java.util.ArrayList;

import com.fms.facility.maintenance.MaintenanceImpl;
import com.fms.facility.usage.*;

public class UnitImpl implements Unit {
	private boolean occupied;
	private String name;
	private ArrayList<PersonImpl> occupants;
	private ArrayList<MaintenanceImpl> outstandingMaintenance;
	private ArrayList<MaintenanceImpl> previousMaintenance;
	private ArrayList<UtilitiesImpl> utilities;
	private unitRecordImpl ur;
	private unitRemodelImpl urem;
	public UnitImpl(String name, boolean occupied){
		this.name = name;
		this.occupied = occupied;
		this.occupants = new ArrayList<PersonImpl>();
		this.outstandingMaintenance = new ArrayList<MaintenanceImpl>();
		this.previousMaintenance = new ArrayList<MaintenanceImpl>();
		this.utilities = new ArrayList<UtilitiesImpl>();
	}
	public String toString(){
		String rtn = name + " occupied:[" + occupied + "]" 
				+ (occupants.size() > 0 ? ", Occupants: " : "");
		for(PersonImpl p : occupants){
			rtn += p.toString() + ",";
		}
		return rtn;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<PersonImpl> getOccupants() {
		return occupants;
	}
	public void addOccupants(PersonImpl occupant) {
		this.occupants.add(occupant);
	}
	public ArrayList<MaintenanceImpl> getOutstandingMaintenance() {
		return outstandingMaintenance;
	}
	public void addOutstandingMaintenance(MaintenanceImpl outstandingMaintenance) {
		this.outstandingMaintenance.add(outstandingMaintenance);
	}
	public ArrayList<MaintenanceImpl> getPreviousMaintenance() {
		return previousMaintenance;
	}
	public void addPreviousMaintenance(MaintenanceImpl previousMaintenance) {
		this.previousMaintenance.add(previousMaintenance);
	}
	public ArrayList<UtilitiesImpl> getUtilities(){
		return utilities;
	}
	public void addUtilities(UtilitiesImpl utilities){
		this.utilities.add(utilities);
	}
	public void listUtilities(UtilitiesImpl utilities){
		System.out.println("The total cost of utilities for this unit is: " + utilities.getTotalBill());
	}
	public unitRecordImpl getUnitRecord(){
		return ur;
	}
	public void setUnitRecord(unitRecordImpl ur){
		this.ur = ur;
	}
	public unitRemodelImpl getUnitRemodel(){
		return urem;
	}
	public void setUnitRemodel(unitRemodelImpl urem){
		this.urem = urem;
	}
}
