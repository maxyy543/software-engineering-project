package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.util.Optional;

import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.ExportAsCSV;
import it.unisa.diem.ingsoftw.gruppo16.model.ExportAsJSON;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * @brief Controller per l'export della rubrica in un file(CSV o JSON).
 * Questa classe permette di scegliere una estensione tra CSV e JSON ed esportare la rubrica in un file
 * 
 * @Class ExportFileController
 */
public class ExportFileController {
    
    private Alert alert;///< Messagio di alert per la conferma di una delle due estensioni.
    private AddressBookModel addrBook; ///< rubrica
    
    /**
     * @brief Costruttore di ExportFileController
     * Inizializza gli attributi definiti e il processo di esportazione della rubrica.
     * @param event
     */
    public ExportFileController(ActionEvent event){
        init(event);
    }

    /**
     * Inizializza l'operazione di export.
     * Se la rubrica è vuota viene fatto visualizzare un messaggio di errore.
     * @param event
     */
    private void init(ActionEvent event){
        addrBook = AddressBookModel.getInstance();
        if(addrBook.isEmpty())
            setAlert();
        else
            reactOnEvent(event);
        
    }
    /**
     * @brief Gestisce l'evento di selezione del formato del file su cui esportare la rubrica.
     * Viene fatto comparire a schermo un alert in cui l'utente deve selezionare un tipo di formato
     * di estensione del file.
     * @param event
     */
    private void reactOnEvent(ActionEvent event){
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Esporta rubrica");
        alert.setHeaderText("Seleziona il formato del file da esportare");
        alert.setContentText("Scegli tra le seguenti opzioni:");

        ButtonType buttonCSV = new ButtonType("CSV");
        ButtonType buttonJSON = new ButtonType("JSON");
        ButtonType buttonCancel = ButtonType.CANCEL;
        alert.getButtonTypes().setAll(buttonCSV, buttonJSON,buttonCancel);
        Optional<ButtonType> choice = alert.showAndWait();
        if(choice.isPresent()){
            if(choice.get() == buttonCSV){
                exportCSV("Test.csv");
            }
            else if(choice.get() == buttonJSON){
                exportJSON("Test.json");
            }else if(choice.get()== buttonCancel){
                System.out.println("Operazione annullata");
            }
        }else{
            System.out.println("Nessuna scelta effettuata");
        }
    }
    /**
     *  @brief Viene fatto visualizzare un messaggio di errore: rubrica vuota.
     */
    private void setAlert(){
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Rubrica vuota!");
        alert.setContentText("Devi inserire dei contatti all'interno della rubrica prima di effettuare un export!");
        alert.showAndWait();
    }
    /**
     * @brief Esporta la rubrica in un file CSV.
     * Viene impostata la strategia CSV per l'export della rubrica.
     * @param filename[inout] path su cui salvare la rubrica. 
     */
    private void exportCSV(String filename){
        ExportAsCSV csv = new ExportAsCSV();
        csv.exportFile(filename, addrBook.getTreeSet());
    }
    /**
     * @brief Esporta la rubrica in un file JSON.
     * Viene impostata la strategia JSON per l'export della rubrica.
     * @param filename[inout] path su cui salvare la rubrica. 
     */
    private void exportJSON(String filename){
        ExportAsJSON json = new ExportAsJSON();
        json.exportFile(filename, addrBook.getTreeSet());
    }
}
