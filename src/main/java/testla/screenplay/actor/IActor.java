package testla.screenplay.actor;

import testla.screenplay.ability.IAbility;
import testla.screenplay.action.IAction;
import testla.screenplay.question.IQuestion;
import testla.screenplay.task.ITask;

/**
 * Actors use abilities in order to execute tasks/actions and answer questions.
 *
 * @author Patrick Doering
 */
public interface IActor {

    /**
     * Store an attribute in the actors attribute collection.
     *
     * @param key the attribute name.
     * @param value the attribute value.
     * @return itself.
     */
    IActor with(String key, Object value);

    /**
     * Get an attribute from the actors attribute collection. If no attribute is stored under the given key, returns null.
     *
     * @param key the key for the attribute.
     * @return the value for the requested attribute if it exists, null if not.
     */
    Object states(String key);

    /**
     * Ask a question.
     *
     * @param question the question to ask.
     * @return the question result.
     */
    <T> T asks(IQuestion<T> question);

    /**
     * Verify if the actor has the given ability.
     *
     * @param ability the ability.
     * @return the associated ability if the actor has it.
     */
    IAbility withAbilityTo(IAbility ability);

    /**
     * Assign one or more abilities to the actor.
     *
     * @param abilities the abilities the actor will be able to use.
     * @return itself.
     */
    IActor can(IAbility... abilities);

    /**
     * Executes the given Tasks.
     *
     * @param activities a list of tasks to execute.
     * @return the return value of the last task executed.
     */
    Object attemptsTo(ITask...activities);

    /**
     * Executes the given Actions.
     *
     * @param activities a list of actions to execute.
     * @return the return value of the last action executed.
     */
    Object attemptsTo(IAction...activities);
}
