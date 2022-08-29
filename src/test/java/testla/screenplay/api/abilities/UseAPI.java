package testla.screenplay.api.abilities;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import testla.screenplay.ability.Ability;
import testla.screenplay.actor.IActor;
import testla.screenplay.api.RequestMethod;
import testla.screenplay.api.Response;
import testla.screenplay.api.ResponseBodyFormat;

public class UseAPI extends Ability {

    private final APIRequestContext requestContext;
    private static UseAPI instance;

    private UseAPI(APIRequestContext requestContext){
        this.requestContext = requestContext;
    }

    public static UseAPI using(APIRequestContext requestContext) {
        UseAPI.instance = new UseAPI(requestContext);
        return UseAPI.instance;
    }

    public static UseAPI as(IActor actor) {
        return (UseAPI) actor.withAbilityTo(UseAPI.instance);
    }

    public Response sendRequest(RequestMethod method, String url, RequestOptions options, ResponseBodyFormat responseBodyFormat) {
        long start = System.currentTimeMillis();

        APIResponse res;
        switch(method) {
            case GET -> {
                res = this.requestContext.get(url, options);
            }
            case POST -> {
                res = this.requestContext.post(url, options);
            }
            case PATCH -> {
                res = this.requestContext.patch(url, options);
            }
            case PUT -> {
                res = this.requestContext.put(url, options);
            }
            case HEAD -> {
                res = this.requestContext.head(url, options);
            }
            case DELETE -> {
                res = this.requestContext.delete(url, options);
            }
            default -> throw new RuntimeException("Error: unknown RequestMethod!");
        }

        long end = System.currentTimeMillis();

        Object resBody;
        switch (responseBodyFormat) {
            case TEXT -> {
                resBody = res.text();
            }
            case BUFFER -> {
                resBody = res.body();
            }
            case NONE -> {
                resBody = null;
            }
            default -> resBody = res.text();
        }

        return new Response(resBody, res.status(), res.headers(), end-start);
    }
}
