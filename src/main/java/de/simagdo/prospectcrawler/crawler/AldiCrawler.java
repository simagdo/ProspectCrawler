package de.simagdo.prospectcrawler.crawler;

import de.simagdo.prospectcrawler.utils.Store;
import de.simagdo.prospectcrawler.utils.Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AldiCrawler extends Crawler {

    private static final String LINK = "https://www.aldi-nord.de/aldi-liefert/";

    public AldiCrawler() throws Exception {
        super(LINK, Store.ALDI);
    }

    @Override
    public void crawlProducts() throws Exception {
        String data = this.getData();
        Utils utils = new Utils();

        Document document = Jsoup.parse(data);

        Elements tiles = document.getElementsByClass("product-card");

        System.out.println(tiles.size());

        for (Element tile : tiles) {
            Elements select = tile.select("img-responsive");

            for (Element current : select) {
                System.out.println(current.toString());
            }

            //System.out.println(tile.toString());
            //String imageURL = tile.child(0).child(0).child(0).attr("src");
            //System.out.println(imageURL);
        }

    }
}
