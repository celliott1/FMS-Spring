package com.fms.facility.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fms.facility.view.*;
import com.fms.facility.usage.*;
import com.fms.facility.maintenance.*;
import com.fms.facility.details.*;

public class TestMain {
	public static void main(String [] args){
		//setting up spring
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		// creating a FacilityClient
		FacilityAppImpl fc = (FacilityAppImpl) context.getBean("facilityApp");
		
		// creating a facility f1
		FacilityImpl f1 = (FacilityImpl) context.getBean("facility");
		f1.setAddress("3423 N. Rondo Ave. Chicago IL 60660");
		f1.setDescription("Apartment Complex");
		f1.setName("The Hilton Rise");
		f1.setId(1);
	
		
		
		UnitImpl u1 = (UnitImpl) context.getBean("unit");
		PersonImpl p1 = (PersonImpl) context.getBean("person");
		p1.setPrimaryOccupant(true);
		u1.addOccupants(p1);
		f1.addUnit(u1);
		
		
		// --- end of creating a facility f1

		// --------------- Facility related testing
		fc.addNewFacility(f1);
		fc.getFacilityInformation(f1);
		fc.listFacilities();
		f1.setAddress("1040 W. Granville Ave. Chicago IL 60626");
		f1.setDescription("Hotel Building");
		f1.setName("The Sovereign");
		
		
		// removing facility with id 1, i.e. f1
	
		fc.listFacilities();
		fc.removeFacility(1);
		fc.listFacilities();
		
		// add f1 and f2 back
		fc.addNewFacility(f1);
		
		
		// --------------- Facility Use related testing
		boolean inUse = fc.isInUseDuringInterval(f1);
		System.out.println("f1 is " + (inUse ? "in use" : "not in use"));
		System.out.println();
		fc.assignFacilityToUse(f1, "Hotel building currently renting units.");
		System.out.println("Printing the usage of f1 : " + f1.getCurUsage());
		System.out.println();
		fc.listInspections(f1);
		System.out.println();
		
		// creating a new inspection
		InspectionImpl ins = (InspectionImpl) context.getBean("inspection");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		try {
			ins.setDate(sdf.parse("01/26/1990"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ins.setDescription("Fire inspection");
		// end of creating a inspection
		f1.addInspection(ins);
		fc.listInspections(f1);
		fc.listActualUsage(f1);
		fc.calcUsageRate(f1);
		
		// ------------ maintenance related testing
		MaintenanceImpl m1 = (MaintenanceImpl) context.getBean("maintenance");
		m1.setCosts(66);
		m1.setDescription("Toilet leaking");
		fc.makeFacilityMaintRequest(u1, m1);
		try {
			fc.scheduleMaintenance(m1, sdf.parse("2/22/2017"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		fc.calcMaintenanceCostForFacility(f1);
		m1.setCosts(33);
		m1.setDescription("Heating problem");
		m1.setRepairDays(1);
		fc.makeFacilityMaintRequest(u1, m1);
		try {
			fc.scheduleMaintenance(m1, sdf.parse("2/23/2017"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		m1.setDescription("AC problem");
		fc.makeFacilityMaintRequest(u1, m1);
		fc.listMaintRequests(u1);
		
		//employee testing
		EmployeeImpl employee = (EmployeeImpl) context.getBean("employee");
		System.out.println("Employee details for: " + employee.toString());
		employee.setPhoneNumber("773-244-3214");
		employee.setAssignedTask("Unclogging toilet for room 101");
		employee.setSalary(40000);
		employee.setTenure(4);
		System.out.println("Phone number: " + employee.getPhoneNumber());
		System.out.println("Assigned Task: " + employee.getAssignedTask());
		System.out.println("Salary: " + employee.getSalary() + " dollars");
		System.out.println("Tenure: " + employee.getTenure() + " years");
		f1.addEmployees(employee);
		f1.listEmployees(f1);
		System.out.println();
		
		// finances
				FinancesImpl fin = (FinancesImpl) context.getBean("finances");
				fin.setExpenses(150000);
				fin.setFacilityProfit(500000);
				fin.setPayroll(200000);
				fin.setPropertyTax(3000);
				fin.setInsurance(100000);
				System.out.println("Facility expense is " + fin.getExpenses() + " dollars");
				System.out.println("Facility profit is " + fin.getFacilityProfit() + " dollars");
				System.out.println("Facility payroll is " + fin.getPayroll() + " dollars");
				System.out.println("Facility property tax is " + fin.getPropertyTax() + " dollars");
				System.out.println("Facility insurance is " + fin.getInsurance() + " dollars");
				System.out.println();
				
				//unit cost related testing
				UtilitiesImpl ut = (UtilitiesImpl) context.getBean("utilities");
				ut.setCableBill(25.50);
				ut.setElectricBill(35.00);
				ut.setGasBill(20.25);
				ut.setInternetBill(60.75);
				ut.setTrashBill(19.99);
				u1.addUtilities(ut);
				u1.listUtilities(ut);
				System.out.println();
				
				TenantCostsImpl tc = (TenantCostsImpl) context.getBean("tenantCosts");
				System.out.println("The tenants monthly costs, excluding utilities, are as follows: ");
		        tc.setLockoutCost(50.00);
				tc.setParkingCost(80.00);
				tc.setPetCost(30.00);
				tc.setRentCost(950.00);
				System.out.println("Lockout cost - " + tc.getLockoutCost() + " dollars");
				System.out.println("Parking cost - " + tc.getParkingCost() + " dollars");
				System.out.println("Pet cost - " + tc.getPetCost() + " dollars");
				System.out.println("Rent cost - " + tc.getRentCost() + " dollars");
				System.out.println();
		
		equipmentExpensesImpl eq = (equipmentExpensesImpl) context.getBean("equipmentExpenses");
		eq.setToolPurchases(48.00);
		System.out.println("The total cost of the tools is: " + eq.getToolPurchases());
		System.out.println();
		eq.setToolRepair(20.00);
		System.out.println("The total cost of the tool repairs is: " + eq.getToolRepair());
		System.out.println();
		eq.setUniformCosts(600.00);
		System.out.println("The total cost of the uniforms is: " + eq.getUniformCosts());
		System.out.println();
		
		successRateImpl sr = (successRateImpl) context.getBean("successRate");
 		sr.setInjuries("Sprained ankle from ladder fall");
		sr.setSatisfactoryCompletion("Customer enjoys new blue paint coating in unit");
		sr.setUnsatisfactoryCompletion("Customer dislikes the new unit's bathroom wallpaper");
		System.out.println("Maintenance injury: " + sr.getInjuries());
		System.out.println();
		System.out.println("Maintenance satisfaction: " + sr.getSatisfactoryCompletion());
		System.out.println();
		System.out.println("Maintenance unsatisfaction: " + sr.getUnsatisfactoryCompletion());
		System.out.println();
		
		improvementProjectsImpl projects = (improvementProjectsImpl) context.getBean("improvementProjects");
		projects.setAddingToFacility("Building a new pool site at this facility");
		projects.setNewTechnology("Adding 20 new computers to the lounge room");
		projects.setRemodelingInProgress("Replacing the carpet in the lobby");
		f1.setImprovementProjects(projects);
		System.out.println("Facility Addition: " + projects.getAddingToFacility());
		System.out.println();
		System.out.println("New Technology: " + projects.getNewTechnology());
		System.out.println();
		System.out.println("Current Remodeling: " + projects.getRemodelingInProgress());
		System.out.println();

		officeRosterImpl roster = (officeRosterImpl) context.getBean("officeRoster");
		roster.setDoorman1("Anthony Billops");
		roster.setDoorman2("Jacob Towns");
		roster.setFacilityManager("Benjamin Franklin");
		roster.setLeasingAgent1("Jimmy Fenders");
		roster.setLeasingAgent2("Samantha Simmons");
		roster.setSrLeasingAgent("Jennifer Bettinger");
		f1.setOfficeRoster(roster);
		System.out.println("Doorman 1: " + roster.getDoorman1());
		System.out.println();
		System.out.println("Doorman 2: " + roster.getDoorman2());
		System.out.println();
		System.out.println("Facility Manager: " + roster.getFacilityManager());
		System.out.println();
		System.out.println("Leasing Agent 1: " + roster.getLeasingAgent1());
		System.out.println();
		System.out.println("Leasing Agent 2: " + roster.getLeasingAgent2());
		System.out.println();
		System.out.println("Senior Leasing Agent: " + roster.getSrLeasingAgent());
		System.out.println();
		
		serviceContractsImpl service = (serviceContractsImpl) context.getBean("serviceContracts");
		service.setParkingCompany("Red Vans");
		service.setSecurityCompany("Norton");
		service.setStaffingCompany("Staffer Delight");
		service.setWebDesignCompany("Digital Vibrancy");
		service.setJanitorialServices("Crystal Clear");
		f1.setServiceContracts(service);
		System.out.println("Parking Company: " + service.getParkingCompany());
		System.out.println();
		System.out.println("Security Company: " + service.getSecurityCompany());
		System.out.println();
		System.out.println("Staffing Company : " + service.getStaffingCompany());
		System.out.println();
		System.out.println("Web Design Company : " + service.getWebDesignCompany());
		System.out.println();
		System.out.println("Janitorial Company : " + service.getJanitorialServices());
		System.out.println();

		unitRecordImpl ur = (unitRecordImpl) context.getBean("unitRecord");
		ur.setMonthsLateRent(4);
		ur.setDamageToUnit(245.00);
		ur.setNoiseComplaints(7);
		ur.setSmokingComplaints(3);
		u1.setUnitRecord(ur);
		System.out.println("This unit has had " + ur.getMonthsLateRent()+ " months of late rent");
		System.out.println();
		System.out.println("This unit has had " + ur.getDamageToUnit() + " dollars of damage from tenant");
		System.out.println();
		System.out.println("This unit has had " + ur.getNoiseComplaints() + " noise complaints");
		System.out.println();
		System.out.println("This unit has had " + ur.getSmokingComplaints() + " smoking complaints");
		System.out.println();

		unitRemodelImpl urem = (unitRemodelImpl) context.getBean("unitRemodel");
		urem.setBlackToStainless(true);
		urem.setCarpetToHardwood(false);
		urem.setFreshPaint(true);
		u1.setUnitRemodel(urem);
		System.out.println("There is " + (urem.getBlackToStainless() ? "a Black to Stainless project in progress" : "no Black to Stainless project in progress"));
		System.out.println();
		System.out.println("There is " + (urem.getCarpetToHardwood() ? "a carpet to hardwood project in progress" : "no carpet to hardwood project in progress"));
		System.out.println();
		System.out.println("There is " + (urem.getFreshPaint() ? "a fresh paint project in progress" : "no fresh pain project in progress"));
		System.out.println();

	}
}
