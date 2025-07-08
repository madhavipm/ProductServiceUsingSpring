package dev.madhavi.productservicespring.AuthCommons;

import dev.madhavi.productservicespring.dtos.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class AuthenticationCommons {
    private RestTemplate restTemplate;
    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public UserDto validateToken(String token) {
            UserDto userdto = restTemplate.getForObject("http://localhost:8081/users/validate/" + token, UserDto.class);
            if(userdto == null) {
                return null;
            }
            return userdto;
    }

}
