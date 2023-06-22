package testla.screenplay.ability;

/**
 * Abilities enable tasks/actions to perform specific requirements.
 *
 * @author Patrick Doering
 */
public abstract class Ability implements IAbility {

    /**
     * Return the name of this Ability.
     *
     * @return the name of this class.
     */
    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }
}
