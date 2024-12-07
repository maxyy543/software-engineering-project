package classes;
import java.util.TreeSet;

public interface ExportFileStrategy {

    public void exportFile(String filename, TreeSet<Contact> contacts);
}
