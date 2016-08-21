package ro.szzsa.keepalive.connector.impl;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import ro.szzsa.keepalive.connector.Connector;
import ro.szzsa.keepalive.model.Request;
import ro.szzsa.keepalive.model.Response;

/**
 *
 */
public class HttpConnector implements Connector {

    @Override
    public Response connect(Request request) {
        Response response = new Response();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(request.getUrl());
        try (CloseableHttpResponse httpResponse = httpclient.execute(httpGet)) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            response.setStatusCode(statusCode);
            response.setSuccess(statusCode == HttpStatus.SC_OK);
            EntityUtils.consume(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
