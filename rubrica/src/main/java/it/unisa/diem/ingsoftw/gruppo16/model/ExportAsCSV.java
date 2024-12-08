package it.unisa.diem.ingsoftw.gruppo16.model;
import java.util.TreeSet;
/**
 * @Class ExportAsCSV
 * @brief Strategia per esportare i contatti in un file in formato CSV
 * @verison 1.0
 */
public class ExportAsCSV implements ExportFileStrategy {
    /**
     * 
     * @param[in] filename Path del file da esportare
     * @param[in] contacts Contatti da salvare nel file CSV
     */
    @Override
    public void exportFile(String filename, TreeSet<Contact> contacts) {
        
    }
}
