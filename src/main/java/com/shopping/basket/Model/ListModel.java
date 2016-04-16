package com.shopping.basket.Model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an List in the app.
 */
@XmlRootElement
@Entity
public class ListModel {

    @Id private Integer listId;
    private String listName;
    private String listDescription;

    //empty constructor needed
    public ListModel(){

    }

    public ListModel(Integer listId, String listName, String listDescription){
        this.listId = listId;
        this.listName = listName;
        this.listDescription = listDescription;
    }
    
    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListDescription() {
        return listDescription;
    }

    public void setListDescription(String listDescription) {
        this.listDescription = listDescription;
    }
}
