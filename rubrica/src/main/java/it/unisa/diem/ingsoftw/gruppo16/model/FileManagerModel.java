package classes;

public class FileManagerModel {

    private ExportFileStrategy exportFileStrategy;

    private ImportFileStrategy importFileStrategy;


    public FileManagerModel(ExportFileStrategy strategy){
        this.exportFileStrategy = strategy;
    }
    public FileManagerModel(ImportFileStrategy strategy){
        this.importFileStrategy = strategy;
    }
    public void setExportFileStrategy(ExportFileStrategy exportFileStrategy) {
        this.exportFileStrategy = exportFileStrategy;
    }
    public void setImportFileStrategy(ImportFileStrategy importFileStrategy) {
        this.importFileStrategy = importFileStrategy;
    }

    
    
}
