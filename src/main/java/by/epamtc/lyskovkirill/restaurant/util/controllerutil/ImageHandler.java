package by.epamtc.lyskovkirill.restaurant.util.controllerutil;

import jakarta.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Paths;

/**
 * Class that is responsible for handling request's {@link Part}
 * and retrieving an image to save it.
 *
 * @author k1ly
 */
public class ImageHandler {
    private static final ImageHandler instance = new ImageHandler();

    private ImageHandler() {
    }

    public static ImageHandler getInstance() {
        return instance;
    }

    public String saveImage(Part fileData, String path) throws IOException {
        String imageUrl = null;
        String filePath = fileData.getSubmittedFileName();
        if (!filePath.isEmpty()) {
            filePath = Paths.get(path, filePath).toString();
            File imageFile = new File(filePath);
            boolean directoryExists = imageFile.getParentFile().exists();
            if (!directoryExists)
                directoryExists = imageFile.getParentFile().mkdirs();
            if (directoryExists) {
                boolean fileExists;
                int copyCounter = 1;
                do {
                    fileExists = imageFile.exists();
                    imageFile = new File(FilenameUtils.removeExtension(filePath)
                            + "(" + copyCounter++ + ")." + FilenameUtils.getExtension(filePath));
                } while (fileExists);
                if (imageFile.createNewFile()) {
                    try (InputStream imageFileInput = fileData.getInputStream();
                         OutputStream imageFileOutput = new FileOutputStream(imageFile)) {
                        IOUtils.copy(imageFileInput, imageFileOutput);
                    }
                    imageUrl = imageFile.getName();
                }
            }
        }
        return imageUrl;
    }
}
