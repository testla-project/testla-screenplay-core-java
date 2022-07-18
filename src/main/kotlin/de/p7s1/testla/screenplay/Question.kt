package de.p7s1.testla.screenplay
import de.p7s1.testla.IQuestion
import de.p7s1.testla.IActor

abstract class Question<T>: IQuestion<T> {
    /**
     * Implementation of the query answer.
     *
     * @param actor the actor that queries.
     * @returns Any
     */
    abstract override fun answeredBy(actor: IActor): T
}
