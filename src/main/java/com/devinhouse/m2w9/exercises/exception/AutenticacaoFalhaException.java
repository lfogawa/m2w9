package com.devinhouse.m2w9.exercises.exception;

import org.springframework.security.core.AuthenticationException;

public class AutenticacaoFalhaException extends AuthenticationException {

    public AutenticacaoFalhaException(String msg) {
        super(msg);
    }

}