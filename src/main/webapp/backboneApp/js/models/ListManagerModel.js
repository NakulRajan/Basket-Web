/**
 * Model for view which lists all the other list.
 */
define([], function(){

    var ListManagerModel = Backbone.Model.extend({
        default:{
            listId: null,
            listName: null,
            listDescription: null
        },
        idAttribute: "listId"

    });

    var ListCollection = Backbone.Collection.extend({
        model: ListManagerModel,
        url: "/api/lists"
    });

    return{
        ListManagerModel: ListManagerModel,
        ListCollection: ListCollection
    }
});

