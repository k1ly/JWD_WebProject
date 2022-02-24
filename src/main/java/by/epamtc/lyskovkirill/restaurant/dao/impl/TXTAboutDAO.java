package by.epamtc.lyskovkirill.restaurant.dao.impl;

import by.epamtc.lyskovkirill.restaurant.dao.AboutDAO;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * TXT {@link AboutDAO} interface implementation.
 *
 * @author k1ly
 */
public class TXTAboutDAO implements AboutDAO {
    private static final String FILE_NAME = "about.txt";

    @Override
    public boolean addAbout(String about) throws DAOException {
        boolean isAboutAdded = false;
        URL url = this.getClass().getResource("/");
        if (url != null) {
            try (FileWriter fileWriter = new FileWriter(Paths.get(url.getPath(), FILE_NAME).toString())) {
                fileWriter.write(about);
                isAboutAdded = true;
            } catch (IOException e) {
                throw new DAOException("About text writing error", e);
            }
        }
        return isAboutAdded;
    }

    @Override
    public String findAbout() throws DAOException {
        String about = "";
        URL url = this.getClass().getClassLoader().getResource(FILE_NAME);
        if (url != null) {
            try (FileReader fileReader = new FileReader(url.getPath());
                 Scanner scanner = new Scanner(fileReader)) {
                StringBuilder text = new StringBuilder();
                while (scanner.hasNextLine()) {
                    text.append(scanner.nextLine());
                }
                about = text.toString();
            } catch (IOException e) {
                throw new DAOException("About text reading error", e);
            }
        }
        return about;
    }
}
