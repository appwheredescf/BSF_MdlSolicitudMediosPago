package mx.appwhere.mediospago.front.domain.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponents;

/**
 * Client for consuming rest api.
 * <p>
 * It is based on {@link org.springframework.web.client.RestTemplate}.
 *
 * @author Alejandro Martin
 * @version 1.0 - 2017/10/11
 */
public interface RestClient {
    void request(UriComponents uriComponents, HttpMethod httpMethod, HttpHeaders httpHeaders,
                 @Nullable Object request) throws RestClientException;

    <T> T request(UriComponents uriComponents, HttpMethod httpMethod, HttpHeaders httpHeaders,
                  @Nullable Object request, Class<T> responseType) throws RestClientException;

    <T> T get(UriComponents uriComponents, HttpHeaders httpHeaders, Class<T> responseType)
            throws RestClientException;

    <T> T post(UriComponents uriComponents, HttpHeaders httpHeaders, @Nullable Object request,
               Class<T> responseType) throws RestClientException;

    <T> T post(UriComponents uriComponents, @Nullable Object request,
               Class<T> responseType) throws RestClientException;

    <T> T post(UriComponents uriComponents, HttpHeaders httpHeaders, Class<T> responseType)
            throws RestClientException;

    <T> T put(UriComponents uriComponents, HttpHeaders httpHeaders, @Nullable Object request,
              Class<T> responseType) throws RestClientException;

    <T> T put(UriComponents uriComponents, HttpHeaders httpHeaders,
              Class<T> responseType) throws RestClientException;

    <T> T patch(UriComponents uriComponents, HttpHeaders httpHeaders, @Nullable Object request,
                Class<T> responseType) throws RestClientException;

    <T> T patch(UriComponents uriComponents, HttpHeaders httpHeaders,
                Class<T> responseType) throws RestClientException;

    void delete(UriComponents uriComponents, HttpHeaders httpHeaders)
            throws RestClientException;

    void delete(UriComponents uriComponents, HttpHeaders httpHeaders, @Nullable Object request)
            throws RestClientException;
}
