package it.unisa.diem.ingsoftw.gruppo16.model;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * @Class ExportAsXML
 * @brief Strategia per esportare i contatti in un file in formato XML
 * @verison 1.0
 */
public class ExportAsXML implements ExportFileStrategy {
    /**
     * 
     * @param[in] filename Path del file da esportare
     * @param[in] contacts Contatti da salvare nel file XML
     */
    @Override
    public void exportFile(String filename, TreeSet<Contact> contacts) {
        XmlMapper xmlMapper = new XmlMapper();
        List<Contact> list = contacts.stream().collect(Collectors.toList());
        try{
            xmlMapper.writeValue(new File(filename), list);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

