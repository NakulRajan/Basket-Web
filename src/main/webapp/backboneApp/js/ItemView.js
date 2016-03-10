/**
 * This View represents each item in the list.
 */
define(['text!templates/Item.html'], function(ItemTpl){

    var ItemView = Backbone.View.extend({

        template: _.template(ItemTpl),

        initialize: function(){
           this.listenTo(this.model, "change", this.render);
       },

       render: function(){
           this.$el.append(this.template({model: this.model.attributes}));
       }
   });

    return{
        ItemView: ItemView
    }
});