package com.poligym.config;

//Configurar uma classe de enums ou Constantes, para o codigo ficar mais legivel
public final class SecurityConstants {

    private SecurityConstants() {
    }

    static final String SECRET = "Polygim";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    // URL que sera permitida ser acessada por qualquer pessoa
    static final String SIGN_UP_URL = "/v1/user/create";
    static final long EXPIRATION_TIME = 7200000;

    // public static void main(String[] args) {
    // System.out.println(TimeUnit.MILLISECONDS.convert(2, TimeUnit.HOURS));
    // }
}
