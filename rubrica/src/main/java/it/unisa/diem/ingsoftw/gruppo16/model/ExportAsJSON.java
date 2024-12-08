package it.unisa.diem.ingsoftw.gruppo16.model;
import java.util.TreeSet;
/**
 * @Class ExportAsJSON
 * @brief Strategia per esportare i contatti in un file in formato JSON
 * @verison 1.0
 */
public class ExportAsJSON implements ExportFileStrategy {
    /**
     * 
     * @param[in] filename Path del file da esportare
     * @param[in] contacts Contatti da salvare nel file JSON
     */
    @Override
    public void exportFile(String filename, TreeSet<Contact> contacts) {
    }
}
