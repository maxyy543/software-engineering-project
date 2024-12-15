package it.unisa.diem.ingsoftw.gruppo16.controller;
import it.unisa.diem.ingsoftw.gruppo16.model.AddressBookModel;
import it.unisa.diem.ingsoftw.gruppo16.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @brief Classe controller per la gestione della ListView: filtrare gli elementi, ottenere la lista della ListView e aggiornare la Listview.
 * @Class ListViewController
 */
public class ListViewController{
    private static ObservableList<Contact> listView; ///< listView static.
    private AddressBookModel addrBook;///< istanza di AddressBookModel.

    /**
     * @brief Costruttore della classe per istanziare gli attributi.
     */
    public ListViewController(){
        addrBook = AddressBookModel.getInstance();
        if(listView == null)
            listView = FXCollections.observableArrayList(addrBook.getTreeSet());
    }
    /**
     * @brief Restituisce l'istanza della listView.
     * @return istanza di listView.
     */
    public ObservableList<Contact> getSharedListView() {
        return listView;
    }

    /**
     * @brief Filtra gli elementi della ListView con una sottostringa del nome e del cognome di un contatto.
     * 
     * @param[in] s stringa di caratteri utilizzati per trovare un elemento nella rubrica. Se il filtro trova un contatto, allora 
     * questo viene aggiunto alla listview "filtrata".
     */
    public void filterList(String s){
        listView.clear();
        for(Contact c : addrBook.getTreeSet()){
            if(c.getName().toLowerCase().contains(s.toLowerCase()) ||
                c.getSurname().toLowerCase().contains(s.toLowerCase())){
                    listView.add(c);
            }
        }
    }
    /**
     * @brief aggiorna la listView con i nuovi contatti della rubrica.
     */
    public void updateList(){
        listView.setAll(addrBook.getTreeSet());
    }
}
