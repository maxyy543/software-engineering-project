package it.unisa.diem.ingsoftw.gruppo16.model;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeSet;

/**
 * @Class ImportAsJSON
 * @brief Strategia per importare i contatti in un file in formato JSON
 * @version 1.0
 */
public class ImportAsJSON implements ImportFileStrategy {

    @Override
    public TreeSet<Contact> importFile(String filename) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'importFile'");
    }
}
    /**
     * 
     * @param[in] filename Path del file da importare
     * @return TreeSet con i contatti importati dal file JSON.
     */
    /*@Override
    */
    
    /*
    public TreeSet<Contact> importFile(String filename) {
        TreeSet<Contact> contacts = new TreeSet<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            /*JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String name = jsonObject.optString("name", "");
                String surname = jsonObject.optString("surname", "");

                if (name.isEmpty() && surname.isEmpty()) {
                    continue;
                }

                Contact contact = new Contact(name, surname);

                if (jsonObject.has("phoneNumbers")) {
                    String[] phoneNumbers = jsonObject.getString("phoneNumbers").split(";");
                    contact.setTelephoneNumber(phoneNumbers);
                }

                if (jsonObject.has("emails")) {
                    String[] emails = jsonObject.getString("emails").split(";");
                    contact.setEmail(emails);
                }

                contacts.add(contact);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return contacts;
        }
}*/
