package by.epamtc.lyskovkirill.restaurant.util.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Json Serializer interface implementation utility class that is
 * responsible for serializing {@link LocalTime}.
 *
 * @author k1ly
 */
public class LocalTimeSerializer implements JsonSerializer<LocalTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private static final LocalTimeSerializer instance = new LocalTimeSerializer();

    private LocalTimeSerializer() {
    }

    public static LocalTimeSerializer getInstance() {
        return instance;
    }

    @Override
    public JsonElement serialize(LocalTime localTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(formatter.format(localTime));
    }
}
