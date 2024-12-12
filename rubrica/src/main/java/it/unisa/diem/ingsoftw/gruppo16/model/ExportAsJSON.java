package it.unisa.diem.ingsoftw.gruppo16.model;

import java.io.File;
import java.io.FileWriter;
import java.util.TreeSet;

import com.google.gson.Gson;

public class ExportAsJSON implements ExportFileStrategy{
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
