/**
 * This view represents the list of items
 */
define(['text!templates/List.html', 'js/ItemView', 'js/ItemModel'], function(ListTpl, ItemViewModule, ItemModel){

    var ItemView = ItemViewModule.ItemView;
    var Item = ItemModel.Item;
    var ItemCollection = ItemModel.ItemCollection;

    var ListView = Backbone.View.extend({

        template: _.template(ListTpl),

        events: {
            'click #addItem': "createItem",
            'keypress #itemVal': "onEnter"
        },

        initialize: function(){
            var that = this;
            this.collection = new ItemCollection();
            this.listenTo(this.collection, 'add', this.addToList());
            this.collection.fetch({
                success: function(items, response){
                    that.buildList(items, response);
                }, error: function(){
                    console.log("Error in retrieving the data");
                }});
        },

        buildList: function(items){
            var that = this;
            items.each(function(item){
                that.addToList(item);
            })
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
                var item = new Item({itemName: itemValue, uniqueId: "1"});
                this.collection.add(item);
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