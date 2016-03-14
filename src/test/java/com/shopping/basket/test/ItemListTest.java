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


    @Test
    public void createListTest(){
        ListService listService = new ListService();
        List<Item> itemList = listService.getList();
        assertEquals(4, itemList.size());
    }

}
