package it.unisa.diem.ingsoftw.gruppo16.model;

/**
 * @Class FileManagerModel
 * @brief Classe per la gestione dell'import e dell'export dei file.
 */
public class FileManagerModel {

    /**
     * strategia di export
     */
    private ExportFileStrategy exportFileStrategy;

    /**
     * strategi di import
     */
    private ImportFileStrategy importFileStrategy;

    /**
     * @brief Costruttore per l'inizializzazione della modalità con cui si vuole esportare un file.
     * @param[in] strategy
     */
    public FileManagerModel(ExportFileStrategy strategy){
        this.exportFileStrategy = strategy;
    }
    /**
     * @brief Costruttore per l'inizializzazione della modalità con cui si vuole importare un file.
     * @param[in] strategy
     */
    public FileManagerModel(ImportFileStrategy strategy){
        this.importFileStrategy = strategy;
    }
    /**
     * @brief Setter della strategia di esportazione del file
     * @param[in] exportFileStrategy
     */
    public void setExportFileStrategy(ExportFileStrategy exportFileStrategy) {
        this.exportFileStrategy = exportFileStrategy;
    }
    /**
     * @brief Setter della strategia di importazione del file
     * @param[in] exportFileStrategy
     */
    public void setImportFileStrategy(ImportFileStrategy importFileStrategy) {
        this.importFileStrategy = importFileStrategy;
    }

    
    
}
