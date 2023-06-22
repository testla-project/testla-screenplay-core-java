package testla.screenplay.actor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import testla.screenplay.ability.IAbility;
import testla.screenplay.action.IAction;
import testla.screenplay.question.IQuestion;
import testla.screenplay.task.ITask;

/**
 * Actors use abilities in order to execute tasks/actions and answer questions.
 *
 * @author Patrick Doering
 */
public class Actor implements IActor {

    // collection of attributes assigned to the actor
    Map<String, Object> attributes = new HashMap<>();

    // map of abilities of this Actor
    private final Map<String, IAbility> abilityMap = new HashMap<>();

    private Actor(String name) {
        this.attributes.put("name", name);
    }

    /**
     * Create a new Actor with a given name.
     *
     * @param name the name of the new actor.
     * @return the newly created Actor.
     */
    public static Actor named(String name) {
        return new Actor(name);
    }

    /**
     * Store an attribute in the actors attribute collection.
     *
     * @param key the attribute name.
     * @param value the attribute value.
     * @return itself.
     */
    @Override
    public Actor with(String key, Object value) {
        this.attributes.put(key, value);
        return this;
    }

    /**
     * Get an attribute from the actors attribute collection. If no attribute is stored under the given key, returns null.
     *
     * @param key the key for the attribute.
     * @return the value for the requested attribute if it exists, null if not.
     */
    @Override
    public Object states(String key) {
        return this.attributes.get(key);
    }

    /**
     * Ask a question.
     *
     * @param question the question to ask.
     * @return the question result.
     */
    @Override
    public <T> T asks(IQuestion<T> question) {
        return question.answeredBy(this);
    }

    /**
     * Verify if the actor has the given ability.
     *
     * @param ability the ability.
     * @return the associated ability if the actor has it.
     */
    @Override
    public IAbility withAbilityTo(IAbility ability) {
        if (ability == null || !this.abilityMap.containsKey(ability.name())) {
            throw new RuntimeException("Error: This Actor does not have this ability!");
        }
        return this.abilityMap.get(ability.name());
    }

    /**
     * Assign one or more abilities to the actor.
     *
     * @param abilities the abilities the actor will be able to use.
     * @return itself.
     */
    @Override
    public Actor can(IAbility... abilities) {
        Arrays.stream(abilities).forEach(ability -> this.abilityMap.put(ability.name(), ability));
        return this;
    }

    /**
     * Executes the given Tasks.
     *
     * @param tasks a list of tasks to execute.
     * @return the return value of the last task executed.
     */
    @Override
    public Object attemptsTo(ITask... tasks) {
        Object[] taskResult = new Object[1];
        Arrays.stream(tasks).forEach(task -> taskResult[0] = task.performAs(this));
        return taskResult[0];
    }

    /**
     * Executes the given Actions.
     *
     * @param actions a list of actions to execute.
     * @return the return value of the last action executed.
     */
    @Override
    public Object attemptsTo(IAction... actions) {
        Object[] actionResult = new Object[1];
        Arrays.stream(actions).forEach(action -> actionResult[0] = action.performAs(this));
        return actionResult[0];
    }
}
