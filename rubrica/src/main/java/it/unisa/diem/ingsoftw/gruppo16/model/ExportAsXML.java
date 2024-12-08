package it.unisa.diem.ingsoftw.gruppo16.model;
import java.util.TreeSet;

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
    }
}
