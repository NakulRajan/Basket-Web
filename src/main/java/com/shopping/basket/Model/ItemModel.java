package com.shopping.basket.Model;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Represents a single item in the list.
 */
@XmlRootElement
@Entity
public class ItemModel {

    @Id private Long itemId;
    private String itemName;
    @Parent private Key<ListModel> parentListKey;
    private Long parentListId;

    //empty constructor needed
    public ItemModel(){

    }

    public ItemModel(Long listId, Long itemId, String itemName){
        this.itemId = itemId;
        this.itemName = itemName;
        this.parentListKey = Key.create(ListModel.class, listId);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @XmlTransient
    public String getParentListKey() {
        return Key.create(parentListKey, ItemModel.class, this.itemId).toString();
    }

    public void setParentListKey(Key<ListModel> key){
        this.parentListKey = key;
    }

    public Long getParentListId() {
        return parentListId;
    }

    public void setParentListId(Long parentListId) {
        this.parentListId = parentListId;
    }

    public void update(ItemModel itemModel){
        this.setItemName(itemModel.getItemName());
    }
}
