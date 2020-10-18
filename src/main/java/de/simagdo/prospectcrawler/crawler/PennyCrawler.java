package de.simagdo.prospectcrawler.crawler;

import de.simagdo.prospectcrawler.utils.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
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

        //Document document = Jsoup.parse(new File(this.getClass().getClassLoader().getResource("penny.html").toURI()), "UTF-8");
        Document document = Jsoup.parse(data);
        Elements offers = document.getElementsByClass("offer-tile");
        offers.forEach(offer -> {
            Elements children = offer.children();

/*

5.99 3.49

-41% JACOBS Krönung* je 500-g-Packung (1 kg = 6.98)

 */

            if (children.size() > 3) {
                String[] prices = children.get(1).text().split(" ");
                String productValue = children.get(3).text();
                double oldPrice = 0.0;
                double newPrice = 0.0;
                String priceDrop = "";
                String productName = "";
                String amount = "";
                double perAmount = 0;
                Product product;

                //Contains old and new Price
                if (!(prices[0].contains("%*") || prices[0].contains("ab") || prices[0].contains("%")) && !prices[0].contains("UVP")) {
                    newPrice = Double.parseDouble(prices[0].contains(".–") ? prices[0].replace(".–", "0") : prices[0]);
                } else if (prices.length == 2 && prices[0].contains("ab")) {
                    newPrice = Double.parseDouble(prices[1].contains(".–") ? prices[1].replace(".–", "0") : prices[1]);
                } else if (prices.length == 2) {
                    oldPrice = Double.parseDouble(prices[0].contains(".–") ? prices[0].replace(".–", "0") : prices[0]);
                    newPrice = Double.parseDouble(prices[1].contains(".–") ? prices[1].replace(".–", "0") : prices[1]);
                    //Contains UVP
                } else if (prices.length == 3) {
                    oldPrice = Double.parseDouble(prices[1].contains(".–") ? prices[1].replace(".–", "0") : prices[1]);
                    newPrice = Double.parseDouble(prices[2].contains(".–") ? prices[2].replace(".–", "0") : prices[2]);
                }

                if (productValue.contains("%")) {
                    priceDrop = productValue.substring(0, productValue.indexOf(" "));
                    productValue = productValue.substring(priceDrop.length()).replaceFirst(" ", "");
                } else if (productValue.contains("Aktion")) {
                    productValue = productValue.replace("Aktion", "");
                }

                if (productValue.contains("je")) {
                    productName = productValue.substring(0, productValue.indexOf("je")).trim().replace("*", "");
                    productValue = productValue.substring(productValue.indexOf("je"));
                }

                if (productValue.contains("(")) {
                    amount = productValue.substring(0, productValue.indexOf("(") - 1);
                    productValue = productValue.substring(productValue.indexOf("("));
                }

                if (productValue.contains("(") || productValue.contains("=")) {
                    productValue = productValue.substring(productValue.indexOf("=") + 1).replace(")", "");
                    perAmount = Double.parseDouble(productValue);
                }

                product = new Product(productName, "", oldPrice, newPrice, priceDrop, amount, perAmount);
                System.out.println(product.toString());
            }
        });

        return products;
    }

}