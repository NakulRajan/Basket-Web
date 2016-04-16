package com.shopping.basket.Service;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;
import com.googlecode.objectify.cmd.Query;
import com.shopping.basket.Model.ItemModel;

import java.util.List;
import java.util.Random;

import static com.shopping.basket.OfyService.ofy;

public class ListItemService {
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
    public List<ItemModel> getList(){
        Query<ItemModel> query = ofy().load().type(ItemModel.class);
        List<ItemModel> itemModels;
        itemModels = query.list();
        return itemModels;
    }

    /**
     * Returns a single item in the list.
     */
    public ItemModel getItem(String itemId){
        Result<ItemModel> result = ofy().load().key(Key.create(ItemModel.class, itemId));
        return result.now();
    }

    /**
     * Adds a new item to the list.
     */
    public ItemModel addItem(ItemModel itemModel){
        itemModel.setItemId(generateUniqueId());
        ofy().save().entity(itemModel).now();
        return itemModel;
    }

    /**
     * Update item in the list.
     */
    public ItemModel updateItem(ItemModel itemModel){
        ItemModel tempItemModel = ofy().load().key(Key.create(ItemModel.class, itemModel.getItemId())).now();
        if(tempItemModel == null)
            return null;
        ofy().save().entity(itemModel).now();
        return itemModel;
    }

    /**
     * Deletes a item
     */
    public void deleteItem(String itemId){
        ofy().delete().key(Key.create(ItemModel.class, itemId)).now();
    }
}
