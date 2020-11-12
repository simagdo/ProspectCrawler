package de.simagdo.prospectcrawler.system;

import de.simagdo.prospectcrawler.crawler.PennyCrawler;

public class ProspectCrawler {

    public static void main(String[] args) throws Exception {
        PennyCrawler pennyCrawler = new PennyCrawler(PennyCrawler.LINK);
        pennyCrawler.getProducts().forEach(product -> System.out.println(product.toString()));
        pennyCrawler.insertDates();
        //pennyCrawler.insertCategories();
        pennyCrawler.insertProducts();
    }

}
