package de.simagdo.prospectcrawler.crawler;

import de.simagdo.prospectcrawler.utils.CategoryDate;
import de.simagdo.prospectcrawler.utils.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PennyCrawler extends Crawler {

    public static final String LINK = "https://www.penny.de/angebote/15A-10-32";

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

        Elements sections = document.getElementsByTag("section");
        ArrayList<CategoryDate> categoryDates = new ArrayList<>();
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

                for (Node content : section.childNodes()) {
                    if (content instanceof Element && ((Element) content).className().contains("category-list")) {
                        Element child = ((Element) content).child(0);

                        for (Node articles : child.childNodes()) {
                            if (articles instanceof Element) {
                                Element article = ((Element) articles).child(0);

                                Element price = article.child(1);
                                Element offer = article.child(3);
                                Element drop = offer.child(0);
                                Element productTest = offer.child(1);
                                Element amnt = offer.child(2);

                                System.out.println("Price: " + price.text() + ", Drop: " + drop.text() + ", Product: " + productTest.text() + ", Amount: " + amnt.text());

                                String[] ignore = {"Aktion", "Rezept", "App", "*"};
                                if (!Arrays.asList(ignore).contains(article.text().split(" ")[0])) {

                                    //if (!(article.text().contains("Rezept") || article.text().contains("App"))) {

                                    System.out.println(article.text());
                                    String[] prices = article.text().split(" ");
                                    String productValue = article.text();
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

                                    product = new Product(productName, "", oldPrice, newPrice, priceDrop, amount, perAmount, date, category);
                                    System.out.println(product.toString());
                                /*for (Node x : article.childNodes()) {
                                    if (x instanceof Element) {
                                        if (((Element) x).className().contains("bubble bubble__price")) {
                                            Element y = (Element) x;
                                            if (y.className().contains("value ellipsis")) {
                                                System.out.println("Old Price: " + y.select("value ellipsis").get(0).text());
                                            }
                                        }

                                    }
                                }*/
                                }
                            }
                        }

                    }
                }

                category = "";
            }
        }

        List<Node> childNodes = sections.get(2).childNodes();

        for (Node node : childNodes) {
            if (node instanceof TextNode) {
                System.out.println("TextNode");
            } else if (node instanceof Element) {
                System.out.println("Element");
            }
        }

        /*offers.forEach(offer -> {
            Elements children = offer.children();

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
        });*/

        return products;
    }

}