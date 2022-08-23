package testla.screenplay.action;

import testla.screenplay.actor.IActor;

/**
 * TODO: Add Description
 *
 * @author Patrick Doering
 */
public interface IAction {
    Object performAs(IActor actor);
}
