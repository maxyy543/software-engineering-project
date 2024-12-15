package it.unisa.diem.ingsoftw.gruppo16.controller;
import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListViewController{
    private static ObservableList<Contact> listView;
    private AddressBookModel addrBook;

    public ListViewController(){
        addrBook = AddressBookModel.getInstance();
        if(listView == null)
            listView = FXCollections.observableArrayList(addrBook.getTreeSet());
    }

    public ObservableList<Contact> getSharedListView() {
        return listView;
    }

    public void filterList(String s){
        listView.clear();
        for(Contact c : addrBook.getTreeSet()){
            if(c.getName().toLowerCase().contains(s.toLowerCase()) ||
                c.getSurname().toLowerCase().contains(s.toLowerCase())){
                    listView.add(c);
            }
        }
    }

    public void updateList(){
        listView.setAll(addrBook.getTreeSet());
    }
}
