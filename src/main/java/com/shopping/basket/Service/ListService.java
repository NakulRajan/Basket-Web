package com.shopping.basket.Service;

import com.shopping.basket.Database.Database;
import com.shopping.basket.Model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ListService {
    Map<String, Item> itemsMap = Database.getItems();

    public ListService(){
        String[] itemNames = {"Cereals", "Onion", "Tomato", "Carrot"};
        String[] uniqueId = {"ASERF", "WDFGT", "AREDS", "LJKUR"};
        //creating some placeholder data
        for(int i=0; i < 4; i++){
            Item item = new Item(uniqueId[i], itemNames[i]);
            itemsMap.put(uniqueId[i], item);
        }
    }

    /**
     * Utility function to generate unique ids
     */
    public String generateUniqueId(){
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        StringBuilder str = new StringBuilder();

        for(int i=0; i<5; i++){
            int index = rand.nextInt(25);
            Character ch = alphabets.charAt(index);
            str.append(ch);
        }

        return str.toString();
    }

    /**
     * Returns all the items in the list.
     */
    public List<Item> getList(){
        List<Item> items = new ArrayList<>();
        for(String itemId: itemsMap.keySet()){
            items.add(itemsMap.get(itemId));
        }
        return items;
    }

    /**
     * Returns a single item in the list.
     */
    public Item getItem(String itemId){
        return itemsMap.get(itemId);
    }

    /**
     * Adds a new item to the list.
     */
    public Item addItem(Item item){
        item.setItemId(generateUniqueId());
        itemsMap.put(item.getItemId(), item);
        return item;
    }

    /**
     * Update item in the list.
     */
    public Item updateItem(Item item){
        if(item.getItemId() == null){
            return null;
        }
        itemsMap.put(item.getItemId(), item);
        return item;
    }

    /**
     * Deletes a item
     */
    public void deleteItem(String itemId){
        itemsMap.remove(itemId);
    }
}
