package com.shopping.basket.API;

import com.shopping.basket.Model.Item;
import com.shopping.basket.Service.ListService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
