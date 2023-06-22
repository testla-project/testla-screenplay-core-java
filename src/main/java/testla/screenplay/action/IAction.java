package testla.screenplay.action;

import testla.screenplay.actor.IActor;

/**
 * Actions can be triggered by calling them from an actor object.
 *
 * @author Patrick Doering
 */
public interface IAction {
    /**
     *  Makes the provided {@link IActor} perform this Action.
     *  This method will have to be overridden with the actual integration of an action.
     *
     * @param actor the IActor that will perform this action.
     * @return Object.
     */
    Object performAs(IActor actor);
}
