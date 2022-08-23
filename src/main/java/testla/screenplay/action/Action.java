package testla.screenplay.action;

import testla.screenplay.actor.IActor;

/**
 * TODO: Add Description
 *
 * @author Patrick Doering
 */
public abstract class Action implements IAction {
    public abstract Object performAs(IActor actor);
}
