package de.simagdo.prospectcrawler.utils;

public enum Store {

    PENNY("Penny");

    private String store;

    Store(String store) {
        this.store=store;
    }

    public String getStore() {
        return store;
    }
}
