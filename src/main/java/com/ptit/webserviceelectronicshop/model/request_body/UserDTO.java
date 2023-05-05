package com.ptit.webserviceelectronicshop.model.request_body;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String full_name;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String role;
    private String avatar;
}
