package testla.screenplay.web.actions;

import com.microsoft.playwright.options.Cookie;
import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.screenplay.web.abilities.BrowseTheWeb;

import java.util.List;

/**
 * TODO: Add Description
 *
 * @author Patrick Döring
 */
public class Add extends Action {

  private List<Cookie> cookies;

  private Add(List<Cookie> cookies) {
      this.cookies = cookies;
  }

  @Override
  public Object performAs(IActor actor) {
      BrowseTheWeb.as(actor).addCookies(cookies);
      return null;
  }
}
