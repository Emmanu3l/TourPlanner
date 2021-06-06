package businesslayer.ImportExport;

import businesslayer.ConfigurationManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.TourItem;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Export {

    public static String exportTours(List<TourItem> tourItems) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss");

        TypeReference<List<TourItem>> typeReference = new TypeReference<>() {};
        String path = ConfigurationManager.GetConfigProperty("FilePath") + LocalDateTime.now().format(formatter) + ".json";
        objectMapper.writerFor(typeReference).writeValue(new File(path), tourItems);
        return path;
    }

}
