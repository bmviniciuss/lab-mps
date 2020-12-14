package business.reports;

import business.model.Date;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import util.ReportCreationException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDFReport extends ReportTemplate {

    public void writeReport(Date date, String name, String title, String content) throws ReportCreationException {

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("PDFReport.pdf"));
            document.open();
            document.add(new Paragraph(title));
            document.add(new Paragraph(content));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Reported by: " + name));
            document.add(new Paragraph("Reported on: " + date.toString()));
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            throw new ReportCreationException();
        }
    }
}

