package testla.screenplay.task;

import testla.screenplay.actor.IActor;

/**
 * Tasks can be triggered by calling them from an actor object.
 *
 * @author Patrick Doering
 */
public interface ITask {
    /**
     *  Makes the provided {@link IActor} perform this Task.
     *  This method will have to be overridden wit hthe actual integration of an action.
     *
     * @param actor the IActor that will perform this action.
     * @return Object.
     */
    Object performAs(IActor actor);
}
