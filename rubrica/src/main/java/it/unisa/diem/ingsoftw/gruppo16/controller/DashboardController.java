package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import it.unisa.diem.ingsoftw.gruppo16.model.ImportAsCSV;
import it.unisa.diem.ingsoftw.gruppo16.model.ImportAsJSON;
import it.unisa.diem.ingsoftw.gruppo16.model.ImportAsXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DashboardController implements Initializable{

    @FXML
    private Button exportBtn;
    @FXML
    private Button addContactBtn;
    @FXML
    private Button allContactsBtn;
    @FXML
    private Button favouriteContactsBtn;
    @FXML
    private Button importBtn;
    @FXML
    private Label contactsLbl;
    @FXML
    private TextField searchBarTf;
    @FXML
    private ListView<Contact> contactListListView;
    
    private ObservableList<Contact> listObservable;
    private AddressBookModel addrBook;
    private SelectedContactController selectedContact;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addrBook = AddressBookModel.getInstance();
        selectedContact = SelectedContactController.getInstance();
        listObservable = FXCollections.observableArrayList(addrBook.getTreeSet());
        contactListListView.setItems(listObservable);
        listViewSelectItemInit();
    }    
    @FXML
    private void exportFileOnAction(ActionEvent event) {
        if(addrBook.isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Rubrica vuota!");
            alert.setContentText("Devi inserire dei contatti all'interno della rubrica prima di effettuare un export!");
            alert.showAndWait();
        }
    }
    @FXML
    private void addButtonOnAction(ActionEvent event) throws IOException {
        switchSceneToModifyContact(event);
    }
    @FXML
    private void favouriteListOnAction(ActionEvent event) {
    }
    @FXML
    private void importFileOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleziona un file che contenga una lista di contatti");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("File CSV", "*.csv"),
            new FileChooser.ExtensionFilter("File JSON", "*.json"),
            new FileChooser.ExtensionFilter("File XML", "*.xml")
        );
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile != null){
            String fileExtension = getFileExtension(selectedFile);
            switch (fileExtension) {
                case "csv":
                    addrBook.clearAddressBook();
                    ImportAsCSV csv = new ImportAsCSV();
                    for(Contact c: csv.importFile(selectedFile)){
                        Validator verificaContatto = Validator.link(new EmailController(), new TelephoneNumberController());
                        if(verificaContatto.check(c))
                            addrBook.addNewContact(c);
                        else
                            System.out.println("Contatto: "+ " " + "non inserito nella rubrica!");
                    }
                    break;
                case "xml":
                    addrBook.clearAddressBook();
                    ImportAsXML xml = new ImportAsXML();
                    for(Contact c: xml.importFile(selectedFile)){
                        Validator verificaContatto = Validator.link(new EmailController(), new TelephoneNumberController());
                        if(verificaContatto.check(c))
                            addrBook.addNewContact(c);
                        else
                            System.out.println("Contatto: "+ " " + "non inserito nella rubrica!");
                    }
                    break;
                case "json":
                    addrBook.clearAddressBook();
                    ImportAsJSON json = new ImportAsJSON();
                    for(Contact c: json.importFile(selectedFile)){
                        Validator verificaContatto = Validator.link(new EmailController(), new TelephoneNumberController());
                        if(verificaContatto.check(c))
                            addrBook.addNewContact(c);
                        else
                            System.out.println("Contatto: "+ " " + "non inserito nella rubrica!");
                    }
                    break;
                default:
                    System.out.println("Estensione non valida");
                    break;
            }
            listObservable = FXCollections.observableArrayList(addrBook.getTreeSet());
            contactListListView.setItems(listObservable);
        }else{
            System.out.println("Nessun file selezionato");
        }
    }

    @FXML
    private void contactSelected() throws IOException{
        selectedContact.setSelectedContact(contactListListView.getSelectionModel().getSelectedItem());
        openDetailOf(selectedContact.getSelectedContact());
    }

    void switchSceneToModifyContact(ActionEvent event) throws IOException{
        try{
            Parent scene2Root = FXMLLoader.load(getClass().getResource("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface3.fxml")); 
            Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow(); 
            Scene scene2 = new Scene(scene2Root); 
            stage.setScene(scene2); 
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private String getFileExtension(File file){
        String filename = file.getName();
        int lastIndex = filename.lastIndexOf('.');
        return (lastIndex > 0) ? filename.substring(lastIndex + 1).toLowerCase() : "";
    }
    private void openDetailOf(Contact contact) throws IOException{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface2.fxml"));;
            Parent root = loader.load();
            DetailController detailController = loader.getController();
            detailController.setContactDetail(contact);
            Scene scene = new Scene(root);
            Stage stage = (Stage) contactListListView.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void listViewSelectItemInit(){
        contactListListView.setCellFactory(param -> new ListCell<Contact>() {
            @Override
            protected void updateItem(Contact contact, boolean empty) {
                super.updateItem(contact, empty);

                if (empty || contact == null) {
                    setText(null);
                } else {
                    setText(contact.getSurname() + " " + contact.getName());
                }
            }});
    }
}
