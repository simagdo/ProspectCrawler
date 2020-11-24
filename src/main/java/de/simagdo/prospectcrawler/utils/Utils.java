package de.simagdo.prospectcrawler.utils;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Utils {

    //private static final String IMAGE_LOCATION = "C:\\Users\\simag\\IdeaProjects\\ProspectCrawler\\ProspectCrawler\\src\\main\\resources\\Product Images";
    private static final String IMAGE_LOCATION = "C:\\Users\\simag\\IdeaProjects\\ProspectCrawler\\prospectcrawlerfrontend\\public\\Images\\Product Images";
    private static final String RELATIVE_IMAGE_PATH = "/Images/Product Images";

    /**
     * @return the Source code from the given Website
     * @throws IOException if there is an Error
     */
    public String getData(String link) throws IOException {
        StringBuilder data = new StringBuilder();

        //init(); //Load init

        //URL url = new URL(LINK);
        URL url = new URL(link);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

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

    public String[] downloadImage(String input, String productName) {
        String[] filePath = new String[2];
        BufferedImage image;
        try {
            URL url = new URL(input);

            //Read the URL
            image = ImageIO.read(url);

            //For PNG
            if (input.contains("png")) {
                filePath[0] = IMAGE_LOCATION + "\\" + productName + ".png";
                filePath[1] = RELATIVE_IMAGE_PATH + "/" + productName + ".png";
                ImageIO.write(image, "png", new File(filePath[0]));
            } else if (input.contains("jpg")) {
                filePath[0] = IMAGE_LOCATION + "\\" + productName + ".jpg";
                filePath[1] = RELATIVE_IMAGE_PATH + "/" + productName + ".jpg";
                ImageIO.write(image, "jpg", new File(filePath[0]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public InputStream getImageAsBlob(String input) {

        try {
            return new URL(input).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
