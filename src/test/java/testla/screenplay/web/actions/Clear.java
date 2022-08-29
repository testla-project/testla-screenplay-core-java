package testla.screenplay.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.screenplay.web.abilities.BrowseTheWeb;

public class Clear extends Action {
    public Object performAs(IActor actor) {
        BrowseTheWeb.as(actor).clearCookies();
        return null;
    }

    public static Clear cookies() {
        return new Clear();
    }

}
