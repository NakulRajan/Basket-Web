/**
 * This View represents each item in the list.
 */
define(['text!templates/Item.html'], function(ItemTpl){

    var ItemView = Backbone.View.extend({

        template: _.template(ItemTpl),

        events: {
            'click .deleteIcon': "deleteItem"
        },

        initialize: function(){

        },

        deleteItem: function(){
            console.log("Delete clicked");
            this.model.destroy({
                success: function (model, respose, options) {
                    console.log("The model has deleted the server");
                },
                error: function (model, xhr, options) {
                    console.log("Something went wrong while deleting the model");
                }
            });
        },

       render: function(){
           this.$el.append(this.template({model: this.model.attributes}));
       }
   });

    return{
        ItemView: ItemView
    }
});