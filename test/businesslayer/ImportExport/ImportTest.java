package businesslayer.ImportExport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.TourItem;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImportTest {

    @Test
    void importTours() throws IOException {
        ObservableList<TourItem> tourItems = FXCollections.observableArrayList();
        tourItems.add(new TourItem(1, "name", "origin", "destination", "description", 1.0));
        tourItems.add(new TourItem(2, "name2", "origin2", "destination2", "description2", 2.0));
        tourItems.add(new TourItem(3, "name3", "origin3", "destination3", "description3", 3.0));
        String path = Export.exportTours(tourItems);

        ObservableList<TourItem> tourItemsCompare = FXCollections.observableArrayList();
        tourItemsCompare.addAll(Import.importTours(path));

        assertEquals(tourItems, tourItemsCompare);
    }
}