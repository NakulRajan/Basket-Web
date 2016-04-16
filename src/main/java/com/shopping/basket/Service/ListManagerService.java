package com.shopping.basket.Service;

import com.googlecode.objectify.cmd.Query;
import com.shopping.basket.Model.ListModel;

import java.util.List;

import static com.shopping.basket.OfyService.ofy;

/**
 * Handles managing/listing all the lists.
 */
public class ListManagerService {

    /**
     * Returns all the lists.
     * @return list of all the lists
     */
    public List<ListModel> getAllList(){
        Query<ListModel> query = ofy().load().type(ListModel.class);
        return query.list();
    }

    public ListModel addList(ListModel listModel){
        ofy().save().entity(listModel).now();
        return listModel;
    }

}
