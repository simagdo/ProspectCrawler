package de.simagdo.prospectcrawler.utils;

public class Product {

    private String productName;
    private String linkToProduct;
    private String price;
    private String pricePerAmount;

    public Product(String productName, String linkToProduct, String price, String pricePerAmount) {
        this.productName = productName;
        this.linkToProduct = linkToProduct;
        this.price = price;
        this.pricePerAmount = pricePerAmount;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPricePerAmount() {
        return pricePerAmount;
    }

    public void setPricePerAmount(String pricePerAmount) {
        this.pricePerAmount = pricePerAmount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", linkToProduct='" + linkToProduct + '\'' +
                ", price='" + price + '\'' +
                ", pricePerAmount='" + pricePerAmount + '\'' +
                '}';
    }
}
