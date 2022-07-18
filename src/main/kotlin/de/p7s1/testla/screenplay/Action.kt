package de.p7s1.testla.screenplay
import de.p7s1.testla.IAction
import de.p7s1.testla.IActor

abstract class Action: IAction {
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