package dev.madhavi.productservicespring.AuthCommons;

import dev.madhavi.productservicespring.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
@Service
public class AuthenticationCommons {
    private RestTemplate restTemplate;
    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public UserDto validateToken(String token) {
        try {
            ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity(
                    "http://USERSERVICE/users/validate/" + token,
                    UserDto.class
            );

            if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
                System.out.println("Validating token: " + token);
                return responseEntity.getBody();
            } else {
                System.out.println("Validating token: " + token);
                throw new RuntimeException("Unauthorized: Token is invalid");
            }

        } catch (HttpClientErrorException.Unauthorized ex) {
            // Explicit 401 error handling
            throw new RuntimeException("Unauthorized: Token rejected by UserService");
        } catch (Exception e) {
            throw new RuntimeException("Token validation failed: " + e.getMessage());
        }
    }
}
