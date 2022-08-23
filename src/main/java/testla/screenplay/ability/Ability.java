package testla.screenplay.ability;

import testla.screenplay.actor.IActor;

/**
 * TODO: Add Description
 *
 * @author Patrick Doering
 */
// ToDo: Why is this class not abstract?
public abstract class Ability implements IAbility {

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    // ToDo: Do we really need this? In JS this Method is static...
    // public IAbility as(IActor actor) {
       // return actor.withAbilityTo(this);
   //  }
}
