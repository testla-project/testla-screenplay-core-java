package testla.screenplay.api.actions;

import com.microsoft.playwright.options.RequestOptions;
import testla.screenplay.actor.IActor;
import testla.screenplay.api.RequestMethod;
import testla.screenplay.api.ResponseBodyFormat;
import testla.screenplay.api.abilities.UseAPI;

import java.util.Map;

public class Head extends ARequest {

    private final String url;

    private final RequestOptions options;

    private ResponseBodyFormat responseBodyFormat = ResponseBodyFormat.JSON;

    private Head(String url) {
        this.url = url;
        this.options = RequestOptions.create();
    }

    @Override
    public Object performAs(IActor actor) {
        return UseAPI.as(actor).sendRequest(RequestMethod.HEAD, this.url, this.options, this.responseBodyFormat);
    }

    public static Head from(String url) {
        return new Head(url);
    }

    public Head withHeaders(Map<String, String> headers) {
        // replaced by Java compiler: headers.forEach((k,v) -> this.options.setHeader(k, v));
        headers.forEach(this.options::setHeader);
        return this;
    }

    public Head withResponseBodyFormat(ResponseBodyFormat responseBodyFormat) {
        this.responseBodyFormat = responseBodyFormat;
        return this;
    }
}
