package business.reports;

import business.model.Date;
import business.model.IUser;
import util.ReportCreationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class ReportTemplate {

    public abstract void writeReport(Date date, String name, String title, String content) throws ReportCreationException;

    public void createReport(IUser user) throws ReportCreationException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        LocalDateTime now = LocalDateTime.now();
        Date date = new Date(dtf.format(now));
        String name = user.getLogin();
        String title = "User Access Report";
        String content = "Users: \n1. User 1 \n2. User 2 \n3. User 3";
        writeReport(date, name, title, content);
    }
}
