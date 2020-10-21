package de.simagdo.prospectcrawler.utils;

public class Product {

    private String productName;
    private String linkToProduct;
    private double oldPrice;
    private double newPrice;
    private String priceDrop;
    private String amount;
    private double pricePerAmount;
    private String date;
    private String category;

    public Product(String productName, String linkToProduct, double oldPrice, double newPrice, String priceDrop, String amount, double pricePerAmount, String date, String category) {
        this.productName = productName;
        this.linkToProduct = linkToProduct;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.priceDrop=priceDrop;
        this.amount = amount;
        this.pricePerAmount = pricePerAmount;
        this.date=date;
        this.category=category;
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

    public double getPricePerAmount() {
        return pricePerAmount;
    }

    public void setPricePerAmount(double pricePerAmount) {
        this.pricePerAmount = pricePerAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
                ", pricePerAmount=" + pricePerAmount +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
