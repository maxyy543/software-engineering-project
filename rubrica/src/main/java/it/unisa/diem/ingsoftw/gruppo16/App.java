package it.unisa.diem.ingsoftw.gruppo16;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.TreeSet;

import it.unisa.diem.ingsoftw.gruppo16.controller.EmailController;
import it.unisa.diem.ingsoftw.gruppo16.controller.NameAndSurnameChecker;
import it.unisa.diem.ingsoftw.gruppo16.controller.TelephoneNumberController;
import it.unisa.diem.ingsoftw.gruppo16.controller.Validator;
import it.unisa.diem.ingsoftw.gruppo16.controller.ViewUpdateController;
import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import it.unisa.diem.ingsoftw.gruppo16.model.ExportAsJSON;
import it.unisa.diem.ingsoftw.gruppo16.model.ImportAsJSON;
import it.unisa.diem.ingsoftw.gruppo16.model.ImportFileStrategy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class App extends Application{
    private static Scene scene;
    AddressBookModel addrBook;
    public static void main( String[] args )
    {
        launch(args);
    }
    
    @Override
    public void start(Stage stage){
        addrBook = AddressBookModel.getInstance();
        Validator verificaContatto;
        String filename = "src/main/resources/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/Test.json";
        File file = new File(filename);
        if(file.exists()){
            ImportFileStrategy strategy = new ImportAsJSON();
            TreeSet<Contact> tempTree = strategy.importFile(file);
            verificaContatto = Validator.link(new NameAndSurnameChecker(), new EmailController(), new TelephoneNumberController());
            for(Contact c: tempTree){
                if(verificaContatto.check(c))
                    addrBook.addNewContact(c);
                else
                    System.out.println("Contatto: "+ c.toString() + "non inserito nella rubrica!");
            }
        }
        ViewUpdateController view = ViewUpdateController.getInstance();
        view.setStage(stage);
        view.setDashboardScene();  
        stage.setOnCloseRequest(event -> {
            event.consume();
            saveAddressBook(stage, filename);
        });
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    private void saveAddressBook(Stage stage, String filename){
        System.out.println("Rubrica salvata");
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Salva rubrica");
        alert.setHeaderText("Vuoi salvare i dati nella rubrica di default?");
        alert.setContentText("Conferma per salvare i contatti sulla rubrica di default");
        Optional<ButtonType> save = alert.showAndWait();
        if(save.isPresent() && save.get() == ButtonType.OK){
            ExportAsJSON exportAsJSON = new ExportAsJSON();
            exportAsJSON.exportFile(filename, addrBook.getTreeSet());
        }
        stage.close();
    }
}
