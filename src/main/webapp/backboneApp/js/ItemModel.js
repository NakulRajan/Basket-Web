/**
 * Represents each Item in the list
 */
define([], function(){

    var Item = Backbone.Model.extend({
        default:{
            itemName: null,
            itemId: null
        },
        idAttribute: "itemId",
        urlRoot: "/api/list"
    });

    var ItemCollection = Backbone.Collection.extend({
        model: Item,
        url: "/api/list"
    });

    return{
        Item: Item,
        ItemCollection: ItemCollection
    }
});