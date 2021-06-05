package businesslayer;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;


public class PDFGenerator {
    //TODO: this is technically working, but of course it doesn't do anything yet

    //TODO: idea: print out new tours, total tours, new logs, total logs, number of searches, number of api requests etc

    //read: https://kb.itextpdf.com/home/it7kb/examples/itext-7-jump-start-tutorial-chapter-1
    //and: http://tutorials.jenkov.com/java-itext/getting-started.html

    public static void generatePDF(int tourCount, int logCount) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("TourPlannerReport.pdf"));
            document.open();
            document.add(new Paragraph("Information on the Tour Planner"));
            document.add(new Paragraph("Number of Tours: " + tourCount));
            document.add(new Paragraph("Number of Logs: " + logCount));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //TOOD: make tour specific report with map image

    public static void addedTour() {

    }

    public static void appendParagraph() {

    }
}
