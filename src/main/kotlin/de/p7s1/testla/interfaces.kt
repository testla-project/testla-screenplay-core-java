package de.p7s1.testla

/**
 * Collection of attributes and methods that an Actor should have.
 */
interface IActor {
    // collection of attributes assigned to the actor
    var attributes: MutableMap<String, Any>

    // set attribute
    fun with(key: String, value: Any): IActor

    // get attribute
    fun states(key: String): Any?

    // connection to questions
    fun <T> asks(question: IQuestion<T>, activityResult: T): T

    // connection to abilities
    fun withAbilityTo(ability: IAbility): IAbility
    fun can(vararg abilities: IAbility): IActor

    // connection to tasks/actions
    // activities: IAction | ITask is not possible -> have to take any and check for type?
    fun attemptsTo(vararg activities: IAction): Any
}

/**
 * This is an empty interface, since every ability has its own call patterns and therefore there is no common ground
 * only the name attribute needs to be set for internal reference.
 */
interface IAbility {
    val name: String
}

/**
 * An object representing an action that an IActor can perform.
 */
interface IAction {
    /**
     *  Makes the provided IActor
     *  perform this Action.
     *
     * @param actor
     * @returns Any
     */
    fun performAs(actor: IActor): Any
}

/**
 * An object representing a task that an IActor can perform.
 */
interface ITask: IAction {
    /**
     *  Makes the provided IActor
     *  perform this Task.
     *
     * @param actor
     * @returns Any
     */
    override fun performAs(actor: IActor): Any
}

/**
 * An object representing a question that an IActor can answer.
 */
interface IQuestion<T> {
    /**
     * Implementation of the query answer.
     *
     * @param actor the actor that queries.
     */
    fun answeredBy(actor: IActor): T
}