package it.unisa.diem.ingsoftw.gruppo16.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/**
 * @Class ImportAsJSON
 * @brief Strategia per importare i contatti in un file in formato JSON
 * @version 1.0
*/
public class ImportAsJSON implements ImportFileStrategy {
    /**
     * @brief metodo per importare dei contatti nella rubrica da un file JSON.
     * 
     * Metodo che implementa l'importFile di {@link ExportFileStrategy}.
     * Il metodo utilizza {@link Gson} per deserializzare un file in un oggetto {@link Contact}
     * da inserire in una {@link TreeSet}.
     * 
     * @param[in] filename path del file da cui importare la rubrica.
     *  
     * @return una treeset contenente contatti da validare con il {@link Validator}.
     */
    @Override
    public TreeSet<Contact> importFile(File file) {
        String filename = file.getAbsolutePath();
        Gson gson = new Gson();
        java.lang.reflect.Type treeSet = new TypeToken<TreeSet<Contact>>(){}.getType();
        try(FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, treeSet);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
