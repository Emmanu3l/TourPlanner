package businesslayer;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PDFGeneratorTest {

    @Test
    void generatePDF() {
        PDFGenerator.generatePDF(1, 1);
        File file = new File("TourPlannerReport.pdf");
        assert(file.exists());
    }
}