package businesslayer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.TourItem;
import models.TourLog;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PDFGeneratorTest {

    @Test
    void generatePDF() {
        ObservableList<TourItem> tourItems = FXCollections.observableArrayList();
        ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();
        String path = PDFGenerator.generatePDF(tourItems, tourLogs);
        File file = new File(path);
        assert(file.exists());
    }

    @Test
    void tourPDF() throws SQLException {
        JavaAppManager manager = JavaAppManagerFactory.GetManager();
        TourItem tourItem = manager.CreateTourItem("name", "origin", "destination", "description", 1.0);
        String path = PDFGenerator.tourPDF(tourItem);
        File file = new File(path);
        assert(file.exists());
    }
}