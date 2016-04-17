package com.shopping.basket.Service;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;
import com.googlecode.objectify.cmd.Query;
import com.shopping.basket.Exceptions.DataNotFound;
import com.shopping.basket.Model.ItemModel;
import com.shopping.basket.Model.ListModel;

import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Random;

import static com.shopping.basket.OfyService.ofy;

public class ListItemService {

    /**
     * Returns all the items in the list.
     */
    public List<ItemModel> getAllItems(Long listId){
        Key<ListModel> parentList = getParentKey(listId);
        Query<ItemModel> query = ofy().load().type(ItemModel.class).ancestor(parentList);
        List<ItemModel> itemModels;
        itemModels = query.list();
        return itemModels;
    }

    /**
     * Returns a single item in the list.
     */
    public ItemModel getItem(Long listId, Long itemId){
        Key<ListModel> parentKey = getParentKey(listId);
        Result<ItemModel> result = ofy().load().key(Key.create(parentKey, ItemModel.class, itemId));
        return result.now();
    }

    /**
     * Adds a new item to the list.
     */
    public ItemModel addItem(Long listId, ItemModel itemModel){
        Key<ListModel> parentKey = getParentKey(listId);
        itemModel.setParentListId(listId);
        itemModel.setParentListKey(parentKey);
        ofy().save().entity(itemModel).now();
        return itemModel;
    }

    /**
     * Update item in the list.
     */
    public ItemModel updateItem(Long listId, ItemModel itemModel){
        Key<ListModel> parentKey = getParentKey(listId);

        ItemModel tempItemModel = ofy().load().key(Key.create(parentKey, ItemModel.class, itemModel.getItemId())).now();
        if(tempItemModel == null){
            throw  new DataNotFound("Invalid item key");
        }
        tempItemModel.update(itemModel);
        ofy().save().entity(tempItemModel).now();
        return tempItemModel;
    }

    /**
     * Deletes a item
     */
    public void deleteItem(Long listId, Long itemId){
        Key<ListModel> parentKey = getParentKey(listId);
        ofy().delete().key(Key.create(parentKey, ItemModel.class, itemId)).now();
    }

    private Key<ListModel> getParentKey(Long listId){
       return Key.create(ListModel.class, listId);
    }
}
