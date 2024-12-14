package it.unisa.diem.ingsoftw.gruppo16.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


/**
 * @Class ImportAsXML
 * @brief Strategia per importare i contatti in un file in formato XML
 * @verison 1.0
 */


public class ImportAsXML implements ImportFileStrategy {

    @Override
    public TreeSet<Contact> importFile(File file) {
        ArrayList<Contact> arrayListContacts = new ArrayList<>();
        XmlMapper xmlMapper = new XmlMapper();
        try {
            arrayListContacts = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, Contact.class));
            TreeSet<Contact> tree = new TreeSet<>();
            for(Contact c: arrayListContacts){
                tree.add(c);
            }
            return tree;
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return null;
    }
}