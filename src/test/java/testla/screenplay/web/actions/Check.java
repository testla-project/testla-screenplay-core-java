package testla.screenplay.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.screenplay.web.abilities.BrowseTheWeb;

/**
 * TODO: Add Description
 *
 * @author Patrick Döring
 */
public class Check extends Action {

    @Override
    public Object performAs(IActor actor) {
        BrowseTheWeb.as(actor)
    }
}
