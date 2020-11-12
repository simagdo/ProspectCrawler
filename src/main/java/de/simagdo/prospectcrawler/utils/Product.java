package de.simagdo.prospectcrawler.utils;

import java.io.File;
import java.time.LocalDate;

public class Product {

    private String productName;
    private String linkToProduct;
    private double oldPrice;
    private double newPrice;
    private String priceDrop;
    private String amount;
    private String perAmount;
    private String unit;
    private String date;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String category;
    private String imageURL;
    private String imagePath;
    private String relativeImagePath;
    private File image;

    public Product(String productName, String linkToProduct, double oldPrice, double newPrice, String priceDrop, String amount, String perAmount, String unit, String date, LocalDate fromDate, LocalDate toDate, String category, String imageURL) {
        this(productName, linkToProduct, oldPrice, newPrice, priceDrop, amount, perAmount, unit, date, fromDate, toDate, category, imageURL, "", "",null);
    }

    public Product(String productName, String linkToProduct, double oldPrice, double newPrice, String priceDrop, String amount, String perAmount, String unit, String date, LocalDate fromDate, LocalDate toDate, String category, String imageURL, String imagePath, String relativeImagePath, File image) {
        this.productName = productName;
        this.linkToProduct = linkToProduct;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.priceDrop = priceDrop;
        this.amount = amount;
        this.perAmount = perAmount;
        this.unit = unit;
        this.date = date;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.category = category;
        this.imageURL = imageURL;
        this.imagePath = imagePath;
        this.relativeImagePath=relativeImagePath;
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLinkToProduct() {
        return linkToProduct;
    }

    public void setLinkToProduct(String linkToProduct) {
        this.linkToProduct = linkToProduct;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public String getPriceDrop() {
        return priceDrop;
    }

    public void setPriceDrop(String priceDrop) {
        this.priceDrop = priceDrop;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPerAmount() {
        return perAmount;
    }

    public void setPerAmount(String perAmount) {
        this.perAmount = perAmount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getRelativeImagePath() {
        return relativeImagePath;
    }

    public void setRelativeImagePath(String relativeImagePath) {
        this.relativeImagePath = relativeImagePath;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", linkToProduct='" + linkToProduct + '\'' +
                ", oldPrice=" + oldPrice +
                ", newPrice=" + newPrice +
                ", priceDrop='" + priceDrop + '\'' +
                ", amount='" + amount + '\'' +
                ", perAmount='" + perAmount + '\'' +
                ", unit='" + unit + '\'' +
                ", date='" + date + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", category='" + category + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", relativeImagePath='" + relativeImagePath + '\'' +
                ", image=" + image +
                '}';
    }
}
