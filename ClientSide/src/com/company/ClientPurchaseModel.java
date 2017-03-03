package com.company;

/**
 * Created by Aleson on 1/26/2017.
 */
public class ClientPurchaseModel {

    String category;
    Purchase item;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Purchase getItem() {
        return item;
    }

    public void setItem(Purchase item) {
        this.item = item;
    }
}
