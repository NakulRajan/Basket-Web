package com.shopping.basket.test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import com.shopping.basket.Model.ItemModel;
import com.shopping.basket.Service.ListItemService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for items list.
 */
public class ListItemServiceTest {

    private ListItemService listItemService = new ListItemService();
    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private Closeable closeable;

    private List<String> initialItemKeys;

    public ListItemServiceTest(){
        ObjectifyService.register(ItemModel.class);
    }

    public void initialData(){
        initialItemKeys = new ArrayList<>();

        ItemModel itemModel = new ItemModel();
        itemModel.setItemName("Chocolate Fudge");
        ItemModel result1 = this.listItemService.addItem(itemModel);
        initialItemKeys.add(result1.getItemId());

        ItemModel itemModel2 = new ItemModel();
        itemModel.setItemName("Raspberry");
        ItemModel result2 = this.listItemService.addItem(itemModel2);
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
        int originalLength = this.listItemService.getList().size();
        ItemModel itemModel = new ItemModel();
        itemModel.setItemName("Sundae Fudge");
        this.listItemService.addItem(itemModel);
        assertEquals(originalLength + 1, this.listItemService.getList().size());
    }

    @Test
    public void listAllTest(){
        List<ItemModel> itemModelList = this.listItemService.getList();
        assertEquals(2, itemModelList.size());
    }

    @Test
    public void getItemTest(){
        ItemModel itemModel = this.listItemService.getItem(initialItemKeys.get(1));
        assertEquals(initialItemKeys.get(1), itemModel.getItemId());
    }

    @Test
    public void updateItemTest(){
        ItemModel itemModel = new ItemModel(initialItemKeys.get(0), "Ice Cream");
        this.listItemService.updateItem(itemModel);
        assertEquals(itemModel.getItemName(), this.listItemService.getItem(itemModel.getItemId()).getItemName());
    }

    @Test
    public void deleteItemTest(){
        int originalLength = this.listItemService.getList().size();
        this.listItemService.deleteItem(initialItemKeys.get(0));
        assertEquals(originalLength-1, this.listItemService.getList().size());
    }
}
