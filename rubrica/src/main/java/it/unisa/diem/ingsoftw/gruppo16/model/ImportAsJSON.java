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
     * 
     * @param[in] filename Path del file da importare
     * @return TreeSet con i contatti importati dal file JSON.
     * 
     * 
    */
    public TreeSet<Contact> importFile(File file) {
        String filename = file.getAbsolutePath();
        Gson gson = new Gson();
        java.lang.reflect.Type treeSet = new TypeToken<TreeSet<Contact>>(){}.getType();
        try(FileReader reader = new FileReader(filename)) {
            return gson.fromJson(filename, treeSet);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
