package com.shopping.basket.API;

import com.shopping.basket.Model.ListModel;
import com.shopping.basket.Service.ListManagerService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * List's all the list's of the user.
 */
@Path("/mylists")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ListManagerAPI {
    private ListManagerService listManager = new ListManagerService();

    @GET
    public List<ListModel> getAllList(){
        return this.listManager.getAllList();
    }
}
