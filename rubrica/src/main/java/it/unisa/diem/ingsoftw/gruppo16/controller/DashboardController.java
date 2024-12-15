package it.unisa.diem.ingsoftw.gruppo16.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class DashboardController extends MainController implements Initializable{
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedContact = SelectedContactController.getInstance();
        view = ViewUpdateController.getInstance();
        list = new ListViewController();
        listView.setItems(list.getSharedListView());
        listViewSelectItemInit();
        initSearchbar();
    }
}
