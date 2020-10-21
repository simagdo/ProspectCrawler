package de.simagdo.prospectcrawler.utils;

public class CategoryDate {

    private String category;
    private String date;
    private int amount;

    public CategoryDate(String category, String date, int amount) {
        this.category = category;
        this.date = date;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CategoryDate{" +
                "category='" + category + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }
}
