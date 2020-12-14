package business.model;

import java.io.Serializable;

public class Date implements Serializable {

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(String date){
        String[] data = date.split("/");
        this.day = Integer.parseInt(data[0]);
        this.month = Integer.parseInt(data[1]);
        this.year = Integer.parseInt(data[2]);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return this.getDay() + "/" + this.getMonth() + "/" + this.getYear();
    }
}
