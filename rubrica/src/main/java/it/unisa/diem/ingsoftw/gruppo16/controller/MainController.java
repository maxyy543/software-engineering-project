package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.IOException;

import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


/**
 * @brief Classe controller-view che gestisce l'interazione tra diverse sottoclassi controller e l'interfaccia GUI di JavaFX. 
 * Definisce e implementa alcune dei principali eventi comuni a tutte le scene, come: import, export, addBtnContact ecc..
 * 
 * @Class MainController
 * 
 */
public abstract class MainController {
    @FXML
    protected Button exportBtn; ///< bottone per esportare una rubrica su un file.
    @FXML 
    protected Button addContactBtn; ///< bottone per aggiungere un nuovo contatto.
    @FXML 
    protected Button allContactsBtn; ///< bottone per visualizzare tutti i contatti della rubrica.
    @FXML
    protected Button favouriteContactsBtn; ///< bottone per visualizzare tutti i contatti preferiti.
    @FXML
    protected Button importBtn; ///< bottone per importare una rubrica da un file.
    @FXML
    protected TextField searchBarTf; ///< searchbar per filtrare la listView.
    @FXML
    protected ListView<Contact> listView; ///< Listview per la visualizzazione dei contatti della rubrica
    
    protected AddressBookModel addrBook = AddressBookModel.getInstance();
    protected SelectedContactController selectedContact = SelectedContactController.getInstance();
    protected ViewUpdateController view = ViewUpdateController.getInstance();
    protected ListViewController list = new ListViewController();
    protected FavouriteListController favList = new FavouriteListController();
    protected static BooleanProperty displayAllContacts = new SimpleBooleanProperty(true);  

    /**
     * Effettua un binding tra gli style e i bottoni allContactsBtn e favouriteContactsBtn.
     * 
     */
    protected void allContactListBind(){
        allContactsBtn.styleProperty().bind(
            Bindings.when(displayAllContacts)
                .then("-fx-background-color:#00a1ff;")
                .otherwise("-fx-background-color:#545454;")
        );
        favouriteContactsBtn.styleProperty().bind(
            Bindings.when(displayAllContacts.not())
                .then("-fx-background-color:#00a1ff;")
                .otherwise("-fx-background-color:#545454;")
        );
    }
    /**
     * Gestisce l'evento export quando il bottone Esporta viene premuto.
     * @param[in] event l'azione viene avviata quando il pulsante Esporta viene premuto.
     */
    @FXML
    protected void exportFileOnAction(ActionEvent event) {
        new ExportFileController(event);
    }
    /**
     * Gestisce l'evento aggiungi nuovo contatto quando viene premuto il bottone Nuovo Contatto.
     * 
     * @param[in] event l'azione viene avviata quando il pulsante Aggiungi contatto viene premuto.
     * @throws IOException se la scena AddContactScene non viene caricata bene.
     */
    @FXML
    protected void addButtonOnAction(ActionEvent event) throws IOException {
        view.setAddContactScene();
    }
    /**
     * Gestisce l'evento visualizza tutti i contatti quando viene premuto il bottone Tutti i contatti.
     * 
     * @param[in] event l'azione viene avviata quando il pulsante Tutti i contatti viene premuto.
     * 
     */
    @FXML
    protected void allContactsOnAction(ActionEvent event){
        displayAllContacts.set(true);
        list.updateList();
        listView.setItems(list.getSharedListView());
    }
    /**
     * Gestisce l'evento visualizza lista preferiti quando viene premuto il bottone Preferiti.
     * 
     * @param[in] event l'azione viene avviata quando il pulsante Preferiti viene premuto.
     * 
     */
    @FXML
    protected void favouriteListOnAction(ActionEvent event) {
        displayAllContacts.set(false);
        list.getSharedListView().setAll(favList.getTreeWithFavContacts());
        listView.setItems(list.getSharedListView());
    }
    /**
     * Gestisce l'evento importa file quando viene premuto il bottone Importa.
     * 
     * @param[in] event l'azione viene avviata quando il pulsante Importa viene premuto.
     * 
     */
    @FXML
    protected void importFileOnAction(ActionEvent event) {
        new ImportFileController(event);
        displayAllContacts.set(true);
        list.updateList();
        listView.setItems(list.getSharedListView());
    }
    /**
     * Gestisce l'evento seleziona contatto quando viene selezionato un contatto nella listview.
     * @throws IOException se la scena DetailContactScene non viene caricata correttamente.
     */
    @FXML
    protected void contactSelected() throws IOException{
        selectedContact.setSelectedContact(listView.getSelectionModel().getSelectedItem());
        view.setDetailOfContactScene();
    }
    /**
     * Metodo che imposta ed aggiorna il contenuto nelle celle della ListView.
     * Ogni cella memorizza cognome e nome di un contatto della ListView.
     */
    protected void listViewSelectItemInit(){
        listView.setCellFactory(param -> new ListCell<Contact>() {
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
    /**
     * Metodo che filtra i contatti della rubrica con un inserimento di una sottostringa del cognome e del nome
     * tramite una searchbar.
     */
    protected void initSearchbar(){
        searchBarTf.textProperty().addListener((observer, oldValue, newValue) -> {
            list.filterList(newValue);
            listView.setItems(list.getSharedListView());
            displayAllContacts.set(true);
        });
    }
}

