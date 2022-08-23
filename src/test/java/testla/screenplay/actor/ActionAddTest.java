package testla.screenplay.actor;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Response;
import org.junit.jupiter.api.Test;

/**
 * TODO: Add Description
 *
 * @author Patrick Döring
 */
class ActionAddTest {

    private Add add = new Add();
    private final Actor myActor = Actor.named("Actor");

    @Test
    void testAddAction() {

        boolean bResult = (boolean) myActor.attemptsTo(add);
        assertTrue(bResult);

    }

    @Test
    void BrowseTheWeb_Navigate() {
        Browser browser = Playwright.create().chromium().launch();
        Page page = browser.newPage();

        Response resp = BrowseTheWeb.using(page).navigate("http://playwright.dev");

        assertTrue(resp.ok());

        System.out.println(resp.text());
    }

}
