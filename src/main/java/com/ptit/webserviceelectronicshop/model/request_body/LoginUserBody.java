package com.ptit.webserviceelectronicshop.model.request_body;

import lombok.Data;

@Data
public class LoginUserBody {
    private String email;
    private String password;

}
