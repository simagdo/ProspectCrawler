package de.simagdo.prospectcrawler.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Utils {

    /**
     * @return the Source code from the given Website
     * @throws IOException if there is an Error
     */
    public String getData(String link) throws IOException {
        StringBuilder data = new StringBuilder();

        //init(); //Load init

        //URL url = new URL(LINK);
        URL url = new URL(link);
        URLConnection con = url.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) data.append(inputLine).append("\n");

        in.close();

        return data.toString();
    }

    public String readAllLines(String fileName) throws Exception {
        StringBuilder data = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Class.forName(Utils.class.getName()).getResourceAsStream(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null)
                data = (data == null ? new StringBuilder("null") : data).append(line).append("\n");
        }
        return data.toString();
    }

    public DayOfWeek convertDate(String dateInput) {

        switch (dateInput) {
            case "Montag":
                return DayOfWeek.MONDAY;
            case "Dienstag":
                return DayOfWeek.TUESDAY;
            case "Mittwoch":
                return DayOfWeek.WEDNESDAY;
            case "Donnerstag":
                return DayOfWeek.THURSDAY;
            case "Freitag":
                return DayOfWeek.FRIDAY;
            case "Samstag":
                return DayOfWeek.SATURDAY;
            default:
                throw new IllegalStateException("Unexpected value: " + dateInput);
        }

    }

    public LocalDate[] getStartEndDate(String inputDate) {
        LocalDate[] localDates = new LocalDate[2];
        LocalDate localDate = LocalDate.now();

        localDates[0] = localDate.with(TemporalAdjusters.next(this.convertDate(inputDate)));
        localDates[1] = localDates[0].with(TemporalAdjusters.next(DayOfWeek.SATURDAY));

        return localDates;
    }

}
