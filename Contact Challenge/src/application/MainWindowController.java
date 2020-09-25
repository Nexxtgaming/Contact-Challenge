package application;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class MainWindowController implements Initializable{
	
	@FXML
	private MenuItem editContact;
	@FXML
	private MenuItem addContact;
	@FXML
	private BorderPane mainBorderPane;
	
	@FXML
	private TableView<Contact> contactTableView;
	@FXML
	private TableColumn<Contact,String> firstNameColumn;
	@FXML
	private TableColumn<Contact,String> lastNameColumn;
	@FXML
	private TableColumn<Contact,String> phoneNumberColumn;
	@FXML
	private TableColumn<Contact,String> notesColumn;
	@FXML
	private ContextMenu tableContextMenu;
	@FXML
	private MenuItem deleteContact;
	
	
	private ContactData contactDatas;

	
	@Override
	public void initialize(URL location,ResourceBundle resources) {
		contactDatas =  new ContactData();
		contactDatas.loadContacts();
		
		// TODO Auto-generated method stub
		 
		contactTableView.setItems(contactDatas.getContacts());
		
		  firstNameColumn.setCellValueFactory(cellData ->
		  cellData.getValue().getFirstName());
		  lastNameColumn.setCellValueFactory(cellData ->
		  cellData.getValue().getLastName());
		  phoneNumberColumn.setCellValueFactory(cellData ->
		  cellData.getValue().getPhoneNumber());
		  notesColumn.setCellValueFactory(cellData -> cellData.getValue().getNotes());
		 
		tableContextMenu = new ContextMenu();
		MenuItem deleteMenuItem = new MenuItem("Delete");
		deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Contact contact = contactTableView.getSelectionModel().getSelectedItem();
				deleteContact(contact);
				
			}
		});
		tableContextMenu.getItems().addAll(deleteMenuItem);
		
		  contactTableView.getSelectionModel().selectedItemProperty().addListener(new
		  ChangeListener<Contact>() {
		 
		  @Override public void changed(ObservableValue<? extends Contact> obsevable,
		  Contact oldValue, Contact newValue) { // TODO Auto-generated method stub
		  if(newValue!=null) { Contact contact =
		  contactTableView.getSelectionModel().getSelectedItem();
		  contactDatas.accessContact(contact).editContact(newValue);
		  contactDatas.saveContacts(); 
		  contactTableView.setItems(contactDatas.getContacts());
		  
		  }
		  
		  }
		  
		  
		  });

		
	}
	
	@FXML
	public void handleExit() {
		Platform.exit();
	}
	
	@FXML
	public void showAddWindow() {
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.initOwner(mainBorderPane.getScene().getWindow());
		dialog.setTitle("Add new contact");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("AddWindow.fxml"));
		try {
			dialog.getDialogPane().setContent(fxmlLoader.load());
		}catch(IOException e) {
			System.out.println("Couldn't load the dialog");
			e.printStackTrace();
			return;
		}
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		Optional<ButtonType>result = dialog.showAndWait();
		if(result.isPresent()&&result.get()==ButtonType.OK) {
			AddWindowController controller = fxmlLoader.getController();
			Contact newContact = controller.processResult();
			contactDatas.addContact(newContact);
			contactDatas.saveContacts();
			contactTableView.getSelectionModel().select(newContact);
		}else {
			System.out.println("Cancle pressed");
		}
	}
	
	  @FXML
	  public void showEditWindow() { 
		  Contact contact = contactTableView.getSelectionModel().getSelectedItem();
		  if(contact==null) {
			  Alert alert = new Alert(Alert.AlertType.INFORMATION);
			  alert.setTitle("No contact selected");
			  alert.setHeaderText(null);
			  alert.setContentText("Please select the contact you want to edit.");
			  alert.showAndWait();
			  return;
		  }
		  Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		  dialog.initOwner(mainBorderPane.getScene().getWindow());
		  dialog.setTitle("Edit contact"); 
		  FXMLLoader fxmlLoader = new FXMLLoader();
		  fxmlLoader.setLocation(getClass().getResource("EditWindow.fxml"));
		  try {
			  dialog.getDialogPane().setContent(fxmlLoader.load());
		  }catch(IOException e) {
			  System.out.println("Couldn't load the dialog"); e.printStackTrace();
			  return;
	  } 
		  dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		  dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		  EditWindowController controller = fxmlLoader.getController();
		  controller.editContact(contact);	
		  Optional<ButtonType>result = dialog.showAndWait();
		  	if(result.isPresent()&&result.get()==ButtonType.OK){
		  		controller.updateContact(contact);
		  		contactDatas.saveContacts();
	  }else {
		  System.out.println("Cancle pressed");
		  } 
	  }
	 
	
	public void deleteContact(Contact contact) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Delete contact");
		alert.setHeaderText("Delete contact" +contact.getFirstName()+" "+contact.getLastName()+"?");
		alert.setContentText("Are you sure? Press OK to confirm, or cancle to Back out.");
		Optional<ButtonType>result = alert.showAndWait();
		
		if(result.isPresent()&&result.get()==ButtonType.OK) {
			contactDatas.deleteContact(contact);
		}
	}
	@FXML
	public void deleteContactClick() {
		Contact contactToDelete = contactTableView.getSelectionModel().getSelectedItem();
		if(contactToDelete==null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("No contact selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select the contact" );
			alert.showAndWait();
			return;
		}
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Delete Contact");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete"+ contactToDelete.getFirstName().get()+" "+contactToDelete.getLastName().get());
		Optional<ButtonType>result = alert.showAndWait();
		if(result.isPresent()&&result.get()==ButtonType.OK) {
			contactDatas.deleteContact(contactToDelete);
			contactDatas.saveContacts();
		}
		
		
		
		
		
		
	}

}
