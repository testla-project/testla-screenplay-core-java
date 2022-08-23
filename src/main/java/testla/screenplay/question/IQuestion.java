package testla.screenplay.question;

import testla.screenplay.actor.IActor;

/**
 * TODO: Add Description
 *
 * @author Patrick Doering
 */
public interface IQuestion<T> {

    T answeredBy(IActor actor);
}
