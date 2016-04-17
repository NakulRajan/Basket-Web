/**
 * This view is responsible for managing the
 * all the lists
 */
define(['js/views/AppView', 'text!templates/ListManager.html', 'text!templates/ListItem.html', '../models/ListManagerModel'],
    function(AppView, ListManagerTpl, ListItemTpl, ListManagerModel){

        var ListManagerCollection = ListManagerModel.ListCollection;
        var ListItemModel = ListManagerModel.ListManagerModel;

        var ListItem  = Backbone.View.extend({

            events: {
                "click .listItemGroup": "showList",
                "click .deleteIcon": "onDelete"
            },

            template: _.template(ListItemTpl),

            initialize: function(){
                this.listenTo(this.model, 'destroy', this.remove);
            },

            onDelete: function(){
                this.model.destroy({
                    success: function (model, respose, options) {
                        console.log("List Item deleted " + model);
                    },
                    error: function (model, xhr, options) {
                        console.log("Something went wrong while deleting list");
                    }
                })
            },

            showList: function(){
                AppView.AppController.navigate("lists/" + this.model.get('listId'), {trigger:true});
            },

            render: function () {
                this.$el.html(this.template({model:this.model.attributes}));
                return this;
            }

        });

        var ListManager = Backbone.View.extend({

            template: _.template(ListManagerTpl),

            initialize: function(){
                this.collection = new ListManagerCollection();
                this.listenTo(this.collection, "add", this.addListItem);

                this.collection.fetch({
                        success: function(){
                            console.log("Fetch successful")
                        },
                        error: function(){
                            console.log("Error in fetching data");
                        }
                    }
                )
            },

            addListItem: function(listItemModel){
                var listItem = new ListItem({model: listItemModel});
                this.$('.listOfList').append(listItem.render().$el);
            },

            render: function(){
                this.$el.html(this.template());
                return this;
            }

        });

        return{
            ListManager: ListManager
        }

    });



