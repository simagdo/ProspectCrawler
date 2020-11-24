package de.simagdo.prospectcrawler.crawler;

import de.simagdo.prospectcrawler.utils.Store;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

public class ReweCrawler extends Crawler {

    private static final String LINK = "https://www.rewe.de/angebote/nationale-angebote/";
    private static final long WAIT = 5000;

    public ReweCrawler() throws Exception {
        super(LINK, Store.REWE);
    }

    @Override
    public void crawlProducts() throws Exception {

        System.setProperty("webdriver.chrome.driver", "G:\\ChromeDriver\\chromedriver - Version 87.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(false);
        WebDriver webDriver = new ChromeDriver(chromeOptions);

        webDriver.get(LINK);

        Thread.sleep(5000);

        //Arrays.stream(webDriver.getPageSource().split("\n")).forEach(System.out::println);

        Document document = Jsoup.parse(webDriver.getPageSource());

        Elements tiles = document.getElementsByClass("card ma-item-card h-100");

        for (Element tile : tiles) {
            //System.out.println(tile.toString());
            String imageURL = "";
            String productName = "";
            double price = 0.0;
            String priceDrop = "";

            if (tile.childNodes().size() == 3) {
                imageURL = tile.child(0).child(0).attr("data-src");
                productName = tile.child(1).child(0).child(1).text();
                price = Double.parseDouble(tile.child(1).child(1).child(0).child(0).text());
                priceDrop = tile.child(1).child(1).child(1).child(1).child(1).text();
            } else {
                imageURL = tile.child(1).child(0).attr("data-src");
                productName = tile.child(2).child(0).child(1).text();
                price = Double.parseDouble(tile.child(2).child(1).child(0).child(0).text());
                priceDrop = tile.child(2).child(1).child(1).child(1).child(1).text();
            }

            System.out.println("Image: " + imageURL + ", Product Name: " + productName + ", Price: " + price + ", Drop: " + priceDrop);
            if (productName.contains("Spargel"))
                System.out.println(true);
        }

    }
}
