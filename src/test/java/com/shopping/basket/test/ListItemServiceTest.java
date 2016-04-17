package com.shopping.basket.test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import com.shopping.basket.Model.ItemModel;
import com.shopping.basket.Model.ListModel;
import com.shopping.basket.Service.ListItemService;
import com.shopping.basket.Service.ListManagerService;
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
    private Long listId;

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private Closeable closeable;

    private List<Long> initialItemKeys;

    public ListItemServiceTest(){
        ObjectifyService.register(ItemModel.class);
    }

    public void initialData(){
        initialItemKeys = new ArrayList<>();

        ListModel l1 = new ListModel(null, "Camping List", "All the things needed for camping");
        ListManagerService listManagerService = new ListManagerService();
        listManagerService.addList(l1);

        this.listId = listManagerService.getAllList().get(0).getListId();


        ItemModel itemModel = new ItemModel(this.listId, null, "Chocolate Fudge");
        ItemModel result1 = this.listItemService.addItem(listId, itemModel);
        initialItemKeys.add(result1.getItemId());

        ItemModel itemModel2 = new ItemModel(this.listId, null, "Raspberry");
        ItemModel result2 = this.listItemService.addItem(listId, itemModel2);
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
        int originalLength = this.listItemService.getAllItems(this.listId).size();
        ItemModel itemModel = new ItemModel();
        itemModel.setItemName("Sundae Fudge");
        this.listItemService.addItem(this.listId, itemModel);
        assertEquals(originalLength + 1, this.listItemService.getAllItems(this.listId).size());
    }

    @Test
    public void listAllTest(){
        List<ItemModel> itemModelList = this.listItemService.getAllItems(this.listId);
        assertEquals(2, itemModelList.size());
    }

    @Test
    public void getItemTest(){
        ItemModel itemModel = this.listItemService.getItem(this.listId, initialItemKeys.get(1));
        assertEquals(initialItemKeys.get(1), itemModel.getItemId());
    }

    @Test
    public void updateItemTest(){
        ItemModel itemModel = new ItemModel(this.listId, initialItemKeys.get(0), "Ice Cream");
        this.listItemService.updateItem(this.listId, itemModel);
        assertEquals(itemModel.getItemName(), this.listItemService.getItem(this.listId, itemModel.getItemId()).getItemName());
    }

    @Test
    public void deleteItemTest(){
        int originalLength = this.listItemService.getAllItems(this.listId).size();
        this.listItemService.deleteItem(this.listId, initialItemKeys.get(0));
        assertEquals(originalLength-1, this.listItemService.getAllItems(this.listId).size());
    }
}
