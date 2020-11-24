package de.simagdo.prospectcrawler.utils;

public enum Store {

    PENNY("Penny"),
    REWE("Rewe"),
    ALDI("Aldi");

    private String store;

    Store(String store) {
        this.store=store;
    }

    public String getStore() {
        return store;
    }
}
