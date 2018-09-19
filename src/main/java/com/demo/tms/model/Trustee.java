package com.demo.tms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="trustees")
public class Trustee {
	@Id
	@GeneratedValue
	private Long id;
	
	private String prefix;
	private String firstName;
	private String middleName;
	private String lastName;
	private String shortName;
	private String ssn;
	private String gender;
	private String maritalStatus;
	private String countryOfResidence;
	private String passport;
	private String countryOfIssuance;
	private String issuanceDate;
	private String expirationDate;
	private int noOfDependents;
	
	public Trustee() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getCountryOfResidence() {
		return countryOfResidence;
	}

	public void setCountryOfResidence(String countryOfResidence) {
		this.countryOfResidence = countryOfResidence;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getCountryOfIssuance() {
		return countryOfIssuance;
	}

	public void setCountryOfIssuance(String countryOfIssuance) {
		this.countryOfIssuance = countryOfIssuance;
	}

	public String getIssuanceDate() {
		return issuanceDate;
	}

	public void setIssuanceDate(String issuanceDate) {
		this.issuanceDate = issuanceDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getNoOfDependents() {
		return noOfDependents;
	}

	public void setNoOfDependents(int noOfDependents) {
		this.noOfDependents = noOfDependents;
	}

	@Override
	public String toString() {
		return "Trustee [id=" + id + ", prefix=" + prefix + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", shortName=" + shortName + ", ssn=" + ssn + ", gender="
				+ gender + ", maritalStatus=" + maritalStatus + ", countryOfResidence=" + countryOfResidence
				+ ", passport=" + passport + ", countryOfIssuance=" + countryOfIssuance + ", issuanceDate="
				+ issuanceDate + ", expirationDate=" + expirationDate + ", noOfDependents=" + noOfDependents + "]";
	}

	public Trustee(Long id, String prefix, String firstName, String middleName, String lastName, String shortName,
			String ssn, String gender, String maritalStatus, String countryOfResidence, String passport,
			String countryOfIssuance, String issuanceDate, String expirationDate, int noOfDependents) {
		super();
		this.id = id;
		this.prefix = prefix;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.shortName = shortName;
		this.ssn = ssn;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.countryOfResidence = countryOfResidence;
		this.passport = passport;
		this.countryOfIssuance = countryOfIssuance;
		this.issuanceDate = issuanceDate;
		this.expirationDate = expirationDate;
		this.noOfDependents = noOfDependents;
	}
	
	
}
