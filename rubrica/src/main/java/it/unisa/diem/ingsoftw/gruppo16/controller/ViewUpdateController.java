package it.unisa.diem.ingsoftw.gruppo16.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewUpdateController {
    
    private static ViewUpdateController instance;
    private static Stage stage;
 


    private ViewUpdateController(){}

    public static ViewUpdateController getInstance(){
        if(instance == null){
            instance = new ViewUpdateController();
        }
        return instance;
    }

    public void setStage(Stage stage){
        ViewUpdateController.stage = stage;
    }
    private void setScene(String fxml){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setDashboardScene(){
        setScene("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface.fxml");
    }
    public void setModifyScene(){
        setScene("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface3.fxml");
    }
    public void setDetailOfContactScene(){
        setScene("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface2.fxml");
    }
    public void setAddContactScene(){
        setScene("/it/unisa/diem/ingsoftw/gruppo16/fxmlDir/interface4.fxml");
    }
}
