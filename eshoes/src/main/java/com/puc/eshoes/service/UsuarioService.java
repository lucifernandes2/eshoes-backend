package com.puc.eshoes.service;

import com.puc.eshoes.domain.Usuario;
import com.puc.eshoes.domain.dto.LoginRequest;
import com.puc.eshoes.domain.dto.SignupRequest;
import com.puc.eshoes.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void signup(SignupRequest request) {
        String email = request.email();
        Optional<Usuario> existingUser = usuarioRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new RuntimeException(String.format("Usuário com o email '%s' já existe.", email));
        }

        String hashedPassword = passwordEncoder.encode(request.senha());
        Usuario usuario = Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(hashedPassword)
                .tipo(request.tipo())
                .build();
        usuarioRepository.save(usuario);
    }

    public Usuario authenticate(LoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.login(),
                        input.password()
                )
        );

        return usuarioRepository.findByEmail(input.login())
                .orElseThrow();
    }

}

