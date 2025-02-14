package com.betacom.rekrutacja.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequest {

    private String login;
    private String password;
}
