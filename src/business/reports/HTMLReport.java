package business.reports;

import business.model.Date;
import util.ReportCreationException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HTMLReport extends ReportTemplate{

    public void writeReport(Date date, String name, String title, String content) throws ReportCreationException {

        try {
            File report = new File("HTMLReport.html");

            BufferedWriter bw = new BufferedWriter(new FileWriter(report));

            bw.write("<html><body><h1>" + title + "</h1>");
            bw.write("<p>" + content+ "</p>");
            bw.write("<br></br>");
            bw.write("Reported by: " + name);
            bw.write("Reported on: " + date.toString());
            bw.write("</body></html>");
            bw.close();
        }catch (IOException e){
            throw new ReportCreationException();
        }
    }
}
