package it.unisa.diem.ingsoftw.gruppo16.model;
import java.util.TreeSet;
/**
 * @Class ImportAsCSV
 * @brief Strategia per importare i contatti in un file in formato CSV
 * @verison 1.0
 */
public class ImportAsCSV implements ImportFileStrategy {
    /**
     * 
     * @param[in] filename Path del file da importare
     * @return TreeSet con i contatti importati dal file CSV.
     */
    @Override
    public TreeSet<Contact> importFile(String filename) {
            return null;
    }
}
