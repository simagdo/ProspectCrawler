package de.simagdo.prospectcrawler.crawler;

import de.simagdo.prospectcrawler.utils.Store;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ReweCrawler extends Crawler {

    //private static final String LINK = "https://www.rewe.de/angebote/nationale-angebote/";
    public static final String LINK = "https://shop.rewe.de/angebote/";
    private static final long WAIT = 5000;

    public ReweCrawler(String link, Store store) throws Exception {
        super(link, store);
    }

    @Override
    public void crawlProducts() throws Exception {

        System.setProperty("webdriver.chrome.driver", "G:\\ChromeDriver\\chromedriver - Version 87.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(false);
        WebDriver webDriver = new ChromeDriver(chromeOptions);

        String reweDomain = "shop.rewe.de";
        String domain = ".rewe.de";

        ArrayList<Cookie> cookies = new ArrayList<>();
        cookies.add(new Cookie("mtc", "s%3AIHXdBl2TqE1h3GVNcbEBiLNKVy8iMTFmNjEtbHIzeTJzQ0xxTCtjc2lINDVEWTJ6N0czM0U4IiC0CLQHqAmWCJ4JmgieAtIKugjGCqIGiAfYBq4J%2FAaGBAAA.P61r%2FXdaFgeFhJpm9JN4Uxy%2FQ1Vde0QfTpNa7Fp5fOU", reweDomain, "/", new Date()));
        cookies.add(new Cookie("__cfruid", "697425cf2015270b5380183e82965807789e0524-1614108171", domain));
        cookies.add(new Cookie("MRefererUrl", "direct", domain));
        cookies.add(new Cookie("__cf_bm", "c5b31991f8085255c1281b6d4c6100ed58e6059a-1614107317-1800-ATkAxme2DDUHNROtlT72sPXnV9dvywF4QbHyTX1yeKRqTAdWd1t22nXYoFDBbbzIsCpzBelE2dgdyUnH0og/NCM=", domain));
        cookies.add(new Cookie("myReweCookie", "%7B%22serviceType%22%3A%22STATIONARY%22%2C%22stationaryMarketId%22%3A%22201608%22%7D", domain));
        cookies.add(new Cookie("rstp", "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJhZXMxOTIiOiJkOTQwZDQ5NGQzNjY4OGVmNTViODQ5MDRjNjYwMjNkOWYyNGE5NTQ3MmE5MTk1ZDZlZWQ2Njc0YWNlNGZmYWJlNWExNDZhNTRkYzJkNzcwNjI1NDAyMjI4MWQ4ZjVlMzcyZGU1MGJlNTRmYTNkM2FkNThkYTBiZWNmOWM5YjgzYTUwNTRmNjI4NDZjNTE4ZGI2MjcxYzBlYjBkMGIwODc4MzEwNGM1MTA4NzFlMzFkNjBjZThiY2M1Y2QxZDQ5YTk2OWM5Y2VkYTg5MWQwNjgzYzhiMWNjZTI2ZDhlYzdkNTYyYWY4MDY0ZjMyY2UyNWU0ZTYzMjhjNzY1ZGIxMmEwZjVhZjFjMDRkODY5MDA2YTZiYjA2Y2M3M2VmMmJmY2NlMWQxNzhmZDk1MzExNGU4Njg2MzY3ZjkzZTYyZmUwYTMzZmM2YTg1NjJlNzJhZDkwODdjYjlkNDg3NjUzNzY0OTdmOThkNjVlODEyMDA3NWMxMzU2MDAyYWFhNTQ3NzRjMzk1NTc1MDAwODUyZjRjY2Y0NmI4N2VmOTliZjY4YmZkNTUzODA1ZWZlNGE5NzgwYzc1NTkyYmNhMDM2YWM1ZjU1MzE5MjgzNjUxZGMxNDA3Y2ZmMTg1YWM0YzMwMzcwNWIxNTI5MmI3ODExOThhMmQ0ODA5NWJhOTkwMTJiYSIsImlhdCI6MTYxNDEwODE3MywiZXhwIjoxNjE0MTA4NzczfQ.wKJNY56QLA0475RobFSdGYJQ5SCqHifFtxf9xMWRC1QufecF-c1euVovngDRX5_ezm_HqzEqNtAetuPFgrO5Fg", domain));
        cookies.add(new Cookie("marketsCookie", "%7B%22online%22%3A%7B%22serviceTypes%22%3A%5B%22PARCEL%22%5D%2C%22customerZipCode%22%3A%2244267%22%7D%2C%22stationary%22%3A%7B%22wwIdent%22%3A%22201608%22%2C%22marketZipCode%22%3A%2244267%22%2C%22serviceTypes%22%3A%5B%22STATIONARY%22%5D%7D%7D", domain));
        cookies.add(new Cookie("consentSettings", "{%22tms%22:1%2C%22necessaryCookies%22:1%2C%22cmpPlatform%22:1%2C%22marketingBilling%22:1%2C%22fraudProtection%22:1%2C%22advertisingOnsite%22:1%2C%22marketingOnsite%22:1%2C%22basicAnalytics%22:1%2C%22sessionMonitoring%22:0%2C%22serviceMonitoring%22:1%2C%22abTesting%22:1%2C%22conversionOptimization%22:1%2C%22feederAnalytics%22:1%2C%22extendedAnalytics%22:0%2C%22personalAdsOnsite%22:0%2C%22remarketingOffsite%22:0%2C%22targetGroup%22:0%2C%22userProfiling%22:0%2C%22status%22:0}", domain));
        cookies.add(new Cookie("__cfduid", "d21dc3052f9c35a10eb508c34e1e3dbb61613587842", domain));
        cookies.add(new Cookie("AMCV_65BE20B35350E8DE0A490D45%40AdobeOrg", "359503849%7CMCMID%7C76365100794798648872090067045428562828%7CMCAID%7CNONE%7CvVersion%7C5.0.1", domain));
        cookies.add(new Cookie("_rdfa", "s%3A565ffaf2-c635-4f19-a3fc-a74aa5dd26f6.IvpO%2FMV7kOFEI6QPcBeMf1d3OzKcODPrTf9dLnyDxTc", domain));

        //chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Windows NT 4.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2049.0 Safari/537.36\n");

        webDriver.get(LINK);

        //webDriver.manage().getCookies().forEach(cookie -> System.out.println(cookie.toString()));

        webDriver.manage().getCookies().forEach(cookie -> {
            cookies.forEach(existingCookie -> {
                if (existingCookie.getName().equals(cookie.getName())) {
                    webDriver.manage().deleteCookie(cookie);
                    webDriver.manage().addCookie(new Cookie.Builder(existingCookie.getName(), existingCookie.getValue()).domain(existingCookie.getDomain()).expiresOn(existingCookie.getExpiry()).path(existingCookie.getPath()).isSecure(existingCookie.isSecure()).build());
                }
            });

        });

        //cookies.forEach(cookie -> webDriver.manage().addCookie(cookie));

        //webDriver.manage().getCookies().clear();

        //webDriver.navigate().refresh();

        System.out.println("");

        webDriver.manage().getCookies().forEach(cookie -> System.out.println(cookie.toString()));

        Thread.sleep(WAIT);

        WebElement settings = webDriver.findElement(By.className("uc-btn-primary"));
        settings.click();

        WebDriverWait webDriverWait = new WebDriverWait(webDriver, WAIT);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className("gbmc-zipcode-input")));

        WebElement zipCode = webDriver.findElement(By.className("gbmc-zipcode-input"));
        zipCode.sendKeys("44267");

        //Thread.sleep(WAIT);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className("gbmc-qa-parcel-trigger")));

        WebElement paketService = webDriver.findElement(By.className("gbmc-qa-parcel-trigger"));
        paketService.click();

        WebElement checkbox = webDriver.findElement(By.id("checkbox"));
        checkbox.click();

        Document document = Jsoup.parse(webDriver.getPageSource());

        Elements tiles = document.getElementsByClass("search-service-productDetailsWrapper");

        for (Element tile : tiles) {
            System.out.println(tile.toString());
            /*String imageURL = "";
            String productName = "";
            double price = 0.0;
            String priceDrop = "";

            if (tile.childNodes().size() == 3) {
                imageURL = tile.child(0).child(0).attr("data-src");
                productName = tile.child(1).child(0).child(1).text();
                price = Double.parseDouble(tile.child(1).child(1).child(0).child(0).text());
                priceDrop = tile.child(1).child(1).child(1).child(1).child(1).text();
            } else {
                imageURL = tile.child(1).child(0).attr("data-src");
                productName = tile.child(2).child(0).child(1).text();
                price = Double.parseDouble(tile.child(2).child(1).child(0).child(0).text());
                priceDrop = tile.child(2).child(1).child(1).child(1).child(1).text();
            }

            System.out.println("Image: " + imageURL + ", Product Name: " + productName + ", Price: " + price + ", Drop: " + priceDrop);*/
        }

    }
}
