package it.unisa.diem.ingsoftw.gruppo16.model;
import java.util.TreeSet;

/**
 * @interface ExportFileStrategy
 * @brief Definisce il metodo exportFile.
 */
public interface ExportFileStrategy {
    /**
     * 
     * @param[in] filename Path del file da esportare
     * @param[in] contacts Contatti da salvare nel file da esportare
     */
    public void exportFile(String filename, TreeSet<Contact> contacts);
}
