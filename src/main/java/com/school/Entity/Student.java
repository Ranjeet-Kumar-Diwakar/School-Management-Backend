package com.school.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {

	@Id
	private int rollNumber;
	private String name;
	private String standard;
	private String section;
	private String dataOfAdmission;
	private String dob;
	private String religion;
	private String category;
	private int pincode;
	private String guardian;
	private String guardianNumber;
	private String currentAddress;
	private String permanentAddress;
	
	
	
	
	
	
	
	public Student(int rollNumber, String name, String standard, String section, String dataOfAdmission, String dob,
			String religion, String category, int pincode, String guardian, String guardianNumber,
			String currentAddress, String permanentAddress) {
		super();
		this.rollNumber = rollNumber;
		this.name = name;
		this.standard = standard;
		this.section = section;
		this.dataOfAdmission = dataOfAdmission;
		this.dob = dob;
		this.religion = religion;
		this.category = category;
		this.pincode = pincode;
		this.guardian = guardian;
		this.guardianNumber = guardianNumber;
		this.currentAddress = currentAddress;
		this.permanentAddress = permanentAddress;
	}
	
	
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getRollNumber() {
		return rollNumber;
	}
	public String getName() {
		return name;
	}
	public String getStandard() {
		return standard;
	}
	public String getSection() {
		return section;
	}
	public String getDataOfAdmission() {
		return dataOfAdmission;
	}
	public String getDob() {
		return dob;
	}
	public String getReligion() {
		return religion;
	}
	public String getCategory() {
		return category;
	}
	public int getPincode() {
		return pincode;
	}
	public String getGuardian() {
		return guardian;
	}
	public String getGuardianNumber() {
		return guardianNumber;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public void setDataOfAdmission(String dataOfAdmission) {
		this.dataOfAdmission = dataOfAdmission;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}
	public void setGuardianNumber(String guardianNumber) {
		this.guardianNumber = guardianNumber;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}



	@Override
	public String toString() {
		return "Student [rollNumber=" + rollNumber + ", name=" + name + ", standard=" + standard + ", section="
				+ section + ", dataOfAdmission=" + dataOfAdmission + ", dob=" + dob + ", religion=" + religion
				+ ", category=" + category + ", pincode=" + pincode + ", guardian=" + guardian + ", guardianNumber="
				+ guardianNumber + ", currentAddress=" + currentAddress + ", permanentAddress=" + permanentAddress
				+ "]";
	}
	
	
	
	
	
}
