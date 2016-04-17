/**
 * This view represents the list of items
 */
define(['text!templates/List.html', 'js/views/ItemView', 'js/models/ItemModel'], function(ListTpl, ItemViewModule, ItemModel){

    var ItemView = ItemViewModule.ItemView;
    var Item = ItemModel.Item;
    var ItemCollection = ItemModel.ItemCollection;

    var ListView = Backbone.View.extend({

        template: _.template(ListTpl),

        events: {
            'click #addItem': "createItem",
            'keypress #itemVal': "onEnter"
        },

        initialize: function(options){
            this.collection = new ItemCollection();
            this.collection.setUrl(options.listId);
            //setting up the listeners
            this.listenTo(this.collection, 'add', this.addToList);

            this.collection.fetch({
                success: function(items, response){
                    console.log("Success in retrieving the list");
                }, error: function(){
                    console.log("Error in retrieving the list");
                }});
        },

        addToList: function(item){
            if(typeof item != "undefined"){
                var itemView = new ItemView({model: item});
                itemView.render();
                this.$('.itemList').append(itemView.$el);
            }
        },

        createItem: function(){
            var itemValue = this.$('#itemVal').val();
            if(itemValue !=  "") {
                var item = new Item({itemName: itemValue, itemId: null});
                this.collection.create(item);
            }else{
                console.log("Please enter a valid input");
            }
            // resetting the input field
            this.$('#itemVal').val('');
        },

        onEnter: function(e){
            if(e.keyCode === 13)
                this.createItem();
        },

        render: function(){
            this.$el.html(this.template());
            return this;
        }
    });

    return{
        ListView: ListView
    }
});