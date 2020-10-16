package de.simagdo.prospectcrawler.crawler;

import de.simagdo.prospectcrawler.utils.Product;
import de.simagdo.prospectcrawler.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Crawler {

    private String link;
    private Utils utils = new Utils();

    public Crawler(String link) {
        this.link = link;
    }

    public String getData() throws Exception {
        //return this.utils.readAllLines(this.link);
        return this.utils.getData(this.link);
    }

    public abstract ArrayList<Product> getProducts() throws IOException, Exception;

}
