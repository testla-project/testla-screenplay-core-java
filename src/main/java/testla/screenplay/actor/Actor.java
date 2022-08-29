package testla.screenplay.actor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import testla.screenplay.ability.IAbility;
import testla.screenplay.action.IAction;
import testla.screenplay.question.IQuestion;
import testla.screenplay.task.ITask;

/**
 * TODO: Add Description
 *
 * @author Patrick Doering
 */
public class Actor implements IActor {

    Map<String, Object> attributes = new HashMap<>();

    private final Map<String, IAbility> abilityMap = new HashMap<>();

    private Actor(String name) {
        this.attributes.put("name", name);
    }

    public static Actor named(String name) {
        return new Actor(name);
    }

    @Override
    public Actor with(String key, Object value) {
        this.attributes.put(key, value);
        return this;
    }

    @Override
    public Object states(String key) {
        return this.attributes.get(key);
    }

    @Override
    public <T> T asks(IQuestion<T> question, T activityResult) {
        return question.answeredBy(this);
    }

    @Override
    public IAbility withAbilityTo(IAbility ability) {
        if (ability == null || !this.abilityMap.containsKey(ability.name())) {
            throw new RuntimeException("Error: This Actor does not have this ability!");
        }
        return this.abilityMap.get(ability.name());
    }

    @Override
    public Actor can(IAbility... abilities) {
        Arrays.stream(abilities).forEach(ability -> this.abilityMap.put(ability.name(), ability));
        return this;
    }

    @Override
    public Object attemptsTo(ITask... tasks) {
        Object[] taskResult = new Object[1];
        Arrays.stream(tasks).forEach(task -> taskResult[0] = task.performAs(this));
        return taskResult[0];
    }

    @Override
    public Object attemptsTo(IAction... actions) {
        Object[] actionResult = new Object[1];
        Arrays.stream(actions).forEach(action -> actionResult[0] = action.performAs(this));
        return actionResult[0];
    }
}
