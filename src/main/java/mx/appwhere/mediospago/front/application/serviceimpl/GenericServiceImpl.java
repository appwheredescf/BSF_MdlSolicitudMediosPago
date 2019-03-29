package mx.appwhere.mediospago.front.application.serviceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import mx.appwhere.mediospago.front.domain.constants.DomainConstants;
import mx.appwhere.mediospago.front.domain.constants.DomainError;
import mx.appwhere.mediospago.front.domain.exceptions.ConnectionException;
import mx.appwhere.mediospago.front.domain.exceptions.RestResponseException;
import mx.appwhere.mediospago.front.domain.exceptions.ajax.AjaxException;
import mx.appwhere.mediospago.front.domain.services.GenericService;
import mx.appwhere.mediospago.front.domain.util.RestClient;


@Service
public class GenericServiceImpl implements GenericService {

	private RestClient restClient;

	@Autowired
	public void setRestClient(RestClient restClient) {
		this.restClient = restClient;
	}

	@Override
	public Object responseObject(String uri, Object request, Class response) {
		Object res;
		try {

			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.ACCEPT, DomainConstants.MEDIA_TYPE_JSON);
			UriComponents uriComponents = UriComponentsBuilder.fromUriString(uri).build();
			res = restClient.post(uriComponents, headers, request, response);

		} catch (RestResponseException ex) {
			ex.getApiError().ifPresent(apiError -> {
				throw new AjaxException(apiError.getMessage(), DomainError.BACKEND_ERROR);
			});
			throw new ConnectionException("Error de conexion");
		}
		return res;
	}

	@Override
	public Object responseMav(String uri, Object request, Class response) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.ACCEPT, DomainConstants.MEDIA_TYPE_JSON);
			UriComponents uriComponents = UriComponentsBuilder.fromUriString(uri).build();
			return restClient.post(uriComponents, headers, request, response);
		} catch (RestResponseException ex) {
			return null;
		}
	}
}
