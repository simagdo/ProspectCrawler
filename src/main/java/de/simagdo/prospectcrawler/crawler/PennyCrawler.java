package de.simagdo.prospectcrawler.crawler;

import de.simagdo.prospectcrawler.utils.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PennyCrawler extends Crawler {

    public static final String LINK = "https://www.penny.de/angebote/15A-10-32";
    //public static final String LINK = "penny.html";

    public PennyCrawler(String link) {
        super(link);
    }

    @Override
    public ArrayList<Product> getProducts() throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        String data = this.getData();

        /*for (String line : data.split("\n")) {
            //System.out.println(line);
            double oldPrice = 0.0;
            double newPrice = 0.0;
            String productName = "";

            if(line.contains("ellipsis")) {
                System.out.println(line);
            }
            if (line.contains("<article")) {
                System.out.println(true);
                System.out.println(line);
            }
        }*/

        //Document document = Jsoup.parse(new File(this.getClass().getClassLoader().getResource(LINK).toURI()), "UTF-8");
        Document document = Jsoup.parse(data);
        Elements price = document.getElementsByClass("offer-tile");
        price.forEach(element -> System.out.println(element.text()));
        //Elements prices = document.getElementsByClass("offer-tile");
        Elements prices = document.getElementsByTag("article");
        /*for (Element element : prices) {
            System.out.println(element.text());
            String[] content = element.text().split(" ");
            for (String current : content) {

            }
        }*/

        /*Elements images = document.getElementsByClass("offer-tile__image");

        for (int i = 0; i < price.size(); i++) {
            System.out.println(price.get(i).text() + " " + images.get(i).html());
        }

        Elements imagesTest = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        for(Element image : imagesTest) {
            System.out.println("Src: " + image.attr("src"));
        }

        System.out.println("Size Offers: " + price.size() + ", Size Images: " + images.size());*/

        /*Elements links = document.getElementsByClass("tile__link--cover");
        for (Element element : links) {
            System.out.println(element.html());
        }*/

        return products;
    }

}