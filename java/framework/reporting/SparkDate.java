package framework.reporting;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SparkDate {
    private Date date;

    public SparkDate() {
        this.date = new Date();
    }

    public String getFormat() {

        SimpleDateFormat DateFor = new SimpleDateFormat("YYYY-MM-dd-hh-mm-ss");
        String stringDate = DateFor.format(date);
        return stringDate;
    }
}
