package businesslayer;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import models.TourItem;
import models.TourLog;

import java.io.FileOutputStream;


public class PDFGenerator {
    //TODO: this is technically working, but of course it doesn't do anything yet

    //TODO: idea: print out new tours, total tours, new logs, total logs, number of searches, number of api requests etc

    //read: https://kb.itextpdf.com/home/it7kb/examples/itext-7-jump-start-tutorial-chapter-1
    //and: http://tutorials.jenkov.com/java-itext/getting-started.html

    public static void generatePDF(ObservableList<TourItem> tourItems, ObservableList<TourLog> tourLogs) {
        try {
            int tourCount = tourItems.size();
            int logCount = tourLogs.size();
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("TourPlannerReport.pdf"));
            document.open();
            document.add(new Paragraph("Tour Planner Summary:"));
            document.add(new Paragraph("Number of Tours: " + tourCount));
            document.add(new Paragraph("Number of Logs: " + logCount));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //TOOD: make tour specific report with map image

    public static String tourPDF(TourItem tourItem) {
        String path = "ID" + tourItem.getId() + ".pdf";
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            document.add(new Paragraph("Tour Report:"));
            document.add(new Paragraph("ID: " + tourItem.getId()));
            document.add(new Paragraph("Name: " + tourItem.getName()));
            document.add(new Paragraph("Origin: " + tourItem.getOrigin()));
            document.add(new Paragraph("Destination: " + tourItem.getDestination()));
            document.add(new Paragraph("Description: " + tourItem.getDescription()));
            document.add(new Paragraph("Distance: " + tourItem.getDistance()));
            document.add(new Paragraph("Image: "));
            String imagePath = MapQuest.createStaticMapImage(tourItem);
            Image image = Image.getInstance(imagePath);
            image.scaleToFit(500, 500);
            document.add(image);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

}
