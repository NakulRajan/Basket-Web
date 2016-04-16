package com.shopping.basket.Service;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.shopping.basket.Exceptions.DataNotFound;
import com.shopping.basket.Model.ListModel;

import java.util.List;

import static com.shopping.basket.OfyService.ofy;

/**
 * Handles managing/listing all the lists.
 */
public class ListManagerService {

    /**
     * returns all the lists.
     * @return list of all the lists
     */
    public List<ListModel> getAllList(){
        Query<ListModel> query = ofy().load().type(ListModel.class);
        return query.list();
    }

    /**
     * returns the list model given the listId
     * @param listId Id of the list
     * @return listModel object
     */
    public ListModel getListModel(Long listId){
        return ofy().load().key(Key.create(ListModel.class, listId)).now();
    }

    /**
     * add a new list your collections of lists.
     * @param listModel listModel you want to add.
     * @return the newly added listModel.
     */
    public ListModel addList(ListModel listModel){
        ofy().save().entity(listModel).now();
        return listModel;
    }


    /**
     * updates a list given the list id.
     * @param listModel new listModel object.
     * @return the updated listModel object.
     */
    public ListModel update(ListModel listModel){
        Long listId = listModel.getListId();
        ListModel oldListModel = ofy().load().key(Key.create(ListModel.class, listId)).now();
        if(oldListModel == null){
            throw new DataNotFound("Not a valid list Id");
        }
        oldListModel.update(listModel);
        ofy().save().entity(oldListModel).now();
        return oldListModel;
    }

    /**
     * deletes an listItem in a list
     * @param listId id of the list
     */
    public void deleteListEntry(Long listId){
        ofy().delete().key(Key.create(ListModel.class, listId)).now();
    }

}
