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

    @Id private Long listId;
    private String listName;
    private String listDescription;

    //empty constructor needed
    public ListModel(){

    }

    public ListModel(Long listId, String listName, String listDescription){
        this.listId = listId;
        this.listName = listName;
        this.listDescription = listDescription;
    }
    
    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
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

    /**
     * Updates the ListModel with the given listModel.
     * @param listModel new ListModel.
     */
    public void update(ListModel listModel){
        this.setListName(listModel.getListName());
        this.setListDescription(listModel.getListDescription());
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;

        if( (o==null) || (this.getClass() != o.getClass()))
            return false;

        ListModel listModel = (ListModel) o;
        return  (listId != null) && (listId.equals(listModel.listId));
    }


    @Override
    public int hashCode(){
        int hash = 7;
        hash = 31 * hash + (null == listId? 0: listId.hashCode());
        return hash;
    }

}
