package net.movierama.test.api.rest;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RestApiHelper {

    private CloseableHttpClient httpclient;

    public RestApiHelper() {
        this.httpclient = HttpClients.createDefault();
    }

    /**
     * Connects to a host retrieving json format of a Restful API doing get requests.
     *
     * @param host The remote host to be conected.
     * @param path The resource path.
     * @param params The request params
     * @return The Response containg json format data and the response code.
     */
    public ResponseDto getResponse(final String host, final String path, final Map<String, String> params) {
        final URI uri = buildURI(host, path, getHttpParamList(params));
        final HttpRequestBase httpRequestGet = new HttpGet(uri);
        httpRequestGet.setHeader("Content-Type", "application/json");
        return getHttpResponse(httpRequestGet);
    }

    private URI buildURI(final String host, final String path, final List<NameValuePair> nameValuePairList) {
        try {
            return new URIBuilder().
                    setScheme("http").
                    setHost(host).
                    setPath(path).
                    addParameters(nameValuePairList).
                    build();
        } catch (URISyntaxException e) {
            throw new RuntimeException();
        }
    }

    private List<NameValuePair> getHttpParamList(final Map<String, String> params) {
        return params.entrySet().stream().
                map(param -> new BasicNameValuePair(param.getKey(), param.getValue())).
                collect(Collectors.toList());
    }

    private ResponseDto getHttpResponse(final HttpRequestBase http) {
        try (CloseableHttpResponse httpResponse = this.httpclient.execute(http)) {
            final HttpEntity entity = httpResponse.getEntity();
            final ResponseDto responseDto = new ResponseDto(
                    httpResponse.getStatusLine().getStatusCode(),
                    EntityUtils.toString(entity, "UTF-8"));
            EntityUtils.consume(entity);
            return responseDto;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
