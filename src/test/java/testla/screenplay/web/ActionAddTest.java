package testla.screenplay.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Response;
import org.junit.jupiter.api.Test;
import testla.screenplay.actor.Actor;
import testla.screenplay.web.abilities.BrowseTheWeb;
import testla.screenplay.web.actions.Add;
import testla.screenplay.web.actions.Clear;

/**
 * TODO: Add Description
 *
 * @author Patrick Döring
 */
class ActionAddTest {

    @Test
    void Test_General() {
        Browser browser = Playwright.create().chromium().launch();
        Page page = browser.newPage();

        Actor myActor = Actor.named("Actor").can(BrowseTheWeb.using(page));

        myActor.attemptsTo(
                Clear.cookies()
        );
    }

}
