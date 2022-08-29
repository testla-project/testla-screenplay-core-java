package testla.screenplay.api.actions;

import com.microsoft.playwright.options.RequestOptions;
import testla.screenplay.actor.IActor;
import testla.screenplay.api.RequestMethod;
import testla.screenplay.api.ResponseBodyFormat;
import testla.screenplay.api.abilities.UseAPI;

import java.util.Map;

public class Get extends ARequest {

    private final String url;

    private final RequestOptions options;

    private ResponseBodyFormat responseBodyFormat = ResponseBodyFormat.JSON;

    private Get(String url) {
        this.url = url;
        this.options = RequestOptions.create();
    }

    @Override
    public Object performAs(IActor actor) {
        return UseAPI.as(actor).sendRequest(RequestMethod.GET, this.url, this.options, this.responseBodyFormat);
    }

    public static Get from(String url) {
        return new Get(url);
    }

    public Get withHeaders(Map<String, String> headers) {
        // replaced by Java compiler: headers.forEach((k,v) -> this.options.setHeader(k, v));
        headers.forEach(this.options::setHeader);
        return this;
    }

    public Get withResponseBodyFormat(ResponseBodyFormat responseBodyFormat) {
        this.responseBodyFormat = responseBodyFormat;
        return this;
    }
}
