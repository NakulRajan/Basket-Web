package com.shopping.basket.Database;

import com.shopping.basket.Model.Item;

import java.util.HashMap;
import java.util.Map;

/**
 *  Mock class representing a database.
 */
public class Database {
    public static Map<String, Item> items = new HashMap<>();

    public static Map<String, Item> getItems(){
        return items;
    }
}
