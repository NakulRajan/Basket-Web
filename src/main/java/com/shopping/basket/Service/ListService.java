package com.shopping.basket.Service;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;
import com.googlecode.objectify.cmd.Query;
import com.shopping.basket.Model.Item;

import java.util.List;
import java.util.Random;

import static com.shopping.basket.OfyService.ofy;

public class ListService {
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
        Query<Item> query = ofy().load().type(Item.class);
        List<Item> items;
        items = query.list();
        return items;
    }

    /**
     * Returns a single item in the list.
     */
    public Item getItem(String itemId){
        Result<Item> result = ofy().load().key(Key.create(Item.class, itemId));
        return result.now();
    }

    /**
     * Adds a new item to the list.
     */
    public Item addItem(Item item){
        item.setItemId(generateUniqueId());
        ofy().save().entity(item).now();
        return item;
    }

    /**
     * Update item in the list.
     */
    public Item updateItem(Item item){
        Item tempItem = ofy().load().key(Key.create(Item.class,item.getItemId())).now();
        if(tempItem == null)
            return null;
        ofy().save().entity(item).now();
        return item;
    }

    /**
     * Deletes a item
     */
    public void deleteItem(String itemId){
        ofy().delete().key(Key.create(Item.class, itemId)).now();
    }
}
