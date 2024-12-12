package it.unisa.diem.ingsoftw.gruppo16;

import java.io.IOException;

import it.unisa.diem.ingsoftw.gruppo16.controller.ViewUpdateController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    private static Scene scene;
    public static void main( String[] args )
    {
        launch(args);
    }
    
    @Override
    public void start(Stage stage){
        ViewUpdateController view = new ViewUpdateController(stage);
        view.setDashboardScene();      
        /*try {
            Parent root = loadFXML("fxmlDir/interface");
            Scene scene1 = new Scene(root);
            stage.setScene(scene1);
            stage.show();
            stage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
