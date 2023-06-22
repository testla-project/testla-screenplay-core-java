package testla.screenplay.question;

import testla.screenplay.actor.IActor;

/**
 * Questions can be triggered by calling them from an actor object.
 *
 * @author Patrick Doering
 */
public interface IQuestion<T> {
    /**
     * Implementation of the query answer.
     *
     * @param actor the actor that queries.
     * @return Object.
     */
    T answeredBy(IActor actor);
}
