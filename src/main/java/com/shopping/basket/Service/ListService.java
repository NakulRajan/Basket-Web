package com.shopping.basket.Service;

import com.shopping.basket.Database.Database;
import com.shopping.basket.Model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ListService {
    Map<String, Item> map = Database.getItems();

    public ListService(){
        String[] itemNames = {"Cereals", "Onion", "Tomato", "Carrot"};
        String[] uniqueId = {"ASERF", "WDFGT", "AREDS", "LJKUR"};
        //creating some placeholder data
        for(int i=0; i < 4; i++){
            Item item = new Item(uniqueId[i], itemNames[i]);
            map.put(uniqueId[i], item);
        }

    }

    /**
     * Utility function to generate unique ids
     */
    public String generateUniqueId(){
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        StringBuilder str = new StringBuilder();

        for(int i=0; i<10; i++){
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
        for(String itemId: map.keySet()){
            items.add(map.get(itemId));
        }
        return items;
    }


}
