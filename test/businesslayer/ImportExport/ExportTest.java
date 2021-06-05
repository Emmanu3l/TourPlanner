package businesslayer.ImportExport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.TourItem;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExportTest {

    @Test
    void exportTours() throws IOException {
        ObservableList<TourItem> tourItems = FXCollections.observableArrayList();
        tourItems.add(new TourItem(1, "name", "origin", "destination", "description", 1.0));
        tourItems.add(new TourItem(2, "name2", "origin2", "destination2", "description2", 2.0));
        tourItems.add(new TourItem(3, "name3", "origin3", "destination3", "description3", 3.0));
        String path = Export.exportTours(tourItems);
        File file = new File(path);
        assert(file.exists());
        //TODO: if you had LocalDateTime for the log as a parameter, you could use Clock to mock the time and keep overwriting the previous logs when testing instead of constantly creating new files
    }
    //TODO: wie heißen die files wenn ich zwei gleichzeitig adde?

}