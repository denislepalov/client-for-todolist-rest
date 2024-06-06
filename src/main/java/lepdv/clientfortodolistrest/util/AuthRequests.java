package lepdv.clientfortodolistrest.util;

import lepdv.clientfortodolistrest.dto.CredentialsDto;
import lepdv.clientfortodolistrest.dto.JwtDto;
import lepdv.clientfortodolistrest.dto.RegisterDto;
import lombok.experimental.UtilityClass;

import static lepdv.clientfortodolistrest.util.RequestMethods.makePostRequest;


@UtilityClass
public class AuthRequests {


    static final String baseUrl = "http://localhost:8080/api/v2/authenticate";



    public static JwtDto performLogin(CredentialsDto credentialsDto) {
        String performLoginUrl = baseUrl + "/login";
        return makePostRequest(performLoginUrl, credentialsDto, JwtDto.class);
    }


    public static JwtDto register(RegisterDto registerDto) {
        String registerUrl = baseUrl + "/registration";
        return makePostRequest(registerUrl, registerDto, JwtDto.class);
    }



}
