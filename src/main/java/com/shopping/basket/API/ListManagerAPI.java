package com.shopping.basket.API;

import com.shopping.basket.Model.ListModel;
import com.shopping.basket.Service.ListManagerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * List's all the list's of the user.
 */
@Path("/lists")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ListManagerAPI {
    private ListManagerService listManager = new ListManagerService();

    @GET
    public List<ListModel> getAllList(){
        return this.listManager.getAllList();
    }

    @GET
    @Path("/{listId}")
    public ListModel getListModel(@PathParam("listId") Long listId){
        return this.listManager.getListModel(listId);
    }

    @POST
    public ListModel addList(ListModel listModel){
        return this.listManager.addList(listModel);
    }

    @PUT
    @Path("/{listId}")
    public ListModel updateList(@PathParam("listId") Long listId, ListModel listModel){
        listModel.setListId(listId);
        return this.listManager.update(listModel);
    }

    @DELETE
    @Path("/{listId}")
    public void deleteList(@PathParam("listId") Long listId){
         this.listManager.deleteListEntry(listId);
    }

    @Path("/{listId}/listItems/")
    public ListItemAPI getListItems(){
        return new ListItemAPI();
    }

}
