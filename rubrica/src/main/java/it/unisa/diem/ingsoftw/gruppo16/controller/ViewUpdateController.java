package it.unisa.diem.ingsoftw.gruppo16.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @biref Classe controller per la gestione delle scene in JavaFX.
 * 
 * Questa classe permette di gestire l'aggiornamento delle scene dell'applicazione.
 * Esiste solo una istanza per questa classe (Singleton pattern).
 * Oltre a caricare le scene da file FXML, la classe permette di inserire anche file css
 * per personalizzare l'applicazione.
 * @Class ViewUpdateController
 */
public class ViewUpdateController {
    
    private static ViewUpdateController instance; ///< istanza di ViewUpdateController.
    private static Stage stage; 
 
    private ViewUpdateController(){}

    /**
     * Metodo per ottenere l'istanza di ViewUpdateController.
     * @return istanza di ViewUpdateController.
     */
    public static ViewUpdateController getInstance(){
        if(instance == null){
            instance = new ViewUpdateController();
        }
        return instance;
    }
    /**
     * Metodo per impostare lo stage principale su cui caricare le scene FXML.
     * @param[in] stage
     */
    public void setStage(Stage stage){
        ViewUpdateController.stage = stage;
    }
    /**
     * 
     * @param[in] fxml scena da caricare sullo stage
     * @param[in] css file per lo styling della scena
     * @param[in] txt testo da impostare per il titolo della scena
     */
    private void setScene(String fxml, String css, String txt){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
            stage.setScene(scene);
            stage.show();
            stage.setTitle(txt);
            stage.setResizable(false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setDashboardScene(){
        setScene("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface.fxml", "/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/style2.css", "Dashboard");
    }
    public void setModifyScene(){
        setScene("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface3.fxml", "/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/style2.css", "Modify Contact");
    }
    public void setDetailOfContactScene(){
        setScene("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface2.fxml", "/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/style2.css", "Contact Detail");
    }
    public void setAddContactScene(){
        setScene("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface4.fxml", "/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/style2.css", "Add Contact");
    }
}
