package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.util.Optional;

import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.ExportAsCSV;
import it.unisa.diem.ingsoftw.gruppo16.model.ExportAsJSON;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class ExportFileController {
    
    private Alert alert;
    private AddressBookModel addrBook;
    
    public ExportFileController(ActionEvent event){
        init(event);
    }

    private void init(ActionEvent event){
        addrBook = AddressBookModel.getInstance();
        if(addrBook.isEmpty())
            setAlert();
        else
            reactOnEvent(event);
        
    }
    private void reactOnEvent(ActionEvent event){
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Esporta rubrica");
        alert.setHeaderText("Seleziona il formato del file da esportare");
        alert.setContentText("Scegli tra le seguenti opzioni:");

        ButtonType buttonCSV = new ButtonType("CSV");
        ButtonType buttonJSON = new ButtonType("JSON");
        ButtonType buttonXML = new ButtonType("XML");
        alert.getButtonTypes().setAll(buttonCSV, buttonJSON, buttonXML);
        Optional<ButtonType> choice = alert.showAndWait();
        if(choice.isPresent()){
            if(choice.get() == buttonCSV){
                exportCSV("Test.csv");
            }
            if(choice.get() == buttonJSON){
                exportJSON("Test.json");
            }
        }
    }
    private void setAlert(){
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Rubrica vuota!");
        alert.setContentText("Devi inserire dei contatti all'interno della rubrica prima di effettuare un export!");
        alert.showAndWait();
    }
    private void exportCSV(String filename){
        ExportAsCSV csv = new ExportAsCSV();
        csv.exportFile(filename, addrBook.getTreeSet());
    }
    private void exportJSON(String filename){
        ExportAsJSON json = new ExportAsJSON();
        json.exportFile(filename, addrBook.getTreeSet());
    }
}
