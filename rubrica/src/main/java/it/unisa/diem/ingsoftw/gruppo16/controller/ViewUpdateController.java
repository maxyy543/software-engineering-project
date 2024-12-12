package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.io.IOException;

import it.unisa.diem.ingsoftw.gruppo16.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewUpdateController {
    
    private static Scene scene;
    private static Stage stage;
    private static Parent root;

    public ViewUpdateController(Stage stage){
        ViewUpdateController.stage = stage;
    }

    public void setDashboardScene(){
        try {
            ViewUpdateController.root = loadFXML("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface");
            ViewUpdateController.scene = new Scene(root);
            ViewUpdateController.stage.setScene(scene);
            ViewUpdateController.stage.show();
            ViewUpdateController.stage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setAddAndModifyScene(){
        try {
            ViewUpdateController.root = loadFXML("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface3");
            ViewUpdateController.scene = new Scene(root);
            ViewUpdateController.stage.setScene(scene);
            ViewUpdateController.stage.show();
            ViewUpdateController.stage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setDetailOfContactScene(){
        try {
            ViewUpdateController.root = loadFXML("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface2");
            ViewUpdateController.scene = new Scene(root);
            ViewUpdateController.stage.setScene(scene);
            ViewUpdateController.stage.show();
            ViewUpdateController.stage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
