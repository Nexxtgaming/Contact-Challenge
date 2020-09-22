package application;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	
	public ContactData contactDatas= new ContactData();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Contact,String>("First Name"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contact,String>("Last Name"));
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Contact,String>("Phone Number"));
		notesColumn.setCellValueFactory(new PropertyValueFactory<Contact,String>("Notes"));
		contactTableView.setItems(contactDatas.getContacts());
		
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
			
		}
	}


}
