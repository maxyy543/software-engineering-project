package it.unisa.diem.ingsoftw.gruppo16.controller;

import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.ExportAsJSON;
import it.unisa.diem.ingsoftw.gruppo16.model.ExportFileStrategy;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ExportFileController {
    
    private Alert alert;
    private AddressBookModel addrBook;
    
    public ExportFileController(ActionEvent event){
        init();
        reactOnEvent(event);
    }

    private void init(){
        addrBook = AddressBookModel.getInstance();
        if(addrBook.isEmpty())
            setAlert();
        
    }
    private void reactOnEvent(ActionEvent event){
        ExportFileStrategy exportFileStrategy = new ExportAsJSON();
        exportFileStrategy.exportFile("file.json", addrBook.getTreeSet());
    }
    private void setAlert(){
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Rubrica vuota!");
        alert.setContentText("Devi inserire dei contatti all'interno della rubrica prima di effettuare un export!");
        alert.showAndWait();
    }
}
