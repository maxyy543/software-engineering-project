package it.unisa.diem.ingsoftw.gruppo16.model;

import java.util.TreeSet;
/**
 * @Class ImportAsJSON
 * @brief Strategia per importare i contatti in un file in formato JSON
 * @verison 1.0
 */
public class ImportJSONStrategy implements ImportFileStrategy {
    @Override
    public TreeSet<Contact> importFile(String filename) {
        TreeSet<Contact> contacts = new TreeSet<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String name = jsonObject.optString("name", "");
                String surname = jsonObject.optString("surname", "");

                // Verifico che almeno uno tra nome e cognome sia presente
                if (name.isEmpty() && surname.isEmpty()) {
                    continue;
                }

                Contact contact = new Contact(name, surname);

                // Aggiungo i numeri di telefono (separati da ";")
                if (jsonObject.has("phoneNumbers")) {
                    String[] phoneNumbers = jsonObject.getString("phoneNumbers").split(";");
                    for (int j = 0; j < Math.min(phoneNumbers.length, 3); j++) {
                        contact.setTelephoneNumber(phoneNumbers[j]);
                    }
                }

                // Aggiungo le email (separate da ";") solo se presenti
                if (jsonObject.has("emails")) {
                    String[] emails = jsonObject.getString("emails").split(";");
                    for (int j = 0; j < Math.min(emails.length, 3); j++) {
                        contact.addEmail(emails[j]);
                    }
                }

                // Aggiungo il contatto al TreeSet
                contacts.add(contact);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return contacts;
    }
}
