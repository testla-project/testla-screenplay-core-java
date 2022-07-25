# Testla Screenplay

## Introduction

The testla project is a collection of tools of different tools to help in the QA automation process.
Testla screenplay core defines the frame for an implementation of the Screenplay Pattern.

## What is Screenplay Pattern and how does it work?

The Screenplay Pattern is a user-centred approach to writing high-quality automated tests. It steers you towards an effective use of layers of abstraction, helps your tests capture the business vernacular, and encourages good testing and software engineering habits.

Instead of focusing on low-level, interface-centric interactions, you describe your test scenarios in a similar way you'd describe them to a human being - an actor in Screenplay-speak. You write simple, readable and highly-reusable code that instructs the actors what activities to perform and what things to check. The domain-specific test language you create is used to express screenplays - the activities for the actors to perform in a given test scenario.

The Screenplay Pattern is beautiful in its simplicity. It's made up of five elements, five types of building blocks that Testla gives you to design any functional acceptance test you need, no matter how sophisticated or how simple.

The key elements of the pattern are: actors, abilities, tasks, actions and questions.

![Screenplay Pattern](/doc/screenplay.png)

## How to use this package?

### Define an ability

Abilities are essential since they define _what_ an actor _can do_. So the first thing we need to do is to define an ability by extending the testla ability.

```java
import @testla/screenplay.Ability;
import @testla/screenplay.Actor;

public class MyBrowseAbility extends Ability {
    private Page page;

    private MyBrowseAbility(Page page) {
        super();
        this.page = page;
    }
    
    // passing in whatever is required for this ability -> in our example a page object from playwright
    public static MyBrowseAbility using(Page page) {
        return new MyBrowseAbility(page);
    }

    // this function is essential so that the actor can execute a task/action with this ability
    public static MyBrowseAbility as(Actor actor) {
        return actor.withAbilityTo(this);
    }

    // navigate functionality by using playwright spicific code for our example
    public void navigate(String url) {
        return this.page.goto(url);
    }

    // fill functionality by using playwright spicific code for our example
    public void fill(String locator, String value) {
        return this.page.fill(locator, value);
    }

    // click functionality by using playwright spicific code for our example
    public void click(String locator) {
        return this.page.click(locator);
    }

    // find functionality by using playwright spicific code for our example
    public Locator find(String locator) {
        return this.page.waitForSelector(locator);
    }

    // further implementations
    // ...
}
```

### Define actions

The next step is to define actions and which can be grouped into tasks later. Actions use abilities to perform actual activities.

```java
import @testla/screenplay.Action;
import @testla/screenplay.Actor;

public class Navigate extends Action {
    private String url;

    private Navigate(String url) {
        super();
        this.url = url;
    }

    // the actual implementation of the action
    public void performAs(actor: Actor) {
        return MyBrowseAbility.as(actor).navigate(this.url);
    }

    // static member method to invoke the action
    public static Navigate to(url: string) {
        return new Navigate(url);
    }
}

public class Fill extends Action {
    private String locator;
    private String value;

    private Fill(String locator, String value) {
        super();
        this.locator = locator;
        this.value = value;
    }

    // the actual implementation of the action
    public void performAs(actor: Actor) {
        return MyBrowseAbility.as(actor).fill(this.locator, this.value);
    }

    // static member method to invoke the action
    public static Fill with(String locator, String value) {
        return new Fill(locator, value);
    }
}

public class Click extends Action {
    private String locator;

    private Click(String locator) {
        super();
        this.locator = locator;
    }

    // the actual implementation of the action
    public void performAs(actor: Actor) {
        return MyBrowseAbility.as(actor).click(this.locator);
    }

    // static member method to invoke the action
    public static Click on(locator: string) {
        return new Click(locator);
    }
}

public class Find extends Action {
    private String locator;

    private Find(locator: string) {
        super();
        this.locator = locator;
    }

    // the actual implementation of the action
    public Locator performAs(actor: Actor) {
        return MyBrowseAbility.as(actor).find(this.locator);
    }

    // static member method to invoke the action
    public static Find element(locator: string) {
        return new Find(locator);
    }
}
```

### Define a task

Tasks group actions into logical entities.

```java
import @testla/screenplay.Actor;
import @testla/screenplay.Task;

public class Login extends Task {
    // the actual implementation of the task
    public void performAs(actor: Actor) {
        return actor.attemptsTo(
            Navigate.to("https://www.my-fancy-url.com"),
            Fill.with("#username", actor.username || ''),
            Fill.with("#password", actor.password || ''),
            Click.on("#login-button"),
        );
    }

    // static member method to invoke the task
    public static Login toApp() {
        return new Login();
    }
}
```

### Define a question

Questions are used to check the status of the application under test.

```java
import @testla/screenplay.Actor;
import @testla/screenplay.Question;

class LoginStatus extends Question<Any> {
    // the actual implementation of the task
    public Any answeredBy(actor: Actor) {
        return BrowseTheWeb.as(actor).find("#logged-in-indicator");
    }

    // static member method to invoke the question
    public static LoginStatus of() {
        return new LoginStatus();
    }
}
```

### Define a test case

The final step is to define a test case using the Actions and Abilities defined above.

```java
import @testla/screenplay.Actor;

// Example test case with Playwright
@Test
void MyFirstTest() {
    try (Playwright playwright = Playwright.create()) {
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        Actor actor = Actor.named("James")
            .with("username", "John Doe")
            .with("password", "MySecretPassword");
            .can(MyBrowseAbility.using(page));

        actor.attemptsTo(Login.toApp());

        expect(actor.asks(LoginStatus.of())).not.toBeNull();
    }
}
```

### What about the 'Screen' in 'Screenplay'?

With screen is meant that all locators for page elements are held in specific files/collections. In our example from above we put the locators inline. A sample screen file for the Login task could look like this:

```java
public final String USERNAME_INPUT = "#username";
public final String PASSWORD_INPUT = "#password";
public final String LOGIN_BUTTON = "#login-button";
```

Within the task the screen elements are then used as:

```java
public void performAs(actor: Actor) {
    return actor.attemptsTo(
        Navigate.to('https://www.my-fancy-url.com'),
        Fill.with(USERNAME_INPUT, actor.states('username') || ''),
        Fill.with(PASSWORD_INPUT, actor.states('passwird') || ''),
        Click.on(LOGIN_BUTTON),
    );
}
```