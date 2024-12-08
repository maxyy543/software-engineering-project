package it.unisa.diem.ingsoftw.gruppo16.model;
import java.util.TreeSet;

/**
 * @Class ImportAsXML
 * @brief Strategia per importare i contatti in un file in formato XML
 * @verison 1.0
 */
public class ImportAsXML implements ImportFileStrategy {
    /**
     * 
     * @param[in] filename Path del file da importare
     * @return TreeSet con i contatti importati dal file XML.
     */
    @Override
    public TreeSet<Contact> importFile(String filename) {
            return null;
        
    }
}
