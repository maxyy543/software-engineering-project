package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class DetailController extends MainController implements Initializable{
    @FXML
    private Button modifyBtn;
    @FXML
    private Button starButton;
    @FXML
    private Button deleteContactBtn;
    @FXML
    private Label contactNameLbl;
    @FXML
    private Label contactDetailsLbl;
    @FXML
    private Label telephoneLbl;
    @FXML
    private Label telephone2Lbl;
    @FXML
    private Label telephone3Lbl;
    @FXML
    private Label emailLbl;
    @FXML
    private Label email2Lbl;
    @FXML
    private Label email3Lbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.addrBook = AddressBookModel.getInstance();
        selectedContact = SelectedContactController.getInstance();
        view = ViewUpdateController.getInstance();
        list = new ListViewController();
        favList = new FavouriteListController();
        listViewSelectItemInit();
        listView.setItems(list.getSharedListView());
        initSearchbar();
        initSelectedContactInfo(selectedContact);
        allContactListBind();
    }
    @FXML
    private void starButtonOnAction(ActionEvent event){
        Boolean isFav = selectedContact.getSelectedContact().getIsFavourite();
        selectedContact.getSelectedContact().setIsFavourite(!isFav);
        favouriteListOnAction(event);
        view.setDetailOfContactScene();
    }
    @FXML
    private void modifyBtnOnAction(ActionEvent event) throws IOException{
        view.setModifyScene();
    }
    @FXML 
    private void delContactOnAction(ActionEvent event) throws IOException{
        Alert alert = new Alert(AlertType.CONFIRMATION); 
        initAlert(alert);
        Optional<ButtonType> result = alert.showAndWait(); 
            if (result.isPresent() && result.get() == ButtonType.OK) { 
                if(selectedContact.getSelectedContact() != null){
                    deleteContactFromAddressBook(selectedContact);
                    view.setDashboardScene();
                }
            } 
            else{ 
                System.out.println("Utente ha annullato l'azione.");
            }
    }
    public void setContactDetail(Contact contact){
        contactNameLbl.setText(contact.getSurname() + " " + contact.getName());
        String[] tel = contact.getTelephoneNumber();
        String[] email = contact.getEmail();
        telephoneLbl.setText(tel[0]);
        telephone2Lbl.setText(tel[1]);
        telephone3Lbl.setText(tel[2]);
        emailLbl.setText(email[0]);
        email2Lbl.setText(email[1]);
        email3Lbl.setText(email[2]);

        if(contact.getIsFavourite()){
            starButton.getStyleClass().clear();
            starButton.getStyleClass().add("button-remove-fav");
            starButton.setText("Rimuovi dai preferiti");
        }else{
            starButton.getStyleClass().clear();
            starButton.getStyleClass().add("button-add-fav");
            starButton.setText("Aggiungi ai preferiti");
        }
        
    }
    private void initAlert(Alert a){
        a.setTitle("Conferma Azione"); 
        a.setHeaderText("Sei sicuro di voler procedere?"); 
        a.setContentText("Questa azione non può essere annullata."); 
    }
    private void deleteContactFromAddressBook(SelectedContactController s){
        selectedContact = SelectedContactController.getInstance();
        addrBook.removeContact(selectedContact.getSelectedContact());
        list.updateList();
        selectedContact.resetSelectedContact();
    }
    private void initSelectedContactInfo(SelectedContactController selectedContact){
        setContactDetail(selectedContact.getSelectedContact());
    }
}
