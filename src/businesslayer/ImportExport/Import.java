package businesslayer.ImportExport;

import businesslayer.ConfigurationManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.TourItem;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Import {

    public static List<TourItem> importTours(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(objectMapper.readValue(Paths.get(filename).toFile(), TourItem[].class));
    }

}
