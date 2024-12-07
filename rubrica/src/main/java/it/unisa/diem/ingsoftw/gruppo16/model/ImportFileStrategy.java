package classes;
import java.util.Collection;

public interface ImportFileStrategy {

    public Collection<Contact> importFile(String filename);
    
}
