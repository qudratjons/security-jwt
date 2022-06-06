package com.jonso.security.service;

import com.jonso.security.entity.AppUser;
import com.jonso.security.error.AppErrors;
import com.jonso.security.error.AppException;
import com.jonso.security.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(AppErrors.USER_NOT_FOUND));

        return new User(appUser.getUsername(), appUser.getPassword(), new ArrayList<>());
    }
}