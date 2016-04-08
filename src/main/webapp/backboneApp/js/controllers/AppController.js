/**
 * App Controller is a router which controls all the views in the
 * application.
 */
define(["../views/AppView", "../views/ListManagerView", "../views/ListView"], function (AppView, ListManagerView, ListViewRef) {

    var ListManager =  ListManagerView.ListManager;
    var ListView = ListViewRef.ListView;

    var AppRouter = Backbone.Router.extend({
        routes: {
            "" : "myLists",
            "list/:listid": "showList"
        },

        myLists: function(){
            AppView.showView(new ListManager());
        },

        showList: function(listId){
            AppView.showView(new ListView(listId));
        }

    });

    return{
        AppController: AppRouter
    }

});


