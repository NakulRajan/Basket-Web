package com.shopping.basket.API;

import com.shopping.basket.Model.ItemModel;
import com.shopping.basket.Service.ListItemService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/listItems")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ListItemAPI {

    private ListItemService listItemService = new ListItemService();

    @GET
    public List<ItemModel> getList(){
        return listItemService.getList();
    }

    @GET
    @Path("/{itemId}")
    public ItemModel getItem(@PathParam("itemId") String itemId){
        return listItemService.getItem(itemId);
    }

    @POST
    public ItemModel createItem(ItemModel itemModel){
        return listItemService.addItem(itemModel);
    }

    @PUT
    @Path("/{itemId}")
    public ItemModel updateItem(@PathParam("itemId")String itemId, ItemModel itemModel){
        itemModel.setItemId(itemId);
        return listItemService.updateItem(itemModel);
    }

    @DELETE
    @Path("/{itemId}")
    public void deleteItem(@PathParam("itemId") String itemId){
         listItemService.deleteItem(itemId);
    }
}
