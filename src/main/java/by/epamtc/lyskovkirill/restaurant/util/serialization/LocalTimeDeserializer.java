package by.epamtc.lyskovkirill.restaurant.util.serialization;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Json Deserializer interface implementation utility class that is
 * responsible for deserializing {@link LocalTime}.
 *
 * @author k1ly
 */
public class LocalTimeDeserializer implements JsonDeserializer<LocalTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private static final LocalTimeDeserializer instance = new LocalTimeDeserializer();

    private LocalTimeDeserializer() {
    }

    public static LocalTimeDeserializer getInstance() {
        return instance;
    }

    public LocalTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalTime.from(formatter.parse(json.getAsJsonPrimitive().getAsString()));
    }
}
