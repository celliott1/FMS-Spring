package com.fms.facility.usage;

import java.util.ArrayList;

import com.fms.facility.maintenance.MaintenanceImpl;
import com.fms.facility.usage.*;

public interface Unit {
	public String toString();
	public boolean isOccupied();
	public void setOccupied(boolean occupied);
	public String getName();
	public void setName(String name);
	public ArrayList<PersonImpl> getOccupants();
	public void addOccupants(PersonImpl occupant);
	public ArrayList<MaintenanceImpl> getOutstandingMaintenance();
	public void addOutstandingMaintenance(MaintenanceImpl outstandingMaintenance);
	public ArrayList<MaintenanceImpl> getPreviousMaintenance();
	public void addPreviousMaintenance(MaintenanceImpl previousMaintenance);
	public ArrayList<UtilitiesImpl> getUtilities();
	public void addUtilities(UtilitiesImpl utilities);
	public void listUtilities(UtilitiesImpl utilities);
	public unitRecordImpl getUnitRecord();
	public void setUnitRecord(unitRecordImpl ur);
	public unitRemodelImpl getUnitRemodel();
	public void setUnitRemodel(unitRemodelImpl urem);
	
}


