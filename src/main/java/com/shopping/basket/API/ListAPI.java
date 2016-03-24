package com.shopping.basket.API;

import com.shopping.basket.Model.ItemModel;
import com.shopping.basket.Service.ListService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/list")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ListAPI {

    private ListService listService = new ListService();

    @GET
    public List<ItemModel> getList(){
        return listService.getList();
    }

    @GET
    @Path("/{itemId}")
    public ItemModel getItem(@PathParam("itemId") String itemId){
        return listService.getItem(itemId);
    }

    @POST
    public ItemModel createItem(ItemModel itemModel){
        return listService.addItem(itemModel);
    }

    @PUT
    @Path("/{itemId}")
    public ItemModel updateItem(@PathParam("itemId")String itemId, ItemModel itemModel){
        itemModel.setItemId(itemId);
        return listService.updateItem(itemModel);
    }

    @DELETE
    @Path("/{itemId}")
    public void deleteItem(@PathParam("itemId") String itemId){
         listService.deleteItem(itemId);
    }
}
