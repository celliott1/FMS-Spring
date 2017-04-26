package com.fms.facility.details;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.fms.facility.maintenance.InspectionImpl;
import com.fms.facility.maintenance.MaintenanceImpl;
import com.fms.facility.usage.UnitImpl;

public interface FacilityApp {
	public ArrayList<FacilityImpl> listFacilities();
	public String getFacilityInformation(FacilityImpl f);
	public int requestAvailableCapacity(FacilityImpl f);
	public void addNewFacility(FacilityImpl f);
	public void addFacilityDetail(FacilityImpl f, String facilityDescription);
	public boolean removeFacility(int id);
	public boolean isInUseDuringInterval(FacilityImpl f);
	public void assignFacilityToUse(FacilityImpl f, String usage);
	public void vacateFacility(FacilityImpl f);
	public ArrayList<InspectionImpl> listInspections(FacilityImpl f);
	public ArrayList<InspectionImpl> listEmployees(FacilityImpl f);
	public int listActualUsage(FacilityImpl f);
	public double calcUsageRate(FacilityImpl f);
	public void makeFacilityMaintRequest(UnitImpl u, MaintenanceImpl maintenance);
	public double calcMaintenanceCostForFacility(FacilityImpl f);
	public double calcProblemRateForFacility(FacilityImpl f);
	public double calcDownTimeForFacility(UnitImpl u);
	public ArrayList<MaintenanceImpl> listMaintRequests(UnitImpl u);
	public ArrayList<MaintenanceImpl> listMaintenance(UnitImpl u);
	public ArrayList<MaintenanceImpl> listFacilityProblems(FacilityImpl f);
}
