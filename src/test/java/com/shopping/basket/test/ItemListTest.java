package com.shopping.basket.test;

import com.shopping.basket.Model.Item;
import com.shopping.basket.Service.ListService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for items list.
 */
public class ItemListTest {

    private ListService listService = new ListService();

    @Test
    public void createListTest(){
        List<Item> itemList = this.listService.getList();
        assertEquals(4, itemList.size());
    }

    @Test
    public void getItemTest(){
        Item item = this.listService.getItem("WDFGT");
        assertEquals("WDFGT", item.getItemId());
    }

    @Test
    public void addItemTest(){
        int originalLength = this.listService.getList().size();
        this.listService.addItem(new Item("WDFRD", "Cookie"));
        assertEquals(originalLength + 1, this.listService.getList().size());
    }

    @Test
    public void updateItemTest(){
        Item item = new Item("WDFGT", "Ice Cream");
        this.listService.updateItem(item);
        assertEquals(item.getItemId(), this.listService.getItem(item.getItemId()).getItemId());
    }

    @Test
    public void deleteItemTest(){
        int originalLength = this.listService.getList().size();
        this.listService.deleteItem("WDFGT");
        assertEquals(originalLength-1, this.listService.getList().size());
    }
}
