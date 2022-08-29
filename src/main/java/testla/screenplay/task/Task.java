package testla.screenplay.task;

import testla.screenplay.actor.IActor;
import testla.screenplay.task.ITask;

/**
 * TODO: Add Description
 *
 * @author Patrick Doering
 */
public abstract class Task implements ITask {

    public abstract Object performAs(IActor actor);
}
