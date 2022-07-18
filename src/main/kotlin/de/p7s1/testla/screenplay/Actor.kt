package de.p7s1.testla.screenplay
import de.p7s1.testla.*

class Actor private constructor(name: String) : IActor {
    // map of attributes that are stored to the actor
    override var attributes: MutableMap<String, Any> = mutableMapOf()

    // map of abilities of this Actor
    private var abilityMap: MutableMap<String, IAbility> = mutableMapOf()

    // constructor continued
    init {
        this.attributes["name"] = name
    }

    // static methods here
    companion object {
        /**
         * Create a new Actor with a given name.
         * @param name the actor's name
         */
        @JvmStatic
        fun named(name: String): Actor {
            return Actor(name)
        }
    }

    /**
     * Store an attribute in the actors attribute collection.
     * @param key attribute name
     * @param value attribute value
     * @returns this Actor
     */
    override fun with(key: String, value: Any): IActor {
        this.attributes[key] = value
        return this
    }

    /**
     * Get an attribute from the actors attribute collection.
     * @param key Key for the attribute
     * @returns Value for the requested attribute
     */
    override fun states(key: String): Any? {
        return this.attributes[key]
    }

    /**
     * Sets username and password for the actor.
     *
     * @param username
     * @param password
     * @returns the actor object
     *
     * @deprecated This method is deprecated and will be removed in the future. Use
     */
    fun withCredentials(username: String, password: String): Actor {
        this.attributes["username"] = username
        this.attributes["password"] = password
        return this
    }

    override fun <T> asks(question: IQuestion<T>, activityResult: T): T {
        return question.answeredBy(this)
    }

    override fun withAbilityTo(ability: IAbility): IAbility {
        if (!abilityMap.containsKey(ability.name)) {
            throw Error("Error: This Actor does not have this ability! ({${ability.name})")
        } else {
            return abilityMap[ability.name]!!
        }
    }

    override fun can(vararg abilities: IAbility): IActor {
        abilities.forEach {
            this.abilityMap[it.name] = it
        }
        return this
    }

    /**
     * Executes the given Tasks/Actions.
     *
     * @param activities a list of tasks to execute.
     */
    override fun attemptsTo(vararg activities: IAction): Any {
        // execute each activity in order.
        val result = activities.forEach {
            it.performAs(this)
        }
        return result
    }
}