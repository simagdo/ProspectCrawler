package de.simagdo.prospectcrawler.system;

import de.simagdo.prospectcrawler.crawler.PennyCrawler;

import java.io.IOException;

public class ProspectCrawler {

    public static void main(String[] args) throws Exception {
        PennyCrawler pennyCrawler = new PennyCrawler(PennyCrawler.LINK);
        pennyCrawler.getProducts();
    }

}
