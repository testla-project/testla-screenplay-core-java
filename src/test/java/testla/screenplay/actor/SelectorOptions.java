package testla.screenplay.actor;

import com.microsoft.playwright.Page.LocatorOptions;
import org.jetbrains.annotations.Nullable;

/**
 * TODO: Add Description
 *
 * @author Patrick Döring
 */
public class SelectorOptions {
    @Nullable
    public String hasText;
    @Nullable
    public Double timeout;

    @Nullable
    public SubSelector subSelector;
}
