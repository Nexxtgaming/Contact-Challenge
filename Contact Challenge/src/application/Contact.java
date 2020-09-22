package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact {
	private StringProperty firstName,lastName, notes ;
	private StringProperty phoneNumber;
	
	public Contact() {
		
	}
	
	public Contact(String firstName, String lastName, String notes,
			String phoneNumber) {
		super();
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.notes = new SimpleStringProperty(notes);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
	}
	public String getFirstName() {
	
		return firstName.get();
	}
	public void setFirstName(String firstName) {
	
		this.firstName = new SimpleStringProperty(firstName);
		
	}
	public String getLastName() {
		return lastName.get();
	}
	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}
	public String getNotes() {
		return notes.get();
	}
	public void setNotes(String notes) {
		this.notes = new SimpleStringProperty(notes);
	}
	public String getPhoneNumber() {
		return phoneNumber.get();
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
	};
	
	
}
