package de.simagdo.prospectcrawler.crawler;

import de.simagdo.prospectcrawler.database.DBTools;
import de.simagdo.prospectcrawler.utils.Product;
import de.simagdo.prospectcrawler.utils.Store;
import de.simagdo.prospectcrawler.utils.Utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Crawler {

    private final String link;
    private Store store;
    private final Utils utils = new Utils();
    protected ArrayList<Product> products;
    protected ArrayList<String> dates;
    protected ArrayList<String> categories;
    protected HashMap<Integer, String> categoriesWithID;
    private DBTools dbTools;

    public Crawler(String link, Store store) throws Exception {
        this.link = link;
        this.store = store;
        this.products = new ArrayList<>();
        this.dates = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.categoriesWithID = new HashMap<>();
        this.dbTools = new DBTools();
        this.crawlProducts();
    }

    public String getData() throws Exception {
        return this.utils.getData(this.link);
    }

    public abstract void crawlProducts() throws Exception;

    public void insertDates() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        for (String date : this.dates) {

            LocalDate startDate = localDate.with(TemporalAdjusters.next(utils.convertDate(date)));
            LocalDate endDate = startDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));

            //Insert Values into Database
            this.dbTools.addValidityDate(this.store, startDate, endDate);

            System.out.println("Start Date: " + startDate);
            System.out.println("End Date: " + endDate);
        }
    }

    public void insertCategories() {
        for (String category : this.categories) {
            this.dbTools.addCategory(this.store, category);
        }

    }

    public void insertProducts() {
        String[] images = new String[2];
        for (Product product : this.products) {

            //Download Image
            images = this.utils.downloadImage(product.getImageURL(), product.getProductName());
            product.setImagePath(images[0]);
            product.setRelativeImagePath(images[1]);

            this.dbTools.addProduct(this.store, product);
        }
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<String> getDates() {
        return dates;
    }

    public void setDates(ArrayList<String> dates) {
        this.dates = dates;
    }

    public void addDate(String date) {
        if (!this.dates.contains(date)) this.dates.add(date);
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void addCategory(String category) {
        if (!this.categories.contains(category)) this.categories.add(category);
    }

}
