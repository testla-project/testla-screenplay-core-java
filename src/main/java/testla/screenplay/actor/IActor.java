package testla.screenplay.actor;

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
public interface IActor {

    // ToDo: Test if it is possible to share the attributes between different actors...
    //Map<String, Object> attributes = new HashMap<>();
    IActor with(String key, Object value);
    Object states(String key);

    <T> T asks(IQuestion<T> question);
    IAbility withAbilityTo(IAbility ability);
    IActor can(IAbility... abilities);

    // ToDo: Possibility to aggregate these two attemptsTo. Maybe one inherit from the other?
    Object attemptsTo(ITask...activities);
    Object attemptsTo(IAction...activities);
}
