package mx.appwhere.mediospago.front.application.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import mx.appwhere.mediospago.front.application.dto.backend.ApiError;
import mx.appwhere.mediospago.front.domain.exceptions.RestResponseException;
import mx.appwhere.mediospago.front.domain.util.RestClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;

/**
 * Client for consuming rest api.
 * <p>
 * NOTE: This client is personalized for, in case of error(no 2xx http status code),
 * try to map the error response to an {@link ApiError}, in case of different error response,
 * exception map is added to logger and the {@code apiError} field is left as null.
 *
 * @author Alejandro Martin
 * @version 1.0 - 2017/10/11
 */
@Component
public class RestClientImpl implements RestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientImpl.class);

    private RestTemplate restTemplate;

    /**
     * Use for exception response deserialization.
     */
    private ObjectMapper objectMapper;

    @Autowired
    public RestClientImpl(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    /**
     * Analyze error response, and try to extract {@link ApiError} from it.
     *
     * @param ex exception
     * @return Api
     */
    private ApiError extractApiError(RestClientException ex) {
        ApiError apiError = null;
        if (ex instanceof RestClientResponseException) {
            try {
                apiError = objectMapper.readValue(((RestClientResponseException) ex).getResponseBodyAsString(), ApiError.class);
            } catch (Exception e) {
                LOGGER.error("Tried to map response to ApiError, but failed. No action needed", e);
            }
        }
        return apiError;
    }

    @Override
    public void request(UriComponents uriComponents, HttpMethod httpMethod, HttpHeaders httpHeaders, @Nullable Object request)
            throws RestResponseException {

        try {
            HttpEntity<?> httpEntity = new HttpEntity<>(request, httpHeaders);
            restTemplate.exchange(uriComponents.toUriString(), httpMethod, httpEntity, String.class);
        } catch (RestClientException ex) {
            ApiError apiError = extractApiError(ex);
            throw new RestResponseException("A rest client exception has occurred.", apiError, ex);
        }
    }

    @Override
    public <T> T request(UriComponents uriComponents, HttpMethod httpMethod, HttpHeaders httpHeaders, @Nullable Object request,
                         Class<T> responseType) throws RestResponseException {
        ResponseEntity<T> response;

        try {
            HttpEntity<?> httpEntity = new HttpEntity<>(request, httpHeaders);
            response = restTemplate.exchange(uriComponents.toUriString(), httpMethod, httpEntity,
                    responseType);
        } catch (RestClientException ex) {
            ApiError apiError = extractApiError(ex);
            throw new RestResponseException("A rest client exception has occurred.", apiError, ex);
        }

        return response.getBody();
    }

    @Override
    public <T> T get(UriComponents uriComponents, HttpHeaders httpHeaders, Class<T> responseType)
            throws RestResponseException {
        return request(uriComponents, HttpMethod.GET, httpHeaders, null, responseType);
    }

    @Override
    public <T> T post(UriComponents uriComponents, HttpHeaders httpHeaders, @Nullable Object request, Class<T> responseType)
            throws RestResponseException {
        return request(uriComponents, HttpMethod.POST, httpHeaders, request, responseType);
    }

    @Override
    public <T> T post(UriComponents uriComponents, @Nullable Object request, Class<T> responseType)
            throws RestResponseException {
        return request(uriComponents, HttpMethod.POST, new HttpHeaders(), request, responseType);
    }

    @Override
    public <T> T post(UriComponents uriComponents, HttpHeaders httpHeaders, Class<T> responseType)
            throws RestResponseException {
        return request(uriComponents, HttpMethod.POST, httpHeaders, null, responseType);
    }

    @Override
    public <T> T put(UriComponents uriComponents, HttpHeaders httpHeaders, @Nullable Object request, Class<T> responseType)
            throws RestResponseException {
        return request(uriComponents, HttpMethod.PUT, httpHeaders, request, responseType);
    }

    @Override
    public <T> T put(UriComponents uriComponents, HttpHeaders httpHeaders, Class<T> responseType)
            throws RestResponseException {
        return request(uriComponents, HttpMethod.PUT, httpHeaders, null, responseType);
    }

    @Override
    public <T> T patch(UriComponents uriComponents, HttpHeaders httpHeaders, @Nullable Object request, Class<T> responseType)
            throws RestResponseException {
        return request(uriComponents, HttpMethod.PATCH, httpHeaders, request, responseType);
    }

    @Override
    public <T> T patch(UriComponents uriComponents, HttpHeaders httpHeaders, Class<T> responseType)
            throws RestResponseException {
        return request(uriComponents, HttpMethod.PATCH, httpHeaders, null, responseType);
    }

    @Override
    public void delete(UriComponents uriComponents, HttpHeaders httpHeaders) throws RestResponseException {
        request(uriComponents, HttpMethod.DELETE, httpHeaders, null);
    }

    @Override
    public void delete(UriComponents uriComponents, HttpHeaders httpHeaders, @Nullable Object request)
            throws RestResponseException {
        request(uriComponents, HttpMethod.DELETE, httpHeaders, request);
    }
}
