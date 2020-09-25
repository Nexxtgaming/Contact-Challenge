package application;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class AddWindowController {
	@FXML
	private TextField firstNameTextArea;
	@FXML
	private TextField lastNameTextArea;
	@FXML
	private TextField phoneNumberTextArea;
	@FXML
	private TextField notesTextArea;
	
	public Contact processResult() {
		String firstName = firstNameTextArea.getText().trim();
		String lastName = lastNameTextArea.getText().trim();
		String phoneNumber = phoneNumberTextArea.getText();
		String notes = notesTextArea.getText().trim();
		Contact newContact = new Contact(firstName, lastName, notes, phoneNumber);
		
		return newContact;
	}

}
