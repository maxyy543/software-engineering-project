package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.File;

import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import it.unisa.diem.ingsoftw.gruppo16.model.ImportAsCSV;
import it.unisa.diem.ingsoftw.gruppo16.model.ImportAsJSON;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @brief Controller per l'import di una rubrica da un file(CSV o JSON).
 * Questa classe permette di scegliere una estensione tra CSV e JSON e importare la rubrica da un file selezionabile
 * tramite una finestra di dialogo
 * 
 * @Class ImportFileController
 */
public class ImportFileController {
    private AddressBookModel addrBook; ///< istanza della rubrica
    private FileChooser fileChooser; ///< file selezionabile
    private Validator verificaContatto; ///< Validator per la verifica dei contatti da inserire nella rubrica.

    /**
     * @brief Costruttore della classe che fa partire i metodi: init, chooseFile e reactOnEvent
     * @param[in] event
     */
    public ImportFileController(ActionEvent event){
        init();
        chooseFile();
        reactOnEvent(event);
    }
    /**
     * Metodo per inizializzare tutti gli attributi della classe, incluso la catena di validator.
     * La catena di validator comprende: {@link EmailController}, {@link TelephoneNumberController}, {@link NameAndSurnameChecker}
     */
    private void init(){
        addrBook = AddressBookModel.getInstance();
        fileChooser = new FileChooser();
        verificaContatto = Validator.link(new EmailController(), new TelephoneNumberController(), new NameAndSurnameChecker());
    }
    /**
     * @brief Metodo per impostare il titolo e i filtri delle estensioni della la finestra di dialogo per la selezione di un file.
     */
    private void chooseFile(){
        fileChooser.setTitle("Seleziona un file che contenga una lista di contatti");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("File CSV", "*.csv"),
            new FileChooser.ExtensionFilter("File JSON", "*.json")
        );
    }
    /**
     * @brief Metodo per far comparire la finestra di dialogo su schermo.
     * @param[in] event
     */
    private void reactOnEvent(ActionEvent event){
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        importAs(selectedFile);
    }
    /**
     * @brief Metodo per selezionare la strategia di import del file.
     * Questo metodo effettua una scelta di strategia di import del file in base all'estensione del file scelto.
     * Nel caso in cui il file passato in input sia nullo, il metodo stamperà su terminale un errore.
     * @pre file selezionato
     * @post strategia di import impostata
     * @param[in] selectedFile
     * 
     */
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
    /**
     * Metodo per ottenere l'estensione del file selezionato nella finestra di dialogo.
     * @param[in] file
     * @return estensione del file
     */
    private String getFileExtension(File file){
        String filename = file.getName();
        int lastIndex = filename.lastIndexOf('.');
        return (lastIndex > 0) ? filename.substring(lastIndex + 1).toLowerCase() : "";
    }
    /**
     * Metodo per impostare la strategia di import in ImportCSV. Il file passato in input
     * verrà deserializzato in una lista di contatti. La lista di contatti verrà fatta scorrere
     * per verificare che ogni contatto venga validato ({@link Validator}). Se il contatto rispetta
     * i criteri di valuazione scelti, allora viene inserito nella rubrica, altrimenti viene scartato. 
     * @pre il file deve esistere.
     * @post la rubrica viene aggiornata.
     * @param[in] selectedFile file scelezionato nella finestra di dialogo.
     */
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
    /**
     * Metodo per impostare la strategia di import in ImportJSON. Il file passato in input
     * verrà deserializzato in una lista di contatti. La lista di contatti verrà fatta scorrere
     * per verificare che ogni contatto venga validato ({@link Validator}). Se il contatto rispetta
     * i criteri di valuazione scelti, allora viene inserito nella rubrica, altrimenti viene scartato. 
     * @pre il file deve esistere.
     * @post la rubrica viene aggiornata.
     * @param[in] selectedFile file scelezionato nella finestra di dialogo.
     */
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
