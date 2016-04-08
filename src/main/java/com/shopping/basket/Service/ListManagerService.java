package com.shopping.basket.Service;

import com.shopping.basket.Model.ListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles managing/listing all the lists.
 */
public class ListManagerService {

    /**
     * Returns all the lists.
     * @return list of all the lists
     */
    public List<ListModel> getAllList(){
        List<ListModel> lists = new ArrayList<>();
        ListModel l1 = new ListModel(1, "Camping", "Must haves in a camping trip");
        ListModel l2 = new ListModel(2, "Itinerary", "US trip" );
        lists.add(l1);
        lists.add(l2);
        return lists;
    }
}
