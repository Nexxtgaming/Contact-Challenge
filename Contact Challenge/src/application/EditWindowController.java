package application;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class EditWindowController {
	@FXML
	private TextField firstNameTextArea;
	@FXML
	private TextField lastNameTextArea;
	@FXML
	private TextField phoneNumberTextArea;
	@FXML
	private TextField notesTextArea;
	
	
	  
	 
	
	/*
	 * public Contact processResult() { String firstName =
	 * firstNameTextArea.getText().trim(); String lastName =
	 * lastNameTextArea.getText().trim(); String phoneNumber =
	 * phoneNumberTextArea.getText(); String notes = notesTextArea.getText().trim();
	 * Contact editedContact = new Contact(firstName, lastName, notes, phoneNumber);
	 * 
	 * return editedContact; }
	 */
	  
	 public void editContact(Contact contact) {
		 firstNameTextArea.setText(contact.getFirstName().get());
		  lastNameTextArea.setText(contact.getLastName().get());
		  phoneNumberTextArea.setText(contact.getPhoneNumber().get());
		  notesTextArea.setText(contact.getNotes().get());
	 }
	 public void updateContact(Contact contact) {
		 contact.setFirstName(firstNameTextArea.getText());
		 contact.setLastName(lastNameTextArea.getText());
		 contact.setPhoneNumber(phoneNumberTextArea.getText());
		 contact.setNotes(notesTextArea.getText());
	 }
}
