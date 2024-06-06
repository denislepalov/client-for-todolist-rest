package lepdv.clientfortodolistrest.util;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@UtilityClass
public class RequestMethods {




    public static <T> T makeGetRequest(String jwt, String url, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<T> request = new HttpEntity<>(null, getHeaders(jwt));
        HttpEntity<T> request = new HttpEntity<>(getHeaders(jwt));
        T response;
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, request, responseType).getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return response;
    }




    public static <T> T makePostRequest(String url, Object requestBody, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return sendPostRequest(url, headers, requestBody, responseType);
    }

    public static <T> T makePostRequest(String jwt, String url, Object requestBody, Class<T> responseType) {
        return sendPostRequest(url, getHeaders(jwt), requestBody, responseType);
    }

    private static <T> T sendPostRequest(String url, HttpHeaders headers, Object requestBody, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<>(requestBody, headers);
        T response;
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, request, responseType).getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return response;
    }



    public static <T> T makePutRequest(String jwt, String url, Class<T> responseType) {
//        HttpEntity<Object> request = new HttpEntity<>(null, getHeaders(jwt));
        HttpEntity<Object> request = new HttpEntity<>(getHeaders(jwt));
        return getPutResponse(url, request,  responseType);
    }

    public static <T> T makePutRequest(String jwt, String url, Object requestBody, Class<T> responseType) {
        HttpEntity<Object> request = new HttpEntity<>(requestBody, getHeaders(jwt));
        return getPutResponse(url, request,  responseType);
    }

    private static <T> T getPutResponse(String url, HttpEntity<Object> request, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        T response;
        try {
            response = restTemplate.exchange(url, HttpMethod.PUT, request, responseType).getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return response;
    }



//    public static <T> T makePutRequest(String jwt, String url, Object requestBody, Class<T> responseType) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<Object> request = new HttpEntity<>(requestBody, getHeaders(jwt));
//        T response;
//        try {
//            response = restTemplate.exchange(url, HttpMethod.PUT, request, responseType).getBody();
//        } catch (RestClientException e) {
//            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
//        }
//        return response;
//    }




    public static <T> T makeDeleteRequest(String jwt, String url, Class<T> responseType) {
        HttpEntity<Object> request = new HttpEntity<>(null, getHeaders(jwt));
        return getDeleteResponse(url, request,  responseType);
    }

    public static <T> T makeDeleteRequest(String jwt, String url, Object requestBody, Class<T> responseType) {
        HttpEntity<Object> request = new HttpEntity<>(requestBody, getHeaders(jwt));
        return getDeleteResponse(url, request,  responseType);
    }

    private static <T> T getDeleteResponse(String url, HttpEntity<Object> request, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        T response;
        try {
            response = restTemplate.exchange(url, HttpMethod.DELETE, request, responseType).getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return response;
    }




    private static HttpHeaders getHeaders(String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwt);
        return headers;
    }


}
