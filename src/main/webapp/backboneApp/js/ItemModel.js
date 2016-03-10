/**
 * Represents each Item in the list
 */
define(['Firebase'], function(){

    var Item = Backbone.Model.extend({
        default:{
            itemName: null,
            uniqueId: null
        }
    });

    var ItemCollection = Backbone.Firebase.Collection.extend({
        model: Item,
        url: "https://popping-inferno-7618.firebaseio.com/Items"

    });

    return{
        Item: Item,
        ItemCollection: ItemCollection
    }
});