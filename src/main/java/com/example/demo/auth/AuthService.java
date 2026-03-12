package com.example.demo.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import java.util.Date;
import javax.crypto.SecretKey;
import javax.management.RuntimeErrorException;


@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEndCoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public String register (String nome, String email, String senha) {
        if (userRepository.findByEmail(email). isPresent()){
            throw new RuntimeException("Vixi Email já registrado bro");
        }
        
        String senhaCriptografada = passwordEndCoder.encode(senha);
        User user = new User();
        user.setNome(nome);
        user.setEmail(email);
        user.setSenha(senhaCriptografada);
        userRepository.save(user);
        return jwtService.generateToken(email);
    }

    public String Login (String email, String senha) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Vixi Email não encontrado bro"));

            if (!passwordEndCoder.matches(senha, user.getPassword())) {
                throw new RuntimeException ("Vixi senha errada amigo");
            }
        return jwtService.generateToken(email);
    }
}