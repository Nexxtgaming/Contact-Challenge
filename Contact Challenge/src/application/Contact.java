package application;

import javafx.beans.property.SimpleStringProperty;


public class Contact {
	private SimpleStringProperty firstName = new SimpleStringProperty("");
	private SimpleStringProperty lastName = new SimpleStringProperty("");
	private SimpleStringProperty notes = new SimpleStringProperty("");
	private SimpleStringProperty phoneNumber = new SimpleStringProperty("");
	
	public Contact() {
		
	}
	
	public Contact(String firstName, String lastName, String notes,
			String phoneNumber) {
		super();
		this.firstName.set(firstName);
		this.lastName.set(lastName);
		this.notes.set(notes);
		this.phoneNumber.set(phoneNumber);
	}
	public SimpleStringProperty getFirstName() {
	
		return firstName;
	}
	public void setFirstName(String firstName) {
	
		this.firstName.set(firstName);
		
	}
	public SimpleStringProperty getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	public SimpleStringProperty getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes.set(notes);
	}
	public SimpleStringProperty getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}
	public void editContact(Contact contact) {
		this.firstName = contact.getFirstName();
		this.lastName = contact.getLastName();
		this.phoneNumber = contact.getPhoneNumber();
		this.notes = contact.getNotes();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Contact{"+"First Name"+firstName+", last Name "+lastName+"phone Number "+phoneNumber+", notes "+notes;
	}
	
}
