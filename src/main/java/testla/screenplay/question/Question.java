package testla.screenplay.question;

import testla.screenplay.actor.IActor;

/**
 * Questions can be triggered by calling them from an actor object.
 *
 * @author Patrick Döring
 */
public abstract class Question<T> implements IQuestion<T>{
    /**
     * Implementation of the query answer.
     *
     * @param actor the actor that queries.
     * @return Object.
     */
    public abstract T answeredBy(IActor actor);
}
