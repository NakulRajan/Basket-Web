package com.shopping.basket.test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import com.shopping.basket.Model.ListModel;
import com.shopping.basket.Service.ListManagerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.shopping.basket.OfyService.ofy;
import static org.junit.Assert.assertEquals;

/**
 * Tests for lists manager
 */
public class ListManagerServiceTest {

    private ListManagerService listManagerService = new ListManagerService();

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private Closeable closeable;

    public ListManagerServiceTest(){
        ObjectifyService.register(ListModel.class);
    }

    public void initializeData(){
        ListModel l1 = new ListModel(null, "Camping", "Stuff needed for camping.");
        ListModel l2 = new ListModel(null, "Road Trip", "Must haves for the road trip");
        listManagerService.addList(l1);
        listManagerService.addList(l2);
    }

    @Before
    public void setUp() {
        helper.setUp();
        closeable = ObjectifyService.begin();
        this.initializeData();
    }

    @After
    public void tearDown() {
        closeable.close();
        helper.tearDown();
    }

    @Test
    public void getAllTest(){
        List<ListModel> list = this.listManagerService.getAllList();
        assertEquals(2, list.size());
    }

    @Test
    public void addListTest(){
        ListModel l3 = new ListModel(3L, "Trek", "Things you need to survive the trek");
        this.listManagerService.addList(l3);
        ListModel listItem  = ofy().load().key(Key.create(ListModel.class, l3.getListId())).now();
        assertEquals(3, listManagerService.getAllList().size());
        assertEquals(l3, listItem);
    }

    @Test
    public void deleteListTest(){
        this.listManagerService.deleteListEntry(2L);
        assertEquals(1, listManagerService.getAllList().size());
    }

    @Test
    public void updateListTest(){
        ListModel updatedCampingList = new ListModel(1L, "Trek", "Things you need to survive the trek");
        this.listManagerService.update(updatedCampingList);
        ListModel newListModel = ofy().load().key(Key.create(ListModel.class, 1)).now();
        assertEquals("Trek", newListModel.getListName());
    }


}
