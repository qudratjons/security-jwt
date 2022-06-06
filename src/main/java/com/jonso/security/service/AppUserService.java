package com.jonso.security.service;

import com.jonso.security.entity.AppUser;
import com.jonso.security.error.AppErrors;
import com.jonso.security.error.AppException;
import com.jonso.security.repository.AppUserRepository;
import com.jonso.security.service.dto.AppUserDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserDTO saveUser(AppUserDTO appUserDTO) {
        if (appUserRepository.existsUser(appUserDTO.getUsername(), appUserDTO.getEmail()))
            throw new AppException(AppErrors.USER_ALREADY_EXISTS);

        AppUser appUser = appUserDTO.toEntity();
        appUser.setPassword(passwordEncoder.encode(appUserDTO.getPassword()));

        return appUserRepository.save(appUser).toDTO();
    }
}
