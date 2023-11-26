package com.devinhouse.m2w9.exercises.controller;

import com.devinhouse.m2w9.exercises.dtos.AutenticacaoRequest;
import com.devinhouse.m2w9.exercises.dtos.AutenticacaoResponse;
import com.devinhouse.m2w9.exercises.dtos.UsuarioRequest;
import com.devinhouse.m2w9.exercises.dtos.UsuarioResponse;
import com.devinhouse.m2w9.exercises.model.Usuario;
import com.devinhouse.m2w9.exercises.service.AutenticacaoService;
import com.devinhouse.m2w9.exercises.service.UsuarioService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private  UsuarioService usuarioService;
    @Autowired
    private AutenticacaoService autenticacaoService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> consultar(){
        var usuarios = usuarioService.consultar();
        var resp = usuarios.stream().map(u -> mapper.map(u, UsuarioResponse.class)).toList();
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> inserir(@RequestBody @Valid UsuarioRequest request){
        var usuario = mapper.map(request, Usuario.class);
        usuario = usuarioService.inserir(usuario);
        var resp = mapper.map(usuario, UsuarioResponse.class);
        return ResponseEntity.created(URI.create(usuario.getId().toString())).body(resp);
    }

    @PostMapping("/autenticar")
    public ResponseEntity<AutenticacaoResponse> login(@RequestBody @Valid AutenticacaoRequest request) {
        var token = autenticacaoService.autenticar(request.getEmail(), request.getSenha());
        return ResponseEntity.ok(new AutenticacaoResponse(token));
    }
}
