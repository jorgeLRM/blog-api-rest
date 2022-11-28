package com.system.blog.controller;

import com.system.blog.dto.LoginDTO;
import com.system.blog.dto.RecordDTO;
import com.system.blog.model.Role;
import com.system.blog.model.User;
import com.system.blog.repository.JWTAuthResponseDTO;
import com.system.blog.repository.RoleRepository;
import com.system.blog.repository.UserRepository;
import com.system.blog.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("login")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

        // get jwtTokenProvider
        String token = jwtTokenProvider.createToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RecordDTO recordDTO) {
        if (userRepository.existsByUsername(recordDTO.getUsername())) {
            return new ResponseEntity<>("That username already exists", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(recordDTO.getEmail())) {
            return new ResponseEntity<>("That email already exists", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(recordDTO.getName());
        user.setUsername(recordDTO.getUsername());
        user.setEmail(recordDTO.getEmail());
        user.setPassword(passwordEncoder.encode(recordDTO.getPassword()));

        Role roles = rolRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
