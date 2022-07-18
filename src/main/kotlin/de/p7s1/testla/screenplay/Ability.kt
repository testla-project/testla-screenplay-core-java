package de.p7s1.testla.screenplay
import de.p7s1.testla.IAbility
import de.p7s1.testla.IActor

/**
 * Abilities enable tasks/actions to perform specific requirements.
 */
abstract class Ability: IAbility {
    override val name: String = this.javaClass.name
/*
    // static methods here
    companion object {
        /**
         * Use this Ability as an Actor.
         *
         * @param actor
         */
        @JvmStatic
        fun As(actor: IActor): IAbility {
            return actor.withAbilityTo(instance)
        }

        // workaround for accessing "this" in companion object
        lateinit var instance: Ability
    }

    // workaround for accessing "this" in companion object
    init {
        instance = this
    }
 */
}