/**
 * Represents each Item in the list
 */
define([], function(){

    var Item = Backbone.Model.extend({
        default:{
            itemName: null,
            itemId: null
        },
        idAttribute: "itemId"
    });

    var ItemCollection = Backbone.Collection.extend({
        model: Item,
        url: "",
        baseUrl: "/api/lists/",

        setUrl: function(listId){
            this.url = this.baseUrl + listId + "/listItems"
        }
    });

    return{
        Item: Item,
        ItemCollection: ItemCollection
    }
});