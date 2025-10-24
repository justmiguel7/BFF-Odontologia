package com.proyecto.odontologiabff.requester;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proyecto.odontologiabff.dto.LoginDTO;
import com.proyecto.odontologiabff.dto.UsuarioDTO;

@Service
public class UsuarioRequesterImp implements UsuarioRequester {

    private final RestTemplate restTemplate;

    @Value("${ms.usuario.url}")
    private String urlUsuario;

    private final String pathRegistrar = "/auth/register";
    private final String pathLogin = "/auth/login";

    public UsuarioRequesterImp(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public void registrarUsuario(UsuarioDTO usuarioDTO) {
        HttpEntity<UsuarioDTO> entity = new HttpEntity<>(usuarioDTO);
        restTemplate.exchange(urlUsuario + pathRegistrar, HttpMethod.POST, entity, Void.class);
    }

    @Override
    public String loginUsuario(LoginDTO loginDTO) {
        HttpEntity<LoginDTO> entity = new HttpEntity<>(loginDTO);
        ResponseEntity<String> response = restTemplate.exchange(urlUsuario + pathLogin, HttpMethod.POST, entity, String.class);
        return response.getBody(); // JWT
    }
}