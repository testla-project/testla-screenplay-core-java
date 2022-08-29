package testla.screenplay.question;

import testla.screenplay.actor.IActor;

/**
 * TODO: Add Description
 *
 * @author Patrick Döring
 */
public abstract class Question<T> implements IQuestion<T>{
    public abstract T answeredBy(IActor actor);
}
