/**
 * The AppView is the core part of the application which is accessible
 * from all parts of the Application.
 */
define([], function(){

    var AppController = null;

    var showView = function(view){
        view.render();
        $('.container').html(view.$el);
    };

    return{
        showView: showView,
        AppController: AppController
    }
});




