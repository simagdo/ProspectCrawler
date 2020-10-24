package de.simagdo.prospectcrawler.crawler;

import de.simagdo.prospectcrawler.utils.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class PennyCrawler extends Crawler {

    public static final String LINK = "https://www.penny.de/angebote/15A-10-32";
    public static final String PENNY = "https://www.penny.de/";

    public PennyCrawler(String link) {
        super(link);
    }

    @Override
    public ArrayList<Product> getProducts() throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        String data = this.getData();

        Document document = Jsoup.parse(data);

        Elements sections = document.getElementsByTag("section");
        String date = "";
        String category = "";

        for (Element section : sections) {
            String id = section.id();
            if (id.contains("angebotszeitraum") && id.contains("angebotskategorie")) {

                id = id.replace("-", " ");
                String[] split = id.split(" ");
                date = split[2].substring(0, 1).toUpperCase() + split[2].substring(1);

                for (int i = 4; i < split.length; i++) {
                    category += split[i].substring(0, 1).toUpperCase() + split[i].substring(1) + " ";
                }

                System.out.println("ID: " + id + ", Date: " + date + ", Category: " + category);

                Elements links = document.select("a.tile__link--cover");
                int index = 0;

                for (Node content : section.childNodes()) {
                    if (content instanceof Element && ((Element) content).className().contains("category-list")) {
                        Element child = ((Element) content).child(0);

                        for (Node articles : child.childNodes()) {
                            if (articles instanceof Element) {
                                Element article = ((Element) articles).child(0);

                                if (article.childNodes().size() == 13) {
                                    Element priceElement = article.child(1);
                                    Element offerElement = article.child(3);
                                    Element dropElement = offerElement.child(0);
                                    Element productNameElement = offerElement.child(1);
                                    Element amountElement = offerElement.child(2);

                                    String[] prices = priceElement.text().split(" ");
                                    double oldPrice = 0.0;
                                    double newPrice = 0.0;
                                    String priceDrop = "";
                                    String productName = productNameElement.text().replace("*", "");
                                    String amount = amountElement.text();
                                    String perAmount = "";

                                    //Replace special Characters within the Prices Array
                                    for (int i = 0; i < prices.length; i++) {
                                        prices[i] = prices[i].replace("%", "").replace("*", "").replace(".–", ".00");
                                    }

                                    //Get the Old and new Price
                                    if (prices.length == 1)
                                        newPrice = Double.parseDouble(prices[0]);
                                    else if (prices.length == 2) {
                                        if (!prices[0].contains("ab")) {
                                            oldPrice = Double.parseDouble(prices[0]);
                                        }
                                        newPrice = Double.parseDouble(prices[1]);
                                    } else {
                                        oldPrice = Double.parseDouble(prices[1]);
                                        newPrice = Double.parseDouble(prices[2]);
                                    }

                                    //Get the Price Drop
                                    if (dropElement.text().contains("%"))
                                        priceDrop = dropElement.text().substring(0, dropElement.text().indexOf("%") + 1);

                                    //Get the Amount
                                    if (amount.contains("(")) {
                                        amount = amount.substring(0, amount.indexOf("(") - 1);
                                    }

                                    //Get the Per Amount
                                    if (amount.contains("je")) {
                                        perAmount = amount.substring(amount.indexOf("=") + 1).replace(")", "");
                                    }

                                    //System.out.println("Old Price: " + oldPrice + ", New Price: " + newPrice + ", Drop: " + priceDrop + ", Product: " + productName + ", Amount: " + amount + ", Per Amount: " + perAmount);
                                    products.add(new Product(productName, PENNY + links.get(index).attr("href"), oldPrice, newPrice, priceDrop, amount, perAmount, date, category));
                                    index++;
                                }
                            }
                        }

                    }
                }

                category = "";
            }
        }

        return products;
    }

}