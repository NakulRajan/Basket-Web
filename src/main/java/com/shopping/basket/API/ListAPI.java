package com.shopping.basket.API;

import com.shopping.basket.Model.Item;
import com.shopping.basket.Service.ListService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/list")
public class ListAPI {

    private ListService listService = new ListService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getList(){
        return listService.getList();
    }

    @GET
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item getItem(@PathParam("itemId") String itemId){
        return listService.getItem(itemId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Item createItem(Item item){
        return listService.addItem(item);
    }

    @PUT
    @Path("/{itemId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Item updateItem(@PathParam("itemId")String itemId, Item item){
        item.setItemId(itemId);
        return listService.updateItem(item);
    }

    @DELETE
    @Path("/{itemId}")
    public void deleteItem(@PathParam("itemId") String itemId){
         listService.deleteItem(itemId);
    }
}
