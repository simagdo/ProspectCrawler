package de.simagdo.prospectcrawler.system;

import de.simagdo.prospectcrawler.crawler.PennyCrawler;
import de.simagdo.prospectcrawler.crawler.ReweCrawler;
import de.simagdo.prospectcrawler.database.DBTools;
import de.simagdo.prospectcrawler.utils.Store;

public class ProspectCrawler {

    public static void main(String[] args) throws Exception {
        /*PennyCrawler pennyCrawler = new PennyCrawler(PennyCrawler.LINK);
        pennyCrawler.crawlProducts();
        pennyCrawler.getProducts().forEach(product -> System.out.println(product.toString()));
        //pennyCrawler.insertDates();
        //pennyCrawler.insertCategories();
        pennyCrawler.insertProducts();*/
        ReweCrawler reweCrawler = new ReweCrawler(ReweCrawler.LINK, Store.REWE);
        reweCrawler.crawlProducts();
    }

}
