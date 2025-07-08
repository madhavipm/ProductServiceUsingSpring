package dev.madhavi.productservicespring.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class UserDto {
    private String name;
    private String email;
    private List<String> roles;
}

