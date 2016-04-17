package com.shopping.basket.API;

import com.shopping.basket.Model.ItemModel;
import com.shopping.basket.Service.ListItemService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ListItemAPI {

    private ListItemService listItemService = new ListItemService();

    @GET
    public List<ItemModel> getAllItems(@PathParam("listId") Long listId){
        return listItemService.getAllItems(listId);
    }

    @GET
    @Path("/{itemId}")
    public ItemModel getItem(@PathParam("listId") Long listId, @PathParam("itemId") Long itemId){
        return listItemService.getItem(listId, itemId);
    }

    @POST
    public ItemModel addItem(@PathParam("listId") Long listId, ItemModel itemModel){
        return listItemService.addItem(listId, itemModel);
    }

    @PUT
    @Path("/{itemId}")
    public ItemModel updateItem(@PathParam("listId") Long listId, @PathParam("itemId")Long itemId, ItemModel itemModel){
        itemModel.setItemId(itemId);
        return listItemService.updateItem(listId, itemModel);
    }

    @DELETE
    @Path("/{itemId}")
    public void deleteItem(@PathParam("listId") Long listId, @PathParam("itemId") Long itemId){
         listItemService.deleteItem(listId, itemId);
    }
}
