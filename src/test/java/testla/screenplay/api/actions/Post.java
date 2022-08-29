package testla.screenplay.api.actions;

import com.microsoft.playwright.options.RequestOptions;
import testla.screenplay.actor.IActor;
import testla.screenplay.api.RequestMethod;
import testla.screenplay.api.ResponseBodyFormat;
import testla.screenplay.api.abilities.UseAPI;

import java.util.Map;

public class Post extends ARequest {

    private final String url;

    private final RequestOptions options;

    private ResponseBodyFormat responseBodyFormat = ResponseBodyFormat.JSON;

    private Post(String url) {
        this.url = url;
        this.options = RequestOptions.create();
    }

    @Override
    public Object performAs(IActor actor) {
        return UseAPI.as(actor).sendRequest(RequestMethod.POST, this.url, this.options, this.responseBodyFormat);
    }

    public static Post to(String url) {
        return new Post(url);
    }

    public Post withHeaders(Map<String, String> headers) {
        // replaced by Java compiler: headers.forEach((k,v) -> this.options.setHeader(k, v));
        headers.forEach(this.options::setHeader);
        return this;
    }

    public Post withResponseBodyFormat(ResponseBodyFormat responseBodyFormat) {
        this.responseBodyFormat = responseBodyFormat;
        return this;
    }

    public Post withData(Object data) {
        this.options.setData(data);
        return this;
    }

    public Post withData(String data) {
        this.options.setData(data);
        return this;
    }

    public Post withData(byte[] data) {
        this.options.setData(data);
        return this;
    }
}
