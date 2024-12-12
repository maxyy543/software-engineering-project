package it.unisa.diem.ingsoftw.gruppo16.model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;


/**
 * @Class ImportAsXML
 * @brief Strategia per importare i contatti in un file in formato XML
 * @verison 1.0
 */


public class ImportAsXML implements ImportFileStrategy {

    @Override
    public TreeSet<Contact> importFile(File file) {
        String filename = file.getAbsolutePath();
        XmlMapper xmlMapper = new XmlMapper();
        try{
            List<Contact> listContacts = xmlMapper.readValue(new File(filename),
                                         xmlMapper.getTypeFactory().constructCollectionType(List.class, Contact.class));
            TreeSet<Contact> treeContacts = new TreeSet<>(listContacts);
            return treeContacts;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}