package com.proto.shankara.pojo;

import java.io.Serializable;

public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3801423615916039285L;
	protected String firstName;
	protected String lastName;
	protected String middleInitial;
	protected String title;
	protected String email;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Employee [getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getMiddleInitial()="
				+ getMiddleInitial() + ", getTitle()=" + getTitle()
				+ ", getEmail()=" + getEmail() + "]";
	}

}