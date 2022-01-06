package validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import dados.Carro;

public class utils {
    public static ObjectMapper createObjectMapper() {
        return new ObjectMapper();
    }
}
