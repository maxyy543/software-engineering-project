package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.File;

import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import it.unisa.diem.ingsoftw.gruppo16.model.ImportAsCSV;
import it.unisa.diem.ingsoftw.gruppo16.model.ImportAsJSON;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImportFileController {
    private AddressBookModel addrBook;
    private FileChooser fileChooser;
    private Validator verificaContatto;

    public ImportFileController(ActionEvent event){
        init();
        chooseFile();
        reactOnEvent(event);
    }

    private void init(){
        addrBook = AddressBookModel.getInstance();
        fileChooser = new FileChooser();
        verificaContatto = Validator.link(new EmailController(), new TelephoneNumberController(), new NameAndSurnameChecker());
    }

    private void chooseFile(){
        fileChooser.setTitle("Seleziona un file che contenga una lista di contatti");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("File CSV", "*.csv"),
            new FileChooser.ExtensionFilter("File JSON", "*.json")
        );
    }

    private void reactOnEvent(ActionEvent event){
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        importAs(selectedFile);
    }
    private void importAs(File selectedFile){
        if(selectedFile != null){
            String fileExtension = getFileExtension(selectedFile);
            switch (fileExtension) {
                case "csv":
                    importAsCSV(selectedFile);
                    break;
                case "json":
                    importAsJSON(selectedFile);
                    break;
                default:
                    System.out.println("Estensione non valida");
                    break;
            }
        }else{
            System.out.println("Nessun file selezionato");
        }
    }
    private String getFileExtension(File file){
        String filename = file.getName();
        int lastIndex = filename.lastIndexOf('.');
        return (lastIndex > 0) ? filename.substring(lastIndex + 1).toLowerCase() : "";
    }
    private void importAsCSV(File selectedFile){
        addrBook.clearAddressBook();
        ImportAsCSV csv = new ImportAsCSV();
        for(Contact c: csv.importFile(selectedFile)){
            if(verificaContatto.check(c))
                addrBook.addNewContact(c);
            else
                System.out.println("Contatto: "+ " " + "non inserito nella rubrica!");
        }
    }
    private void importAsJSON(File selectedFile){
        addrBook.clearAddressBook();
        ImportAsJSON json = new ImportAsJSON();
        for(Contact c: json.importFile(selectedFile)){
            if(verificaContatto.check(c))
                addrBook.addNewContact(c);
            else
                System.out.println("Contatto: "+ c.toString() + "non inserito nella rubrica!");
        }
    }
}
