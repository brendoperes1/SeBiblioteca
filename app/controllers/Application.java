package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }
    public Result brasileira(){ return ok(brasileira.render()); }
    public Result internacional(){ return ok(internacional.render()); }

}
