package com.shopping.basket.test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import com.shopping.basket.Model.Item;
import com.shopping.basket.Service.ListService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for items list.
 */
public class ItemListTest {

    private ListService listService = new ListService();
    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private Closeable closeable;

    private List<String> initialItemKeys;

    public ItemListTest(){
        ObjectifyService.register(Item.class);
    }

    public void initialData(){
        initialItemKeys = new ArrayList<>();

        Item item = new Item();
        item.setItemName("Chocolate Fudge");
        Item result1 = this.listService.addItem(item);
        initialItemKeys.add(result1.getItemId());

        Item item2 = new Item();
        item.setItemName("Raspberry");
        Item result2 = this.listService.addItem(item2);
        initialItemKeys.add(result2.getItemId());
    }

    @Before
    public void setUp() {
        helper.setUp();
        closeable = ObjectifyService.begin();
        this.initialData();
    }

    @After
    public void tearDown() {
        closeable.close();
        helper.tearDown();
    }

    @Test
    public void addItemTest(){
        int originalLength = this.listService.getList().size();
        Item item = new Item();
        item.setItemName("Sundae Fudge");
        this.listService.addItem(item);
        assertEquals(originalLength + 1, this.listService.getList().size());
    }

    @Test
    public void listAllTest(){
        List<Item> itemList = this.listService.getList();
        assertEquals(2, itemList.size());
    }

    @Test
    public void getItemTest(){
        Item item = this.listService.getItem(initialItemKeys.get(1));
        assertEquals(initialItemKeys.get(1), item.getItemId());
    }

    @Test
    public void updateItemTest(){
        Item item = new Item(initialItemKeys.get(0), "Ice Cream");
        this.listService.updateItem(item);
        assertEquals(item.getItemName(), this.listService.getItem(item.getItemId()).getItemName());
    }

    @Test
    public void deleteItemTest(){
        int originalLength = this.listService.getList().size();
        this.listService.deleteItem(initialItemKeys.get(0));
        assertEquals(originalLength-1, this.listService.getList().size());
    }
}
