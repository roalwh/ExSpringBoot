package pe.roalwh.exSpringBoot.dto;

import lombok.Data;

@Data
public class AddUserRequest {
    private String email;
    private String password;
}
