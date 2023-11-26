package com.devinhouse.m2w9.exercises.service;

import com.devinhouse.m2w9.exercises.exception.RegistroJaExistenteException;
import com.devinhouse.m2w9.exercises.model.Usuario;
import com.devinhouse.m2w9.exercises.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario inserir(Usuario usuario) {
        if (repo.existsByEmail(usuario.getEmail()))
            throw new RegistroJaExistenteException("Usuario", usuario.getEmail());
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return repo.save(usuario);
    }

    public List<Usuario> consultar(){
        return repo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Optional<Usuario> usuarioOpt = repo.findByEmail(email);
        if(usuarioOpt.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return usuarioOpt.get();
    }
}