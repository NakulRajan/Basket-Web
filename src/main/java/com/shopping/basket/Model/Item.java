package com.shopping.basket.Model;

/**
 * Represents a single item in the list.
 */
public class Item {
    private String itemId;
    private String itemName;

    //empty constructor needed
    public Item(){

    }

    public Item(String itemId, String itemName){
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
