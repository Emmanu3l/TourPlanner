package businesslayer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.TourItem;
import models.TourLog;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PDFGeneratorTest {

    @Test
    void generatePDF() {
        ObservableList<TourItem> tourItems = FXCollections.observableArrayList();
        ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();
        PDFGenerator.generatePDF(tourItems, tourLogs);
        File file = new File("TourPlannerReport.pdf");
        assert(file.exists());
    }
}