package de.simagdo.prospectcrawler.utils;

import java.time.LocalDate;

public class Product {

    private String productName;
    private String linkToProduct;
    private double oldPrice;
    private double newPrice;
    private String priceDrop;
    private String amount;
    private String perAmount;
    private String date;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String category;

    public Product(String productName, String linkToProduct, double oldPrice, double newPrice, String priceDrop, String amount, String perAmount, String date, LocalDate fromDate, LocalDate toDate, String category) {
        this.productName = productName;
        this.linkToProduct = linkToProduct;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.priceDrop = priceDrop;
        this.amount = amount;
        this.perAmount = perAmount;
        this.date = date;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.category = category;
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
                ", date='" + date + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", category='" + category + '\'' +
                '}';
    }
}
