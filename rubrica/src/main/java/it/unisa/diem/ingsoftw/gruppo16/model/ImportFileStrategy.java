package it.unisa.diem.ingsoftw.gruppo16.model;
import java.io.File;
import java.util.TreeSet;

/**
 * @interface ImportFileStrategy
 * @brief Definisce il metodo importFile.
 */
public interface ImportFileStrategy {
    /**
     * 
     * @param[in] filename Path del file da importare
     * @return TreeSet con i contatti importati dal file.
     */
    public TreeSet<Contact> importFile(File file);
    
}
