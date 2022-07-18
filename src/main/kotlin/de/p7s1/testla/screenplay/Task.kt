package de.p7s1.testla.screenplay
import de.p7s1.testla.IActor
import de.p7s1.testla.ITask

abstract class Task: ITask {
    /**
     *  Makes the provided IActor perform this Action.
     *
     * @param actor the actor
     * @returns Any
     *
     * @override This method will have to be overridden with the actual integration of an action.
     */
    abstract override fun performAs(actor: IActor): Any
}