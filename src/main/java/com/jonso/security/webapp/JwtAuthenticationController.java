package com.jonso.security.webapp;

import com.jonso.security.security.JwtTokenUtil;
import com.jonso.security.service.JwtUserDetailsService;
import com.jonso.security.service.dto.JwtRequest;
import com.jonso.security.service.dto.JwtResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class JwtAuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        return ResponseEntity.ok(
                new JwtResponse(jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(authenticationRequest.getUsername())))
        );
    }
}
