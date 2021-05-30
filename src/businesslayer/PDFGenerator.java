package businesslayer;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;


public class PDFGenerator {
    //TODO: this is technically working, but of course it doesn't do anything yet
    //read: https://kb.itextpdf.com/home/it7kb/examples/itext-7-jump-start-tutorial-chapter-1
    //and: http://tutorials.jenkov.com/java-itext/getting-started.html

    public static void generatePDF() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("TourPlannerReport.pdf"));
            document.open();
            document.add(new Paragraph("Information on the Tour Planner"));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
