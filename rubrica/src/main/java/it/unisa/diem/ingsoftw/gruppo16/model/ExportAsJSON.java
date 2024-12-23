package it.unisa.diem.ingsoftw.gruppo16.model;

import java.io.File;
import java.io.FileWriter;
import java.util.TreeSet;

import com.google.gson.Gson;

public class ExportAsJSON implements ExportFileStrategy{
    /**
     * @brief metodo per esportare una rubrica in un file JSON.
     * 
     * Metodo che implementa l'exportFile di {@link ExportFileStrategy}.
     * Il metodo visita tutta la lista di contatti e sovrascrive sul file ogni contatto(cognome,nome, numeri di telefono e email)
     * presente nella lista. 
     * 
     * @pre il file inserito deve esistere e la lista di contatti non deve essere vuota.
     * @post viene generato un file CSV con i contatti della rubrica.
     * 
     * @param[inout] filename path del file su cui esportare la rubrica.
     * @param[in] contacts {@link TreeSet} lista di contatti.
     *  
     */
    @Override
    public void exportFile(String filename, TreeSet<Contact> contacts) {
        Gson gson = new Gson();
        String json = gson.toJson(contacts);
        try{
            File file = new File(filename);
            if(!file.exists()){
                file.createNewFile();
            }
            try(FileWriter fw = new FileWriter(filename)){
                fw.write(json);
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
