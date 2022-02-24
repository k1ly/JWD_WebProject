package by.epamtc.lyskovkirill.restaurant.dao.impl;

import by.epamtc.lyskovkirill.restaurant.bean.contact.Contact;
import by.epamtc.lyskovkirill.restaurant.dao.ContactDAO;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.util.serialization.LocalTimeDeserializer;
import by.epamtc.lyskovkirill.restaurant.util.serialization.LocalTimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Optional;

/**
 * JSON {@link ContactDAO} interface implementation.
 *
 * @author k1ly
 */
public class JSONContactDAO implements ContactDAO {
    private static final String FILE_NAME = "contacts.json";

    @Override
    public boolean addContact(Contact contact) throws DAOException {
        boolean isContactAdded = false;
        if (contact != null) {
            URL url = this.getClass().getResource("/");
            if (url != null) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(LocalTime.class, LocalTimeSerializer.getInstance());
                Gson gson = gsonBuilder.setPrettyPrinting().create();
                String path = url.getPath().replaceFirst("/", "").replace("/$", "");
                try (FileWriter fileWriter = new FileWriter(Paths.get(path, FILE_NAME).toString())) {
                    gson.toJson(contact, fileWriter);
                    isContactAdded = true;
                } catch (IOException exception) {
                    throw new DAOException("Contacts serialization error", exception);
                }
            }
        }
        return isContactAdded;
    }

    @Override
    public Optional<Contact> findContact() throws DAOException {
        Optional<Contact> contact = Optional.empty();
        URL url = this.getClass().getClassLoader().getResource(FILE_NAME);
        if (url != null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalTime.class, LocalTimeDeserializer.getInstance());
            Gson gson = gsonBuilder.create();
            Type contactType = new TypeToken<Contact>() {
            }.getType();
            String path = url.getPath().replaceFirst("/", "").replace("/$", "");
            try (FileReader fileReader = new FileReader(path)) {
                contact = Optional.of(gson.fromJson(fileReader, contactType));
            } catch (IOException exception) {
                throw new DAOException("Contacts deserialization error", exception);
            }
        }
        return contact;
    }
}
